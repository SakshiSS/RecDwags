<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Match Results</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Current matches</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
 
				
    <td><b>Match Id</b></td>
    <td><b>Home Points</b></td>
    <td><b>Away Points</b></td>
    <td><b>Match Date</b></td>
     <td><b>Venue-Name</b></td>
    <td><b>Home Team</b></td>
    <td><b>Away Team</b></td>
    
  </tr>
 <#list matches as match>
 
  <tr>
    <td>${match[0]}</td>
    <td>${match[1]}</td>
    <td>${match[2]}</td>
    <td>${match[3]}</td>
    <td>${match[4]}</td>
    <td>${match[5]}</td>
    <td>${match[6]}</td>
    
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="League_Home.html"> League HomePage </a>
<p><p>Back to the <a href="Admin_HomePage.html"> Main Page </a>
  
</body>
</html>
