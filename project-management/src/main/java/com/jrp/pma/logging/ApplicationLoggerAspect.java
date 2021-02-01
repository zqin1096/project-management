package com.jrp.pma.logging;

import org.aspectj.lang.annotation.After;
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
	@Pointcut("within(com.jrp.pma.controllers..*) || within(com.jrp.pma.dao..*)")
	public void definePackagePointcuts() {
		// Empty method. Used to name the location specified in the pointcut.
	}

	// What.
	// log() will run after everything in com.jrp.pma.controllers package.
	@After("definePackagePointcuts()")
	public void log() {
		logger.debug(" ----------------------- ");
	}

}
