/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.servlet;

import com.prise.listener.PriseServletRequestListener;
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
public class GuestEditorServlet extends HttpServlet {

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
        String option = request.getParameter("opt");
        int userId = Integer.parseInt(request.getParameter("userid"));
        int eventId = Integer.parseInt(request.getParameter("eventid"));
        int guestNo = Integer.parseInt(request.getParameter("guestno"));
        int status = 0;
        
        switch (option) {
            case "status":
                //init param value.
                status = Integer.parseInt(request.getParameter("gstatus"));
                System.out.println("userId > "+userId+"  : eventId > "+eventId+" : guestNo > "+guestNo+" : status > "+status);
                PriseEngine.chageGuestStatus(status, userId, eventId, guestNo);
                request.setAttribute("userid", userId);
                request.setAttribute("eventid", eventId);
//                getServletContext().addListener(PriseServletRequestListener.class);
                getServletContext().getRequestDispatcher("/DoRefresh").forward(request, response);
                break;
            case "all":
                //init param value.
                status = Integer.parseInt(request.getParameter("gstatus"));
                
                String seatRow = request.getParameter("seatrow");
                String seatNo = request.getParameter("seatno");
                String guestName = request.getParameter("name");
                String guestCorp = request.getParameter("corp");
                String guestPosition = request.getParameter("position");
                String award = request.getParameter("award");
                int awardNo = Integer.parseInt(request.getParameter("awardno"));
                PriseEngine.editGuestInfo(seatRow, seatNo, award, awardNo, guestName, guestCorp, guestPosition, status, userId, eventId, guestNo);
                request.setAttribute("userid", userId);
                request.setAttribute("eventid", eventId);
                getServletContext().getRequestDispatcher("/DoRefresh").forward(request, response);
                break;
            case "remove":
                PriseEngine.deleteGuest(userId, eventId, guestNo);
                request.setAttribute("userid", userId);
                request.setAttribute("eventid", eventId);
                getServletContext().getRequestDispatcher("/DoRefresh").forward(request, response);
                break;
            default: //TODO
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
