package com.example.grand.soap.adapter;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.grand.soap.util.GrandServiceUrl;
import com.sample.soap.xml.dm.QueryEmpConfigs;
import com.sample.soap.xml.dm.QueryEmpConfigsResponse;
import com.sample.soap.xml.dm.SelectAllEmpAllData;
import com.sample.soap.xml.dm.SelectAllEmpAllDataResponse;

/**
 * <B>Type name:</B>SpringBootGrandRestAdapterClient.java</BR>
 * <B>Description:</B> Helper class of soap adapter implementation used to
 * consume/produce soap request/response. <B>References:</B>
 * 
 * @author mlahariya
 * @version 1.0, Dec 2016
 */

@Component
public class SpringBootGrandRestAdapterClient {

    @Value("${dm.service.baseendpoint}")
    private String grand_service_baseendpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GrandServiceUrl grandServiceUrl;

    private static final Log log = LogFactory.getLog(SpringBootGrandRestAdapterClient.class);

    /**
     * This is helper method will be converting SOAP to rest request/response
     * and call the rest services for business process for the queryEmpConfigs
     * operation.
     * 
     * @param QueryEmpConfigs
     * @return QueryEmpConfigsResponse
     * @throws RestClientException
     * @throws GrandslamAccountServiceFault
     */
    public QueryEmpConfigsResponse queryEmpConfigs(QueryEmpConfigs queryEmpConfigs) throws Exception {

        ResponseEntity<com.example.spring.boot.rest.types.EmpDetails> response = null;

        com.sample.soap.xml.dm.EmpConfigIdentifier soapEmpConfigIdentifier = queryEmpConfigs.getArg0();

        com.example.spring.boot.rest.types.EmpConfigIdentifier restRequest = new com.example.spring.boot.rest.types.EmpConfigIdentifier();

        if (soapEmpConfigIdentifier.getId() != 0) {

            // Setting soap request data to rest request
            restRequest.setId(soapEmpConfigIdentifier.getId());
        }

        // Define the headers
        HttpHeaders headers = this.setHttpHeaders();

        HttpEntity<com.example.spring.boot.rest.types.EmpConfigIdentifier> entity = new HttpEntity<com.example.spring.boot.rest.types.EmpConfigIdentifier>(
                restRequest, headers);

        String endPoint = grand_service_baseendpoint.trim() + grandServiceUrl.GET_EMP_CONFIGURATION;

        try {

            // POST or GET out to the source
            log.info("Processing of " + SpringBootGrandRestAdapterClient.class.getName()
                    + "::queryEmpConfigs is started with request parameter " + queryEmpConfigs + "and endPoint is "
                    + endPoint);

            response = restTemplate.exchange(endPoint, HttpMethod.POST, entity,
                    com.example.spring.boot.rest.types.EmpDetails.class);

        } catch (HttpStatusCodeException cause) {
            log.error("While processing of queryCustomer request HttpStatusCodeException is occure" + cause);

        } catch (Exception cause) {

            log.error("While processing of queryCustomer request GenericException is occured" + cause);

        }

        // Soap response object creation
        QueryEmpConfigsResponse queryEmpConfigsResponse = new QueryEmpConfigsResponse();
        com.sample.soap.xml.dm.EmpDetails soapEmpDetails = null;
        if (response != null && response.getBody() != null) {

            com.example.spring.boot.rest.types.EmpDetails restEmpDetails = response.getBody();

            if (restEmpDetails != null) {
                soapEmpDetails = new com.sample.soap.xml.dm.EmpDetails();
                soapEmpDetails.setId(restEmpDetails.getId());
                soapEmpDetails.setFirstName(restEmpDetails.getFirstName());
                soapEmpDetails.setLastName(restEmpDetails.getLastName());
                soapEmpDetails.setAddress(restEmpDetails.getAddress());
                soapEmpDetails.setCity(restEmpDetails.getCity());
                soapEmpDetails.setEmail(restEmpDetails.getEmail());
                soapEmpDetails.setMobile(restEmpDetails.getMobile());
            }

        }

        queryEmpConfigsResponse.setReturn(soapEmpDetails);

        log.info("Processing of " + SpringBootGrandRestAdapterClient.class.getName()
                + ":: queryCustomer is finished with response parameter " + queryEmpConfigsResponse);

        return queryEmpConfigsResponse;
    }

    public SelectAllEmpAllDataResponse selectAllEmpAllData(SelectAllEmpAllData selectAllEmpAllData) throws Exception {

        ResponseEntity<List<com.example.spring.boot.rest.types.EmpDetails>> response = null;

        com.sample.soap.xml.dm.EmpAddress soapEmpAddress = selectAllEmpAllData.getArg0();

        com.example.spring.boot.rest.types.EmpAddress restRequest = new com.example.spring.boot.rest.types.EmpAddress();

        if (soapEmpAddress.getAddress() != null) {

            // Setting soap request data to rest request
            restRequest.setAddress(soapEmpAddress.getAddress());
        }

        // Define the headers
        HttpHeaders headers = this.setHttpHeaders();

        HttpEntity<com.example.spring.boot.rest.types.EmpAddress> entity = new HttpEntity<com.example.spring.boot.rest.types.EmpAddress>(
                restRequest, headers);

        String endPoint = grand_service_baseendpoint.trim() + grandServiceUrl.GET_ALL_EMPBYADDRESS;

        try {

            // POST or GET out to the source
            log.info("Processing of " + SpringBootGrandRestAdapterClient.class.getName()
                    + "::selectAllEmpAddrData is started with request parameter " + selectAllEmpAllData
                    + "and endPoint is " + endPoint);
            ParameterizedTypeReference<List<com.example.spring.boot.rest.types.EmpDetails>> responseType = new ParameterizedTypeReference<List<com.example.spring.boot.rest.types.EmpDetails>>() {
            };

            response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, responseType);

        } catch (HttpStatusCodeException cause) {
            log.error("While processing of queryCustomer request HttpStatusCodeException is occure" + cause);

        } catch (Exception cause) {

            log.error("While processing of queryCustomer request GenericException is occured" + cause);

        }

        // Soap response object creation
        SelectAllEmpAllDataResponse selectAllEmpAllDataResponse = new SelectAllEmpAllDataResponse();

        com.sample.soap.xml.dm.EmpDetails soapEmpDetails = null;

        if (response != null && response.getBody() != null) {

            List<com.example.spring.boot.rest.types.EmpDetails> restEmpDetails = response.getBody();

            if (restEmpDetails != null) {

                for (com.example.spring.boot.rest.types.EmpDetails empDetails : restEmpDetails) {
                    soapEmpDetails = new com.sample.soap.xml.dm.EmpDetails();
                    soapEmpDetails.setId(empDetails.getId());
                    soapEmpDetails.setFirstName(empDetails.getFirstName());
                    soapEmpDetails.setLastName(empDetails.getLastName());
                    soapEmpDetails.setAddress(empDetails.getAddress());
                    soapEmpDetails.setCity(empDetails.getCity());
                    soapEmpDetails.setEmail(empDetails.getEmail());
                    soapEmpDetails.setMobile(empDetails.getMobile());
                    selectAllEmpAllDataResponse.getReturn().add(soapEmpDetails);
                }

            }

        }

        log.info("Processing of " + SpringBootGrandRestAdapterClient.class.getName()
                + ":: queryCustomer is finished with response parameter " + selectAllEmpAllDataResponse);

        return selectAllEmpAllDataResponse;
    }

    /**
     * This is utility method to set the headers
     * 
     * @return
     */
    private HttpHeaders setHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        return headers;

    }

}
