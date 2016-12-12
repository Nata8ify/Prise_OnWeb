<%-- 
    Document   : view_event
    Created on : 9 มิ.ย. 2559, 0:39:26
    Author     : QuartierLatin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (request.getSession(false).getAttribute("user") == null | request.getParameter("eventid") == null) {
        response.sendRedirect("index.jsp");
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise :. View Event .:</title>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
        <style>
            #status_bg{
                background-color: ${guest.status == 1?'green':''}${guest.status == 2?'gray':''}${guest.status == 3?'red':''}${guest.status == 4?'blue':''}${guest.status == 5?'gold':''} ;
            }
            #bg_getLightGreen{
                background-color:#ccffcc;
            }
            #bg_getLightGrey{
                background-color:#e6e6e6;
            }
            #bg_getLightRed{
                background-color:#ffcccc;
            }
            #bg_getLightBlue{
                background-color:#ccccff;
            }
            #bg_getLightGold{
                background-color:#fff7cc;
            }
        </style>
    </head>
    <body id="sim">
        <jsp:include page="WEB-INF/jsp/include_bar.jsp" flush="false" />
        <h1 >Event</h1>
        <br>
        <button onclick="doLoad()">Refresh</button>
        <br>
        <a href="insert_guest.jsp?userid=${param.userid}&eventid=${param.eventid}">Insert New One</a>
        <table  id="guestTable" style="text-align: center">
            <thead>
                <tr>
                    <th>Guest No</th>
                    <th>Seat Row</th>
                    <th>Seat No</th>
                    <th>Award Progression</th>
                    <th>Award</th>
                    <th>Name</th>
                    <th>Corp</th>
                    <th>Position</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${guests!=null}">
                        <c:forEach items="${guests}" var="guest">
                            <tr>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.guestNo}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.seatRow != ""?guest.seatRow:"-"}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.seatNo != ""?guest.seatNo:"-"}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.awardNo}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.award}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.guestName}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.corp != ""?guest.corp:"-"}</td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">${guest.position != ""?guest.position:"-"}</td>
                                <td style="background-color: ${guest.status == 1?'green':''}${guest.status == 2?'gray':''}${guest.status == 3?'red':''}${guest.status == 4?'blue':''}${guest.status == 5?'gold':''};">
                                    <form action="GuestEditor">
                                        <input  type="hidden" name="opt" value="status" />
                                        <input  type="hidden" name="userid" value="${param.userid}" />
                                        <input  type="hidden" name="eventid" value="${param.eventid}" />
                                        <input  type="hidden" name="guestno" value="${guest.guestNo}" />
                                        <select name="gstatus" onchange="this.form.submit()">
                                            <option value="1" ${guest.status == 1?'selected':''} style="color:green">Stanby</option>
                                            <option value="2" ${guest.status == 2?'selected':''} style="color:gray">Unready</option>
                                            <option value="3" ${guest.status == 3?'selected':''} style="color:red">Absent</option>
                                            <option value="4" ${guest.status == 4?'selected':''} style="color:blue">Award Recieved</option>
                                            <option value="5" ${guest.status == 5?'selected':''} style="color:gold">Already Quited</option>
                                        </select>
                                    </form>
                                </td>
                                <td id="${guest.status == 1?'bg_getLightGreen':''}${guest.status == 2?'bg_getLightGrey':''}${guest.status == 3?'bg_getLightRed':''}${guest.status == 4?'bg_getLightBlue':''}${guest.status == 5?'bg_getLightGold':''}">
                                    <a href="edit_guest.jsp?userid=${param.userid}&eventid=${param.eventid}&seatrow=${guest.seatRow}&seatno=${guest.seatNo}&name=${guest.guestName}&corp=${guest.corp}&position=${guest.position}&award=${guest.award}&awardno=${guest.awardNo}&status=${guest.status}&guestno=${guest.guestNo}">Edit</a>
                                    <a href="GuestEditor?opt=remove&userid=${param.userid}&eventid=${param.eventid}&guestno=${guest.guestNo}">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="9">It same there are no guest in this event</td>
                        </tr>
                        <tr> 
                            <td colspan="9"><a href="insert_guest.jsp?userid=${param.userid}&eventid=${param.eventid}">Insert New One</a></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <a href="main.jsp">Back</a>
        <script>
            $(document).ready(function () {
                $('#guestTable').dataTable();
            });

            function doLoad() {
                var xhhtp = new XMLHttpRequest();
                xhhtp.onreadystatechange = function () {
                    if (xhhtp.readyState == 4 && xhhtp.status == 200) {
                        document.getElementById("sim").innerHTML = xhhtp.responseText
                    }

                };
                xhhtp.open("GET", "DoRefresh?eventid=${param.eventid}&userid=${param.userid}", true);
                xhhtp.send();
            }
        </script>
    </body>
</html>
