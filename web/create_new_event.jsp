<%-- 
    Document   : create_new_event
    Created on : 9 มิ.ย. 2559, 0:38:34
    Author     : QuartierLatin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise :.Creat New Event.:</title>
    </head>
    <body>
        <h1>:.Creat New Event.:</h1>
        <form action="CreateNewEvent?asuser=${user.userId}">
            <fieldset>
                <br>
                <legend>Please fill a details about your event</legend>
                <input name="title" required autofocus="" type="text" placeholder="Event title" />
                <br><br>
                <textarea name="desc" cols="30" rows="5" placeholder="Event description" ></textarea>
                <br><br>
                <input type="submit"/>
                <br><br>
                <c:if test="${message != null}">${message}</c:if>
            </fieldset>
        </form>

    </body>
</html>
