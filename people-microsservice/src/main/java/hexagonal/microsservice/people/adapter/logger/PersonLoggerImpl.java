package hexagonal.microsservice.people.adapter.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexagonal.microsservice.people.domain.ports.logger.PersonLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class PersonLoggerImpl implements PersonLogger {

    Logger logger = LoggerFactory.getLogger(PersonLoggerImpl.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void info(Map<String, Object> info) {
        try {
            logger.info(objectMapper.writeValueAsString(info));
        }catch (Exception e){
            logger.error("Error in LogInfo method;");
        }
    }

    @Override
    public void error(Map<String, Object> error) {
        try {
            logger.info(objectMapper.writeValueAsString(error));
        }catch (Exception e){
            logger.error("Error in LogInfo method;");
        }
    }
}
