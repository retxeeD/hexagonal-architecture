package hexagonal.microsservice.people.domain.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class RentBookException extends Exception {
    private Map errors;
    private HttpStatus statusCode;

    public RentBookException(String errors, HttpStatus statusCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.errors = objectMapper.readValue(errors, LinkedHashMap.class);
        }catch (Exception ex){
            System.out.println("Print error log.");
        }
        this.statusCode = statusCode;
    }

    public Map getErrors() {
        return errors;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
