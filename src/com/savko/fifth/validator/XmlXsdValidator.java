package com.savko.fifth.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlXsdValidator {

    private final static Logger LOGGER = Logger.getLogger(XmlXsdValidator.class);

    public static boolean validate(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (IOException e) {
            LOGGER.error("Check your files! " + xmlPath + " and " + xmlPath);
        } catch (SAXException e) {
            LOGGER.error("Encapsulate a general SAX error or warning!", e.getCause());
        }
        return false;
    }

}
