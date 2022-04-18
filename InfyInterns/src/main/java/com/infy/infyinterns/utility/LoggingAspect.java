package com.infy.infyinterns.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.infyinterns.exception.InfyInternException;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    
    @AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(InfyInternException exception)
    {
	// code
    	LOGGER.error(exception.getMessage(), exception);
    }

}
