<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create League</title>
</head>
<body style="background-color:#ffffcc">

 <form action="EditLeague" method="post" name="form4"> 
 <tr><td align="center"><h1>
Edit League
</h1></td></tr>    
    <tr>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
        </tr>
        
        
         <#list leagues as league1>
  
        <tr>
          <td align="right" valign="top">League Name<br>
          </td>
          <td valign="top"><input name="lName" type="text" value="${league1[0]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
        
          
      
     <tr>
          <td align="right" valign="top">League Rules<br>
          </td>
          <td valign="top"><input name="lRules" type="text" value="${league1[2]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
        
    <tr>
          <td align="right" valign="top">Match Rules<br>
          </td>
          <td valign="top"><input name="mRules" type="text" value="${league1[3]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
       
     <tr>
          <td align="right" valign="top">Enter Venue Type<br>
          </td>
          <td valign="top">
          <#-- <input name="venueType" type="radio" value = "indoor" checked>Indoor </td> -->
          <td >Indoor</td>
          <td >Outdoor</td>
          <br>
         <#if league1[4] == "Indoor">
         
         <input type="radio" name="venueType" value="Indoor" checked>
         <input type="radio" name="venueType" value ="Outdoor">
         <#else>
        
         <input type="radio" name="venueType" value="Indoor" >
         <input type="radio" name="venueType" value ="Outdoor" checked>
         </#if>
          </td>
          <td valign="top">
          <#-- <input name="venueType" type="radio" value = "outdoor">Outdoor </td> -->
         
          </td><br>
        </tr>
      <tr>
          <td align="right" valign="top">Minimum Teams<br>
          </td>
          <td valign="top"><input name="minTeams" type="text" value="${league1[5]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td align="right" valign="top">Maximum Teams<br>
          </td>
          <td valign="top"><input name="maxTeams" type="text" value="${league1[6]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td align="right" valign="top">Minimum  Members<br>
          </td>
          <td valign="top"><input name="minMembers" type="text" value="${league1[7]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td align="right" valign="top">Maximum  Members<br>
          </td>
          <td valign="top"><input name="maxMembers" type="text" value="${league1[8]}"> </td>
          <td valign="top"><br>
          </td>
        </tr>
        
        
      <td align="right" valign="top"><input name ="Submit" value="Edit" type="submit">
         
          </td>
    <td align="right" valign="top"><input name ="Reset" value="Reset" type="reset">
         
          </td>
          </#list>
 </form>
</body>
</html>