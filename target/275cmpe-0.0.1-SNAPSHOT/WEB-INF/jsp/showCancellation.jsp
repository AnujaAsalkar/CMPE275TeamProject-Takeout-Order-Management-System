<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Cancel</title>

<script>

var arr=[];
var temp;

function getValue(id1)
{
//	alert("id1:"+id1);
//	alert("appended:"+('c'+id1));
	if(document.getElementById('c'+id1).checked)
	{
	//	alert("value is:"+document.getElementById('c'+id1).value);	
		temp=document.getElementById('c'+id1).value;//+"-"+id1;
		arr.push(temp);
	//	alert(arr);
	}
	else
	{
	//	alert("no id");	
	//	alert(arr.length);
		for(var i=0;i<arr.length;i++)
		{
			temp=document.getElementById('c'+id1).value;
			if(arr[i]==temp)
			{
			//	alert("match");	
				arr.splice(i,1);
			}
		}
	}
	
}

function getArray()
{
	//alert("inside getArray");
	document.getElementById('myField').value=arr;
	//alert("arr is:"+document.getElementById('myField').value);
	
}
</script>
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


<form action="cancelUserOrder.html" method="POST">
   <table class="table table-sm">
   
    <tr>
       
    </tr>
     <tr>
        
       
    </tr>
    <thead style="color:#459cdc;">
		    <tr>
		        <th>Order ID</th>
		         <th>User ID</th>
		         <th>Pick Date</th>
		          <th>Pick Time</th>
		          <th>Start Time</th>
		          <th>End Time</th>
		           <th>Status</th>
		           <th>Menu Items</th>
		           <th> Select to Cancel</th>
		       
		    </tr>
	</thead>
    
     <tbody style="color:#66ccff">
     <c:forEach items="${frontList}" var="order" varStatus="status">
		        <tr>
		            <td>${order.order_id}</td>
		            <td>${order.userId}</td>
		           	<td>${order.pickDate}</td> 
		           	<td>${order.pickTime}</td> 
		           	<td>${order.start_time}</td> 
		           	 <td>${order.end_time}</td> 
		           	 <td>${order.status}</td> 
		          <td>${backList[status.index]}</td>
		          <td><input type="checkbox" id="c${order.order_id}" value="${order.order_id}" class="${order.order_id}"  name="menus" onchange="getValue('${order.order_id}')" /></td>
		        </tr>
		       
		    </c:forEach>
		    <c:forEach items="${frontList_delivered}" var="order" varStatus="status">
		        <tr>
		            <td>${order.order_id}</td>
		            <td>${order.userId}</td>
		           	<td>${order.pickDate}</td> 
		           	<td>${order.pickTime}</td> 
		           	<td>${order.start_time}</td> 
		           	 <td>${order.end_time}</td> 
		           	 <td>${order.status}</td> 
		          <td>${backList_delivered[status.index]}</td>
		          
		        </tr>
		       
		    </c:forEach>
		</tbody>
</table>

 <div align="center" style="margin-top: 50px">
	<input type="hidden" id="myField" value="" name="array" />
	 <input  class="btn btn-info btn-lg" type="submit" style="background-color: #459cdc; color:white; font-size: medium;" name="submit_order" value="Cancel order" onclick="getArray()"/>
	<!-- <input type="submit" name="submit_order" value="Place order" id="place_order" onclick="getArray()"/> -->
	</div>	
	          
</form>
   
</body>
</html>