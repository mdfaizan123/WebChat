 <!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
        crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css\login.css">
    
    <title>ChatWeb</title>
</head>
 
<body>
    <!-- navbar -->
    <nav id="mainNavbar" class="navbar navbar-expand-lg navbar-light bg-light navbar-top">
        <div class="container">
            <a class="navbar-brand" href="#">ChatWeb</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="login.jsp">login</a>
                    </li>
            </div>
        </div>
        
    </nav>
    
    
       <%
                              String msg=(String)session.getAttribute("msg");
                              if(msg!=null)  
                              {
                          %>
                              <div class="pjumbotron">
                                  <div class="text-center">
                                      <p><%=msg%></p>
                                  </div>
                              </div>
                              <%
                                  session.setAttribute("msg", null);
                                  }
                              %>
     
    
    <!--login page-->
    <% String result=(String)session.getAttribute("result");
            if(result==null){ %>
        
    <form action="Password" method='post' class="container login-margin">
        
        <div class="margin-donator text50">
            <strong class="text-color-red">Enter your Email</strong>
        </div>
          
                             
        <div class="form-group1">
            <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" required="true">
        </div>
        
            <div class="button-paddin">
            <button type="submit" class="btn btn-primary" value='Password'> submit </button>
           </div>
    </form>
        <%}else if(result.equals("yes")){%>
        
    <form action="checkOTP" method='post' class="container login-margin">
        <div class="margin-donator text50">
            <strong class="text-color-red">Enter OTP</strong>
        </div>
        
        <div class="form-group">
            <input type="otp" name="otp" class="form-control" id="exampleInputPassword1" placeholder="Enter OTP" required="true">
        </div>
        
        
        <div class="button-paddin">
            
            <button type="submit" class="btn btn-primary" value='checkOTP'> submit </button>
        </div>
        
    </form>
       <% }else if(result.equals("matched")){ %>
      
     <form action="UpdatePassword" method='post' class="container login-margin">
         <div class="margin-donator text50">
            <strong class="text-color-red">Enter New Password</strong>
        </div>
         
        <div class="form-group1">
            <input type="password" name="password1" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" required="true">
        </div>
        <div class="form-group">
            <input type="password" name="password2" class="form-control" id="exampleInputPassword1" placeholder="Re-Enter Password" required="true">
        </div>
        <div class="button-paddin">
            <button type="submit" class="btn btn-primary" value='UpdatePassword'> submit </button>
        </div>
        
    </form>   
        <% }  %>
        
    
    <!-- footer -->
    <nav id="mainNavbar" class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
        <div class="container">
            <footer class="blockquote-footer">The design and developed by <cite title="Source Title">MD FAIZAN</cite></footer>
        </div>
        
    </nav>
   
   
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>