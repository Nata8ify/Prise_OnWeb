/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.servlet;

import com.prise.model.PriseEngine;
import com.prise.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author QuartierLatin
 */
public class ViewEventServlet extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        int eventid = Integer.parseInt(request.getParameter("eventid"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        int sessionUserId = ((User) request.getSession(false).getAttribute("user")).getUserId();
        System.out.println("sessionUserId:" + sessionUserId);
        System.out.println("userid:" + userid);

        if (request.getParameter("opt") != null && (userid == sessionUserId)) {

            getServletContext().getRequestDispatcher("/view_event.jsp").forward(request, response);
        } else if (userid == sessionUserId) {
            request.setAttribute("guests", PriseEngine.getAllGuests(eventid, userid));
        } else if (userid != sessionUserId && PriseEngine.isSharedToThisUser(eventid, userid, sessionUserId)) {
            request.setAttribute("guests", PriseEngine.getAllGuests(eventid, userid));
        } else {
            request.setAttribute("message", "คุณไม่สามารถเข้าหน้านี้ได้ :D");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/view_event.jsp").forward(request, response);

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
