<%@page import="Entiny.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit account</title>
<!-- start include font and link framework -->
<%@ include file="link-css-font-include.jsp"%>
<!-- end include font and link framework -->
<link rel="stylesheet" href="css/admin-style.css" type="text/css" />

</head>
<body>

	<!-- start include header -->
	<%@ include file="nav-manage-admin.jsp"%>
	<!-- end include header -->
	<div class="container mt-4" style="display: flex; justify-content: center;">
		<div class="main-content">
			<form id="product-form" method="POST" action="UpdateAccount">
				<div class="wrap-field">
					<input type="hidden" name="id" value="<%=request.getParameter("id")%>" id="account-id"/>
					<div class="clear-both"></div>
				</div>
				<div class="wrap-field">
					<label>Name</label> <input type="text" name="name" id="account-name"/>
					<div class="clear-both"></div>
				</div>
				<div class="wrap-field">
					<label>Email</label> 
					<input type="hidden" name="email" class="account-email" />
					<input type="email" class="account-email"  disabled/>
					<div class="clear-both"></div>
					
				</div>

				<div class="wrap-field">
					<label>Password</label> <input class="input-password"
						type="text" name="password" id="account-pw"
						value=""
						/>
					<div class="clear-both"></div>
				</div>

				<div class="wrap-field">
					<label>Confirm Password</label> 
					<input class="input-cfpassword" id="account-cfpw"
					value=""
						type="text" />
					
					<div class="clear-both"></div>
					<p id="errorText" style="margin-left: 10px"></p>
				</div>


				<div class="wrap-field">
					<label>Phone </label> <input type="text" name="phone" value="" id="account-phone"/>
					<div class="clear-both"></div>
				</div>

				<div class="wrap-field mt-4">
					<label>Role </label>
					<div class="d-flex flex-column box-role">
						<div class="form-check">
								<input class="form-check-input" type="radio" 
									id="account-role-admin"
									name="role" id="flexRadioDefault1" value="0"> 
	                                <label
									class="form-check-label" for="">Admin  
							</label>
					</div>
					
					<div class="form-check">
								<input class="form-check-input" type="radio" 
									id="account-role-user"
									name="role" id="" value="1"
									
									>
									
	                                <label
									class="form-check-label" for="">User 
							</label>
					</div>
					</div>
					<div class="clear-both"></div>
				</div>
				
				
				<div class="wrap-field mt-4">
					<label>Status </label>
					<div class="d-flex flex-column box-role">
						<div class="form-check">
								<input class="form-check-input" type="radio"
									id="account-status-1"
									name="status"  value="1"> 
	                                <label
									class="form-check-label" for="">Action
							</label>
					</div>
					
					<div class="form-check">
								<input class="form-check-input" type="radio" 
								
									id="account-status-0"
									name="status" id="" value="0">
	                                <label
									class="form-check-label" for="">Disabled
							</label>
					</div>
					</div>
					<div class="clear-both"></div>
				</div>
				<button class="btn btn-primary my-5 btnAdd"
					style="margin-left: 142px;">Update</button>
			</form>
			<div class="clear-both"></div>
		</div>
	</div>
	</div>

	<!-- start include script -->
	<%@ include file="link-script-include.jsp"%>
	<!-- end include script -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
	/*---------------------
	js for avata nav admin
	------------------------- */
	const avata = document.querySelector('.avata');
	const boxAdmin = document.querySelector('.box-admin');
	avata.addEventListener('click', () => {
		boxAdmin.classList.toggle('d-none');
	});
	
	
	
	//lay du lieu bang id
	const accountID = document.getElementById("account-id").value;
	const accountName = document.getElementById("account-name");
	const accountEmail = document.querySelectorAll('.account-email');
	const accountPw = document.getElementById("account-pw");
	const accountCfPw = document.getElementById("account-cfpw");
	const accountPhone = document.getElementById("account-phone");
	const accountRoleAdmin = document.getElementById("account-role-admin");
	const accountRoleUser = document.getElementById("account-role-user");
	const accountStatus1 = document.getElementById("account-status-1");
	const accountStatus0 = document.getElementById("account-status-0");
	$.ajax({
			url : "./GetAccountById",
			type : "POST",
			data : {
				id : accountID,
			},
			success: function(data){
				accountName.value = data.name;
				accountEmail.forEach(element => {
					element.value = data.email;
				   });
				accountPw.value = data.password;
				accountCfPw.value = data.password;
				accountPhone.value = data.phone;
				if (data.role == 0)
					{
					accountRoleAdmin.checked = true;
					}
				else{
					accountRoleUser.checked = true;
				}
				
				if (data.status == 0)
				{
					accountStatus0.checked = true;
				}
				else{
					accountStatus1.checked = true;
				}
				},
				error: function(xhr){
				
			}
		});
	
	
	
	
	
	// Thêm event listener để kiểm tra khi người dùng nhập dữ liệu
	const inputPw = document.querySelector('.input-password');
	const inputCfPw = document.querySelector('.input-cfpassword');
  	const errorText = document.getElementById("errorText");
  	const btnAdd = document.querySelector(".btnAdd");
  	inputPw.addEventListener("input", validatePassword);
  	inputCfPw.addEventListener("input", validatePassword);
	//xu ly password
	function validatePassword(checkPw){
	if(inputCfPw.value != "")
	{
		if (inputPw.value !== inputCfPw.value){
		    errorText.textContent = "Passwords do not match!";
		    errorText.style.color = "red"
		  	} else {
		    // Passwords match, proceed with form submission or other actions
		    errorText.textContent = "Passwords match!";
		    errorText.style.color = "#00a67d";
		  	}
		}
	else{
	}
  	
	}
	
	
	
	//function checkEmailInDB
	
	
	//xu ly btn
	btnAdd.addEventListener('click', function() {
    const errorTextColor = errorText.style.color;
    console.log(errorTextColor)
    if (errorTextColor === "red") {
        alert("Thong tin khong hop le")
        event.preventDefault();
    }

	});
	</script>
	
	</script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous">

  
  </script>
</body>
</html>