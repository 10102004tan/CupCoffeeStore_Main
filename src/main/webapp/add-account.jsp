<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add account</title>
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
			<form id="product-form" method="POST" action="AddAccountController">
				<div class="wrap-field">
					<label>Name</label> <input type="text" name="name" value="" />
					<div class="clear-both"></div>
				</div>
				<div class="wrap-field">
					
					<label>Email</label>
					<input type="email" name="email" class="email"/>
					
					<div class="clear-both"></div>
					<p class="alert alert-email" style="display: none; color: red">Email da duoc lien ket voi tai khoan khac</p>
				</div>

				<div class="wrap-field">
					<label>Password</label> <input class="input-password"
						type="password" name="password" />
					<div class="clear-both"></div>
				</div>

				<div class="wrap-field">
					<label>Confirm Password</label> <input class="input-cfpassword"
						type="password" />
					
					<div class="clear-both"></div>
					<p id="errorText" style="margin-left: 10px"></p>
				</div>


				<div class="wrap-field">
					<label>Phone </label> <input type="text" name="phone" />
					<div class="clear-both"></div>
				</div>

				<div class="wrap-field mt-4">
					<label>Role </label>
					<div class="d-flex flex-column box-role">
						<div class="form-check">
								<input class="form-check-input" type="radio"
									name="role" id="flexRadioDefault1" value="0"> 
	                                <label
									class="form-check-label" for="">Admin
							</label>
					</div>
					
					<div class="form-check">
								<input class="form-check-input" type="radio" checked
									name="role" id="" value="1">
	                                <label
									class="form-check-label" for="">User
							</label>
					</div>
					</div>
					<div class="clear-both"></div>
				</div>

				






				<button class="btn btn-primary my-5 btnAdd"
					style="margin-left: 142px;">Add</button>
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
		if (inputPw.value !== inputCfPw.value) {
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
	
	const email = document.querySelector(".email");
	email.addEventListener("input", function(){
		$.ajax({
			url : "./GetAllEmails",
			type : "POST",
			data : {
				email : email.value,
			},
			success: function(data){
				const alertEmail = document.querySelector(".alert-email");
				if (data == false)
				{
					alertEmail.style.display = "block";
				}
				else{
					alertEmail.style.display = "none";
				}
			},
			error: function(xhr){
				
			}
		});
	});
	
	
	//xu ly btn
	btnAdd.addEventListener('click', function() {
    const errorTextColor = errorText.style.color;
    const alertEmailDisplay = document.querySelector(".alert-email").style.display;
    console.log(errorTextColor)
    console.log(alertEmailDisplay)
    if (errorTextColor === "red" || alertEmailDisplay === "block") {
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