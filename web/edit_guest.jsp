<%-- 
    Document   : edit_guest
    Created on : 14 มิ.ย. 2559, 13:07:05
    Author     : QuartierLatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <h1>Edit Guest!</h1>
                    <form action='GuestEditor?opt=all' method='post'>
                        <input type="hidden" value="${param.userid}" name='userid'/>
                        <input type="hidden" value="${param.eventid}" name='eventid'/>
                        <input type="hidden" value="${param.guestno}" name='guestno'/>
                        <table cellpadding="11" style="text-align: center" class="table table-hover table-striped">
                            <tr>
                                <td>Seat Row:</td>
                                <td><input class="form-control" type="text" name="seatrow" placeholder="Enter Seat Row such as 'A, B, 1, 2, etc'" value="${param.seatrow}" /></td>
                            </tr>
                            <tr>
                                <td>Seat No.:</td>
                                <td><input class="form-control" type="number" name="seatno" placeholder="Enter Seat Number"  value="${param.seatno}"/></td>
                            </tr>
                            <tr>
                                <td>Guest Name:</td>
                                <td><input class="form-control" type="text" name="name" placeholder="Enter Guest Name" required="" value="${param.name}"/></td>
                            </tr>
                            <tr>
                                <td>Corp:</td>
                                <td><input class="form-control" type="text" name="corp" placeholder="Enter Guest Corperation" value="${param.corp}"/></td>
                            </tr>
                            <tr>
                                <td>Posititon</td>
                                <td><input class="form-control" type="text" name="position" placeholder="Enter Guest Position" value="${param.position}"/></td>
                            </tr>
                            <tr>
                                <td>Award</td>
                                <td><input class="form-control" type="text" name="award" placeholder="Enter Guest Award" required="Enter Award" value="${param.award}"/></td>
                            </tr>
                            <tr>
                                <td>Award Progression</td>
                                <td><input class="form-control" type="number" name="awardno" placeholder="Enter Award Progression" required="" value="${param.awardno}"/></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td>
                                    <select name="gstatus" required="" class="form-control">
                                        <option value="1" ${guest.status == 1?'selected':''} style="color:green">Stanby</option>
                                        <option value="2" ${guest.status == 2?'selected':''} style="color:gray">Unready</option>
                                        <option value="3" ${guest.status == 3?'selected':''} style="color:red">Absent</option>
                                        <option value="4" ${guest.status == 4?'selected':''} style="color:blue">Award Recieved</option>
                                        <option value="5" ${guest.status == 5?'selected':''} style="color:gold">Already Quited</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" class="btn btn-primary"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                 
            });
        </script>
    </body>
</html>
