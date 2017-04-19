<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Menu</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<style type="text/css">
 img{

    width: 100px;
    height:100px;
  }
  
        #header {
            height: 60px;
            margin: 0px
        }
        #secondSection {
            height: 50px;
        }
        .background {
            -webkit-filter: blur(10px);
            filter: blur(10px);
        }
        .container {
            font-weight: bold;
            font-family: Calibri;
            font-size: x-large;

        }
</style>

<script type="text/javascript">


var arr=[];
var temp;

function getValue(id1)
{
	//alert("id1:"+id1);
	if(document.getElementById(id1+'_c').checked)
	{
	//	alert("value is:"+document.getElementById(id1).value);	
		temp=document.getElementById(id1).value+"-"+id1;
		arr.push(temp);
		//alert(arr);
	}
	else
	{
		//alert("no id");	
		//alert(arr.length);
		for(var i=0;i<arr.length;i++)
		{
			temp=document.getElementById(id1).value+"-"+id1;
		//	alert("temp:"+temp);
		//	alert("arr[i]:"+arr[i]);
			if(arr[i]==(temp))
			{
			//	alert("match");	
				arr.splice(i,1);
			}
		}
		//alert("no id");	
	}
	
}

function getArray()
{
//	alert("inside getArray");
	document.getElementById('myField').value=arr;
	//alert("arr is:"+document.getElementById('myField').value);
	
}

</script>
  
</head>

<body style="background-color:black">
<header>
<!-- background-color:#dc0e28 -->
    <div id="header" style="background-color: #dc0e28;" align="left" class="container-fluid">
        <p align="center" style="font-weight:bold;color:#000;font-size:40px">Food-To-Go</p>
        <form role="form" action="logout.html" method="POST" >
        <ul class="nav navbar-nav navbar-right">
            <!--<form role="form" method="POST" action="logout.html" >
                <input type="submit" class=" form-control" style="background-color: #459cdc; color:white; font-size: medium;" value="Logout" />
            </form>-->
           
            <li><input type="submit" align="right" class="btn btn-info btn-lg" style="font-size: 30px"  value="Logout" /></li>

        </ul>
        </form>
       
    </div>
 </header>

 <li><h3 align="center" style="color:greenyellow" ><%= session.getAttribute("email") %></h3></li>
<form role="form" action="receive_order.html" method="POST">

<div class="container">
<ul class="nav nav-pills nav-stacked col-md-4">
  <li class="active"><a href="#tab_a" data-toggle="pill">Drink</a></li>
  <li><a href="#tab_b" data-toggle="pill">Appetizer</a></li>
  <li><a href="#tab_c" data-toggle="pill">Main Course</a></li>
  <li><a href="#tab_d" data-toggle="pill">Dessert</a></li>
</ul>


<div class="tab-content col-md-8">
		
        <div class="tab-pane active" id="tab_a">
           <!--   <h2 text-align="center">Drink</h2> -->
			<table class="table table-sm">
			<thead style="color:#459cdc;">
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Quantity</th>
		        
		    </tr>
		    </thead>
		    <tbody style="color:#66ccff">
		     <c:forEach items="${drinks}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		            <td><input type="number" name="menus" id="${menu.item_name}" min=1 max=100 placeholder="1" value=1></input></td>
		            <td><input type="checkbox" id="${menu.item_name}_c" value="${menu.item_name}" name="menus" onchange="getValue('${menu.item_name}')"/></td>
		        </tr>
		    </c:forEach> 
		    </tbody>
			</table>
			
			
        </div>
        <div class="tab-pane" id="tab_b">
            <!--  <h4>Appetizer</h4> -->
            <table class="table table-sm" >
            <thead style="color:#459cdc;">
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Quantity</th>
		        
		    </tr>
		    </thead>
		    <tbody style="color:#66ccff">
		    <c:forEach items="${appetizers}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		             <td><input type="number" id="${menu.item_name}" name="menus" min=1 max=100 ng-model="${menu.quantity}" placeholder="1" value=1></input></td>
		            <td><input type="checkbox" id="${menu.item_name}_c" value="${menu.item_name}" name="menus" onchange="getValue('${menu.item_name}')"/></td>
		        </tr>
		    </c:forEach>
		    </tbody>
			</table>
        </div>
        <div class="tab-pane" id="tab_c">
          <!--    <h4>Main Course</h4> -->
            <table class="table table-sm">
            <thead style="color:#459cdc;">
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Quantity</th>
		        
		    </tr>
		    </thead>
		    <tbody style="color:#66ccff">
		    <c:forEach items="${main_courses}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		             <td><input type="number" id="${menu.item_name}" name="menus" min=1 max=100 placeholder="1" value=1></input></td>
		            <td><input type="checkbox" id="${menu.item_name}_c" value="${menu.item_name}" name="menus" onchange="getValue('${menu.item_name}')"/></td>
		        </tr>
		    </c:forEach>
		    </tbody>
			</table>
        </div>
        <div class="tab-pane" id="tab_d">
             <!-- <h4>Dessert</h4> -->
         <table class="table table-sm">
         <thead style="color:#459cdc;">
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Quantity</th>
		        
		    </tr>
		    </thead>
		    <tbody style="color:#66ccff">
		    <c:forEach items="${desserts}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		             <td><input type="number" name="menus" id="${menu.item_name}"  min=1 max=100  placeholder="1" value=1></input></td>
		            <td><input  type="checkbox"  id="${menu.item_name}_c" value="${menu.item_name}" name="menus" onchange="getValue('${menu.item_name}')"/></td> <!-- onclick="getValue(this.id)"/></td> -->
		        	
		        </tr>
		    </c:forEach>
		    </tbody>
			</table>
        </div>
        
       
        
</div><!-- tab content -->
 
</div>
	 <div align="center" style="margin-top: 50px">
	<input type="hidden" id="myField" value="" name="array" />
	 <input  class="btn btn-info btn-lg" type="submit" style="background-color: #459cdc; color:white; font-size: medium;" name="submit_order" value="Place order" onclick="getArray()"/>
	<!-- <input type="submit" name="submit_order" value="Place order" id="place_order" onclick="getArray()"/> -->
	
	</div>
</form>
<div style="margin-top:100px; align:left ">
 <form action="viewOrders.html" method="POST" style="margin-right:50" >
        	<ul class="nav navbar-nav navbar-right">
        	<input type="submit" class="btn btn-info btn-lg"  style="font-size: 30px" value="View Orders" />
        	</ul>
        </form>
        </div>
<%-- <h2>${success}</h2>
<form role="form" method="POST" action="logout.html" >
	<input type="submit" name="logout" value="LOGOUT" />
</form> --%>




</body>
</html>