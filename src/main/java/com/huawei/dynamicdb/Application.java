package com.huawei.dynamicdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
//@Import({DataSourceRegistrar.class})
@ImportResource({"classpath:spring-mysql.xml"})
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class Application 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}
