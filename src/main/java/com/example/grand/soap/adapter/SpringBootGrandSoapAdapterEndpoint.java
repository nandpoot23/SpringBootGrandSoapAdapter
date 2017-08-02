package com.example.grand.soap.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.grand.soap.util.GrandCommonUtility;
import com.sample.soap.xml.dm.QueryEmpConfigs;
import com.sample.soap.xml.dm.QueryEmpConfigsResponse;
import com.sample.soap.xml.dm.SelectAllEmpAllData;
import com.sample.soap.xml.dm.SelectAllEmpAllDataResponse;

/**
 * <B>Type name:</B>SpringBootGrandSoapAdapterEndpoint.java</BR>
 * <B>Description:</B> Endpoint of soap adapter implementation used to consume
 * soap request. <B>References:</B> <I> RestClient.java </I>
 * 
 * Marks an endpoint method as the handler for an incoming request. The
 * annotation values signify the request payload root element that is handled by
 * the method.
 * 
 * @author mlahariya
 * @version 1.0, Dec 2016
 */

@Component
@Endpoint("SpringBootGrandSoapAdapterEndpoint")
public class SpringBootGrandSoapAdapterEndpoint {

    private static final String NAMESPACE_URI = "http://dm.xml.soap.sample.com/";

    private static final Logger logger = LoggerFactory.getLogger(SpringBootGrandSoapAdapterEndpoint.class);

    @Autowired
    private SpringBootGrandRestAdapterClient dmGrandRestClient;

    /**
     * This method is use to consume soap request and internally calling to rest
     * service for business processing for the queryEmpConfigs operation
     * 
     * @param QueryEmpConfigs
     * @param MessageContext
     * @return QueryEmpConfigsResponse
     * @throws Exception
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "queryEmpConfigs")
    @ResponsePayload
    public QueryEmpConfigsResponse queryEmpConfigs(@RequestPayload QueryEmpConfigs queryCustomer,
            MessageContext messageContext) throws Exception {

        logger.info("SOAP Request Received Of queryCustomer " + queryCustomer);
        QueryEmpConfigsResponse response = null;
        response = dmGrandRestClient.queryEmpConfigs(queryCustomer);

        // Utility method to set the soap response header.
        GrandCommonUtility.createResponseHeader(messageContext);
        logger.info("SOAP Response Sent Of queryEmpConfigs  : " + response.toString());

        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "selectAllEmpAllData")
    @ResponsePayload
    public SelectAllEmpAllDataResponse selectAllEmpAllData(@RequestPayload SelectAllEmpAllData selectAllEmpAllData,
            MessageContext messageContext) throws Exception {

        logger.info("SOAP Request Received Of selectAllEmpAddrData " + selectAllEmpAllData);
        SelectAllEmpAllDataResponse response = null;
        response = dmGrandRestClient.selectAllEmpAllData(selectAllEmpAllData);

        // Utility method to set the soap response header.
        GrandCommonUtility.createResponseHeader(messageContext);
        logger.info("SOAP Response Sent Of selectAllEmpAddrData  : " + response.toString());

        return response;

    }

}
