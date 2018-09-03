package com.useronly;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet(name = "DeleteFile")
public class DeleteFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentDirectory = (String) request.getSession().getAttribute("currentDirectory");
        File file = new File(currentDirectory, request.getParameter("fileToDelete"));
        try {
            deleteDir(file);
        } catch (IOException e) {
            System.out.println("Problem occurs when deleting the directory : " + file);
            e.printStackTrace();
        }
        response.sendRedirect("UserFiles");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    /**
     * Delete a file or a directory and its children.
     * @param file The directory to delete.
     * @throws IOException Exception when problem occurs during deleting the directory.
     */
    private void deleteDir(File file) throws IOException {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                if (childFile.isDirectory()) {
                    deleteDir(childFile);
                } else {
                    if (!childFile.delete()) {
                        throw new IOException();
                    }
                }
            }
        }

        if (!file.delete()) {
            throw new IOException();
        }
    }
}
