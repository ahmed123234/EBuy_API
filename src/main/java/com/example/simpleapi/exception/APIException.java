package com.example.simpleapi.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class APIException extends Exception{
    String message;
    Date date;
}
