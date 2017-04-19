
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Placed Orders</title>
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


<h1>${message }</h1>
 <h3 style="color:#459cdc;" > PickUp Date: ${pickDate}</h3>
   <h3 style="color:#459cdc;" > PickUp Time:${pickTime }</h3>
   <table class="table table-sm">
   
    <tr>
       
    </tr>
     <tr>
        
       
    </tr>
    <thead style="color:#459cdc;">
		    <tr>
		        <th>Menu Name</th>
		         <th>Quantity</th>
		       
		         <th>Unit Price</th>
		       
		    </tr>
	</thead>
    
     <tbody style="color:#66ccff">
     <c:forEach items="${orderList}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		           	<td>${menu.quantity}</td> 
		            
		            <td>${menu.unitPrice}</td>
		             
		        </tr>
		       
		    </c:forEach>
		</tbody>
</table>	
	<div style="color:#459cdc;">
	<h2>Total Price:$ ${total_price}</h2>
	</div>
   <form role="form" method="POST" action="cancelOrder.html">
		<input type=submit value="Cancel Order" name="cancel_order" />
	</form>  
</body>
</html>