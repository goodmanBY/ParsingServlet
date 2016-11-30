package com.savko.fifth.converter;

import com.savko.fifth.exception.ConverterException;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ConvertToHtml {

    public static void convert(String xslPath, String xmlPath) throws ConverterException {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource(xslPath);
            Source xmlDoc = new StreamSource(xmlPath);

            String outputFileName = "data/tableOfDeposits.html";

            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));

        } catch (FileNotFoundException e) {
            throw new ConverterException("Can't find input file! " + xmlPath + " or " + xmlPath, e);
        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            throw new ConverterException("Transformation exception!", e);
        }
    }

}
