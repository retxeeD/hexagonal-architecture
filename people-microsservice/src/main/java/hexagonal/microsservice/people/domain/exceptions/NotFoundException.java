package hexagonal.microsservice.people.domain.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class NotFoundException extends Exception{

    private Map errors;
    private HttpStatus statusCode;

    public NotFoundException(String errors, HttpStatus statusCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.errors = objectMapper.readValue(errors, LinkedHashMap.class);
        }catch (Exception e){
            throw new RuntimeException("NotFoundException build error. \b Error message: "+ e.getMessage());
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
