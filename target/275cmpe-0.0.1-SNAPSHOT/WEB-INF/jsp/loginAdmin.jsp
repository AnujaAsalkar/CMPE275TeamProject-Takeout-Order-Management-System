<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- AngularJs library -->
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

    <!-- Loading the AngularJS file from /public/angularJS folder -->
    <script src="angularjs/login.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script>


    <style>

        #header {
            height: 60px;
            margin: 0px
        }

        #secondSection {
            height: 50px;
        }


    </style>

</head>



<body style="background-color:black">

 <header>
    <div id="header" style="background-color:#dc0e28;" align="left" class="container-fluid">


            <p align="center" style="font-weight:bold;color:#000;font-size:40px">Food-To-Go</p>

    </div>

</header>

<div id="secondSection" align="center">
    <img src="https://hawksworthrestaurant.com/wp-content/uploads/2013/06/Hawksworth-Restaurant-Squid-Ceviche-home.jpg" width="100%" height="350">
</div>

 <div class="row container-fluid" style="margin-top:350px">
    <div id="leftSection" class="col-md-4"></div>
      <div name="middleSection" class="col-md-4">
        <h3 style="color: #7ddc27;" align="center">Admin Login</h3>
        <form role="form" method="POST" action="getprofilehtml.html">
            <div class="form-group" style="margin: 10px;align:center">
                <input class="form-control" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group" style="margin: 10px;align:center">
                <input class="form-control" type="password" name="password" placeholder="Password" required>
            </div>
            <input type="submit" class=" form-control"  style="background-color: #459cdc; color:white; font-size: medium;"  value="Login" />
        </form>
    </div>
 <div id="rightSection" class="col-md-4"></div>
</div>

</body>
</html>