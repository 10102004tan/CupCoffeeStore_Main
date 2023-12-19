<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header start -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light position-relative">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
          <img
            style="width: 40px"
            src="https://www.pma-india.org/assets/frontend/img/pma-images/project-management-blue.png"
            alt=""
          />
        </a>

        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="ManageAccount"
                >Manage Account</a
              >
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Manage ecommerce
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="ManageProduct">Manage Products</a></li>
                <li>
                  <a class="dropdown-item" href="ManageCategory">Manage Categories</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>

      <div class="d-flex align-items-start">
        <img class="avata" src="https://demos.themeselection.com/materio-bootstrap-html-admin-template/assets/img/avatars/17.png" style="width: 50px; border-radius: 99px; margin-right: 15px; cursor: pointer;" alt="" >
      </div>

      <div class="py-4 px-3 rounded-2 flex-column box-admin position-absolute bg-white d-none" style="width: 350px; top: 60px; right: 0; border: 1px solid #eee; display: flex">
        <a href="#" class="d-flex text-decoration-none align-items-center">
          <img  src="https://demos.themeselection.com/materio-bootstrap-html-admin-template/assets/img/avatars/17.png" style="width: 50px; border-radius: 99px; margin-right: 15px;" alt="" >
          <span class="fs-5 text-dark"><%=(session.getAttribute("nameAdmin")) == null ? "" : session.getAttribute("nameAdmin") %></span>
        </a>
        <a href="Logout" class="btn btn-dark mt-4">Logout</a>
      </div>
    </nav>
    
    