package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignIn")
public class SignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dosome(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dosome(request, response);
    }

    private void dosome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out=response.getWriter();
        String username=request.getParameter("Username");
        String password=request.getParameter("Password");
        String hashedPassword = SignUp.getSHA256Hash(password);
        ShesterikovDriveDB db = new ShesterikovDriveDB();
        if(db.loginUser(username, hashedPassword)){
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", username);
            newSession.setAttribute("currentDirectory","\\ShesterikovDriveFiles\\" + username+ "\\");

            response.sendRedirect("UserFiles");
        }
        else{
            HttpSession session = request.getSession(true);
            session.invalidate();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(request, response);

        }
    }
}
