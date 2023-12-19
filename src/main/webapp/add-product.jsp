<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add product</title>
<!-- start include font and link framework -->
<%@ include file="link-css-font-include.jsp"%>

<!-- end include font and link framework -->
<link rel="stylesheet" href="css/admin-style.css" type="text/css" />
<script src="./resources/ckeditor/ckeditor.js"></script>

</head>
<body>

	<!-- start include header -->
	<%@ include file="nav-manage-admin.jsp"%>
	<!-- end include header -->
	<div class="container mt-4"
		style="display: flex; justify-content: center;">
		<div class="main-content">
			<form id="product-form" method="POST" action="AddProduct" enctype="multipart/form-data">
				<div class="wrap-field">
					<label>Name</label> <input type="text" name="name" value="" />
					<div class="clear-both"></div>
				</div>
				<div class="wrap-field">

					<label>Price</label> <input type="number" name="price"
						class="price" />
					<div class="clear-both"></div>
				</div>
				
				<div class="wrap-field">

					<label>Quantity</label> <input type="number" name="quantity"
						class="quantity" />
					<div class="clear-both"></div>
				</div>



				<div class="wrap-field mt-4">
					<label>Category </label>
					<div class="d-flex flex-column box-categories">
					</div>
					<div class="clear-both"></div>
				</div>
				
				<div class="wrap-field mt-3">
                <label>Image product : </label>
                <div class="right-wrap-field">
                    <img class="image" 
                        src="https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg" /><br />
                    <!-- <input type="hidden" name="image" /> -->
                    <input type="file" name="image" class="input-image"/>
                </div>

                <div class="clear-both"></div>
            	</div>


				<div class="wrap-field mt-5">
					<label>Description</label>
					<textarea id="product-content" name="desc"></textarea>
					<div class="clear-both"></div>
				</div>




				<button class="btn btn-primary my-5"
					style="margin-left: 142px;">Add</button>
			</form>
			<div class="clear-both"></div>
		</div>
	</div>
	</div>

	<!-- start include script -->
	<%@ include file="link-script-include.jsp"%>
	<!-- end include script -->

	<script>
            // Replace the <textarea id="editor1"> with a CKEditor
            // instance, using default configuration.
            CKEDITOR.replace('product-content');
    </script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
	
	//function regex image
	const imageFileRegex = /\.(webp|jpg|jpeg|png|gif|bmp)$/i;
    const inputImage = document.querySelector('.input-image');
    const imageMain = document.querySelector('.image')

    inputImage.addEventListener('change', function () {
        const file = this.files[0]; // Lấy tệp tin được chọn
        if (file) {
            const fileName = file.name;
            if (imageFileRegex.test(fileName)) {
                //set image when up success
                const imageUrl = URL.createObjectURL(file);
                imageMain.setAttribute('src', imageUrl);
            } else {
                inputImage.value = '';
                alert("File image invalid")
                imageMain.setAttribute('src', 'https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg');
            }
        }
    })
	
	
	/*---------------------
	js for avata nav admin
	------------------------- */
	const avata = document.querySelector('.avata');
	const boxAdmin = document.querySelector('.box-admin');
	avata.addEventListener('click', () => {
		boxAdmin.classList.toggle('d-none');
	});
	
	
	
	//=> categories 
	const boxCategories = document.querySelector('.box-categories');
	$.ajax({
			url : "./GetAllCategories",
			type : "POST",
			success: function(data){
				
				data.forEach(element => {
					const htmlItemCategory = `
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category_id" required
							id="flexRadioDefault1" value="` + element.id + `"> <label
							class="form-check-label" for="">` + element.name  + `</label>
					</div>
					`;
					boxCategories.insertAdjacentHTML('beforeend', htmlItemCategory);
				})
			},
			error: function(xhr){
				
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