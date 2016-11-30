package com.savko.fifth.parser;

import com.savko.fifth.constant.Tags;
import com.savko.fifth.entity.Bank;
import com.savko.fifth.entity.Deposit;
import com.savko.fifth.entity.DepositType;
import com.savko.fifth.exception.ParsingException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParser extends DefaultHandler {

    private Bank bank;
    private Deposit deposit;
    private String currentTag;

    public void startElement(String uri, String localName, String qName,
                             Attributes attr) {
        currentTag = qName;
        switch (qName) {
            case Tags.BANK:
                bank = new Bank();
                break;
            case Tags.DEPOSIT:
                deposit = new Deposit();
                deposit.setDepositId(Integer.parseInt(attr.getValue(0)));
                break;
        }
    }

    public void characters(char[] buf, int start, int length) {
        String value = new String(buf, start, length);
        switch (currentTag) {
            case Tags.BANK_NAME:
                deposit.setBankName(value);
                break;
            case Tags.COUNTRY:
                deposit.setCountry(value);
                break;
            case Tags.DEPOSIT_TYPE:
                deposit.setDepositType(DepositType.getEnumValue(value));
                break;
            case Tags.DEPOSITOR:
                deposit.setDepositor(value);
                break;
            case Tags.ACCOUNT_ID:
                deposit.setAccountId(Integer.parseInt(value));
                break;
            case Tags.AMOUNT:
                deposit.setAmount(Integer.parseInt(value));
                break;
            case Tags.PROFITABILITY:
                deposit.setProfitability(Integer.parseInt(value));
                break;
            case Tags.TIME_CONSTRAINTS:
                deposit.setTimeConstraints(Integer.parseInt(value));
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case Tags.DEPOSIT: {
                bank.addDeposit(deposit);
            }
        }
        currentTag = "";
    }

    public Bank getDeposits() {
        return bank;
    }

    public Bank parse(String path) throws ParsingException {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SaxParser parSax = new SaxParser();
            parser.parse(path, parSax);
            return parSax.getDeposits();
        } catch (ParserConfigurationException e) {
            throw new ParsingException("Indicates a serious configuration error!", e.getCause());
        } catch (SAXException e) {
            throw new ParsingException("Encapsulate a general SAX error or warning!", e.getCause());
        } catch (IOException e) {
            throw new ParsingException("Check your file! " + path, e.getCause());
        }
    }

}
