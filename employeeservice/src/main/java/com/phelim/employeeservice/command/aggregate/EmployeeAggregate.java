package com.phelim.employeeservice.command.aggregate;

import com.phelim.employeeservice.command.command.CreateEmployeeCommand;
import com.phelim.employeeservice.command.command.DeleteEmployeeCommand;
import com.phelim.employeeservice.command.command.UpdateEmployeeCommand;
import com.phelim.employeeservice.command.event.EmployeeCreatedEvent;
import com.phelim.employeeservice.command.event.EmployeeDeletedEvent;
import com.phelim.employeeservice.command.event.EmployeeUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class EmployeeAggregate {
    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String Kin;
    private Boolean isDisciplined;

    public EmployeeAggregate() {
    }

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command){
        EmployeeCreatedEvent event = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event){
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.Kin = event.getKin();
        this.isDisciplined = event.getDisciplined();
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand command){
        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdateEmployeeCommand event){
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.Kin = event.getKin();
        this.isDisciplined = event.getDisciplined();
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand command){
        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeleteEmployeeCommand event){
        this.id = event.getId();
    }
}
