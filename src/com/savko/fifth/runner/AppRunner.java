package com.savko.fifth.runner;

import com.savko.fifth.exception.ParsingException;
import com.savko.fifth.parser.DomParser;
import com.savko.fifth.parser.SaxParser;
import org.apache.log4j.Logger;

public class AppRunner {

    private final static Logger LOGGER = Logger.getLogger(AppRunner.class);
    private static final String XML_PATH = "F:\\IdeaProjects\\TestServlet\\data\\deposits.xml";
    private static final String XSL_PATH = "data/deposits.xsl";
    private static final String XSD_PATH = "data/deposits.xsd";

    public static void main(String args[]) {

        try {
            //DOM Parser
            DomParser domParser = new DomParser();
            System.out.print(domParser.parse(XML_PATH));


            //SAX Parser
            SaxParser saxParser = new SaxParser();


            //StAX Parser


            //Is XML file validates against XSD file


            //Converting XML and XSL files to HTML


        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

}
