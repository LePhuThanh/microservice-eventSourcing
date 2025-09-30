package com.phelim.borrowingservice.command.controller;

import com.phelim.borrowingservice.command.command.CreateBorrowingCommand;
import com.phelim.borrowingservice.command.model.BorrowingCreateModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowingCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createBorrowing (@RequestBody BorrowingCreateModel model) {
        CreateBorrowingCommand command = new CreateBorrowingCommand(UUID.randomUUID().toString(), model.getBookId(), model.getEmployeeId(), new Date());
        return commandGateway.sendAndWait(command);

    }

}
