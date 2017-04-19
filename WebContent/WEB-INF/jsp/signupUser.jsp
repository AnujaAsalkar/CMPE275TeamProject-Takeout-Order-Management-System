<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- AngularJs library -->
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

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
        <p align="center" style="font-weight:bold;color:#000;font-size:40px">Online Restro</p>


    </div>

    </div>

</header>

<div id="secondSection" align="center">
    <img src="https://hawksworthrestaurant.com/wp-content/uploads/2013/06/Hawksworth-Restaurant-Squid-Ceviche-home.jpg" width="100%" height="400">
</div>


<div class="row container-fluid" style="margin-top:350px">

    <div id="leftSection" class="col-md-4"></div>

    <div name="middleSection" class="col-md-4">

        <h3 style="color: #7ddc27;" align="center">Sign Up</h3>
        
        <form name="signUpForm" id="signUpForm" role="form" method="POST" action="signup.html">


            <div class="form-group" align="center">
                <input class="form-control" type="email" name="email" ng-model="signUp.email" size=20 required placeholder="Email" required>
            </div>
            <div class="form-group" style="margin-bottom:10px;" >
                <input style="width:50%;" class="form-control col-md-6" type="text" name="first_name"  required title="This field is mandatory" placeholder="First Name">
                <input style="width:50%;" class="form-control col-md-6" type="text" name="last_name"  required title="This field is mandatory" placeholder="Last Name">
            </div>
            &nbsp;
            <div class="form-group">
                <input class="form-control" type="password" name="password" ng-model="signUp.password" size=16 required  autocomplete="off" title="This field is mandatory" placeholder="Password" required>
            </div>

            <div class="form-group">
                <input class="form-control" type="phone" name="phone_no" ng-model="signUp.phone" size=16 required  autocomplete="off" placeholder="Phone">
            </div>

            <input type="submit" class=" form-control" style="background-color: #459cdc; color:white; font-size: medium;"  value="Send Verification Code" />

    </form>
</div>

<div id="rightSection" class="col-md-4"></div>

</div>

</body>
</html>