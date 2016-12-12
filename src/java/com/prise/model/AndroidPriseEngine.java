/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author QuartierLatin
 */
public class AndroidPriseEngine {
//PriseEngine.getAllGuests(eventid, userid)

    public static JSONObject getAllGuestsViaJSON(int eventid, int userid) {
        try {
            return new JSONObject().put("guests", PriseEngine.getAllGuests(eventid, userid));
        } catch (JSONException ex) {
            Logger.getLogger(AndroidPriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JSONObject getAllEventsViaJSON(int userId) {
        try {
            return new JSONObject().put("events", PriseEngine.getEventsByUserId(userId));
        } catch (JSONException ex) {
            Logger.getLogger(AndroidPriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JSONObject getAllGuestsViaJSONForDataTable(int eventid, int userid) {
        ArrayList<Guest> guests = PriseEngine.getAllGuests(eventid, userid);
        JSONObject root = null;
        JSONArray rootSet = null;
        JSONArray set;
        for (Guest g : guests) {
            if (rootSet == null) {
                rootSet = new JSONArray();
            }
            set = new JSONArray();
            set.put(g.getGuestNo());
            set.put(g.getSeatRow());
            set.put(g.getSeatNo());
            set.put(g.getAwardNo());
            set.put(g.getAward());
            set.put(g.getGuestName());
            set.put(g.getCorp());
            set.put(g.getPosition());
            set.put(g.getStatus());
            rootSet.put(set);
        }
        if (root == null) {
            root = new JSONObject();
        }
        try {
            root.put("data", rootSet);
        } catch (JSONException ex) {
            Logger.getLogger(AndroidPriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }

    public static JSONObject getAllSortedGuestsViaJSON(int eventid, int userid, String sortBy, String sortType) {
        try {
            return new JSONObject().put("guests", PriseEngine.getAllSotedGuests(eventid, userid, sortBy, sortType));
        } catch (JSONException ex) {
            Logger.getLogger(AndroidPriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JSONObject getFilterAllGuestsByStatusViaJSON(int eventid, int userid, int statusId) {
        try {
            return new JSONObject().put("guests", PriseEngine.getAllSotedGuests(eventid, userid, statusId));
        } catch (JSONException ex) {
            Logger.getLogger(AndroidPriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Object getFilterAllGuestsByStatusViaJSON(int eventid, int userid, int statusId, String sortBy, String sortType) {
        try {
            return new JSONObject().put("guests", PriseEngine.getAllSotedWithStatusFilterGuests(eventid, userid, statusId, sortBy, sortType));
        } catch (JSONException ex) {
            Logger.getLogger(AndroidPriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
