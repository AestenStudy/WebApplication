package com.emse.spring.faircorp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication(
        exclude = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
public class FaircorpApplication implements ApplicationRunner {
    private static final Logger logger = LogManager.getLogger(FaircorpApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FaircorpApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("Debugging log");
        logger.info("Information log");
        logger.warn("Warning log");
        logger.error("Error log");
        logger.fatal("Fatal error log");
    }
}
