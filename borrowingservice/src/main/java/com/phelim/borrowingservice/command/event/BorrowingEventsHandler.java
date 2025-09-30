package com.phelim.borrowingservice.command.event;

import com.phelim.borrowingservice.command.data.Borrowing;
import com.phelim.borrowingservice.command.data.BorrowingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowingEventsHandler {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @EventHandler
    public void on(BorrowingCreatedEvent borrowingCreatedEvent){
        Borrowing model = new Borrowing();
        model.setId(borrowingCreatedEvent.getId());
        model.setBorrowingDate(borrowingCreatedEvent.getBorrowingDate());
        model.setBookId(borrowingCreatedEvent.getBookId());
        model.setEmployeeId(borrowingCreatedEvent.getEmployeeId());
        borrowingRepository.save(model);
    }
}
