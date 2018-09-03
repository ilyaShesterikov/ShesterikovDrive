package com.useronly;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OpenFolder")
public class OpenFolder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String folderName = request.getParameter("openFolderName");
        String directoryName = (String)request.getSession().getAttribute("currentDirectory");
        directoryName = directoryName + folderName + "\\";
        request.getSession().setAttribute("currentDirectory", directoryName);
        response.sendRedirect("UserFiles");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
