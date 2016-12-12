<%-- 
    Document   : view_event
    Created on : 9 มิ.ย. 2559, 0:39:26
    Author     : QuartierLatin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (request.getSession(false).getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise :. View Event .:</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script> 
        <link rel="stylesheet" type="text/css" href="css/custom_bs.css">
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/include_bar.jsp" flush="false" />

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="jumbotron">
                        <h2>Event : ${param.event}</h2>
                        <br>
                        <p>Event's Description : ${param.eventdesc}</p>
                        <br>
                        <a href="insert_guest.jsp?userid=${param.userid}&eventid=${param.eventid}">Insert New One </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="">
                        <table  id="guestTable" style="text-align: center" >
                            <thead>
                                <c:choose>
                                    <c:when test="${guests!=null}">
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
                            </thead>
                        </table>
                    </div>
                </div>
            </div>


            <a href="main.jsp">Back</a>
        </div>

        <script>
            $(document).ready(function () {
                var url = "AndroidView?opt=allguests_for_datatable&userid=" +${param.userid} + "&eventid=" +${param.eventid};
                var table = $('#guestTable').dataTable({
                    stateSave : true,
                    ajax: {
                      
                        'url': url,
                        'type': 'GET',
                        'dataSrc': function (json) {
                            return json.data;
                        }

                    },
                    'columnDefs': [{
                            'targets': -2,
                            'data': null,
                            'defaultContent': "<select name='gstatus' id='gstatus' >\n\
                                        <option value='1'} style='color:green'>Stanby</option>\n\
                                        <option value='2'} style='color:gray'>Unready</option>\n\
                                        <option value='3'} style='color:red'>Absent</option>\n\
                                        <option value='4'} style='color:blue'>Award Recieved</option>\n\
                                        <option value='5'} style='color:gold'>Already Quited</option>\n\
                                    </select>"
                        }, {
                            'targets': -1,
                            'data': null,
                            'defaultContent': '<a href="#" class="edit">Edit<a/>\n\
                    <a href="#" class="del">Delete<a/>'
                        }],
                    'drawCallback': function (setting) {
                        var callBackData = this.api().rows({page: 'current'}).data();
                        $.each(callBackData, function (index, element) {
                            $('#guestTable tr select').eq(index).val(element[8]);
                            var color;
                            switch (element[8]) {
                                case 1 :
                                    color = ['green', '#ccffcc'];
                                    break;
                                case 2 :
                                    color = ['grey', '#e6e6e6'];
                                    break;
                                case 3 :
                                    color = ['red', '#ffcccc'];
                                    break;
                                case 4 :
                                    color = ['blue', '#ccccff'];
                                    break;
                                case 5 :
                                    color = ['gold', '#fff7cc'];
                                    break;
                            }
                            $('#guestTable tr').eq(index + 1).css('background-color', color[1]);
                            $('#guestTable tr').eq(index + 1).children('td').eq(8).css('background-color', color[0]);
                            // Do overwrite status info here.
                        });
                    }
                });
                //http://localhost:8080/Prise/GuestEditor?eventid=2&userid=2&opt=status&guestno=2&gstatus=5

                $('#guestTable tbody').on('change', 'select', function (e) {
                    var data = table.api().row($(this).parents('tr')).data();
                    var status = this.options[e.target.selectedIndex].value;
                    $.ajax({
                        url: "GuestEditor?opt=status&userid=" +${param.userid} + "&eventid=" +${param.eventid} + "&guestno=" + data[0] + "&gstatus=" + status,
                        success: function (data, textStatus, jqXHR) {
                            reload();
                        }});
                    //                    alert(status);
                });
                $('#guestTable tbody').on('click', '.edit', function (e) {
                    var data = table.api().row($(this).parents('tr')).data();
                    $(this).attr('href', 'edit_guest.jsp?userid=${param.userid}&eventid=${param.eventid}&seatrow=' + data[1] + '&seatno=' + data[2] + '&name=' + data[5] + '&corp=' + data[6] + '&position=' + data[7] + '&award=' + data[4] + '&awardno=' + data[3] + '&status=' + data[8] + '&guestno=' + data[0]);
                });
                $('#guestTable tbody').last().on('click', '.del', function (e) {
                    var data = table.api().row($(this).parents('tr')).data();
                    console.log(data);
                    var isUnwanted = confirm("Continue to delete " + data[5] + "");
                    if (isUnwanted) {
                        $(this).attr('href', 'GuestEditor?opt=remove&userid=${param.userid}&eventid=${param.eventid}&guestno=' + data[0]);
                    }
                });
//                 Pls do with internal JSON file 

                setInterval(function () {
                    reload();
                }, 8000);
                function reload() {
                    table.api().ajax.reload(null, false);
                }
            });
        </script>
<!--        <a href="ViewEvent?userid=${param.userid}&eventid=${param.eventid}&opt=v1"><p>View Ver.1</p></a>
        <a href="insert_guest.jsp?userid=${param.userid}&eventid=${param.eventid}">Insert New One</a>-->
    </body>
</html>
