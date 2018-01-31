<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>User Details</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Name</b></td>
    <td><b>Id</b></td>
    <td><b>Address</b></td>
   
    </tr>
 <#list teams as team>
  <tr>
    <td>${team[0]}</td>
    <td>${team[1]}</td>
    <td>${team[2]}</td>
    
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
 <p><p>Back to the <a href="Admin_HomePage.html"> Main Page </a>
  
</body>
</html>
