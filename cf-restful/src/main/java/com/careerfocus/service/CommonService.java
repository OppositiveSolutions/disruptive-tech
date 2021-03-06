package com.careerfocus.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.CommonDAO;
import com.careerfocus.entity.States;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.ValidationUtils;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    private static final int LOG4J_TRACE = 5;
    private static final int LOG4J_OFF = 4;
    private static final int LOG4J_ERROR = 3;
    private static final int LOG4J_INFO = 2;
    private static final int LOG4J_DEBUG = 1;

    @Autowired
    CommonDAO commonDAO;

    @Autowired
    UserRepository userRepo;

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    public String setLogLevel(int level) throws Exception {
        String message = "log4j set to ";

        Logger rootLogger = (Logger) LoggerFactory.getLogger("com.careerfocus");

        switch (level) {
            case LOG4J_DEBUG:
                rootLogger.setLevel(Level.DEBUG);
                message += "Debug";
                log.debug(message);
                break;
            case LOG4J_INFO:
                rootLogger.setLevel(Level.INFO);
                message += "Info";
                log.info(message);
                break;
            case LOG4J_ERROR:
                rootLogger.setLevel(Level.ERROR);
                message += "Error";
                log.error(message);
                break;
            case LOG4J_OFF:
                rootLogger.setLevel(Level.OFF);
                message += "Off";
                log.debug(message);
                break;
            case LOG4J_TRACE:
                rootLogger.setLevel(Level.TRACE);
                message += "Trace";
                log.trace(message);
                break;
            default:
                rootLogger.setLevel(Level.ERROR);
                message += "-Error";
                log.error(message);
                break;
        }
        return message;
    }

    public List<States> getStates() {
        return commonDAO.getStates();
    }

    public Response checkEmailExists(String emailId) {
        List<Error> errors = new ArrayList<>();
        if (!ValidationUtils.isValidEmailAddress(emailId))
            errors.add(new Error(ErrorCodes.INVALID_EMAIL_ID, ErrorCodes.INVALID_EMAIL_ID_MSG));
        if (userRepo.findByUsername(emailId) != null)
            errors.add(new Error(ErrorCodes.EMAIL_EXISTS, ErrorCodes.EMAIL_EXISTS_MSG));

        if (!errors.isEmpty())
            return Response.status(ErrorCodes.VALIDATION_FAILED)
                    .error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();

        return Response.ok().build();
    }

	public List<Map<String,Object>> getCoachingTypes() {
		return commonDAO.getCoachingTypes();
	}
}
