package com.savko.fifth.parser;

import com.savko.fifth.constant.XmlTags;
import com.savko.fifth.entity.Bank;
import com.savko.fifth.entity.Deposit;
import com.savko.fifth.entity.DepositType;
import com.savko.fifth.exception.ParsingException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StaxParser {

    private Bank bank;
    private Deposit deposit;
    private String currentTag;

    public static Bank parse(String path) throws ParsingException {
        try {
            StaxParser staxParser = new StaxParser();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            File xmlFile = new File(path);
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);
            return staxParser.process(reader);
        } catch (XMLStreamException e) {
            throw new ParsingException("Unexpected processing conditions! ", e.getCause());
        } catch (FileNotFoundException e) {
            throw new ParsingException("Check your file! " + path, e.getCause());
        }
    }

    public Bank process(XMLStreamReader reader) {
        try {
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:
                        if (XmlTags.BANK.equals(reader.getLocalName())) {
                            bank = new Bank();
                        }
                        if (XmlTags.DEPOSIT.equals(reader.getLocalName())) {
                            deposit = new Deposit();
                            deposit.setDepositId(Integer.parseInt(reader.getAttributeValue(0)));
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        currentTag = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case XmlTags.DEPOSIT:
                                bank.addDeposit(deposit);
                                break;
                            case XmlTags.BANK_NAME:
                                deposit.setBankName(currentTag);
                                break;
                            case XmlTags.COUNTRY:
                                deposit.setCountry(currentTag);
                                break;
                            case XmlTags.DEPOSIT_TYPE:
                                deposit.setDepositType(DepositType.getEnumValue(currentTag));
                                break;
                            case XmlTags.DEPOSITOR:
                                deposit.setDepositor(currentTag);
                                break;
                            case XmlTags.ACCOUNT_ID:
                                deposit.setAccountId(Integer.parseInt(currentTag));
                                break;
                            case XmlTags.AMOUNT:
                                deposit.setAmount(Integer.parseInt(currentTag));
                                break;
                            case XmlTags.PROFITABILITY:
                                deposit.setProfitability(Integer.parseInt(currentTag));
                                break;
                            case XmlTags.TIME_CONSTRAINTS:
                                deposit.setTimeConstraints(Integer.parseInt(currentTag));
                                break;
                        }
                        break;
                    case XMLStreamConstants.START_DOCUMENT:
                        bank = new Bank();
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return bank;
    }

}


