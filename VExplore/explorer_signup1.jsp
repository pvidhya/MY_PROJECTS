
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VEXPLORE</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
<link rel="stylesheet" href="input.css" type="text/css" />

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>
<body>
<div id="container">

  <nav>
<div class="width">
   <ul>
   <li class="start selected" style="padding-right: 700px;"><a href="explorer_home.jsp">Vexplore</a></li>
       <li ><a href="explorer_home.jsp">Home</a></li>
           <li class=""><a href="explorer_login1.jsp">Login</a></li>
         <li class="start selected"><a href="explorer_signup1.jsp">Signup</a></li>

     </ul>
</div>
</nav>
<%String errorMsg=(String)session.getAttribute("errorMsg");%>


<br><br><br>
<div class="login-page">
  <div class="form">


    <form class="login-form" method= "post" action="explorer_signup.jsp" style=";position:relative;z-index: 1;background: #FFFFFF;max-width: 360px;margin: 0 auto 100px;padding: 45px;text-align: center;box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
      <%try{
          if(!errorMsg.equals("null")){%>

                  <h4><%=errorMsg%></h4>
                <%}
            }
        catch(Exception e)
      {}%>
      <input type="text" id="fname" name="fname" required="required" placeholder="First Name" />
      <input type="text" id="lname" name="lname" required="required" placeholder="Last Name" />
      <input type="text" id="username" name="username" required="required" placeholder="username"/>
      <input type="text" id="contact" name="contact" required="required" placeholder="contact"/>
      <input type="text" id="email_id" name="email_id" required="required" placeholder="email_id"/>
      <input type="password" id="pass" name="pass" required="required" placeholder="Password" />
      <input type="password" id="cpass" name="cpass" required="required" placeholder="Retype Password" />
      <input type="submit" value="Signup" style="font-family:  sans-serif;text-transform: uppercase;outline: 0;background: #3882D6;width: 100%;border: 0;padding: 15px;color: #FFFFFF;font-size: 14px;-webkit-transition: all 0.3 ease;transition: all 0.3 ease;cursor: pointer;"/>

    </form>
  </div>
</div>
    </div>
    </body>
     </html>
