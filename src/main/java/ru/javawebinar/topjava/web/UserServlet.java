package ru.javawebinar.topjava.web;

import java.io.IOException;

/**
 * Created by Ilya on 16.07.2017.
 */
public class UserServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}