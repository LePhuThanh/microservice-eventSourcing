package com.phelim.bookservice.query.projection;

import com.phelim.bookservice.command.data.Book;
import com.phelim.bookservice.command.data.BookRepository;
import com.phelim.bookservice.command.model.BookRequestModel;
import com.phelim.bookservice.query.model.BookResponseModel;
import com.phelim.bookservice.query.queries.GetAllBookQuery;
import com.phelim.bookservice.query.queries.GetBookDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    //Listen form Query Gateway
    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query){
        List<Book> list = bookRepository.findAll();
        List<BookResponseModel> listBookResponse = new ArrayList<>();
        list.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book, model);
            listBookResponse.add(model);
        });
        //Java 8
//        list.stream().map(book -> {
//            BookRequestModel model = new BookRequestModel();
//            BeanUtils.copyProperties(book, model);
//            return model;
//        }).toList();
        return listBookResponse;
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query){
        BookResponseModel bookResponseModel = new BookResponseModel();
        bookRepository.findById(query.getId()).ifPresent(book -> {
            BeanUtils.copyProperties(book, bookResponseModel);
        });
        return bookResponseModel;
    }

}
