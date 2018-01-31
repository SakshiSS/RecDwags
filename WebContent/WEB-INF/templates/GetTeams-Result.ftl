<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List of Teams</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Team Details</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Id</b></td>
    <td><b>Name</b></td>
    <td><b>Captain Id</b></td>
    <td><b>Captain Student Id</b></td>
    <td><b>Captain Name</b></td>
    <td><b>League Id</b></td>
    <td><b>League Name</b></td>
  </tr>
 <#list teams as teamObject>
  <tr>
    <td>${teamObject[0]}</td>
    <td>${teamObject[1]}</td>
    <td>${teamObject[2]}</td>
    <td>${teamObject[3]}</td>
    <td>${teamObject[4]}</td>
    <td>${teamObject[5]}</td>
    <td>${teamObject[6]}</td>
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="Team_HomePage.html"> Team HomePage </a>
<p><p>Back to the <a href="Admin_HomePage.html"> Main Page </a>
  
</body>
</html>