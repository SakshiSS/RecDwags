<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>League Results</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Currently active leagues</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Id</b></td>
    <td><b>League Id</b></td>
    <td><b>League Name</b></td>
    <td><b>League Rules</b></td>
    <td><b>Match Rules</b></td>
    <td><b>Venue-Type</b></td>
    <td><b>Minimum Teams</b></td>
    <td><b>Maximum Teams</b></td>
    <td><b>Minimum Members</b></td>
    <td><b>Maximum Members</b></td>
  </tr>
 <#list leagues as league>
  <tr>
    <td>${league[0]}</td>
    <td>${league[1]}</td>
    <td>${league[2]}</td>
    <td>${league[3]}</td>
    <td>${league[4]}</td>
    <td>${league[5]}</td>
    <td>${league[6]}</td>
    <td>${league[7]}</td>
    <td>${league[8]}</td>
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="League_Home.html"> League HomePage </a>
<p><p>Back to the <a href="Admin_HomePage.html"> Main Page </a>
  
</body>
</html>
