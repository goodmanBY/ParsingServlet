package com.savko.servlet;

import com.savko.fifth.command.Command;
import com.savko.fifth.constant.JspConstant;
import com.savko.fifth.entity.Bank;
import com.savko.fifth.exception.ParsingException;
import com.savko.fifth.holdel.CommandHolder;
import com.savko.fifth.util.FileUploader;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
public class ParsingServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ParsingServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parserType = request.getParameter(JspConstant.PARSER_TYPE_PARAMETER);
        Command command = CommandHolder.getInstance().getCommand(parserType);

        try {
            Part filePart = request.getPart(JspConstant.FILE_PART);
            String name = filePart.getSubmittedFileName();
            String filePath = JspConstant.UPLOAD_DIRECTORY + File.separator + name;
            InputStream fileContent = filePart.getInputStream();
            FileUploader.upload(filePath, fileContent);
            Bank bank = command.parse(filePath);
            request.setAttribute(JspConstant.BANK, bank);
            request.setAttribute(JspConstant.TYPE, parserType);

        } catch (ParsingException e) {
            LOGGER.error("Parsing error." + e);
            throw new ServletException("Parsing error.", e);
        } catch (Exception e) {
            LOGGER.error("File Upload Failed due to " + e);
            throw new ServletException("File Upload Failed", e);
        }

        request.getRequestDispatcher(JspConstant.PATH_PAGE_MAIN).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
