package com.useronly;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "UploadFiles")
public class UploadFiles extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        int maxMemSize = 4 * 1024;
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        int maxFileSize = 10 * 1024 * 1024;
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    String fileName = fi.getName();

                    String currentDirectory = (String)request.getSession().getAttribute("currentDirectory");

                    // Write the file
                    File file;
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        file = new File( currentDirectory + fileName.substring( fileName.lastIndexOf("\\"))) ;
                    } else {
                        file = new File( currentDirectory + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write(file) ;
                    System.out.println("Uploaded Filename: " + fileName);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        response.sendRedirect("UserFiles");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
