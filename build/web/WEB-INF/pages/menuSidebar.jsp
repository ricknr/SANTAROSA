<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
<!-- Brand Logo -->
<a href="index3.html" class="brand-link">
  <img src="dist/img/cementerio_icono.png" alt="Caritas Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
  <span class="brand-text font-weight-light"><b>SANTA ROSA</b></span>
</a>

<!-- Sidebar -->
<div class="sidebar">
  <!-- Sidebar user panel (optional) -->
  <div class="user-panel mt-3 pb-3 mb-3 d-flex">
    <div class="image">
      <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
    </div>
    <div class="info">
      <a href="#" class="d-block">${sessionScope.sNombreUsuario}</a>
    </div>
  </div>
    <!-- Sidebar Menu -->
  <nav class="mt-2">
    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
      <!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->      
      <li class="nav-item">
        <a href="entrar" class="nav-link">
          <i class="nav-icon fas fa-th"></i>
          <p>Dashboard<span class="right badge badge-danger">Inicio</span></p>
        </a>
      </li>
     <c:if test="${fn:contains(sessionScope.sRoles,'ROLE_PERMISSIONS') || fn:contains(sessionScope.sRoles,'ROLE_ADMIN')}">
     <li class="nav-item">
      <a href="${pageContext.request.contextPath}/cliente" class="nav-link">
         <i class="nav-icon fas fa-edit"></i>
          <p>Clientes</p>
      </a>
      </li>
      <li class="nav-item">
        <a href="pages/widgets.html" class="nav-link">
          <i class="nav-icon fas fa-edit"></i>
          <p>Pedidos</p>
        </a>
      </li>
      <li class="nav-item">
        <a href="${pageContext.request.contextPath}/contrato" class="nav-link">
          <i class="nav-icon fas fa-edit"></i>
          <p>Contratos</p>
        </a>
      </li>
      <li class="nav-item">
        <a href="pages/widgets.html" class="nav-link">
          <i class="nav-icon fas fa-edit"></i>
          <p>Bitacora Recibos</p>
        </a>
      </li>
      </c:if>
      <li class="nav-item">
        <a href="#" class="nav-link">
          <i class="nav-icon fas fa-book"></i>
          <p>Reportes<i class="right fas fa-angle-left"></i></p>
        </a>
        <ul class="nav nav-treeview">
          <li class="nav-item">
            <a href="pages/charts/chartjs.html" class="nav-link">
              <i class="far fa-circle nav-icon"></i>
              <p>Dirección</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="pages/charts/flot.html" class="nav-link">
              <i class="far fa-circle nav-icon"></i>
              <p>Administración</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="pages/charts/inline.html" class="nav-link">
              <i class="far fa-circle nav-icon"></i>
              <p>Casos</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="pages/charts/uplot.html" class="nav-link">
              <i class="far fa-circle nav-icon"></i>
              <p>Sistemas</p>
            </a>
          </li>
        </ul>
      </li>
      <li class="nav-item">
            <a href="logout" class="nav-link">
              <i class="nav-icon far fa-circle text-danger"></i>
              <p class="text">Cerrar Sesión</p>
            </a>
          </li>
      
      
    </ul>
  </nav>
  <!-- /.sidebar-menu -->
</div>
<!-- /.sidebar -->
</aside>