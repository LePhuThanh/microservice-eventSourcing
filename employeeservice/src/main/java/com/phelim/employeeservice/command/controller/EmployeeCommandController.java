package com.phelim.employeeservice.command.controller;

import com.phelim.employeeservice.command.command.CreateEmployeeCommand;
import com.phelim.employeeservice.command.command.UpdateEmployeeCommand;
import com.phelim.employeeservice.command.model.CreateEmployeeModel;
import com.phelim.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model){
        CreateEmployeeCommand command =
                new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), false);
        return commandGateway.sendAndWait(command);
    }
    @PutMapping("/{employeeId}")
    public String updatedEmployee(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId){
        UpdateEmployeeCommand command =
                new UpdateEmployeeCommand(employeeId, model.getFirstName(), model.getLastName(), model.getKin(), model.getDisciplined());
        return commandGateway.sendAndWait(command);
    }



}
