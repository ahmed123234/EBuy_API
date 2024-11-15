package com.example.simpleapi.responsehandeling;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class Response {

    public static void setResponse(HttpServletResponse response, Integer statusCode, Map<String, ?> message) {
        response.setStatus(statusCode);
        response.setContentType(APPLICATION_JSON_VALUE);
        try {
            new ObjectMapper().writeValue(response.getOutputStream(), message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setResponse(HttpServletResponse response, Integer statusCode, Object body) {
        response.setStatus(statusCode);
        response.setContentType(APPLICATION_JSON_VALUE);
        try {
            new ObjectMapper().writeValue(response.getOutputStream(), body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void checkObjectAvailability(HttpServletResponse response, Optional<?> object, String error, Object parameter) {

        boolean isPresent = object.isPresent();
        Map<String, Object> body = new HashMap<>();

        if (isPresent) {
            body.put("message", object.get());
            Response.setResponse(response, 200, body);
        } else {

            body.put("error", String.format(error, parameter));
            Response.setResponse(response, 404, body);
        }
    }


    public static boolean isAvailableUser(HttpServletResponse response, Optional<?> object, String error,
                                               Object parameter) {

        boolean isEmpty= object.isEmpty();
        if (!isEmpty) {
            Map<String, String> body = new HashMap<>();
            body.put("error", String.format(error, parameter));
            Response.setResponse(response, 503, body);
        }
        return isEmpty;
    }

    public static boolean checkObjectAvailability(HttpServletResponse response, Optional<?> object, String message,
                                                  String error, Object parameter) {

        Map<String, String> body = new HashMap<>();

        boolean isPresent = object.isPresent();
        if (isPresent) {

            body.put("message", String.format(message, parameter));
            Response.setResponse(response, 200, body);
        } else {

            body.put("error", String.format(error, parameter));
            Response.setResponse(response, 404, body);
        }
        return isPresent;
    }


    public static void checkObjectAvailability(HttpServletResponse response, Object object, Object message,
                                                  String error, Object parameter) {

        boolean isPresent = object != null;
        if (isPresent) {
            Response.setResponse(response, 200, message);
        } else {

            Map<String, String> body = new HashMap<>();
            body.put("error", String.format(error, parameter));
            Response.setResponse(response, 404, body);
        }
    }


}
