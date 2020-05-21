<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart</title>
 
</head>
<body>
<%! int totalPrice=0; %>
<%-- ${auth.access_token} --%>
   <jsp:include page="_header.jsp" />
  <div class="row">
  <div class="col-md-8">
  <h3 align="center"> Cart Items</h3>
  <c:set var="cartTotal" value="${0}"/>
  <c:set var="cartQuantity" value="${0}"/>
  <c:forEach items="${products}" var="product">
  <div class="card text-center" style="width: 80%; margin-left: 10%">
  <c:set var="cartTotal" value="${cartTotal + product.quanity * product.unitPrice}" />
  <c:set var="cartQuantity" value="${cartQuantity + product.quanity}"/>
  <div class="card-header">
    ${product.productName}
  </div>
  <div class="card-body">
    <p class="card-text">${product.productDescription}</p>
   <p class="card-text">MRP: ${product.unitPrice}</p>
    <a href="/addToCart/cart?productId=${product.id}" class="btn btn-primary">Add</a> &nbsp;&nbsp; <a href="/removeFromCart/cart?productId=${product.id}" class="btn btn-danger">Remove</a>&nbsp;&nbsp;
    
  </div>
  <div class="card-footer text-muted">
    ${product.quanity} in cart
  </div>
</div>
<br>
</c:forEach>
</div>

<div class="col-md-4">
<h3 align="left"> Cart Total</h3>
  <div class="form-group row">
    <label for="items" class="col-sm-6 col-form-label">Total Items:</label>
    <div class="col-sm-6">
      <label for="items" class="col-form-label">${cartQuantity}</label>
    </div>
  </div>
  <div class="form-group row">
    <label for="cartTotal" class="col-sm-6 col-form-label">Cart Total:</label>
    <div class="col-sm-6">
      <label class="col-form-label" id="cartTotal">${cartTotal}</label>
    </div>
  </div>
  <a href="/checkout" class="btn btn-primary">Checkout</a>
</div>
 </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>