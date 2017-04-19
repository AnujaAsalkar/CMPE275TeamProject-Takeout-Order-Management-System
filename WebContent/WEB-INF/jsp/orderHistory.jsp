
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order History</title>
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
		          <th> Order Time</th>
		           <th>Status</th>
		           <th>Menu Items</th>
		       
		    </tr>
	</thead>
    
     <tbody style="color:#66ccff">
   <!--  <c:forEach items="${orderHistory}" var="order" varStatus="status">
		        <tr>
		            <td>${order.order_id}</td>
		            <td>${order.userId}</td>
		           	<td>${order.pickDate}</td> 
		           	  	<td>${order.pickTime}</td> 
		           	  	<td>${order.start_time}</td> 
		           	  	<td>${order.end_time}</td> 
		           	  	<td>${order.orderTime}</td>
		           	  	  	<td>${order.status}</td> 
		           	  	  	
		          	<td>${backList[status.index]}</td>
		             
		        </tr>
		       
		    </c:forEach>-->
	
		    <c:forEach var="type" items="${backList}">
		    <tr>
 <td>${type.key.order_id}</td>
  <td>${type.key.userId}</td>
    <td>${type.key.pickDate}</td>
      <td>${type.key.pickTime}</td>
        <td>${type.key.start_time}</td>
          <td>${type.key.end_time}</td>
            <td>${type.key.orderTime}</td>
              <td>${type.key.status}</td>
  <td> ${type.value}</td>
  </tr>
</c:forEach>
		</tbody>
</table>	
	
      
        <form role="form" method="POST" action="orderHistory.html" id="five" >
  		<input type="submit" name="OrderTime" value="Sort By Order Time" /> 
	   	<input type="submit" name="StartTime" value="Sort By Start Time" />    
		</form>
   
</body>
</html>