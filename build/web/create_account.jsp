 <!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta name="viewport" content="width=device-width, initial-scale=1" >

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
        crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css\create_account.css">
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
    
    <!--create page-->
    <form  action="Register" method='post' class="container create-margin" enctype='multipart/form-data'>
        <div class="margin-donator text40">
            <strong class="text-color-red">Create an accoount</strong>
        </div>
        
        <div class="form-group1 row">
          <label for="exampleInputEmail1" class="col-sm-2 col-form-label">Email</label>
          <div class="col-sm-10">
              <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter your email" required>
          </div>
        </div>
        
        <div class="form-group row">
          <label for="name" class="col-sm-2 col-form-label">Name</label>
          <div class="col-sm-10">
              <input type="name" name="name" class="form-control" id="name" placeholder="Enter your name" required>
          </div>
        </div>
        
        <div class="form-group row">
          <label for="number" class="col-sm-2 col-form-label">Number</label>
          <div class="col-sm-10">
              <input type="phone" name="number" class="form-control" id="number" placeholder="Enter your number">
          </div>
        </div>
        
        <div class="form-group row">
          <label for="dateofbirth" class="col-sm-2 col-form-label">DOB</label>
          <div class="col-sm-10">
              <input type="text" name="dob" class="form-control" id="dateofbirth" placeholder="DD/MM/YYYY">
          </div>
        </div>

        
        <fieldset class="form-group">
          <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Gender</legend>
            <div class="col-sm-10">
              <div class="form-check">
                <input class="form-check-input" type="radio" name="gender" id="gridRadios1" value="Male" checked>
                <label class="form-check-label" for="gridRadios1">Male</label>
                <input class="form-check-input" type="radio" name="gender" id="gridRadios2" value="Female">
                <label class="form-check-label" for="gridRadios2">Female</label>
                <input class="form-check-input" type="radio" name="gender" id="gridRadios2" value="Other">
                <label class="form-check-label" for="gridRadios2">Other</label>
              </div>
            </div>
          </div>
        </fieldset>
        
        <div class="form-group row">
                <label for="photo" class="col-sm-2 col-form-label">Photo:</label>
                <div class="col-lg-10">
                        <input type="file" name="photo" class="form-control" id="photo"  accept="image/*"/>
                </div>
        </div>
        <div class="form-group row">
          <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
          <div class="col-sm-10">
              <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Enter your password" required>
          </div>
        </div>
        
        
        <div class="button-paddin-create">
            <button type="submit" class="btn btn-primary" value='Register'>Sign Up</button>
        </div>
    </form>
    <br>
    <br>
    <br>
    <!-- footer -->
    <nav id="mainNavbar" class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
        <div class="container">
            <footer class="blockquote-footer">The design and developed by <cite title="Source Title">MD FAIZAN</cite></footer>
        </div>
        
    </nav>
   
   
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>

</html>