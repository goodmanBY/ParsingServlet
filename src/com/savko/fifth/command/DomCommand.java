package com.savko.fifth.command;


import com.savko.fifth.entity.Bank;
import com.savko.fifth.exception.ParsingException;
import com.savko.fifth.parser.DomParser;

public class DomCommand implements Command {
    @Override
    public Bank parse(String path) throws ParsingException {
        DomParser parser = new DomParser();
        return parser.parse(path);
    }
}
