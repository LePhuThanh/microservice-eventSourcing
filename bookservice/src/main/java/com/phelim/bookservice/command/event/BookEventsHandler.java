package com.phelim.bookservice.command.event;


import com.phelim.bookservice.command.data.Book;
import com.phelim.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
