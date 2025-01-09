package com.phelim.bookservice.query.controller;

import com.phelim.bookservice.query.model.BookResponseModel;
import com.phelim.bookservice.query.queries.GetAllBookQuery;
import com.phelim.bookservice.query.queries.GetBookDetailQuery;
import com.phelim.commonservice.services.KafkaService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;
    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/get-all-books")
    public List<BookResponseModel> getAllBooks(){
        GetAllBookQuery query = new GetAllBookQuery();
        //Handler asynchronously, (Unnecessary, but to learn)
//        CompletableFuture<List<BookResponseModel>> bookFuture = queryGateway.query(query, BookResponseModel.class);
//        bookFuture.thenAccept(books -> {
//            System.out.println("Books" + books);
//        });

        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
    }

    @GetMapping("{bookId}")
    public BookResponseModel getDetail(@PathVariable String bookId){
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway.query(query, ResponseTypes.instanceOf(BookResponseModel.class)).join();
    }

    //Test Kafka
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message){
        kafkaService.sendMessage("test", message);
    }
}
