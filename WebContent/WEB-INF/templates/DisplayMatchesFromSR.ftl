<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Matches/title>
</head>
<body style="background-color:#ffffcc">
<form name="form1" action="GetMatchResultsSR" method="post"> 

<table border="0" cellpadding="2" cellspacing="2" height="146"
width="559">
<tbody>
<tr>
<td colspan="2" rowspan="1" valign="top"><font
color="#663366"><em><strong>Select Match </strong></em></font></td>
<td valign="top"><br>
</td>
</tr>
<!-- <tr>
<td valign="top"><br>
</td>
<td valign="top"><br>
</td>
<td valign="top"><br>
</td>
</tr> --> <tr>
<td valign="top">Select Match<br>
</td>
<td valign="top">
<select name="ddlMatchId">
<#list match_ids as matchid>;
<option value="${matchid[0]}">${matchid[0]} - [ ${matchid[1]} - ${matchid[2]} ]</option>
</#list>
</select>
<#--<input name="txtLeagueName" value="" type="text"> --> </td>
</tr>
<tr>
<td valign="top"><input name="GetTeamScores" value="Get Team Scores" type="submit">
</td>
<td valign="top"><input name="Reset" value="Reset" type="reset">
</td><td valign="top"><br>
</td></tr>
<tr>
<td valign="top"><br>
</td>
<td valign="top"><br>
</td><td valign="top"><br>
</td></tr>
<tr>
<td valign="top"><br>
</td>
<td valign="top"><br>
</td><td valign="top"><br>
</td></tr>
</tbody>
</table>
</form>
</body></html>