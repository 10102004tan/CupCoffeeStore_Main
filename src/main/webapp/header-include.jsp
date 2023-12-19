<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Entiny.Product"%>
<%@page import="java.util.Map"%>
<%@page import="Entiny.Order"%>


<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
	<div class="offcanvas__option"></div>
	<div class="offcanvas__nav__option"></div>
	<div id="mobile-menu-wrap"></div>
</div>
<!-- Offcanvas Menu End -->


<div class="container">
	<div class="row d-flex" style="align-items: center;">
		<div class="col-lg-1 col-md-1">
			<div class="header__logo">
				<a href="./Home"><img
					style="width: 60px; height: 60px; object-fit: cover;"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDfQFe1EcYRT-lf5N2KtFL_jDoviESVDokaA&usqp=CAU"
					alt="" /></a>
			</div>
		</div>
		<div class="col-lg-8 col-md-8">
			<nav class="header__menu mobile-menu">
				<ul>
					<li class=""><a href="./Home">Home</a></li>
					<li><a href="Shop">Shop</a></li>
					<li><a href="./Contact">Contacts</a></li>
					<%
					if (session.getAttribute("name") == null && session.getAttribute("nameAdmin") == null) {
					%>
					<li><a href="./Login">Login</a></li>
					<li><a href="./Register">Register</a></li>
					<%
					} else {
					%>
					
					<% if (session.getAttribute("nameAdmin") != null) {%>
					<li style="font-weight: bold;">Hello <%=session.getAttribute("nameAdmin")%>
						!
					</li>
					<% }else { %>
						<li style="font-weight: bold;">Hello <%=session.getAttribute("name")%>
						!
					</li>
					<% } %>
					<li><a href="Logout">Logout</a></li>
					<%
					}
					%>
				</ul>
			</nav>
		</div>
		<div class="col-lg-3 col-md-3">
			<div class="header__nav__option d-flex">
				<div class="px-3">
					<form action="FindProductByKey">
						<div class="d-flex" style="border: 1px solid #eee;">
							<input type="text" class="form-control input-key" name="key">
							<button class="btn btn-dark" style="border: none">
								<svg style="width: 20px" xmlns="http://www.w3.org/2000/svg"
									fill="none" viewBox="0 0 24 24" stroke-width="1.5"
									stroke="currentColor" class="w-6 h-6">
  									<path stroke-linecap="round" stroke-linejoin="round"
										d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" />
									</svg>
							</button>
						</div>
					</form>

					<div class="position-absolute w-100 px-3 py-4 rounded" style="z-index: 99999">
						<ul class="list-group box-find bg-white">
							
						</ul>
					</div>
				</div>
				<a href="./Cart" style="position: relative;">
				<svg style="width: 30px" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
  				<path stroke-linecap="round" stroke-linejoin="round" d="M15.75 10.5V6a3.75 3.75 0 10-7.5 0v4.5m11.356-1.993l1.263 12c.07.665-.45 1.243-1.119 1.243H4.25a1.125 1.125 0 01-1.12-1.243l1.264-12A1.125 1.125 0 015.513 7.5h12.974c.576 0 1.059.435 1.119 1.007zM8.625 10.5a.375.375 0 11-.75 0 .375.375 0 01.75 0zm7.5 0a.375.375 0 11-.75 0 .375.375 0 01.75 0z" />
				</svg>
				
				 <%
 	Map<Integer, Order> orders = (Map<Integer, Order>) session.getAttribute("orders");
 %> 
 <span class="quantity-cart " style="position: absolute;top:34%; right: 40%;border-radius: 99px; display: inline-block; font-weight: 500; transform : translate(-50%;-50%)"><%=(session.getAttribute("orders") != null) ? orders.size() : "0"%></span></a>
			</div>
		</div>
	</div>
	<div class="canvas__open">
		<i class="fa fa-bars"></i>
	</div>
</div>
</header>

