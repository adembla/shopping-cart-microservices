<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart Finalize</title>
</head>
<body>
	<jsp:include page="_header.jsp" />


	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Thank you for Order</h1>
				<h3>Your order number is: ${orderDetails.invoiceId}</h3>
			</div>
			<div class="col-md-6">
				<h3 align="left">Order Details:</h3>
				<br>
				<!-- Define for each loop of products -->


				<div class="invoice-content">
					<!-- begin table-responsive -->
					<div class="table-responsive">
						<table class="table table-invoice">
							<thead>
								<tr>
									<th class="text-center" width="20%">Product Name</th>
									<th class="text-center" width="5%">Quantity</th>
									<th class="text-right" width="5%">Unit Price</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${orderDetails.items}" var="item">
								<tr>
									<td class="text-center">${item.productName}</td>
									<td class="text-center">${item.quantity}</td>
									<td class="text-right">${item.unitCost}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="invoice-price">
						<div class="invoice-price-left">
							<div class="invoice-price-row">
								<div class="sub-price">
									<small>Payment Mode</small> <span class="text-inverse">Online</span>
								</div>
								<div class="sub-price">
									<i class="fa fa-plus text-muted"></i>
								</div>
								<div class="sub-price">
									<small>Delivery Date</small> <span class="text-inverse">Next
										Sunday</span>
								</div>
							</div>
						</div>
						<div class="invoice-price-right">
							<small>TOTAL</small> <span class="f-w-600">${orderDetails.totalCost}</span>
						</div>
					</div>
				</div>
				<br>
				<div align="right">
					<a href="/home" class="btn btn-primary">Continue Shopping</a>
					&nbsp; &nbsp; <a href="/orders" class="btn btn-primary">My
						Orders</a>
				</div>
				<br> <br>
			</div>
		</div>
	</div>
	<jsp:include page="_footer.jsp" />

</body>
</html>