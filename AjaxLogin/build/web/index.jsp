<%-- 
    Document   : index.jsp
    Created on : 10/11/2015, 03:52:44 PM
    Author     : JoanVasquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/main.css">
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-md-12 login">
                    <form class="form-inline" id="login-form">
                        <div class="form-group">
                            <label for="email">Name</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Insert your email"><br>
                            <span class="error"></span>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="pass" placeholder="Insert your password"><br>
                            <span class="error"></span>
                        </div>
                        <button type="submit" class="btn btn-primary">Send</button>
                    </form>
                    <span class="error"></span>
                </div>
            </div>
        </div>

        <script src="resources/js/jquery.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/main.js"></script>
    </body>
</html>
