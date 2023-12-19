<%@page import="Entiny.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Entiny.Account"%>
<%@page import="java.util.ArrayList"%>
 <!-- Data -->
 <% ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<title>Manage products</title>
<style>
body, a, button {
	font-family: 'Poppins', sans-serif;
	position: relative;
}

.action {
	position: relative;
	padding-left: 20px;
	display: inline-block;
	color: #747070;
}

.action::after {
	content: "";
	width: 12px;
	height: 12px;
	border-radius: 99px;
	background-color: #1fe11e;
	position: absolute;
	top: 34px;
	left: 55px;
	transform: translateY(50%);
}

.disabled {
	position: relative;
	padding-left: 20px;
	display: inline-block;
}

.disabled::after {
	content: "";
	width: 12px;
	height: 12px;
	border-radius: 99px;
	background-color: #f72323;
	position: absolute;
	top: 34px;
	left: 55px;
	transform: translateY(50%);
}

.button {
	padding: 10px 20px;
	color: #fff;
	margin-right: 20px;
	text-decoration: none;
	border-radius: 8px;
	font-weight: 500;
}

.button:hover {
	color: #fff;
}

thead {
	padding: 10px 0;
}

.manager .search {
	border: 1px solid #eee;
	padding: 5px 8px;
	border-radius: 8px;
}

.manager .search button {
	background-color: transparent;
	border-left: 1px solid #eee;
}

tbody tr {
	border-bottom: 1px solid #eee;
}

.details-item {
	box-shadow: 0 0 11px 2px #eee;
}

.border-radius-image {
	border-radius: 99px;
}

.details-item ul li div span:last-child {
	color: #747070;
	font-size: 16px;
}

.user-add {
	max-width: 700px;
}

.avata {
	cursor: pointer;
}

.box-admin {
	z-index: 999;
}

.radio-item {
	border: 1px solid #000; border-radius: 99px; width: 30px; height: 30px; align-items: center;justify-content: center;
	cursor: pointer;
	text-decoration: none;
	color: #000;
}

.actived {
background: #000;
color : #fff;
}

td {
	white-space: nowrap;
}
</style>

</head>
<body>
	<%@ include file="nav-manage-admin.jsp"%>
	<!-- content -->
	<div class="container-fluid">
		<div class="main row mt-4">
			<div class="manager">
				<div
					class="w-100 d-flex justify-content-between align-items-center py-4">
					<form class="search" action="FindProduct" method="get">
						<div class="row py-1 px-1">
							<div class="col-10 ">
								<input type="text" name="keyword" id="search"
									placeholder="Search" class="border-0 form-control">
							</div>
							<div class="col-2">
								
								<button class="bg-white border-0" type="submit">
									<svg style="width: 30px;" xmlns="http://www.w3.org/2000/svg"
										fill="none" viewBox="0 0 24 24" stroke-width="1.5"
										stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round"
											stroke-linejoin="round"
											d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" />
                    </svg>
								</button>
							</div>
						</div>
					</form>
					
					<div>
						<a class="btn btn-primary" href="AddProduct"
							>Add
							Product</a>
					</div>
				</div>

				
			</div>
		</div>
		
		<% if (session.getAttribute("mess") != null) {%>
		<div class="alert alert-success w-25"><%=session.getAttribute("mess")%> </div>
		<%
		session.removeAttribute("mess");
		} %>
		<div class="table w-100" style="overflow-x: auto ">
			<table class="w-100 border-1">
					<thead class="bg-light text-dark">
						<th>action</th>
						<th>id</th>
						<th>image</th>
						<th>name</th>
						<th>created_at</th>
						<th>updated_at</th>
					</thead>
					<tbody >
						<% for (Product product : products) {%>
						<tr>
							<td>
							<div class="me-5 d-flex">
								<a class="btn btn-primary me-2"
								href="UpdateProduct?id=<%=product.getId()%>">Update</a>
								<form  action="DeleteProduct" method="post" onsubmit=" return confirm('Confirm delete')">
									<button class="btn btn-danger" value="<%=product.getId()%>" type ="submit" name="product-id">Delete</button>
								</form>
							</div>
							</td>
							<td class="py-2"><%=product.getId() %></td>
							<td class="py-2">
								<div>
									<img  style="width: 70px;height: 70px; object-fit: cover" alt="" src="images/<%=product.getUrlImage()%>">
								</div>
							</td>
							<td class="py-2"><%=product.getName() %></td>
							<td class="py-2"><%=product.getCreated_at() %></td>
							<td class="py-2"><%=product.getUpdated_at() %></td>
							
						</tr>
						<% } %>
						
					</tbody>
				</table>
		</div>

		
		
		<div class="w-100 d-flex justify-content-center">
			<div class="d-flex mt-5 ">
		
			<%
				
				String url =(String)request.getAttribute("url");
				int currentPageforPre = 0;
				int currentPageNext = 0;
				int currentPage = 1;
				int endPage = Integer.parseInt(request.getAttribute("endPage").toString());
				if (request.getParameter("currentPage") != null)
				{
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				currentPageforPre = (currentPage == 1 ) ? 1 : currentPage -1 ;
				currentPageNext = (currentPage == endPage) ? endPage : currentPage + 1;
				%>
				<!-- xu ly -->
				<% if (endPage != 1) {%>
				<% if (currentPage != 1) { %>
				<a class="me-4 text-center d-flex radio-item" style="" href="<%=url%>currentPage=<%=1%>"><<</a>
				<a class="me-4 text-center d-flex radio-item" style="" href="<%=url%>currentPage=<%=currentPageforPre%>"><</a>
				<%}%>
				<%for (int i = 1 ; i <= endPage ; i++) {
					
					if (i >= (currentPage - 2) && i <= (currentPage + 2)){
				%>
					<a class="me-4 text-center d-flex radio-item <%=(i == currentPage) ? "actived" : "" %> " style="" href="<%=url%>currentPage=<%=i%>"><%=i%></a>
				<%} %>
				<%} %>
				
				<% if (currentPage != endPage) { %>
				<a class="me-4 text-center d-flex radio-item" style="" href="<%=url%>currentPage=<%=currentPageNext%>">></a>
				<a class="me-4 text-center d-flex radio-item" style="" href="<%=url%>currentPage=<%=endPage%>">>></a>
				<%}%>
				<%} %>
		</div>
		</div>
		<script>
	/*---------------------
	js for avata nav admin
	------------------------- */

	const avata = document.querySelector('.avata');
	const boxAdmin = document.querySelector('.box-admin');
	
	
	avata.addEventListener('click', () => {
		boxAdmin.classList.toggle('d-none');
	});

	</script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous">

  
  </script>
</body>
</html>
    