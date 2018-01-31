<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<form name="form1" action="EnterScoreSchedule" method="post">
  
<h1>Schedule</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <#list teams as team>
  <tr>
   <td valign="top"><b><font size="+1"><font color="#663366"><button
                    style="font-size:15px" name="btnMatch1" value=${team[2]}]${team[0]}]${team[1]}>${team[0]}  vs ${team[1]}</button></font></font></b></td>
         
    
         
   </tr>
    
 
  
 
 </#list></table>

 <#list teams1 as team1>
  <tr>
   <td valign="top"><b><font size="+1"><font color="#663366"><button
                    style="font-size:15px" name="btnMatch2" value=${team1[2]}]${team1[0]}]${team1[1]}>${team1[0]}  vs ${team1[1]}</button></font></font></b></td>
         
    
         
   </tr>
    
 
  
 
 </#list></table>

  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>
