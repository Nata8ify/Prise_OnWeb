/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.servlet;

import com.prise.model.PriseEngine;
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
public class InsertNewGuestServlet extends HttpServlet {

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
        int userId = Integer.parseInt(request.getParameter("userid"));
        int eventId = Integer.parseInt(request.getParameter("eventid"));

        String seatRow = request.getParameter("seatrow");
        String seatNo = request.getParameter("seatno");
        String guestName = request.getParameter("name");
        String guestCorp = request.getParameter("corp");
        String guestPosition = request.getParameter("position");
        String award = request.getParameter("award");
        int awardNo = Integer.parseInt(request.getParameter("awardno"));
        int status = Integer.parseInt(request.getParameter("status"));
        if (PriseEngine.insertGuest(seatRow, seatNo, guestName, guestCorp, guestPosition, award, awardNo, status, userId, eventId)) {
//            request.setAttribute("guests", PriseEngine.getAllGuests(eventId, userId));
            getServletContext().getRequestDispatcher("/DoRefresh").forward(request, response);
        } else {
//            request.setAttribute("message", guestName + " and " + award + " already existed in this event.");
            getServletContext().getRequestDispatcher("/insert_guest.jsp?message=(" + guestName + " with " + award + " already existed in this event.)").forward(request, response);
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
