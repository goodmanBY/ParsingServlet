package com.savko.fifth.parser;

import com.savko.fifth.constant.Tags;
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
                        if (Tags.BANK.equals(reader.getLocalName())) {
                            bank = new Bank();
                        }
                        if (Tags.DEPOSIT.equals(reader.getLocalName())) {
                            deposit = new Deposit();
                            deposit.setDepositId(Integer.parseInt(reader.getAttributeValue(0)));
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        currentTag = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case Tags.DEPOSIT:
                                bank.addDeposit(deposit);
                                break;
                            case Tags.BANK_NAME:
                                deposit.setBankName(currentTag);
                                break;
                            case Tags.COUNTRY:
                                deposit.setCountry(currentTag);
                                break;
                            case Tags.DEPOSIT_TYPE:
                                deposit.setDepositType(DepositType.getEnumValue(currentTag));
                                break;
                            case Tags.DEPOSITOR:
                                deposit.setDepositor(currentTag);
                                break;
                            case Tags.ACCOUNT_ID:
                                deposit.setAccountId(Integer.parseInt(currentTag));
                                break;
                            case Tags.AMOUNT:
                                deposit.setAmount(Integer.parseInt(currentTag));
                                break;
                            case Tags.PROFITABILITY:
                                deposit.setProfitability(Integer.parseInt(currentTag));
                                break;
                            case Tags.TIME_CONSTRAINTS:
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


