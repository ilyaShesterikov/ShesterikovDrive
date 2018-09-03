package com.useronly;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserFiles")
public class UserFiles extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("files", getFiles(request));
        request.getSession().setAttribute("folders", getFolders(request));
        response.sendRedirect("userfiles.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public ArrayList<String> getFiles(HttpServletRequest request) {
        ArrayList<String> files = new ArrayList();
        String directoryName = (String)request.getSession().getAttribute("currentDirectory");
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if(fList == null){
            return files;
        }
        for (File file : fList){
            if (file.isFile()){
                files.add(file.getName());
            }
        }
        return files;
    }

    public ArrayList<String> getFolders(HttpServletRequest request) {
        ArrayList<String> folders = new ArrayList();
        String directoryName = (String)request.getSession().getAttribute("currentDirectory");
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if(fList == null){
            return folders;
        }
        for (File file : fList){
            if (file.isDirectory()){
                folders.add(file.getName());
            }
        }
        return folders;
    }
}
