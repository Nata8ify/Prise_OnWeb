/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author QuartierLatin
 */
public class PriseServletRequestListener implements ServletRequestListener{

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        System.out.println(sre.getServletRequest().getParameter("opt"));
//       if(sre.getServletRequest().getParameter("opt").equals("status")){
//           
//       }
        
   }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        System.out.println("requestInitialized");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
