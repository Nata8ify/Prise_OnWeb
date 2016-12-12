<%-- 
    Document   : insert_guest
    Created on : 10 มิ.ย. 2559, 13:14:43
    Author     : QuartierLatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise :. Insert Guest .:</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/include_bar.jsp" flush="false" />
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1>Insert Guest!</h1>
                    <p style="color:red">${param.message}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <form action='InsertNewGuest' method='post'><br/><br/>
                        <input type="hidden" value="${param.userid}" name='userid'/>
                        <input type="hidden" value="${param.eventid}" name='eventid'/>
                        <table cellpadding="11" style="text-align: center" class="table table-hover table-striped">
                            <tr>
                                <td>Seat Row:</td>
                                <td><input class="form-control" type="text" name="seatrow" placeholder="Enter Seat Row such as 'A, B, 1, 2, etc'" /></td>
                            </tr>
                            <tr>
                                <td>Seat No.:</td>
                                <td><input class="form-control" type="number" name="seatno" placeholder="Enter Seat Number" /></td>
                            </tr>
                            <tr>
                                <td>Guest Name:</td>
                                <td><input class="form-control" type="text" name="name" placeholder="Enter Guest Name" required=""/></td>
                            </tr>
                            <tr>
                                <td>Corp:</td>
                                <td><input class="form-control" type="text" name="corp" placeholder="Enter Guest Corperation" /></td>
                            </tr>
                            <tr>
                                <td>Posititon</td>
                                <td><input class="form-control" type="text" name="position" placeholder="Enter Guest Position" /></td>
                            </tr>
                            <tr>
                                <td>Award</td>
                                <td><input class="form-control" type="text" name="award" placeholder="Enter Guest Award" required="Enter Award"/></td>
                            </tr>
                            <tr>
                                <td>Award Progression</td>
                                <td><input class="form-control" type="number" name="awardno" placeholder="Enter Award Progression" required=""/></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td>
                                    <select class="form-control" name="status" required="">
                                        <option value="1" style="background-color: green;color: white">Stanby</option>
                                        <option value="2" style="background-color: gray;color: white">Unready</option>
                                        <option value="3" style="background-color:red;color: white">Absent</option>
                                        <option value="4" style="background-color: blue;color: white">Award Recieved</option>
                                        <option value="5" style="background-color: gold;color: white">Already Quited</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><button class="btn btn-default"><a href="javascript:history.back();">Back</a></button></td>
                                <td><input class="btn btn-danger" type="reset"/>&nbsp; &nbsp;<input class="btn btn-primary" type="submit"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
