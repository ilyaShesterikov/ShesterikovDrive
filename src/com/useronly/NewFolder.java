package com.useronly;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@WebServlet(name = "NewFolder")
public class NewFolder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String folderName = request.getParameter("folderName");
        HttpSession newSession = request.getSession(true);
        String currentDirectory = (String)newSession.getAttribute("currentDirectory");
        File f = new File(currentDirectory + folderName);
        f.mkdirs();
        response.sendRedirect("UserFiles");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

