package com.savko.fifth.parser;

import com.savko.fifth.constant.Attributes;
import com.savko.fifth.constant.Tags;
import com.savko.fifth.entity.Bank;
import com.savko.fifth.entity.Deposit;
import com.savko.fifth.entity.DepositType;
import com.savko.fifth.exception.ParsingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomParser {

    public Bank parse(String path) throws ParsingException {

        Bank bank = new Bank();

        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(Tags.DEPOSIT);

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);
                Deposit deposit = new Deposit();
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    deposit.setDepositId(Integer.parseInt(eElement.getAttribute(Attributes.ID)));
                    deposit.setBankName(eElement.getElementsByTagName(Tags.BANK_NAME)
                            .item(0).getTextContent());
                    deposit.setCountry(eElement.getElementsByTagName(Tags.COUNTRY)
                            .item(0).getTextContent());
                    deposit.setDepositType((DepositType.getEnumValue(eElement.getElementsByTagName(Tags.DEPOSIT_TYPE)
                            .item(0).getTextContent())));
                    deposit.setDepositor(eElement.getElementsByTagName(Tags.DEPOSITOR)
                            .item(0).getTextContent());
                    deposit.setAccountId(Integer.parseInt(eElement.getElementsByTagName(Tags.ACCOUNT_ID)
                            .item(0).getTextContent()));
                    deposit.setAmount(Integer.parseInt(eElement.getElementsByTagName(Tags.AMOUNT)
                            .item(0).getTextContent()));
                    deposit.setProfitability(Integer.parseInt(eElement.getElementsByTagName(Tags.PROFITABILITY)
                            .item(0).getTextContent()));
                    deposit.setTimeConstraints(Integer.parseInt(eElement.getElementsByTagName(Tags.TIME_CONSTRAINTS)
                            .item(0).getTextContent()));
                }
                bank.addDeposit(deposit);
            }
        } catch (SAXException e) {
            throw new ParsingException("Encapsulate a general SAX error or warning!", e.getCause());
        } catch (ParserConfigurationException e) {
            throw new ParsingException("Indicates a serious configuration error!", e.getCause());
        } catch (IOException e) {
            throw new ParsingException("Check your file! " + path, e.getCause());
        }
        return bank;
    }

}
