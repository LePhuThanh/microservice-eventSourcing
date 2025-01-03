package com.phelim.employeeservice.query.controller;

import com.phelim.employeeservice.query.model.EmployeeResponseModel;
import com.phelim.employeeservice.query.queries.GetAllEmployeeQuery;
import com.phelim.employeeservice.query.queries.GetDetailEmployeeQuery;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Query")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @Operation(
            summary = "Get List Employee",
            description = "Get endpoint for employee with filter",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    )
            }

    )
    @GetMapping("get-all-employees")
    public List<EmployeeResponseModel> getAllEmployee(@RequestParam(name = "Disciplined",required = false, defaultValue = "false") Boolean isDisciplined){
        GetAllEmployeeQuery query = new GetAllEmployeeQuery(isDisciplined);
        return queryGateway.
                query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    //@Hidden
    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getDetailEmployee(@PathVariable String employeeId){
        GetDetailEmployeeQuery query = new GetDetailEmployeeQuery(employeeId);
        return queryGateway.
                query(query, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }
}
