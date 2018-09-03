<%--<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%
  if (!request.getParameter("Password").equals(request.getParameter("ConfirmPassword"))) {
    request.setAttribute("errorMessage", "Invalid username or password");
  }
%>--%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Registration|ShesterikovDrive</title>

</head>
<body>
<div>
  <a href="index.html">Log In</a>
</div>
<form action="SignUp" method="POST">
  <fieldset>
    <legend>REGISTRATION</legend>
    <div style="color:red">${errorMessage}</div>
    <p>
      <label>
        <b>
          Username
        </b>
        <input type="text" name="Username" placeholder="...">
      </label>
    </p>
    <p>
      <label>
        <b>
          Email</b>
        <input type="email" name="Email" placeholder="..." value="shesterikov9@gmail.com">
        <!-- "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" -->
      </label>
    </p>
    <p>
      <label>
        <b>
          Password
        </b>
        <input type="password" name="Password" placeholder="...">
      </label>
    </p>
    <p>
      <label>
        <b>
          Confirm your password
        </b>
        <input type="password" name="ConfirmPassword" placeholder="...">
      </label>
    </p>
    <p>
      <label>
        <input type="checkbox" value="terms" required>
        I agree to the <a href="#">terms and conditions</a>
      </label>
    </p>
    <p>
      <input type="submit" name="submitbutton" value="Sign Up" >
    </p>
  </fieldset>
</form>
</body>
</html>
