/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.servlet;

import com.prise.model.AndroidPriseEngine;
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
public class AndroidViewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *///http://localhost:8080/Prise/AndroidView?userid=4&eventid=3
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String option = request.getParameter("opt");
        int userid = Integer.parseInt(request.getParameter("userid"));
        int eventid = 0;
        if (request.getParameter("eventid") != null) {
            eventid = Integer.parseInt(request.getParameter("eventid"));
        }
        switch (option) {
            case "allguests":
                eventid = Integer.parseInt(request.getParameter("eventid"));
                String sortBy = request.getParameter("sortby");
                String sortType,
                 filterNo,
                 filterArgument = null;
//                System.out.println("sortby:" + sortBy);
                if (sortBy.equals("")) {
                    System.out.println("sortBy.equals(\"\") Condition");
                    request.setAttribute("args", AndroidPriseEngine.getAllGuestsViaJSON(eventid, userid));
                } else {
                    System.out.println("!sortBy.equals(\"\") Condition");
//                    http://52.221.255.26:8080/Prise/AndroidView?userid=2&eventid=2&sortby=1&sorttype=1&opt=allguests&filterno=1&filterargs=3
                    sortType = request.getParameter("sorttype");
                    if (!request.getParameter("filterno").equals("") & !request.getParameter("filterargs").equals("")) {
                        filterNo = request.getParameter("filterno"); // SUPPORTED STATUS FILTER FOR  NOW
                        filterArgument = request.getParameter("filterargs");
//                        request.setAttribute("args", AndroidPriseEngine.getFilterAllGuestsByStatusViaJSON(eventid, userid, Integer.valueOf(filterArgument)));
                    request.setAttribute("args", AndroidPriseEngine.getFilterAllGuestsByStatusViaJSON(eventid, userid, Integer.valueOf(filterArgument),sortBy, sortType));
                    } else {
                        request.setAttribute("args", AndroidPriseEngine.getAllSortedGuestsViaJSON(eventid, userid, sortBy, sortType));
                    }
                }
                break;

            case "allevents":
                request.setAttribute("args", AndroidPriseEngine.getAllEventsViaJSON(userid));
                break;

            case "allguests_for_datatable":
                request.setAttribute("args", AndroidPriseEngine.getAllGuestsViaJSONForDataTable(
                        Integer.parseInt(request.getParameter("eventid")), userid));
                break;

            case "allguest_filter":
                if (!request.getParameter("filterBy").equals("") & !request.getParameter("filterargs").equals("")) {
                    System.out.println("Filter Case!");
                    String filterBy = request.getParameter("filterBy");
                    switch (filterBy) {
                        case "status":

                            int statusId = Integer.valueOf(request.getParameter("filterargs"));
                            request.setAttribute("args", AndroidPriseEngine.getFilterAllGuestsByStatusViaJSON(eventid, userid, statusId));
                            break;
                        case "keyword":
                            break;
                    }

                }
                break;
            default:
                request.setAttribute("args", "ZzzzZzzzz...");
        }

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
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
