package com.savko.fifth.command;


import com.savko.fifth.entity.Bank;
import com.savko.fifth.exception.ParsingException;
import com.savko.fifth.parser.SaxParser;

public class SaxCommand implements Command {
    @Override
    public Bank parse(String path) throws ParsingException {
        SaxParser parser = new SaxParser();
        return parser.parse(path);
    }
}
