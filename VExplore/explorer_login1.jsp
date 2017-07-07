
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
           <li class="start selected"><a href="explorer_login1.jsp">Login</a></li>
         <li><a href="explorer_signup1.jsp">Signup</a></li>

     </ul>
</div>
</nav>
<%String errorMsg=(String)session.getAttribute("errorMsg");%>


<br><br><br>
<div class="login-page">
  <div class="form">

    <form class="login-form" method= "post" action="explorer_login.jsp" style="position:relative;z-index: 1;background: #FFFFFF;max-width: 360px;margin: 0 auto 100px;padding: 45px;text-align: center;box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
      <%try{
          if(!errorMsg.equals("null")){%>

                  <h4><%=errorMsg%></h4>
                <%}
            }
        catch(Exception e)
      {}
      session.setAttribute("errorMsg", "");
      %>
    
      <input type="text" id="username" name="username" required="required" placeholder="username"/>
      <input type="password" id="pass" name="pass" required="required" placeholder="password"/>
      <input type="submit" id= "submit" value="Login" />
    </form>
    </div>

</div>

    </div>
    </body>
     </html>
