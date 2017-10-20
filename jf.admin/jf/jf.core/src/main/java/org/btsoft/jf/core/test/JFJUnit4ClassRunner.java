package org.btsoft.jf.core.test;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class JFJUnit4ClassRunner extends SpringJUnit4ClassRunner {
	
	static{
		try {
			Log4jConfigurer.initLogging("classpath:log4j.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	}

	public JFJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

}
