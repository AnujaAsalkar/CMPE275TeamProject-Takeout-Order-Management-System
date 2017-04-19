<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Verify Code</title>
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

<body style="background-color:black" >

<header>
    <div id="header" style="background-color:#dc0e28;" align="left" class="container-fluid">
        <nav>
            <p align="center" style="font-weight:bold;color:#000;font-size:40px">Food-To-Go</p>
            </nav>
    </div>
</header>

<!--  **************************  image *********************************  -->

<div id="secondSection" align="center">
    <img src="https://hawksworthrestaurant.com/wp-content/uploads/2013/06/Hawksworth-Restaurant-Squid-Ceviche-home.jpg" width="100%" height="350">
</div>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<!-- *************** Verification div **************************  -->
<div style="margin-top:350px;align:center">
    <h3 style="color: #7ddc27;" align="center">Verify Code</h3>
	 <form role="form" action="signup2.html" method="POST">
    <div align="center">

               
                <div  style="margin: 10px;align:center">
                    <input style="margin-top:30px" type="number"  id="inputVerificationCode" name="verification_code" required title="This field is mandatory" placeholder="Enter Code here">
                </div>

    </div>
    <div align="center" style="margin-top: 50px">
        
            <button type="submit" class="btn btn-primary">Sign Up</button>
        
    </div>
	</form>
</div>



</body>
</html>