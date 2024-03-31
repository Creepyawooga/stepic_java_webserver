package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet  extends HttpServlet {
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    // sign up
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid login or password");
            return;
        }

        if (accountService.getUserByLogin(login) != null) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("User already exists");
            return;
        }

        // Создаем профиль пользователя с использованием конструктора, который принимает только логин
        UserProfile userProfile = new UserProfile(login);

        // Устанавливаем пароль для профиля
        userProfile.setPass(password);

        // Добавляем пользователя в сервис аккаунтов
        accountService.addNewUser(userProfile);

        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("User registered successfully: " + login);
    }
}