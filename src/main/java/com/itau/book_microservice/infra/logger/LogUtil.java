package com.itau.book_microservice.infra.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LogUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger =  Logger.getLogger(LogUtil.class);

    public static void log (Level level, String message){
        logger.log(level, formatRequisitionLog(message));
    }

    public static void info(String message){
        logger.log(Level.INFO, formatRequisitionLog(message));
    }

    public static void error(String message){
        logger.log(Level.ERROR, formatRequisitionLog(message));
    }

    public static void debug(String message){
        logger.log(Level.DEBUG, formatRequisitionLog(message));
    }

    private static String formatRequisitionLog(String requisition){
        String formattedRequisition;
        try {
            Object json = objectMapper.readValue(requisition, Object.class);
            formattedRequisition = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            formattedRequisition = requisition;
        }
        return formattedRequisition;
    }

}
