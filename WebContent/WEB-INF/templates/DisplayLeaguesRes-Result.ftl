<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Rounds in League</title>
  </head>
  <body style="background-color:#ffffcc">
  <form name="form1" action="GetMatchResults" method="get">
    <table border="0" cellpadding="2" cellspacing="2" width="100%">
      <tbody>
        <tr>
          <td colspan="2" rowspan="1" valign="top"><font color="#663366"><em><strong>Rounds in League
                  </strong></em></font></td>
          <td valign="top"><br>
          </td>
        </tr>
      <!--  <tr>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
        </tr> -->
        <tr>
          <td valign="top">Select League<br>
          </td>
          <td valign="top">
          
          <select name="ddlRounds">
          <#list rounds as round>
          <option value=${round[0]}>${round[0]}</option>
          </#list>
          </select>
            <#--<input type="text" name="txtLeagueName" value=""> -->
        
        </tr>
        
        <tr>
          <td valign="top"><input name="GetMatches" value="Get Matches" type="submit">
          </td>
          <td valign="top"><input name="Reset" value="Reset" type="reset">
          </td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
          <td valign="top"><br>
          </td>
        </tr>
      </tbody>
    </table>
  </form>
</body></html>