package com.example.timesheet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.timesheet.model.Timesheet;

@SpringBootApplication
@EnableJpaAuditing
public class TimesheetApplication extends SpringBootServletInitializer {
	
	private static final Logger logger = LogManager.getLogger(Timesheet.class);

	public static void main(String[] args) {
		logger.info("Starting timesheet application");
		SpringApplication.run(TimesheetApplication.class, args);
	}
}
