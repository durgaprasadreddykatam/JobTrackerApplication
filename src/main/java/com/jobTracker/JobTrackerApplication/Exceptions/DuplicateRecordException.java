package com.jobTracker.JobTrackerApplication.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String message){
        super(message);
    }
}
