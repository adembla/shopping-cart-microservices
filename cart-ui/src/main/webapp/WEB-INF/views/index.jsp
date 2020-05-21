<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="/resources/css/style.css">
<link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet">
        <script src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/app.js"></script>

<title>Spring Boot</title>
</head>
<body>
<jsp:include page="_header.jsp" />
  <h1>Spring Boot - MVC web application example</h1>
  <hr>

	<div class="container">
	<h1>Spring Boot with Bootstrap</h1>
	</div>

  <div class="form">
    <form action="hello" method="post" onsubmit="return validate()">
      <table>
        <tr>
          <td>Enter Your name</td>
          <td><input id="name" name="name"></td>
          <td><input type="submit" value="Submit"></td>
        </tr>
      </table>
    </form>
  </div>

</body>
</html>