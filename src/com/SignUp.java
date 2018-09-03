package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet(name = "SignUp")
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dosome(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dosome(request, response);
    }

    private void dosome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        String email = request.getParameter("Email");



        String hashedPassword = getSHA256Hash(password);
        ShesterikovDriveDB db = new ShesterikovDriveDB();
        if(!db.checkUsername(username)) {
            if(!request.getParameter("Password").equals(request.getParameter("ConfirmPassword"))) {
                request.setAttribute("errorMessage", "Password does not match the confirm password.");
                RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
                rd.forward(request, response);
            } else {
                db.addNewUser(username, hashedPassword, email);
            }
        } else{
            request.setAttribute("errorMessage", "Provided username already in use.");
            RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
            rd.forward(request, response);
        }

        File f = new File("C:\\Users\\shest\\IdeaProjects\\ShesterikovDrive\\resources\\" + username);
        f.mkdirs();
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("user", username);
        newSession.setAttribute("currentDirectory","C:\\Users\\shest\\IdeaProjects\\ShesterikovDrive\\resources\\" + username+ "\\");

        response.sendRedirect("UserFiles");
    }

    /**
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
     * @param data
     * @return
     */
    public static String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            result = DatatypeConverter.printHexBinary(hash);
            return result;
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
