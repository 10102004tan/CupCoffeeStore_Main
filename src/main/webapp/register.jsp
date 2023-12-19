<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <!-- import link and tag meta -->
	<%@include file="link-css-font-include.jsp"%>
    <title>Register -SHOP</title>
</head>
<body>

	<!-- import header -->	 
	 <%@ include file="header-include.jsp" %>

   <!-- Start register form -->
    <div class="container py-5 mx-auto">
      <form action="Register" method="post">
      	<div class="card card0">
        <div class="d-flex flex-lg-row flex-column-reverse">
          <div class="card card1">
            <div class="row box-center">
              <div class="col-md-8 col-10 my-5">
                
                <h3 class="mb-5 text-center heading">Trung Nguyen VN</h3>
                <h6 class="msg-info fw-bold">Create account now !</h6>

				<div class="alert alert-danger alert-fail" style="display: none">Dang ki khong thanh cong</div>
                <div class="form-group">
                  <label class="form-control-label text-muted" required>Fullname</label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    placeholder="Fullname"
                    class="form-control"
                  />
                </div>

                <div class="form-group">
                
                  <label class="form-control-label text-muted">Email</label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="Email"
                    class="form-control email"
                  />
                  <p class="alert alert-email" style="display: none; color: red">Email da duoc lien ket voi tai khoan khac</p>
                </div>
                
                
                <div class="form-group">
                
                  <label class="form-control-label text-muted">Phone</label>
                  <input
                    type="phone"
                    id="phone"
                    name="phone"
                    placeholder="Phone"
                    class="form-control phone"
                  />
                 
                </div>

                <div class="form-group">
                  <label class="form-control-label text-muted " >Password</label>
                  <input
                    type="password"
                    id="password"
                    name="password" required
                    placeholder="Password"
                    class="form-control input-password"
                  />
                </div>

                <div class="form-group">
                  <label class="form-control-label text-muted"
                    >Password Confirm</label
                  >
                  <input
                    type="password" required
                    id="passwordConfirm"
                    name="passwordConfirm"
                    placeholder="Password Confirm"
                    class="form-control input-cfpassword"
                  />
                  <p id="errorText" style="margin-left: 10px"></p>
                </div>
                <div class="row justify-content-center my-3 px-3">
                  <button type="submit" class="form-button btnReg">Register</button>
                </div>

              </div>
            </div>
            <div class="bottom mb-5 form-text">
              <p class="sm-text mx-auto mb-3">
                Have account?<a href="Login" class=""> Login now </a>
              </p>
            </div>
          </div>
          <div class="card card2">
            <div class="my-auto mx-md-5 px-md-5 right">
              <h3 class="fw-bold">We are more than just a company</h3>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat.
              </p>
            </div>
          </div>
        </div>
      </div>
      </form>
    </div>
    
    <!-- end register form -->

   <!-- import footer -->	 
	 <%@ include file="footer-include.jsp" %>
	 
	 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <script >
 // Thêm event listener để kiểm tra khi người dùng nhập dữ liệu
	const inputPw = document.querySelector('.input-password');
	const inputCfPw = document.querySelector('.input-cfpassword');
  	const errorText = document.getElementById("errorText");
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
	}

	
	
	
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
	const btnReg = document.querySelector(".btnReg")
	const alertFail = document.querySelector(".alert-fail")
	btnReg.addEventListener('click', function() {
    const errorTextColor = errorText.style.color;
    const alertEmailDisplay = document.querySelector(".alert-email").style.display;
    console.log(errorTextColor)
    console.log(alertEmailDisplay)
    if (errorTextColor === "red" || alertEmailDisplay === "block") {
       
    	event.preventDefault();event.preventDefault();
    	alertFail.style.display = "block";
        
    }

	});
	
    </script>
    
    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery.nicescroll.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/jquery.countdown.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>