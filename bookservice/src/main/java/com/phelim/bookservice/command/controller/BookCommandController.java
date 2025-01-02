package com.phelim.bookservice.command.controller;

import com.phelim.bookservice.command.command.CreateBookCommand;
import com.phelim.bookservice.command.command.DeleteBookCommand;
import com.phelim.bookservice.command.command.UpdateBookCommand;
import com.phelim.bookservice.command.model.BookRequestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@Valid @RequestBody BookRequestModel bookRequestModel){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), bookRequestModel.getName(), bookRequestModel.getAuthor(), true);
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel bookRequestModel, @PathVariable String bookId){
        UpdateBookCommand updateBookCommand = new UpdateBookCommand(bookId, bookRequestModel.getName(), bookRequestModel.getAuthor(), bookRequestModel.getIsReady());
        return commandGateway.sendAndWait(updateBookCommand);
    }
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId){
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(deleteBookCommand);
    }

}
