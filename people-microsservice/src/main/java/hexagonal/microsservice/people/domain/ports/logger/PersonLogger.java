package hexagonal.microsservice.people.domain.ports.logger;

import java.util.Map;

public interface PersonLogger {

    void info(Map<String, Object> info);

    void error(Map<String, Object> error);


}
