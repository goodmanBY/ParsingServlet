package com.savko.servlet;

import com.savko.fifth.command.Command;
import com.savko.fifth.entity.Bank;
import com.savko.fifth.exception.ParsingException;
import com.savko.fifth.holdel.CommandHolder;
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
    private static final String UPLOAD_DIRECTORY = "F:\\uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parserType = request.getParameter("parserType");
        Command command = CommandHolder.getInstance().getCommand(parserType);

        try {
            Part filePart = request.getPart("file");
            String name = filePart.getSubmittedFileName();
            String filePath = UPLOAD_DIRECTORY + File.separator + name;
            InputStream fileContent = filePart.getInputStream();
            OutputStream out = new FileOutputStream(new File(filePath));
            int read;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            LOGGER.info("TEST");

            Bank bank = command.parse(filePath);
            request.setAttribute("bank", bank);
            request.setAttribute("type", parserType);

        } catch (ParsingException e) {
            request.setAttribute("error", "Error!");
        } catch (Exception ex) {
            request.setAttribute("error", "File Upload Failed due to " + ex);
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
