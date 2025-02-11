<%-- 
    Document   : AgregarCliente
    Created on : 27-ene-2025, 12:20:46
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
.content-wrapper {
  max-width: 100%; /* Asegúrate de usar todo el ancho del contenedor padre */
  padding: 20px;
  background-color: #f9f9f9; /* Opcional para visibilidad */
}

.card.card-primary {
  width: 100%; /* Ajusta el ancho al contenedor */
  max-width: 250%; /* Define un ancho máximo */
  min-height: 400px; /* Define una altura mínima */
  padding: 20px;
}

</style>
<!--Header start--> 
    <jsp:include page="header.jsp" /> 
 <!--Header end--> 
 <!--sidebar start--> 
    <jsp:include page="menuSidebar.jsp" />
 <!--sidebar end-->
<div class="content-wrapper" style="min-height: 1157.2px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Agregar Cliente</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/cliente">Cliente</a></li>
              <li class="breadcrumb-item active">Agregar Cliente</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- left column -->
          <div class="col-md-6">
            <!-- general form elements -->
            <div class="card card-primary">
    
             

              <div class="card-body">
                <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Nombre">
                </div>

                <div class="input-group mb-3">
                  <input type="text" class="form-control" placeholder="Apellido Paterno">
                </div>
                  
                <div class="input-group mb-3">
                  <input type="text" class="form-control" placeholder="Apellido Materno">
                </div>
  
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Razon Social">
                 </div>
                  <br>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                  </div>
                  <input type="email" class="form-control" placeholder="Email">
                </div>

                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="C.F.D.I.">
                  <div class="input-group-append">
                    <span class="input-group-text"><i class="fas fa-check"></i></span>
                  </div>
                </div>
                  
                  <h6>¿Ocupa Factura?</h6>
                  
                   <div class="form-check">
                          <input class="form-check-input" type="radio" name="radio1">
                          <label class="form-check-label">Si</label>
                        </div> 
                  <div class="form-check">
                          <input class="form-check-input" type="radio" name="radio1">
                          <label class="form-check-label">No</label>
                        </div> 
                  <br>
                  
                  <div class="">
                  <button type="submit" class="btn btn-info">Registrar</button>
                  <button type="submit" class="btn btn-default float-right">Cancelar</button>
                </div>
                    <!-- /input-group -->
                  </div>
                  <!-- /.col-lg-6 -->
                </div>
                <!-- /.row -->
                  </section>
    <!-- /.content -->
  </div>
  <!-- Footer start--> 
    <jsp:include page="footer.jsp" /> 
 <!-- Footer end-->

