<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Order</title>
</head>

<body style="background-color:black">

<header>
    <div id="header" style="background-color:#dc0e28;" align="left" class="container-fluid">

        <nav>
            <p align="center" style="font-weight:bold;color:#000;font-size:40px">Food-To-Go</p>

        </nav>

    </div>

</header>
<div id="secondSection" align="center">
    <img src="https://hawksworthrestaurant.com/wp-content/uploads/2013/06/Hawksworth-Restaurant-Squid-Ceviche-home.jpg" width="100%" height="350">
</div>


<script type="text/javascript">
window.onload=function()
{
	//alert("Inside onload");
	myFunction();
}
var myFunction = function()
{
	//alert("Inside function");
	var today= new Date();
	var current_hours=today.getHours();
	var current_min=today.getMinutes();
	var current_sec=today.getSeconds();
	var dd=today.getDate();
	var mm=today.getMonth()+1;
	var mm1=mm+1;
	var yy=today.getFullYear();
	if(dd<10)
	{
		dd='0'+dd;	
	}
	if(mm<10)
	{
		mm='0'+mm;	
	}
	if(mm1<10)
	{
		mm1='0'+mm1;
	}
	var date = yy+'-'+mm+'-'+dd;
	var max_date=yy+'-'+mm1+'-'+dd;

	//alert("Minimum function "+date);
	var n=document.getElementById("current_date");
	n.setAttribute("type","date");
	n.setAttribute("min",date);
	n.setAttribute("max",max_date);
}
/* var n = d.toDateInputValue();
document.getElementById("current_date").innerHTML = d.toDateInputValue(); */
</script>



<form role="form" action="userTime.html" method="POST">
 <h3 style="color:#459cdc;" > PICK UP Date: <input type="date" id="current_date" name="pickup_date" /></h3>
  <h3 style="color:#459cdc;" >     PICK UP Time: <input type="time" name="pickup_time" min="06:01:00" max="21:00:00" /></h3>
      <button type="submit" class="btn btn-info btn-lg">Place Order</button>
     </form>
     
      <form role="form" action="confirmOrder.html" method="POST">

     <h3 style="color:#459cdc;" >Earliest Pick Up Date: <input type="date" name="pickup_date" value="${date}" readonly="readonly"/></h3>
    <h3 style="color:#459cdc;" >Earliest Pick Up Time: <input type="text"  class="form-control" name="pickup_time" value="${early}" readonly="readonly"/></h3>
      <button type="submit" class="btn btn-info btn-lg">ORDER NOW!!!!!</button>
     </form>
     <h3 style="color:#459cdc;">Note:<input type="text"  class="form-control" name="pickup_time" value="${msg}" readonly="readonly" style="width: 800px;"/></h3>
	

</body>
</html>
