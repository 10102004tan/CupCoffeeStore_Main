<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <!-- import link and tag meta -->
	<%@include file="link-css-font-include.jsp"%>
    <title>Login -SHOP</title>
</head>
<body>

	<!-- import header -->	 
	 <%@ include file="header-include.jsp" %>

    <div class="container py-5 mx-auto">
     <form action="Login" method="post">
     	 <div class="card card0">
        <div class="d-flex flex-lg-row flex-column-reverse">
          <div class="card card1">
            <div class="row box-center">
              <div class="col-md-8 col-10 my-5">
               
                <h3 class="mb-5 text-center heading">Trung Nguyen VN</h3>
                <h6 class="msg-info fw-bold">Please login to your account</h6>
                
                <%if (request.getAttribute("mess") != null ){ %>
                <div class="alert alert-danger"><%=request.getAttribute("mess")%></div>
                <%} %>
                <div class="form-group">
                  <label class="form-control-label text-muted" id="email">Email</label>
                  <input
                    type="email" required
                    id="email"
                    name="email"
                    placeholder="Email"
                    class="form-control"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label text-muted">Password</label>
                  <input
                    type="password" required
                    id="psw"
                    name="password"
                    placeholder="Password"
                    class="form-control"
                  />
                </div>
                <div class="row justify-content-center my-3 px-3">
                  <button class="form-button">Login</button>
                </div>
                <div class="row justify-content-center my-2">
                  <a href="#" class="text-muted">Forgot Password?</a
                  >
                </div>
              </div>
            </div>
            <div class="bottom  mb-5 form-text">
              <p  class="sm-text mx-auto mb-3">
                Don't have an account?<a href="register.jsp" class="">
                  Create new
                </a>
              </p>
            </div>
          </div>
          <div class="card card2">
            <div class="my-auto mx-md-5 px-md-5 right">
              <h3 class="fw-bold" >We are more than just a company</h3>
              <p
                >Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco
                laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
          </div>
        </div>
      </div>
     </form>
    </div>

   <!-- import footer -->	 
	 <%@ include file="footer-include.jsp" %>
	 
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