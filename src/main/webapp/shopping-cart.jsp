<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<%@include file="link-css-font-include.jsp"%>
<link rel="stylesheet" href="css/card-style.css" type="text/css" />
<title>Cart</title>
</head>

<body>
    <%@ include file="header-include.jsp" %>

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>Shopping Cart</h4>
                        <div class="breadcrumb__links">
                            <a href=".Home">Home</a>
                            <a href="./Shop">Shop</a>
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="shopping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            	if (orders != null) {
                                    for (Order order : orders.values()) {
                            	%>
                                <tr>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__pic" >
                                            <img class="image-fluid" style="width: 70px; height: 70px;object-fit: cover" src="images/<%=order.getProduct().getUrlImage()%>" alt="">
                                        </div>
                                        <div class="product__cart__item__text">
                                            <h6><%=order.getProduct().getName()%></h6>
                                            <h5 class="format-price"><%=order.getProduct().getPrice()%></h5>
                                        </div>
                                    </td>
                                    <td class="quantity__item">
                                        <div class="quantity d-flex">
                                           <input class="input-quantity w-25" type="number" value="<%=order.getQuantity()%>" data-value="<%=order.getId()%>"  name="quantity" >
                                        </div>
                                    </td>
                                    <td class="cart__price format-price"><%=order.getPrice()*order.getQuantity() %></td>
                                    <td class="cart__close"><i class="fa fa-close" style="cursor: pointer;" data-value="<%=order.getId()%>"></i></td>
                                </tr>
                                <%} %>
                                <%} %>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn">
                                <a href="./Shop">Continue Shopping</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="cart__total">
                        <h6>Cart total</h6>
                        <ul>
                            <li>
                            Subtotal :
                            <span class="format-price"><%=(session.getAttribute("totalPrice")) == null ?"0" :  session.getAttribute("totalPrice")%></span></li>
                        </ul>
                        <a href="checkout.jsp" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->

       <!-- import footer -->
	<%@ include file="footer-include.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script >
	const cartCloses = document.querySelectorAll('.fa-close');
	const quantityCart = document.querySelector('.quantity-cart');
	const cartTotal = document.querySelector('.cart__total').querySelector('span');
	cartCloses.forEach(element => {
		element.addEventListener('click', function(){
			$.ajax({
				url : "./DeleteOrderById",
				type : "POST",
				data : {
					product_id : element.getAttribute('data-value'),
				},
				success: function(data){
					element.parentNode.parentNode.remove();
					cartTotal.textContent = data.totalPrice.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
					quantityCart.textContent = data.sizeOrders;
					},
					error: function(xhr){
					
				}
			});
		})
		
		
	})
	
	
	//prosessing quantity update
	const inputQuantities = document.querySelectorAll(".input-quantity")
	console.log(inputQuantities)
	inputQuantities.forEach(element => {
		console.log(element.previousElementSibling)
		element.addEventListener('change',function(){
			
			let value = parseInt(this.value); 

		    if (isNaN(value)) {
		        this.value = 10; 
		    } else {
		        if (value < 1)
		        {
		        	this.value = 1;
		 
		        } else if (value > 25) {
		            this.value = 25; // Nếu lớn hơn 5, đặt giá trị là 5
		        }
		    }
		    
			$.ajax({
				url : "./UpdateQuantityOrder",
				type : "POST",
				data : {
					product_id : element.getAttribute('data-value'),
					quantity : element.value,
				},
				success: function(data){
					element.parentNode.parentNode.parentNode.querySelector(".cart__price").textContent = data.updatedPrice.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
					cartTotal.textContent = data.totalPrice.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
					},
					error: function(xhr){
					
				}
			});
		})
	})
	</script>
    <!-- Js Plugins -->
	<%@include file="link-script-include.jsp"%>
</body>

</html>