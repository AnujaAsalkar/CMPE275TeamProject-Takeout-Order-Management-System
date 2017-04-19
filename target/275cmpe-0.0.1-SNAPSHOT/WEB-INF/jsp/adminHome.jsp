<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminHome</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        #header {
            height: 60px;
            margin: 0px
        }
        #secondSection {
            height: 50px;
        }
        div.image{
            position: relative;
            left:400px;
            width: 50%;
            height:50px
        }
        body{
            background-color:black;
            color:white;
        }
        select{
            margin-top: 7px;
            width: 150px;
            height: 30px;
        }

    </style>

</head>
<body style="background-color:black">
<header>
    <div id="header" style="background-color:#dc0e28;" align="left" class="container-fluid">
        <nav>
            <p align="center" style="font-weight:bold;color:#000;font-size:40px">Food-To-Go</p>
            <div align="left" class="container-fluid" >

            <nav class="navbar navbar-inverse" style="font-weight:bold;font-size:20px">

                    <ul class="nav navbar-nav" >
                        <li class="active"><a href="welcome">Home</a></li>
                        <li><a href="#one">Add Item</a></li>
                        <li><a href="#two">Remove Item</a></li>
                        <li><a href="#three">Activate Item</a></li>
                        <li><a href="#four">Edit Item</a></li>
				     <li><a href="#five">History</a></li>
				     <li><a href="#six">Popularity Report</a></li>
                        <li><a href="http://54.172.21.45:8080/275cmpe/">Logout</a></li>
                    </ul>

            </nav>

        </div>

        </nav>
    </div>
</header>	

<!-- <div id="container" align="center" style="margin-top: 125px">

    <div ><h1>Welcome to the Admin Page!</h1></div>

</div> -->


     <br>
    <br>
    <br>
    <br>
                 
<div  style="margin-top:50px; margin-left:50px" >
    <h1> <font size="5" color="white">Add Menu Item</font></h1>

    <form role="form" method="POST" action="addMenu.html" enctype="multipart/form-data" id="one">
        <div class="form-group">
            <table>
                <tr>
                    <td>Name: <input class="form-control" type="text" name="itemName" required></td>

                </tr>
                <tr>
                    <td>Price: <input class="form-control" type="text" name="price" required></td>

                </tr>
                <tr>
                    <td>Category:
                    	<select class="form-control" name="category" >
                        <option value="Appetizer">Appetizer</option>
                        <option value="Drink">Drink</option>
                        <option value="Main Course">Main Course</option>
                        <option value="Dessert">Dessert</option>
                    </select>
                    </td>

                </tr>
                <tr>
                    <td>Image Upload: <input type="file" name="pic" accept="image/*"></td>

                </tr>
                <tr>
                    <td>Calories: <input class="form-control" type="text" name="calories" required></td>

                </tr>
                <tr>
                    <td>Preperation Time: <!-- <input class="form-control" type="number" name="time" max=10 min=1 required> -->
                    	<select class="form-control" name="time">
                        <option value="1" >1</option>
                        <option value="2" >2</option>
                        <option value="3" >3</option>
                        <option value="4" >4</option>
                        <option value="5" >5</option>
                        <option value="6" >6</option>
                        <option value="7" >7</option>
                        <option value="8" >8</option>
                        <option value="9" >9</option>
                        <option value="10" >10</option>
                      
                    </select>
                    </td>

                </tr>


                <tr>
                    <td>Status: <!-- <input class="form-control" type="text" name="status" required> -->
           
                    <select class="form-control" name="status">
                        <option value="True" >True</option>
                        <option value="False" >False</option>
                      
                    </select>
                    </td>
                    </td>

                </tr>
				<tr></tr>
                <tr>
                    <td colspan="2">
                        <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Add Menu Item"/>
                    </td>
                </tr>

            </table>
        </div>
    </form>
 <br>
    <br>
    <br>
    <br>
    
    <form role="form" method="POST" action="deactivate.html" id="two">
        Name: <input class="form-control" type="text" name="itemName" style="width:15%" required />
        <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Deactivate Item"/>
   <h3 style="color:#459cdc;"><input type="text"  class="form-control" name="pickup_time" value="${msg4}" readonly="readonly" style="width: 800px; color:red; background-color: transparent; border:none"/></h3>
       
    </form>
     <br>
    <br>
    <br>
    <br>
    
    <form role="form" method="POST" action="activate.html" id="three">
        Name: <input class="form-control" type="text" name="itemName" style="width:15%" required />
        <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Activate Menu Item"/>
          <h3 style="color:#459cdc;"><input type="text"  class="form-control" name="pickup_time" value="${msg3}" readonly="readonly" style="width: 800px; color:red; background-color: transparent; border:none"/></h3>
       
    </form>
     <br>
    <br>
    <br>
    <br>
    
    <form role="form" method="POST" action="editMenu.html" id="four">
        ID: <input class="form-control" type="text" name="id" style="width:15%" required />
        <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Edit Menu Item"/>
         <h3 style="color:#459cdc;"><input type="text"  class="form-control" name="pickup_time" value="${msg2}" readonly="readonly" style="width: 800px; color:red; background-color: transparent; border:none"/></h3>
        
    </form>
     <br>
    <br>
    <br>
    <br>
    
    <form role="form" method="POST" action="orderHistory.html" id="five" >
    <h3 style="color:#459cdc;" > FROM Date: <input type="date" id="current_date" name="from_date" required/></h3>
  <h3 style="color:#459cdc;" >    TO DATE: <input type="date" id="current_date" name="to_date" required/></h3>
   <h3 style="color:#459cdc;"><input type="text"  class="form-control" name="pickup_time" value="${msg1}" readonly="readonly" style="width: 800px; color:red; background-color: transparent; border:none"/></h3>
	
	<input type="submit" name="history" value="Order History" class="btn btn-info btn-lg" />
</form> 
 <br>
    <br>
    <br>
    <br>
    
    <form action="popularityReport.html" method="POST" id="six">
     <h3 style="color:#459cdc;" > FROM Date: <input type="date" id="current_date" name="from_date" required/></h3>
  <h3 style="color:#459cdc;" >    TO DATE: <input type="date" id="current_date" name="to_date" required/></h3>
   <h3 style="color:#459cdc;"><input type="text"  class="form-control" name="pickup_time" value="${msg}" readonly="readonly" style="width: 800px; color:red; background-color: transparent; border:none""/></h3>
	
        <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" name="popularity" value="Popularity Report"/>
       
    </form>

    
    <br>
    <br>
    <br>
    <br>
    
    <div >
    <form action="resetMenu.html" method="POST">
        <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" name="reset" value="RESET"/>

        <!--<input type="submit"  name="reset" value="RESET">-->
    </form>
    </div>
    <div>

    </div>
</div>


</body>
</html>