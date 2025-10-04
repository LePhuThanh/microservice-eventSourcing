package com.phelim.bookservice.command.event;


import com.phelim.bookservice.command.data.Book;
import com.phelim.bookservice.command.data.BookRepository;
import com.phelim.commonservice.event.BookRollBackStatusEvent;
import com.phelim.commonservice.event.BookUpdateStatusEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventsHandler {
    @Autowired
    private BookRepository bookRepository;
    @EventHandler
    public void on(BookCreateEvent bookCreateEvent){
        Book book = new Book();
        BeanUtils.copyProperties(bookCreateEvent, book);

        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent bookUpdatedEvent){
        Optional<Book> oldBook = bookRepository.findById(bookUpdatedEvent.getId());
        if(oldBook.isPresent()){
            Book book = oldBook.get();
            book.setName(bookUpdatedEvent.getName());
            book.setAuthor(bookUpdatedEvent.getAuthor());
            book.setIsReady(bookUpdatedEvent.getIsReady());

            bookRepository.save(book);
        }
//        Java8
//        bookRepository.findById(bookUpdatedEvent.getId()).ifPresent(book -> {
//            book.setName(bookUpdatedEvent.getName());
//            book.setAuthor(bookUpdatedEvent.getAuthor());
//            book.setIsReady(bookUpdatedEvent.getIsReady());
//
//            bookRepository.save(book);
//        });
    }

    @EventHandler
    public void on(BookDeletedEvent bookDeletedEvent){
        Optional<Book> oldBook = bookRepository.findById(bookDeletedEvent.getId());
        if(oldBook.isPresent()){
            bookRepository.delete(oldBook.get());
        }
//        Java8
//        bookRepository.findById(bookDeletedEvent.getId()).ifPresent(book -> bookRepository.delete(book));
//        bookRepository.findById(bookDeletedEvent.getId()).ifPresent(bookRepository::delete);
    }

    @EventHandler
    public void on(BookUpdateStatusEvent bookUpdateStatusEvent){
        Optional<Book> oldBook = bookRepository.findById(bookUpdateStatusEvent.getBookId());
        oldBook.ifPresent(book -> {
            book.setIsReady(bookUpdateStatusEvent.getIsReady());
            bookRepository.save(book);
        });
    }

    @EventHandler
    public void on(BookRollBackStatusEvent bookRollBackStatusEvent){
        Optional<Book> oldBook = bookRepository.findById(bookRollBackStatusEvent.getBookId());
        oldBook.ifPresent( book -> {
            book.setIsReady(bookRollBackStatusEvent.getIsReady());
            bookRepository.save(book);
        });
    }

}
