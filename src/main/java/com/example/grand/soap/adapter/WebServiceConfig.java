package com.example.grand.soap.adapter;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

/**
 * @author mlahariya
 * @version 1.0, Dec 2016
 */

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * This is the base URL which will be exposed for soap proxies.
     *
     * @param applicationContext
     * @return
     */

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/DmService/*");
    }

    @Bean(name = "1.0")
    public SimpleWsdl11Definition simpletWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(dmSchema());
        return wsdl11Definition;
    }

    @Bean
    public Resource dmSchema() {
        return new ClassPathResource("MathsDbService.wsdl");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
