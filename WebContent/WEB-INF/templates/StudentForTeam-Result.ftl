<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<form name="form1" action="AppointCaptain" method="post">
  
<h1>Student Participation details</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
  <td><b>Id</b></td>
    <td><b>Name</b></td>
    </tr>
 <#list students as student>
  <tr>
   <td valign="top"><b><font size="+1"><font color="#663366"><button
                    style="font-size:15px" name="btnLeagueId" value=${student[1]}>${student[1]}</button></font></font></b></td>
         
    
  <td>${student[2]}</td>
         
   </tr>
    
 
 
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>
