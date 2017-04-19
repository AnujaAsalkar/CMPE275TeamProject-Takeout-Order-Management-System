<html>
<head>
<title>
Group10
</title>
</head>
<body>

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



	<form method="POST" action="update.html" role="form" enctype="multipart/form-data">
	<div class="form-group">
   <table>
    <tr>
        <td>ID: <input type="text"  class="form-control" name="id" value="${id}" readonly="readonly"></td>
       
    </tr>
     <tr>
        <td>Name: <input type="text" class="form-control" name="itemName" value="${itemName}" style="width:auto;"></td>
       
    </tr>
     <tr>
        <td>Price: <input type="text" class="form-control" name="price" value="${price}" style="width:auto;"></td>
       
    </tr>
     <tr>
        <td>Category: <input type="text" class="form-control"  name="category" value="${category}" style="width:auto;"></td>
       
    </tr>
     <tr>
        <td>Calories: <input type="text" class="form-control"  name="calories" value="${calories}" style="width:auto;"></td>
       
    </tr>
     <tr>
        <td>Time: <input type="text" class="form-control"  name="time" value="${time}" style="width:auto;"></td>
       
    </tr>
     <tr>
        <td>Status: <input type="text" class="form-control" name="status" value="${status}" style="width:auto;"></td>
       
    </tr>
    
     <tr>
        <td>Image: <img src="${file}" height="42" width="42"></td>
       
    </tr>
     <tr>
        <td>Image Upload: <input type="file" name="pic" accept="image/*" required></td>
       
    </tr>
   
</table>  

 <tr>
        <td colspan="2">
            <input type="submit" class="btn btn-info btn-lg" data-toggle="modal" name="update" value="update"/>
	   </td>
    </tr>

    </div>
</form>
</body>
</html>