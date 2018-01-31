<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Student Details</title>
  </head>
  <body style="background-color:#ffffcc">
  <form name="form1" method="post" action="UpdateStudentDetailsDB">
    <b>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </b><br>
    <div alink="#EE0000" bgcolor="#ffffcc" link="#0000EE" text="#000000"
      vlink="#551A8B">
      <div align="center">
        <div align="left"> <br>
          <table border="0" cellpadding="2" cellspacing="2" height="132"
            width="575">
            <tbody>
              <tr>
                <td colspan="7" rowspan="1" bgcolor="#ffcccc"
                  valign="top">
                  <h3 align="center">
                    <font color="#663366"><em><big>RecDawgs</big></em></font></h3>
                </td>
              </tr>
              <tr>
                <td valign="top"><br>
                </td>
                <td valign="top"><br>
                </td>
                <td colspan="5" rowspan="1" align="right" valign="top"><font
                    color="#330033">&nbsp;Logged in as </font><font
                    color="#3333ff"><i>Robert&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <a href="Logout">Logout</a></i></font><br>
                  <br>
                </td>
              </tr>
              <tr>
                <td valign="top"><br>
                </td>
                 <td valign="top"><b><font size="+1"><font color="#663366"><button
                  name="league" style="font-size:15px">Leagues</button></font></font></b>
                  </td>
          <td valign="top"><b><font size="+1"><font color="#663366"><button
                  name="summary" style="font-size:15px">Summary</button></font></font></b></td>
          <td valign="top"><b><font size="+1"><font color="#663366"><button
                   name="venue" style="font-size:15px">Venues</button><br>
                  <br>
               </td>
            <td valign="top"><b><font size="+1"><font color="#663366"><button
                   name="teams" style="font-size:15px">Teams</button><br>
                  <br>
               </td>
            <td valign="top"><b><font size="+1"><font color="#663366"><button
                   name="sMatch" style="font-size:15px">Match</button><br>
                  <br>
               </td>
            
            <td valign="top"><b><font size="+1"><font color="#663366"><button
                  name="findUser" style="font-size:15px">FindStudent</button></font></font></b>
                  </td>
              
              <td valign="top"><b><font size="+1"><font color="#663366"> 
            <input type="submit"    name="update" style="font-size:15px" value="Update"><br>
                  <br>
               </td>  
               <td valign="top"><b><font size="+1"><font color="#663366"><button
                  name="CancelRegisteration" style="font-size:15px">CancelRegisteration</button></font></font></b>
                  </td>
                
                  
             <td valign="top"><b><font size="+1"><font color="#663366"><button
                    name="contact" style="font-size:15px">Contact</button><br>
                  <br>
                </font></font></b></td>
                
                <td valign="top"><b><font size="+1"><font color="#663366"><button
                  name="logout" style="font-size:15px">Logout</button></font></font></b>
                  </td>
                  
                  
                       </tr>
            </tbody>
          </table>
          <br>
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          &nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <font color="#663366"><b>Update Student Details<br>
            </b></font></div>
        <br>
        <div align="left">
       <#list students as student>
 
          <table border="0" cellpadding="2" cellspacing="2" height="178"
            width="300">
            <tbody>
			  <tr>
                <td valign="top"><font color="#663366"><b>First Name:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="firstName" value="${student[1]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Last Name:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="lastName" value="${student[2]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Username:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="username" value="${student[3]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Password:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="password" value="${student[4]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Email:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="email" value="${student[5]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Student ID:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="studentId" value="${student[6]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Major:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="major" value="${student[7]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><font color="#663366"><b>Address:</b></font><br>
                </td>
                <td valign="top"><input type="text" name="address" value="${student[8]}"><br>
                </td>
              </tr>
              <tr>
                <td valign="top"><br>
                </td>
                <td valign="top"><br><input type="hidden" name="type_user" value="student">
                </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="submit" type="submit" value="Submit"> &nbsp;&nbsp; <input name="cancel" value="Cancel" type="submit">
                  
                </td>
              </tr>
            </tbody>
          </table>
          </form>
          <br>
          </#list>
        </div>
      </div>
    </div>
  </body>
</html>
