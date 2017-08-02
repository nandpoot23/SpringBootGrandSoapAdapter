package com.example.grand.soap.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

/**
 * @author mlahariya
 * @version 1.0, Dec 2016
 */

public class GrandCommonUtility {

    private static final Logger logger = LoggerFactory.getLogger(GrandCommonUtility.class);

    /*
     * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        if (date == null) {
            return null;
        }
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        return xmlCalendar;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Calendar calendar) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        if (calendar == null) {
            return null;
        }
        gCalendar.setTime(calendar.getTime());
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        return xmlCalendar;
    }

    public static void createResponseHeader(MessageContext messageContext) {
        logger.info("Read SOAP Header from request and append in response : Start");

        try {
            SaajSoapMessage soapResponse = (SaajSoapMessage) messageContext.getResponse();
            SoapHeader respheader = soapResponse.getSoapHeader();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            SaajSoapMessage soapRequest = (SaajSoapMessage) messageContext.getRequest();
            SoapHeader reqheader = soapRequest.getSoapHeader();
            Transformer transformer = transformerFactory.newTransformer();
            Iterator<SoapHeaderElement> itr = reqheader.examineAllHeaderElements();
            while (itr.hasNext()) {
                SoapHeaderElement ele = itr.next();
                transformer.transform(ele.getSource(), respheader.getResult());
            }
        } catch (TransformerException transformerException) {
            logger.error("Transformation failed : ", transformerException.getMessage());
        }
        logger.info("Read SOAP Header from request and append in response : End");
    }

    public static Calendar toGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar == null) {
            return null;
        } else {
            return xmlGregorianCalendar.toGregorianCalendar();
        }
    }

}