package com.phelim.bookservice.command.controller;

import com.phelim.bookservice.command.command.CreateBookCommand;
import com.phelim.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel bookRequestModel){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), bookRequestModel.getName(), bookRequestModel.getAuthor(), true);
        return commandGateway.sendAndWait(command);
    }
}
