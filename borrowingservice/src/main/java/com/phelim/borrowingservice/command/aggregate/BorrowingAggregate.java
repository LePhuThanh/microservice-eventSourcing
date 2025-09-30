package com.phelim.borrowingservice.command.aggregate;

import com.phelim.borrowingservice.command.command.CreateBorrowingCommand;
import com.phelim.borrowingservice.command.event.BorrowingCreatedEvent;
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

    //---------------------------Event Listening-------------------

    @EventSourcingHandler
    public void on(BorrowingCreatedEvent borrowingCreatedEvent){
        this.id = borrowingCreatedEvent.getId();
        this.bookId = borrowingCreatedEvent.getBookId();
        this.employeeId = borrowingCreatedEvent.getEmployeeId();
        this.borrowingDate = borrowingCreatedEvent.getBorrowingDate();
    }
}
