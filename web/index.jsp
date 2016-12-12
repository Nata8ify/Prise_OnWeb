<%-- 
    Document   : index
    Created on : 9 มิ.ย. 2559, 15:20:14
    Author     : QuartierLatin
--%>
<% if (request.getSession(false).getAttribute("user") != null) {
        response.sendRedirect("main.jsp");
    } else {%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise Login</title>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/input_style_viaSO.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body><br><br><br>
        <div class="container">
            <center><div style="letter-spacing:2px;font-size: 36px "><p style="size: 36px">.: Login :.</p></div><p style="${ok==null?'color: red':'color: green'}">${message}</p></center><br><br>
            
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4 well">
                    
                    <form action="Login?opt=web" method="post" role="form">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <div class="inner-addon left-addon">
                                <input type="text" required name="username" placeholder="Username" autofocus class="form-control"/>
                                <i class="glyphicon glyphicon-user "></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <div class="inner-addon left-addon">
                                <input type="password" required name="password" placeholder="Password" class="form-control"/>
                                <i class="glyphicon glyphicon-lock "></i>
                            </div>
                        </div>
                        <button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-log-in"></i>&nbsp;&nbsp;Login</button>

                        &nbsp;&nbsp;<a href="Seem?to=signUp">Create Accout</a>
                    </form>

                </div>
                <div class="col-sm-4"></div>
            </div>
        </div>

        
    </body>
</html>
<%}%>