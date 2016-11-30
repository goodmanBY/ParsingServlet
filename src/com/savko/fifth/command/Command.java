package com.savko.fifth.command;


import com.savko.fifth.entity.Bank;
import com.savko.fifth.exception.ParsingException;

public interface Command {

    Bank parse(String path) throws ParsingException;

}
