/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.servlet;

import com.google.gson.GsonBuilder;
import com.prise.model.PriseEngine;
import com.prise.model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author QuartierLatin
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String option = request.getParameter("opt");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        switch (option) {
            case "web":
                user = PriseEngine.login(username, password);
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60 * 60 * 3);
                    request.setAttribute("events", PriseEngine.getEventsByUserId(user.getUserId()));
                    getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
                } else {
                    if (PriseEngine.isExistedUsername(username)) {
                        request.setAttribute("message", "Password is incorrect");
                        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    request.setAttribute("username", username);
                    getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
                }
                break;
            case "app":
                if (PriseEngine.login(username, password) != null) {
                    user = PriseEngine.login(username, password);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60 * 60 * 3);
                    request.setAttribute("args", new GsonBuilder().create().toJson(user));
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
                } else {
                    request.setAttribute("args", new GsonBuilder().create().toJson(new User()));
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
