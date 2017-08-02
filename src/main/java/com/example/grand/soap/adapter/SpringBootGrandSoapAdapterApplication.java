package com.example.grand.soap.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author mlahariya
 * @version 1.0, Dec 2016
 */

@SpringBootApplication
@ComponentScan(basePackages = { "com.example", "com.sample.soap.xml.dm" })
public class SpringBootGrandSoapAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGrandSoapAdapterApplication.class, args);
    }
}
