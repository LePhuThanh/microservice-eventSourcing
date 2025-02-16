package com.phelim.bookservice.command.aggregate;

import com.phelim.bookservice.command.command.CreateBookCommand;
import com.phelim.bookservice.command.command.DeleteBookCommand;
import com.phelim.bookservice.command.command.UpdateBookCommand;
import com.phelim.bookservice.command.event.BookCreateEvent;
import com.phelim.bookservice.command.event.BookDeletedEvent;
import com.phelim.bookservice.command.event.BookUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.sql.Delete;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    public BookAggregate() {
    }

    @CommandHandler
    public BookAggregate (CreateBookCommand createBookCommand){
        //publish
        //BookCreateEvent bookCreateEvent = new BookCreateEvent(createBookCommand.getId(), createBookCommand.getName(), createBookCommand.getAuthor(), true);
        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
        AggregateLifecycle.apply(bookCreateEvent);
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand){
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand){
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }

    // Event Listening
    @EventSourcingHandler
    public void on(BookCreateEvent bookCreateEvent){
        this.id = bookCreateEvent.getId();
        this.name = bookCreateEvent.getName();
        this.author = bookCreateEvent.getAuthor();
        this.isReady = bookCreateEvent.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookUpdatedEvent bookUpdatedEvent){
        this.id = bookUpdatedEvent.getId();
        this.name = bookUpdatedEvent.getName();
        this.author = bookUpdatedEvent.getAuthor();
        this.isReady = bookUpdatedEvent.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookDeletedEvent bookDeletedEvent){
        this.id = bookDeletedEvent.getId();
    }
}
