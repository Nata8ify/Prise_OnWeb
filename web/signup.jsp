<<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise .: Create new User :.</title>
        
        <link rel="stylesheet" href="css/input_style_viaSO.css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <br><br><br>
        <div class="container">
            <center><div style="letter-spacing:2px;font-size: 36px "><p style="size: 36px">.: Create New Account :.</p></div></center><br><br>
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6 well">
                    <form action="Signup" method="post" role="form" id="sigeup_form">
                        <div class="form-group">
                            <label for="username"><i id="red_aster">*</i> Username</label>
                            <div class="inner-addon left-addon">
                                <input type="text" required name="username" value="${username}" placeholder="Username" autofocus class="form-control"/>
                                <i class="glyphicon glyphicon-user "></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password"><i id="red_aster">*</i> Password</label>
                            <div class="inner-addon left-addon">
                                <input id="password" type="password" required name="password" placeholder="Password" class="form-control"/>
                                <i class="glyphicon glyphicon-lock "></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passwordConfirmed"><i id="red_aster">*</i> Confirmed Password&nbsp;&nbsp;<b id="confirmed_pwd_msg"></b></label>
                            <div class="inner-addon left-addon">
                                <input id="confirmed_password" type="password" required  placeholder="Confirmed Password" class="form-control"/>
                                <i class="glyphicon glyphicon-lock "></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name"><i id="red_aster">*</i> First Name and Last Name</label>
                            <div class="inner-addon left-addon">
                                <input type="text" required name="name" placeholder="First-Name & Last-Name" class="form-control"/>
                                <i class="glyphicon glyphicon-info-sign"></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email"><i id="red_aster">*</i> E-mail Address</label>
                            <div class="inner-addon left-addon">
                                <input type="email" required name="email" placeholder="E-mail Address" class="form-control"/>
                                <i class="glyphicon glyphicon-envelope"></i>
                            </div>
                        </div>
                                <button  id="btn_submit" class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-log-in"></i>&nbsp;&nbsp;Create new Account</button>
                        <button class="btn btn-danger" type="reset">Reset</button>
                        <a href="javascript:history.back()"><button class="btn btn-default" type="button" style="position: absolute; right: 20px"><i class="glyphicon glyphicon-home" ></i>&nbsp;&nbsp;Home</button></a>
                    </form>

                </div>
                <div class="col-sm-3"></div>
            </div>
        </div>
        <script>
            $("document").ready(function(){
                $("#confirmed_password, #password").keyup(function(){
                    console.log($("#confirmed_password").val() === $("#password").val());
                    if($("#password").val() === $("#confirmed_password").val()){
                        $("#confirmed_pwd_msg").html("MATCHED");
                        $("#confirmed_pwd_msg").css("color","green");
                        $("#btn_submit").prop("disabled",false);
                    } else {
                        $("#confirmed_pwd_msg").html("NOT MATCH");
                        $("#confirmed_pwd_msg").css("color","red");
                        $("#btn_submit").prop("disabled",true);
                    }
                });
            });
            
        </script>
    </body>
</html>
