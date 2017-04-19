<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rating and Review</title>
<head>
       <link rel="stylesheet" href="jquery.rating.css">
        <script src="jquery.js"></script>
        <script src="jquery.rating.js"></script>
</head>
<body>
<form>
Rating:
  <input type="radio" name="rating" value="1" class="star">
  <input type="radio" name="rating" value="2" class="star">
  <input type="radio" name="rating" value="3" class="star">
  <input type="radio" name="rating" value="4" class="star">
  <input type="radio" name="rating" value="5" class="star"> 
Review:
<textarea name="review" rows="4" cols="50"></textarea>
           
</form>




</body>
</html>