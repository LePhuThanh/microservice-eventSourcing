package com.phelim.borrowingservice.command.model;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class BorrowingCreateModel {
    @NotBlank(message = "Name s mandatory")
    private String bookId;
    @NotBlank(message = "Name s mandatory")
    private String employeeId;

    public BorrowingCreateModel() {
    }

    public BorrowingCreateModel(String bookId, String employeeId) {
        this.bookId = bookId;
        this.employeeId = employeeId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
