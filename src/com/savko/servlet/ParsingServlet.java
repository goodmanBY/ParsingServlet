package com.savko.servlet;

import com.savko.fifth.command.Command;
import com.savko.fifth.constant.JspAttributes;
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
import java.io.*;

@MultipartConfig
public class ParsingServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ParsingServlet.class);
    private static final String UPLOAD_DIRECTORY = "F:\\WebParsingXml\\Uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parserType = request.getParameter(JspAttributes.PARSER_TYPE_PARAMETER);
        Command command = CommandHolder.getInstance().getCommand(parserType);

        try {
            Part filePart = request.getPart(JspAttributes.FILE_PART);
            String name = filePart.getSubmittedFileName();
            String filePath = UPLOAD_DIRECTORY + File.separator + name;
            InputStream fileContent = filePart.getInputStream();
            FileUploader.upload(filePath, fileContent);
            Bank bank = command.parse(filePath);
            request.setAttribute(JspAttributes.BANK, bank);
            request.setAttribute(JspAttributes.TYPE, parserType);

        } catch (ParsingException e) {
            LOGGER.error("Parsing error." + e);
            throw new ServletException("Parsing error.", e);
        } catch (Exception e) {
            LOGGER.error("File Upload Failed due to " + e);
            throw new ServletException("File Upload Failed", e);
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
