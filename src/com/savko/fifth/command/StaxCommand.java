package com.savko.fifth.command;


import com.savko.fifth.entity.Bank;
import com.savko.fifth.exception.ParsingException;
import com.savko.fifth.parser.StaxParser;

public class StaxCommand implements Command {
    @Override
    public Bank parse(String path) throws ParsingException {
        return StaxParser.parse(path);
    }
}
