package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Before("execution(* ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator.*.generate())")
    void generatingDataLogging(JoinPoint joinPoint) {
        logger.info("Generator Method | {} | starting to generate data...", joinPoint);
    }
    
    @AfterReturning("execution(* ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator.*.generate())")
    void generatingDataAfterLogging(JoinPoint joinPoint) {
        logger.info("Generator Method | {} | success!", joinPoint);
    }

}