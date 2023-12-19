<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Entiny.Category"%>
<%@page import="java.util.ArrayList"%>
<!-- Data -->
<%
ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>

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
<title>Manage Category</title>
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
					<form class="search" action="FindCategory" method="get">
						<div class="row py-1 px-1">
							<div class="col-10 ">
								<input type="text" name="name" id="search" placeholder="Search"
									class="border-0 form-control">
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
						<button class="button btn btnAdd"
							style="background-color: #9055fd;">Add
							Category</button>
					</div>
				</div>

				<div class="table" style="overflow-x: auto;">
					<table class="border-1">
						<thead class="bg-light text-dark">
							<th>action</th>
							<th>id</th>
							<th class="px-4">name</th>
							<th>created_at</th>
							<th>updated_at</th>

						</thead>
						<tbody>

							<%
							for (Category category : categories) {
							%>
							<tr>
								<td>
									<div class="me-5 d-flex">
										<button class="btn btn-primary btnEdit me-3" type="button">Edit</button>
										<form action="DeleteCategory" method="post" onsubmit="return confirm('Delete category');">
											<button class="btn btn-danger" name="category_id" value="<%=category.getId()%>">Delete</button>
										</form>
									</div>
								</td>
								<td class="py-2 category-id"><%=category.getId()%></td>
								<td class="py-2 px-4">
									<div class="d-flex">
										<input class="form-control me-2 input-name " type="text"
											name="name" value="<%=category.getName()%> " disabled>
										<button class="btn btn-primary btnSave d-none">Save</button>
									</div>
								</td>
								<td class="py-2"><%=category.getCreated_at()%></td>
								<td class="py-2"><%=category.getUpdated_at()%></td>
							</tr>
							<%
							}
							%>
							
							<tr class="box-add-category d-none">
								<td></td>
								<td></td>
								<td class="py-2 px-4">
									<form action="AddCategory" method="post">
										<div class="d-flex">
										<input class="form-control me-2 input-add-name " type="text"
											name="name">
										<button class="btn btn-primary btnAddName" type="submit">Add</button>
									</div>
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>








		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script>
	/*---------------------
	js for avata nav admin
	------------------------- */

	const avata = document.querySelector('.avata');
	const boxAdmin = document.querySelector('.box-admin');
	
	
	//edit category
	const btnEdits = document.querySelectorAll('.btnEdit');
	
	btnEdits.forEach(element => {
		let varCheckInp = true;
		const btnSave = element.parentNode.parentNode.parentNode.querySelector('.btnSave');
		const inputName = element.parentNode.parentNode.parentNode.querySelector('.input-name');
		const id = element.parentNode.parentNode.parentNode.querySelector('.category-id').textContent.trim();
		element.addEventListener('click', function() {
	        varCheckInp = !varCheckInp;
	        inputName.disabled = varCheckInp;
	        btnSave.classList.toggle('d-none');
	    });
		
		btnSave.addEventListener('click', function(){
			$.ajax({
				url : "./UpdateCategory",
				type : "POST",
				data : {
					id : id,
					name : inputName.value,
				},
				success: function(data){
					if (data == true)
					{
						alert("Sua thanh cong");
						btnSave.classList.toggle('d-none');
						inputName.disabled = true;
					}
					else{
						alert("Sua that bai");
					}
				},
				error: function(xhr){
					
				}
			});
		})
	})
	
	
	
	
	
	avata.addEventListener('click', () => {
		boxAdmin.classList.toggle('d-none');
	});
	
	
	//add
	const btnAdd = document.querySelector('.btnAdd');
	const boxAddCategory = document.querySelector('.box-add-category');
	btnAdd.addEventListener('click', function(){
		boxAddCategory.classList.toggle('d-none');
	});
	
	</script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous">

  
  </script>
</body>
</html>
