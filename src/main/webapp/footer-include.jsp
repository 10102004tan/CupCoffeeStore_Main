<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- Footer Section Begin -->
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="footer__about">
              <div class="footer__logo">
                <a href="#"><img src="img/footer-logo.png" alt="" /></a>
              </div>
              <p>
                The customer is at the heart of our unique business model, which
                includes design.
              </p>
              <a href="#"><img src="img/payment.png" alt="" /></a>
            </div>
          </div>
          <div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
            <div class="footer__widget">
              <h6>Shopping</h6>
              <ul>
                <li><a href="#">Clothing Store</a></li>
                <li><a href="#">Trending Shoes</a></li>
                <li><a href="#">Accessories</a></li>
                <li><a href="#">Sale</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-2 col-md-3 col-sm-6">
            <div class="footer__widget">
              <h6>Shopping</h6>
              <ul>
                <li><a href="#">Contact Us</a></li>
                <li><a href="#">Payment Methods</a></li>
                <li><a href="#">Delivary</a></li>
                <li><a href="#">Return & Exchanges</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
            <div class="footer__widget">
              <h6>NewLetter</h6>
              <div class="footer__newslatter">
                <p>
                  Be the first to know about new arrivals, look books, sales &
                  promos!
                </p>
                <form action="#">
                  <input type="text" placeholder="Your email" />
                  <button type="submit">
                    <span class="icon_mail_alt"></span>
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="footer__copyright__text">
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
              <p>
                Copyright ©
                <script>
                  document.write(new Date().getFullYear());
                </script>
                2020 All rights reserved | This template is made with
                <i class="fa fa-heart-o" aria-hidden="true"></i> by
                <a href="https://colorlib.com" target="_blank">Colorlib</a>
              </p>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
      <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
          <input type="text" id="search-input" placeholder="Search here....." />
        </form>
      </div>
      
    
    </div>
    <!-- Search End -->
    
     <!-- Search Begin -->
    <div class="search-model">
      <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
          <input type="text" id="search-input" placeholder="Search here....." />
        </form>
      </div>
    </div>
    <!-- Search End -->
    
     
     <script>
    	//format price
    	const priceToFormatVnd = document.querySelectorAll('.format-price');
    	priceToFormatVnd.forEach(element => {
    		element.textContent = Number(element.textContent.trim()).toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    	})
    	
    	
    	//find product ajax
    	const inputKey = document.querySelector('.input-key');
    	const boxFind = document.querySelector('.box-find');
    	inputKey.addEventListener("input", function(){
    		
    		if (inputKey.value != "") {
    		$.ajax({
    			url : "./GetProductByKeyAjax",
    			type : "GET",
    			data : {
    				key : inputKey.value,
    			},
    			success: function(data){
    	            let htmlContent = createFindBox(data);
    	            htmlContent+=`<a href="FindProductByKey?key=`+ inputKey.value +`"  class="text-center">Xem them</a>`;
    	            boxFind.innerHTML = htmlContent;
    			},
    			error: function(xhr){
    				
    			}
    		});
    		}
    		else{
    			boxFind.innerHTML = "";
    		}
    	})
    	
    	function  createFindBox(data) {
    		let html = "";
			data.forEach(item => {
				html+=`<li class="list-group-item">
					<a href="./ShowDetails?id=` + item.id +`" class="d-flex">
					<div>
				<img style="width: 50px; height: 50px; object-fit: cover" alt="" src="images/` + item.urlImage +`">
				</div>
				<div>
				<span style="margin-left: 70px;">` + item.name + `</span>
				</div>
				</a>
			</li>`;
			})
			
			
			
			
			return html;
		}
    	
    	
    	let currentURL = window.location.href;
    	const listLi = document.querySelector('.header__menu').querySelectorAll('li');

    	let parts = currentURL.split('/');
    	let lastPart = parts[parts.length - 1];

    	listLi.forEach(element => {
    	  let anchor = element.querySelector('a'); // Lấy thẻ 'a' bên trong thẻ 'li'
    	  let href = anchor.getAttribute('href'); // Lấy giá trị thuộc tính 'href' của thẻ 'a'

    	  // Kiểm tra xem 'lastPart' có tồn tại trong 'href' không
    	  if (href.includes(lastPart)) {
    	    element.classList.add('active');
    	  }
    	});

    	
    	
     </script>