<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<form name="form1" action="LeagueWinner" method="Get">
  
<h1>Teams</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
  <td><b>Id</b></td>
    <td><b>Name</b></td>
    </tr>
 <#list teams as team>
  <tr>
   <td valign="top"><b><font size="+1"><font color="#663366"><button
                    style="font-size:15px" name="btnLeagueId" value=${team[0]}]${team[2]}]${team[1]}>${team[0]}</button></font></font></b></td>
         
    
  <td>${team[1]}</td>
         
   </tr>
    
 
 
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>
