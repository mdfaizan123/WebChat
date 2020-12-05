<%@page import="java.util.HashMap"%> 
<%
    HashMap pb=(HashMap)session.getAttribute("publicDetails");
%>
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
    <link rel="stylesheet" href="css/home.css">
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
                        <a class="nav-link" href="home.jsp">Home<span class="sr-only">(current)</span> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Friend?id=<%if(pb!=null){%><%=pb.get("id")%><%}%>">message</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">logout</a>
                    </li>
            </div>
        </div>
        
    </nav>
    <%
            String msg=(String)session.getAttribute("msg");
            if(msg!=null)  
            {
        %>
        <div class="panel panel-danger">
            <div class="panel-heading text-center">
                <p><%=msg%></p>
            </div>
        </div>
        <%
            session.setAttribute("msg", null);
            }
        %>
   <%if(pb!=null){%>
   <input id="id" type="hidden" value="<%=pb.get("id")%>">
   <%}%>
   <div class="marginh">
       <div class="row">
           <div class="width25">
               <div class="image">
                   <img src="GetPost?id=<%= pb.get("id") %>" alt="">
               </div>
           </div>
           <div class="column">
               <div class="name mt">
                   <span>name: <%=pb.get("name")%></span>
               </div>
               <div class="gender mt">
                   <span>gender: <%=pb.get("gender")%></span>
               </div>
               <div class="email mt">
                   <span>email: <%=pb.get("email")%></span>
               </div>
               <div class="dob mt">
                   <span>dob :<%=pb.get("dob")%></span>
               </div>
           </div>
       </div>
   </div>
   
    
    
   <!-- footer -->
   <nav id="mainNavbar" class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
        <div class="container">
            <footer class="blockquote-footer">The design and developed by <cite title="Source Title">MD FAIZAN</cite></footer>
        </div>
        
    </nav>
   
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/notification.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
    
    
</body>

</html>