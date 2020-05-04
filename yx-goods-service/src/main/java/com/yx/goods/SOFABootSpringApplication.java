package com.yx.goods;

import com.yx.goods.facade.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:META-INF/yx-goods/*.xml"})
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SOFABootSpringApplication {
    private static final Logger logger = LoggerFactory.getLogger(SOFABootSpringApplication.class);

    public static void main(String[] args){

        SpringApplication springApplication = new SpringApplication(SOFABootSpringApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);
        SampleService sampleService = applicationContext.getBean(SampleService.class);

        if (logger.isInfoEnabled()){
            logger.info("Sample Service Result = " + sampleService.message());
            logger.info("Current Application Context : " + applicationContext);
        }
    }
}
