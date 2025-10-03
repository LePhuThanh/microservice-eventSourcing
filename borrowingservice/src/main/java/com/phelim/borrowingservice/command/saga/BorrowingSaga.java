package com.phelim.borrowingservice.command.saga;

import com.phelim.borrowingservice.command.command.DeleteBorrowingCommand;
import com.phelim.borrowingservice.command.event.BorrowingCreatedEvent;
import com.phelim.commonservice.command.UpdateStatusBookCommand;
import com.phelim.commonservice.event.BookUpdateStatusEvent;
import com.phelim.commonservice.model.BookResponseCommonModel;
import com.phelim.commonservice.queries.GetBookDetailQuery;
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
        SagaLifecycle.end();
    }

    private void rollbackBorrowingRecord(String id){
        DeleteBorrowingCommand deleteBorrowingCommand = new DeleteBorrowingCommand(id);
        commandGateway.sendAndWait(deleteBorrowingCommand);
        SagaLifecycle.end();
    }
}
