<%-- 
    Document   : main
    Created on : 7 มิ.ย. 2559, 15:30:59
    Author     : QuartierLatin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.prise.model.Event"%>
<%@page import="com.prise.model.Event"%>
<%@page import="com.prise.model.User"%>
<%@page import="com.prise.model.PriseEngine"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  User user = ((User) request.getSession(false).getAttribute("user"));
    if (user != null) {
        ArrayList<Event> events = PriseEngine.getEventsByUserIdWithShared(user.getUserId());
        pageContext.setAttribute("events", events);
        pageContext.setAttribute("user", user);
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Prise! </title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-tokenfield.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="js/bootstrap-tokenfield.js"></script>
        <style>
            a:hover{
                text-decoration: none;
            }
            .tiny-padding {
                padding-right: 15px;
                padding-left: 15px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/include_bar.jsp" flush="false" />
        <div class="container-fluid">
            <h1 color="${colorShrMsg}">${messageShr}</h1>
            <div class="row">
                <div class="col-sm-12">
                    <div style='padding-bottom:  10px'>
                        <button data-target="#createEventModal" data-toggle="modal" class='btn btn-default'>
                            <i class="glyphicon glyphicon-plus" style="margin-right: 1%"></i>
                            Create new event</button>

                        <!--                        <a href="Seem?to=eventSch"><button data-target="#" data-toggle="" class='btn btn-default'>
                                                        <i class="glyphicon glyphicon-calendar" style="margin-right: 1%"></i>
                                                        Schedule</button></a>-->
                        <!--</a>-->
                    </div>
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Event</th>
                                <th>Description</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${events != null}" >
                                    <c:forEach items="${events}" var="e" varStatus="number">
                                        <tr>
                                            <th>${number.count}</th>
                                            <td>${e.event}</td>
                                            <td>${e.description}</td>
                                            <td>
                                                <a href="ViewEvent?eventid=${e.eventId}&userid=${e.userId}&event=${e.event}&eventdesc=${e.description}">
                                                    <button class="btn btn-primary tiny-padding" data-target><i class="glyphicon glyphicon-search" style=""></i></button>
                                                </a>

                                                <!--btn Edit-->
                                                <button type="button" class="btn btn-default btn-to-edit tiny-padding" userId='${e.userId}' eventId='${e.eventId}' event='${e.event}' eventDesc='${e.description}' data-toggle='modal'  > <i class ="glyphicon glyphicon-pencil" style=""></i></button>

                                                <!--btn Sharing-->
                                                <button type="button" class="btn btn-default btn-to-share" userId='${e.userId}' eventId='${e.eventId}' event='${e.event}' eventDesc='${e.description}' data-toggle='modal' > <i class ="glyphicon glyphicon-share" style=""></i></button>

                                                <!--btn Delete-->
                                                <button class="btn btn-danger" onclick='delE(${e.eventId}, ${user.userId})'><i class="glyphicon glyphicon-trash"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="3">Event is empty. For create new event select "Create New Event"</td>
                                        <!--<td colspan="3"><a href="create_new_event.jsp">Create new event</a></td>-->
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <p>${request.message}</p>
        <jsp:include page="WEB-INF/jsp/create_event_modal.jsp" flush="true"/>
        <jsp:include page="WEB-INF/jsp/edit_event_modal.jsp" flush="true"/>
        <jsp:include page="WEB-INF/jsp/share_event_modal.jsp" flush="true"/>
        <script type="text/javascript" src="js/lookup.js"></script>
    </body>
</html>
