<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Login</title>
 
<script src="webjars/jquery/2.1.4/jquery.min.js"></script>
<link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 
</head>
<body>
 
   <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
          
          <%-- <div th:if="${param.error}">
                        <div class="alert alert-danger">
                            Invalid username and password.
                        </div>
                    </div>

                    <div th:if="${param.logout}">
                        <div class="alert alert-info">
                            You have been logged out.
                        </div>
                    </div> --%>
            <h5 class="card-title text-center">Sign In</h5>
            <form class="form-signin" action ="login" method="post" modelAttribute="login">
              <div class="form-label-group">
                <input type="text" id="username" name = "username" class="form-control" placeholder="Username" required autofocus>
                <label for="username">Username</label>
              </div>

              <div class="form-label-group">
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                <label for="inputPassword">Password</label>
              </div>

              <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Remember password</label>
              </div>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
              <hr class="my-4">
              <!-- <h5 class="card-title text-center">Sign In</h5> -->
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
 
   <!-- <div class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form action="#" method="post">
                <fieldset>

                    

                    <div class="form-group">
                        <input type="text" name="username" id="username" class="form-control input-lg"
                               placeholder="UserName" required="true" autofocus="true"/>
                    </div>

                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="Password" required="true"/>
                    </div>

                    <div class="row">
                        <div class="col-sm-3" style="float: none; margin: 0 auto;">
                            <input type="submit" class="btn btn-primary btn-block" value="Login"/>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>

</div>
  -->
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>