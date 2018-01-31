<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Team</title>
  </head>
  <body style="background-color:#ffffcc">
  <form name="form1" action="CreateTeam" method="post">
    <table border="0" cellpadding="2" cellspacing="2" width="100%">
      <tbody>
        <tr>
          <td colspan="2" rowspan="1" valign="top"><font color="#663366"><em><strong>Edit
                  Team</strong></em></font></td>
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
          <td valign="top">Select League<br>
          </td>
          <td valign="top">
          
          <select name="ddlLeagueName">
          <#list leagues as league>
          <option value="${league[0]}">${league[1]}</option>
          </#list>
          </select>
            <#--<input type="text" name="txtLeagueName" value=""> -->
          </td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td valign="top">Name:<br>
          </td>
          <td valign="top"><input name="txtTeamName" type="text" ></td>
          <td valign="top"><br>
          </td>
        </tr>
        <tr>
          <td valign="top">Captain : <br>
          </td>
          
          <td valign="top"><input name="txtCaptainName" type="text" value="${captain_name}" ></td>
          
          <td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <br>
          </td>
        </tr>
        <tr>
          <td valign="top"><input name="Submit" value="Submit" type="submit">
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