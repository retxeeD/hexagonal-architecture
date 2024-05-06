package hexagonal.microsservice.people.adapter.configuration;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import hexagonal.microsservice.people.domain.exceptions.NotFoundException;
import hexagonal.microsservice.people.domain.exceptions.RentBookException;
import hexagonal.microsservice.people.domain.ports.logger.PersonLogger;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @Autowired
    private PersonLogger logger;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationErrors(HandlerMethodValidationException ex, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        Object[] detailMessageArguments = ex.getDetailMessageArguments();
        Optional<Object> valueOptional = Arrays.stream(detailMessageArguments)
                .findFirst();
        return new ResponseEntity<>(valueOptional.get().toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> duplicateBookNumberError(HttpServletRequest request){
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>("O campo 'documento' da pessoa não pode ser duplicado na base de dados, insira outro valor.", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> NotReadableError(HttpMessageNotReadableException ex, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof JsonMappingException jsonMappingException) {
            String fieldName =  jsonMappingException.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .reduce((first, second) -> first + "." + second)
                    .orElse(null);
            String[] fieldDataType = ((InvalidFormatException) jsonMappingException).getTargetType().getName().split("\\.");

            return new ResponseEntity<>("O campo '"+ fieldName +"' aceita somente dados do tipo "+ fieldDataType[fieldDataType.length -1] +".", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Revise a documentação, há campos sendo enviados com tipo incorreto.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> notFoundBookError(EntityNotFoundException ex, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> typeMismatchError(MethodArgumentTypeMismatchException ex, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        String[] paramType = ex.getRequiredType().toString().split("\\.");
        return new ResponseEntity<>("O parametro '"+ ex.getName() +"' aceita somente dados do tipo "+ paramType[paramType.length -1] +".", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> missingPathVariable(MissingPathVariableException ex, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>("O parametro '"+ ex.getVariableName() +"' é obrigatório na rota.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFound(NotFoundException ex, HttpServletRequest request) {
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>(ex.getErrors(), ex.getStatusCode());
    }

    @ExceptionHandler(RentBookException.class)
    public ResponseEntity<Object> consultNotFoundBook(RentBookException e, HttpServletRequest request) {
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>(e.getErrors(), e.getStatusCode());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> invalidContentType(HttpServletRequest request){
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>("O header 'Content-Type' recebe apenas o valor  'application/json'", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeError(RuntimeException e, HttpServletRequest request){
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericError(HttpServletRequest request){
        logger.error(buildErrorLog(request));
        return new ResponseEntity<>("Ocorreu um erro inesperado, estamos trabalhando para resolver.", HttpStatus.BAD_REQUEST);
    }

    Map<String, Object> buildErrorLog(HttpServletRequest request){
        Map<String, Object> log = new HashMap<>();

        log.put("URL:", request.getRequestURL());
        log.put("Method:", request.getMethod());

        return log;
    }
}
