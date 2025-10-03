package com.phelim.borrowingservice.command.aggregate;

import com.phelim.borrowingservice.command.command.CreateBorrowingCommand;
import com.phelim.borrowingservice.command.command.DeleteBorrowingCommand;
import com.phelim.borrowingservice.command.event.BorrowingCreatedEvent;
import com.phelim.borrowingservice.command.event.BorrowingDeletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class BorrowingAggregate {
    @AggregateIdentifier
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;

    public BorrowingAggregate() {
    }
    @CommandHandler
    public BorrowingAggregate(CreateBorrowingCommand createBorrowingCommand){
        //publish
        BorrowingCreatedEvent borrowingCreatedEvent = new BorrowingCreatedEvent();
        BeanUtils.copyProperties(createBorrowingCommand, borrowingCreatedEvent);
        AggregateLifecycle.apply(borrowingCreatedEvent);
    }

    @CommandHandler
    public void handle(DeleteBorrowingCommand deleteBorrowingCommand){
        BorrowingDeletedEvent borrowingDeletedEvent = new BorrowingDeletedEvent(deleteBorrowingCommand.getId());
        AggregateLifecycle.apply(borrowingDeletedEvent); // Bc there is only 1 property, there is no need to BeanUtils.copyProperties(0
    }
    //---------------------------Event Listening-------------------

    @EventSourcingHandler
    public void on(BorrowingCreatedEvent borrowingCreatedEvent){
        this.id = borrowingCreatedEvent.getId();
        this.bookId = borrowingCreatedEvent.getBookId();
        this.employeeId = borrowingCreatedEvent.getEmployeeId();
        this.borrowingDate = borrowingCreatedEvent.getBorrowingDate();
    }

    @EventSourcingHandler
    public void on(BorrowingDeletedEvent borrowingDeletedEvent){
        this.id = borrowingDeletedEvent.getId();
    }
}
