package com.useronly;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "ParentFolder")
public class ParentFolder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentDirectory = (String) request.getSession().getAttribute("currentDirectory");
        File folder = new File(currentDirectory);
        String parentFolder = folder.getParent();
        String aa = currentDirectory.substring(currentDirectory.lastIndexOf('\\', currentDirectory.length() - 2) + 1, currentDirectory.length() - 1);

        if (aa.equals((String)request.getSession().getAttribute("user"))) {
            response.sendRedirect("index.html");
        }else {
            request.getSession().setAttribute("currentDirectory", parentFolder + "\\");
            response.sendRedirect("UserFiles");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
