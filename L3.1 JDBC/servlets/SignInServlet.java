package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || !login.equals("your_login") || !password.equals("your_password")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Authorized: " + login);
    }
}
