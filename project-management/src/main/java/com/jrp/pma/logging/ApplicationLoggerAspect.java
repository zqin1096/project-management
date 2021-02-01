package com.jrp.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApplicationLoggerAspect {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// Where. ..* means classes, methods, and others.
	@Pointcut("within(com.jrp.pma.controllers..*)")
	public void definePackagePointcuts() {
		// Empty method. Used to name the location specified in the pointcut.
	}

	// What.
	// log() will run after everything in com.jrp.pma.controllers package.
	@After("definePackagePointcuts()")
	public void logAfter(JoinPoint joinpoint) {
		logger.debug("\n\n\n");
		logger.debug("******** After Method Execution ******** \n {}.{} () with argument[s] = {}",
				joinpoint.getSignature().getDeclaringTypeName(), joinpoint.getSignature().getName(),
				Arrays.toString(joinpoint.getArgs()));
		logger.debug("_____________________________\n\n\n");

	}

	// Need to use ProceedingJoinPoint with @Around.
	@Around("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint joinpoint) {
		logger.debug("\n\n\n");
		logger.debug("******** Before Method Execution ******** \n {}.{} () with argument[s] = {}",
				joinpoint.getSignature().getDeclaringTypeName(), joinpoint.getSignature().getName(),
				Arrays.toString(joinpoint.getArgs()));
		logger.debug("_____________________________\n\n\n");

		Object object = null;
		try {
			// Proceed to next step.
			object = joinpoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("******** After Method Execution ******** \n {}.{} () with argument[s] = {}",
				joinpoint.getSignature().getDeclaringTypeName(), joinpoint.getSignature().getName(),
				Arrays.toString(joinpoint.getArgs()));
		logger.debug("_____________________________\n\n\n");
		return object;

	}

}
/*
 * A joinpoint is a candidate point in the Program Execution of the application
 * where an aspect can be plugged in. This point could be a method being called,
 * an exception being thrown, or even a field being modified.
 */