<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Details of Team</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>League Details</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Name</b></td>
    <td><b>Id</b></td>
    <td><b>League Rules</b></td>
    <td><b>Match Rules</b></td>
    <td><b>Indoor/Outdoor</b></td>
    <td><b>Minimum Teams</b></td>
    <td><b>Maximum Teams</b></td>
    <td><b>Minimum Members</b></td>
    <td><b>Maximum Members</b></td>
  </tr>
  <tr>
  
 
    <#list leagues as league1>
  <tr>
    <td>${league1[0]}</td>
    <td>${league1[1]}</td>
    <td>${league1[2]}</td>
    <td>${league1[3]}</td>
    <td>${league1[4]}</td>
    <td>${league1[5]}</td>
    <td>${league1[6]}</td>
    <td>${league1[7]}</td>
    <td>${league1[8]}</td>
   
   
  
  
  </tr>
 </#list>   
  </tr>
 </table>
  
<p><p>Back to the <a href="League_Home.html"> League HomePage </a>
<p><p>Back to the <a href="Admin_HomePage.html"> Main Page </a>
  
</body>
</html>