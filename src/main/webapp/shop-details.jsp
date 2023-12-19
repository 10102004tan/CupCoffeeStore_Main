<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="us">
<head>
<%@include file="link-css-font-include.jsp"%>
<title>Details</title>

<style>
.back-click {
	color: #000;
}
.back-click:hover {
	color: #000
}
</style>
</head>

<body>
	<!-- import header -->
	<%@ include file="header-include.jsp"%>
	<% Product product =(Product)request.getAttribute("product");%>
	<div class="container mt-5 mb-5">
		<div class="row d-flex justify-content-center">
			<div class="col-md-10">
				<div class="card">
					<div class="row">
						<div class="col-md-6">
							<div class="images p-3">
								<div class="text-center p-4">
									<img id="main-image" src="images/<%=product.getUrlImage() %>"
										width="400" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="product p-4">
								<div class="d-flex justify-content-between align-items-center">
									<a href="./Shop" class="d-flex align-items-center back-click"
										style="cursor: pointer;"> <i class="fa fa-long-arrow-left"></i>
										<span class="ml-1">Back</span>
									</a>
								</div>
								<div class="mt-4 mb-3">
									<span class="text-uppercase text-muted brand">Orianz</span>
									<h5 class="text-uppercase product__name"><%=product.getName() %></h5>
									<div class="price d-flex flex-row align-items-center">
										Price : <span class="act-price format-price"><%=product.getPrice() %></span>

									</div>
								</div>
								<p class="about"><%=product.getDescription()%></p>

								<div class="cart mt-4 align-items-center">
									<a href="AddToCart?id=<%=product.getId()%>"
										class="btn btn-danger text-uppercase mr-2 px-4">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	

	<!-- import footer -->
	<%@ include file="footer-include.jsp"%>


	<script >
	const productName = document.querySelector('.product__name');
	document.title = productName.textContent;
	</script>

	<!-- Js Plugins -->
	<%@include file="link-script-include.jsp"%>
</body>

</html>