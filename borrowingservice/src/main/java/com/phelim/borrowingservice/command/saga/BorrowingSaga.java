package com.phelim.borrowingservice.command.saga;

import com.phelim.borrowingservice.command.command.DeleteBorrowingCommand;
import com.phelim.borrowingservice.command.event.BorrowingCreatedEvent;
import com.phelim.borrowingservice.command.event.BorrowingDeletedEvent;
import com.phelim.commonservice.command.RollBackStatusBookCommand;
import com.phelim.commonservice.command.UpdateStatusBookCommand;
import com.phelim.commonservice.event.BookRollBackStatusEvent;
import com.phelim.commonservice.event.BookUpdateStatusEvent;
import com.phelim.commonservice.model.BookResponseCommonModel;
import com.phelim.commonservice.model.EmployeeResponseCommonModel;
import com.phelim.commonservice.queries.GetBookDetailQuery;
import com.phelim.commonservice.queries.GetDetailEmployeeQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class BorrowingSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id") // "id" is primary key of BorrowingCreatedEvent
    private void handle(BorrowingCreatedEvent event){
        System.out.println("log =================> BorrowingCreatedEvent in saga for BookId: " + event.getBookId() + " : EmployeeId: " + event.getEmployeeId());
        try{
            GetBookDetailQuery getBookDetailQuery = new GetBookDetailQuery(event.getBookId());
            BookResponseCommonModel bookResponseCommonModel = queryGateway.query(getBookDetailQuery,
                    ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            if(!bookResponseCommonModel.getIsReady()){
                throw new Exception("The book has been borrowed");
            }else{
                SagaLifecycle.associateWith("bookId", event.getBookId());
                UpdateStatusBookCommand updateStatusBookCommand = new UpdateStatusBookCommand(event.getBookId(), false, event.getEmployeeId(), event.getId()); //false ~~ The book has been borrowed
                commandGateway.sendAndWait(updateStatusBookCommand);
            }
        }catch (Exception ex){
            rollbackBorrowingRecord(event.getId());
            System.out.println(ex.getMessage());
        }
    }

    @SagaEventHandler(associationProperty = "bookId")
    private void handler(BookUpdateStatusEvent event){
        System.out.println("log =================> BookUpdateStatusEvent in saga for BookId: " + event.getBookId());
        try{
            GetDetailEmployeeQuery getDetailEmployeeQuery = new GetDetailEmployeeQuery(event.getEmployeeId());
            EmployeeResponseCommonModel employeeModel = queryGateway.query(getDetailEmployeeQuery,
                    ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
            if(employeeModel.getDisciplined()){
                throw new Exception("The employee has been disciplined");
            }else {
                System.out.println("log =================> Book borrowed successfully");
                SagaLifecycle.end(); // => Pass
            }
        }catch (Exception ex){
            rollBackBookStatus(event.getBookId(), event.getEmployeeId(), event.getBorrowingId());
            System.out.println(ex.getMessage());
        }
    }

    private void rollbackBorrowingRecord(String id){
        DeleteBorrowingCommand deleteBorrowingCommand = new DeleteBorrowingCommand(id);
        commandGateway.sendAndWait(deleteBorrowingCommand);
    }

    private void rollBackBookStatus(String bookId, String employeeId, String borrowingId){
        SagaLifecycle.associateWith("bookId", bookId);
        RollBackStatusBookCommand rollBackStatusBookCommand = new RollBackStatusBookCommand(bookId, true, employeeId, borrowingId);
        commandGateway.sendAndWait(rollBackStatusBookCommand);
    }
    @SagaEventHandler(associationProperty = "bookId")
    private void handler(BookRollBackStatusEvent bookRollBackStatusEvent){
        System.out.println("log =================> BookRollBackStatusEvent in saga for BookId: " + bookRollBackStatusEvent.getBookId());
        rollbackBorrowingRecord(bookRollBackStatusEvent.getBorrowingId());
    }

    @SagaEventHandler(associationProperty = "id")
    private void handle(BorrowingDeletedEvent borrowingDeletedEvent){
        System.out.println("log =================> BorrowingDeletedEvent in saga for BookId: " + borrowingDeletedEvent.getId());
        SagaLifecycle.end();
    }


}
