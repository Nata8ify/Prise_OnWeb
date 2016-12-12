/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.model;

import com.prise.utils.EncryptTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author QuartierLatin
 */
public class PriseEngine {

    protected static final int TOP_LEVEL_USER_PRIVILAGE = 1;

//    User Account Handler
    public static User login(String username, String password) {
        try {
            User user;
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM prise.user WHERE user_username = ? AND user_password = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1, username);
            pstm.setString(2, EncryptTo.md5(password));
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                user = new User();
                setUser(rs, user);
                conn.close();
                return user;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public static boolean createUser(String username, String password, String name, String email) {
        if (!isExistedUsername(username)) {
            try {
                Connection conn = BuildConnection.getConnection();
                String sqlCmd = "INSERT INTO `prise`.`user` (`user_username`, `user_password`, `user_name`, `user_privilage`, `user_email`) VALUES (?, ?, ?, ?, ?);";
                PreparedStatement pstm = conn.prepareStatement(sqlCmd);
                pstm.setString(1, username);
                pstm.setString(2, EncryptTo.md5(password));
                pstm.setString(3, name);
                pstm.setInt(4, TOP_LEVEL_USER_PRIVILAGE);
                pstm.setString(5, email);
                if (pstm.executeUpdate() == 1) {
                    conn.close();
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteEvent(int userId, int eventId) {

        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "DELETE FROM prise.event WHERE event_id = ? AND event_byuserid = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, eventId);
            pstm.setInt(2, userId);
            if (pstm.executeUpdate() == 1) {
                sqlCmd = "DELETE FROM prise.guest_info WHERE ginfo_eventid = ? AND ginfo_userid = ?;";
                pstm = conn.prepareStatement(sqlCmd);
                pstm.setInt(1, eventId);
                pstm.setInt(2, userId);
                if (pstm.executeUpdate() == 1) {
                    conn.close();
                    return true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean isExistedUsername(String username) {
        System.out.println(username);
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT user_username FROM prise.`user` WHERE user_username = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            if (rs.first()) {
                System.out.println(rs.getString("user_username"));
                conn.close();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Query Setter 
    private static void setUser(ResultSet rs, User user) {
        try {
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("user_username"));
            user.setPassword(rs.getString("user_password"));
            user.setName(rs.getString("user_name"));
            user.setPrivilage(rs.getInt("user_privilage"));
            user.setEmail(rs.getString("user_email"));
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setEvent(ResultSet rs, Event event) {
        try {
            event.setEventId(rs.getInt("event_id"));
            event.setUserId(rs.getInt("event_byuserid"));
            event.setEvent(rs.getString("event_name"));
            event.setDescription(rs.getString("event_description"));
        } catch (SQLException sqlEx) {
            return;
        }
    }

    private static void setGuest(ResultSet rs, Guest guest) {
        try {
            guest.setGuestNo(rs.getInt("ginfo_no"));
            guest.setSeatRow(rs.getString("ginfo_seatrow"));
            guest.setSeatNo(rs.getString("ginfo_seatno"));
            guest.setAward(rs.getString("ginfo_award"));
            guest.setAwardNo(rs.getInt("ginfo_awardno"));
            guest.setGuestName(rs.getString("ginfo_guestname"));
            guest.setCorp(rs.getString("ginfo_corp"));
            guest.setPosition(rs.getString("ginfo_position"));
            guest.setStatus(rs.getInt("ginfo_status"));
            guest.setUserId(rs.getInt("ginfo_userid"));
            guest.setEventId(rs.getInt("ginfo_eventid"));
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Event Handler
    public static boolean createEvent(int userId, String title, String description) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "INSERT INTO `prise`.`event` "
                    + "(`event_id`, `event_byuserid`, `event_name`, `event_description`) VALUES "
                    + "(?, ?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, incrementEventId(userId));
            pstm.setInt(2, userId);
            pstm.setString(3, title);
            pstm.setString(4, description);
            if (pstm.executeUpdate() == 1) {
                conn.close();
                return true;
            }
        } catch (SQLException sqlEx) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, sqlEx);
        }
        return false;
    }

    public static ArrayList<Event> getEventsByUserId(int userId) {
        Event event;
        ArrayList<Event> events = null;
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM `prise`.`event` WHERE event_byuserid = ? ORDER BY `event_id` ASC;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                event = new Event();
                if (events == null) {
                    events = new ArrayList<Event>();
                }
                setEvent(rs, event);
                events.add(event);
            }
            conn.close();
            return events;
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.toString());
        }
        return null;
    }

    public static ArrayList<Event> getEventsByUserIdWithShared(int userId) {
        ArrayList<Event> events = getEventsByUserId(userId);
        ArrayList<Event> eventsShared = getSharingEvents(userId);
        if (events != null) {
            if (eventsShared != null) {
                events.addAll(eventsShared);
            }
        } else {
            events = eventsShared;
        }
        return events;
    }

    private static int incrementEventId(int userId) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT MAX(event_id) AS max_event_id FROM `prise`.`event` WHERE event_byuserid = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int incrementId = rs.getInt("max_event_id") + 1;
            System.out.println("incrementId: " + incrementId);
            return incrementId;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static Event getEventByUserId(int userid, int eventid) {
        Event event = null;
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM `prise`.`event` WHERE event_byuserid = ? AND event_id = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, userid);
            pstm.setInt(2, eventid);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                event = new Event();
                setEvent(rs, event);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

        return event;
    }

    public static boolean editEvent(int eventId, int userId, String eventName, String eventDesc) {
        Connection conn;
        try {
            conn = BuildConnection.getConnection();
            String sqlCmd = "UPDATE `prise`.`event` SET event_name = ?, event_description = ? WHERE event_byuserid = ? AND event_id = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1, eventName);
            pstm.setString(2, eventDesc);
            pstm.setInt(3, userId);
            pstm.setInt(4, eventId);
            int rowChanged = pstm.executeUpdate();
            if (rowChanged != 0) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Guest Handler
    public static ArrayList<Guest> getAllGuests(int eventid, int userid) {
        ArrayList<Guest> guests = null;
        Guest guest;
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM prise.`guest_info` WHERE ginfo_userid = ? AND ginfo_eventid = ? ORDER BY ginfo_awardno ASC;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, userid);
            pstm.setInt(2, eventid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                guest = new Guest();
                if (guests == null) {
                    guests = new ArrayList<Guest>();
                }
                guest = new Guest();
                setGuest(rs, guest);
                guests.add(guest);
            }
            conn.close();
            return guests;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean insertGuest(String seatRow, String seatNo, String guestName, String guestCorp, String guestPosition, String award, int awardNo, int status, int userId, int eventId) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "INSERT INTO `prise`.`guest_info` "
                    + "(`ginfo_no`, `ginfo_seatno`, `ginfo_seatrow`, `ginfo_award`, `ginfo_awardno`, `ginfo_guestname`, `ginfo_corp`, `ginfo_position`, `ginfo_status`, `ginfo_userid`, `ginfo_eventid`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, latestGuestId(userId, eventId) + 1);
            pstm.setString(2, seatNo);
            pstm.setString(3, seatRow);
            pstm.setString(4, award);
            pstm.setInt(5, awardNo);
            pstm.setString(6, guestName);
            pstm.setString(7, guestCorp);
            pstm.setString(8, guestPosition);
            pstm.setInt(9, status);
            pstm.setInt(10, userId);
            pstm.setInt(11, eventId);
            if (pstm.executeUpdate() == 1) {
                conn.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static int latestGuestId(int userId, int eventId) throws SQLException {
        Connection conn = BuildConnection.getConnection();
        String sqlCmd = "SELECT MAX(`ginfo_no`) AS latest FROM `prise`.`guest_info` WHERE  `ginfo_userid` = ? AND `ginfo_eventid` = ?;";
        PreparedStatement pstm = conn.prepareStatement(sqlCmd);
        pstm.setInt(1, userId);
        pstm.setInt(2, eventId);
        ResultSet rs = pstm.executeQuery();
        rs.first();
        int latest = rs.getInt("latest");
        conn.close();
        return latest;
    }

    public static void chageGuestStatus(int status, int userId, int eventId, int guestNo) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "UPDATE prise.guest_info SET ginfo_status = ? WHERE ginfo_userid = ? AND ginfo_eventid = ? AND ginfo_no = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, status);
            pstm.setInt(2, userId);
            pstm.setInt(3, eventId);
            pstm.setInt(4, guestNo);
            pstm.executeUpdate();
            conn.close();
            System.out.println("NO EXCEPTION");
            return;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean editGuestInfo(String seatRow, String seatNo, String award, int awardNo, String guestName, String guestCorp, String guestPosition, int status, int userId, int eventId, int guestNo) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "UPDATE prise.guest_info "
                    + "SET ginfo_seatno=?, ginfo_seatrow=?, ginfo_award=?, ginfo_awardno=?, ginfo_guestname=?,"
                    + " ginfo_corp=?, ginfo_position=?, ginfo_status = ? "
                    + "WHERE ginfo_userid = ? AND ginfo_eventid = ? AND ginfo_no = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(11, guestNo);
            pstm.setString(1, seatNo);
            pstm.setString(2, seatRow);
            pstm.setString(3, award);
            pstm.setInt(4, awardNo);
            pstm.setString(5, guestName);
            pstm.setString(6, guestCorp);
            pstm.setString(7, guestPosition);
            pstm.setInt(8, status);
            pstm.setInt(9, userId);
            pstm.setInt(10, eventId);
            if (pstm.executeUpdate() == 1) {
                conn.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void deleteGuest(int userId, int eventId, int guestNo) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "DELETE FROM prise.guest_info WHERE ginfo_userid = ? AND ginfo_eventid = ? AND ginfo_no = ?";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, userId);
            pstm.setInt(2, eventId);
            pstm.setInt(3, guestNo);
            pstm.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static ArrayList<Guest> getAllSotedGuests(int eventid, int userid, String sortBy, String sortType) {
//        http://52.221.255.26:8080/Prise/AndroidView?opt=allguests&sortby=ginfo_awardno&userid=2&eventid=2&sorttype=
        try {
            ArrayList<Guest> guests = null;
            Guest guest = null;
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = null;
            PreparedStatement pstm = null;
            if (!sortBy.equals("seat")) {
                sqlCmd = "SELECT * FROM prise.guest_info WHERE ginfo_userid = ? AND ginfo_eventid = ? ORDER BY " + sortBy + " " + sortType + ";";
//            System.out.println("SELECT * FROM prise.guest_info WHERE ginfo_userid = " + userid + " AND ginfo_eventid = " + eventid + " ORDER BY " + sortBy + " " + sortType + ";");
                pstm = conn.prepareStatement(sqlCmd);
//                pstm.setInt(1, userid);
//                pstm.setInt(2, eventid);
            } else {
                sqlCmd = "SELECT * FROM prise.guest_info WHERE ginfo_userid = ? AND ginfo_eventid = ? ORDER BY ginfo_seatrow " + sortType + ", CAST(ginfo_seatno AS SIGNED) " + sortType + ";";
//            System.out.println("SELECT * FROM prise.guest_info WHERE ginfo_userid = " + userid + " AND ginfo_eventid = " + eventid + " ORDER BY " + sortBy + " " + sortType + ";");
                pstm = conn.prepareStatement(sqlCmd);
//                pstm.setInt(1, userid);
//                pstm.setInt(2, eventid);
            }
            pstm.setInt(1, userid);
            pstm.setInt(2, eventid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                if (guests == null) {
                    guests = new ArrayList<Guest>();
                }
                guest = new Guest();
                setGuest(rs, guest);
                guests.add(guest);
            }

            return guests;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Guest> getAllSotedGuests(int eventid, int userid, int statusId) {
        ArrayList<Guest> guests = null;
        Guest guest;
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM prise.guest_info WHERE ginfo_userid = ? AND ginfo_eventid = ? AND ginfo_status = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, userid);
            pstm.setInt(2, eventid);
            pstm.setInt(3, statusId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                guest = new Guest();
                if (guests == null) {
                    guests = new ArrayList<Guest>();
                }
                setGuest(rs, guest);
                guests.add(guest);
            }
            conn.close();
            return guests;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Guest> getAllSotedWithStatusFilterGuests(int eventid, int userid, int statusId, String sortBy, String sortType) {
        ArrayList<Guest> guests = null;
        Guest guest;
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = null;
            PreparedStatement pstm = null;
            if (!sortBy.equals("seat")) {
                System.out.println("this" + sortBy + "::" + sortType);
                sqlCmd = "SELECT * FROM prise.guest_info WHERE ginfo_userid = ? AND ginfo_eventid = ? AND ginfo_status = ? ORDER BY " + decodeSortById(sortBy) + " " + sortType + ";";
                pstm = conn.prepareStatement(sqlCmd);
            } else {
                sqlCmd = "SELECT * FROM prise.guest_info WHERE ginfo_userid = ? AND ginfo_eventid = ? AND ginfo_status = ? ORDER BY ginfo_seatrow " + sortType + ", CAST(ginfo_seatno AS SIGNED) " + sortType + ";";
            }
            pstm.setInt(1, userid);
            pstm.setInt(2, eventid);
            pstm.setInt(3, statusId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                guest = new Guest();
                if (guests == null) {
                    guests = new ArrayList<Guest>();
                }
                setGuest(rs, guest);
                guests.add(guest);
            }
            conn.close();
            return guests;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String decodeSortById(String sortById) {
        switch (sortById) {
            case "1":
                return "ginfo_no";
            case "2":
                return null; // Sorting by Seat Property.
            case "3":
                return "ginfo_awardno";
            case "4":
                return "ginfo_award";
            case "5":
                return "ginfo_guestname";
            case "6":
                return "ginfo_corp";
            case "7":
                return "ginfo_position";
            case "8":
                return ""; // TODO Next case.

        }
        return null;
    }

    // Sharing Module 
    public static boolean shareEventToUser(int byUserId, String username, int eventId) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "INSERT INTO prise.sharing_event (sharing_event_eventid, sharing_event_byuserid, sharing_event_touserid) "
                    + "VALUES (?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, eventId);
            pstm.setInt(2, byUserId);
            int userId = PriseEngine.findIdByUsername(username);
            if (userId == -1) {
                throw new SQLException("User ID is -1.");
            }
            pstm.setInt(3, userId);
            if (pstm.executeUpdate() != 0) {
                conn.close();
                return true;
            } else {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String shareEventToUsers(int byUserId, String usernames, int eventId) {
        String operationMsg;
        boolean isCompletelySharing = true;
        try {
            operationMsg = "";
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "INSERT INTO prise.sharing_event (sharing_event_eventid, sharing_event_byuserid, sharing_event_touserid) "
                    + "VALUES (?, ?, ?);";
            PreparedStatement pstm;
            StringTokenizer usersTokenize = new StringTokenizer(usernames, " ");
            while (usersTokenize.hasMoreTokens()) {
                pstm = conn.prepareStatement(sqlCmd);
                pstm.setInt(1, eventId);
                pstm.setInt(2, byUserId);
                String username = usersTokenize.nextToken();
                int userId = PriseEngine.findIdByUsername(username);
                if (userId == -1) {
                    isCompletelySharing = false;
                    operationMsg += username + " ";
                    continue;
                }
                pstm.setInt(3, userId);
                if (pstm.executeUpdate() != 0) {
                    continue;
                } else {
                    isCompletelySharing = false;
                    continue;
                }
            }
            conn.close();
            if (isCompletelySharing) {
                return "Sharing is Sucessfuly.";
            } else {
                return "Can't share this event to these users... [" + operationMsg + "]";
            }

        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Sharing Failed. It's seem someone of these already shared with.";
    }

    public static int findIdByUsername(String username) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT user_id FROM prise.user WHERE user_username = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int userId = rs.getInt(1);
            conn.close();
            return userId;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static ArrayList<Event> getSharingEvents(int toUserId) {
        ArrayList<Event> events = null;
        Event event;
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT e.* FROM prise.event e JOIN prise.sharing_event s "
                    + "ON e.event_id = s.sharing_event_eventid  "
                    + "AND e.event_byuserid = s.sharing_event_byuserid "
                    + "WHERE s.sharing_event_touserid = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, toUserId);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                event = new Event();
                if (events == null) {
                    events = new ArrayList<Event>();
                }
                setEvent(rs, event);
                events.add(event);
            }
            conn.close();
            return events;
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // Sharing Module 

    public static boolean isSharedToThisUser(int eventId, int byUserId, int toUserId) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM prise.sharing_event "
                    + "WHERE sharing_event_eventid = ?"
                    + " AND sharing_event_byuserid = ?"
                    + " AND sharing_event_touserid = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, eventId);
            pstm.setInt(2, byUserId);
            pstm.setInt(3, toUserId);
            if (pstm.executeQuery().next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PriseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
