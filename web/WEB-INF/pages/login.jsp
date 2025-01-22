<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="intl"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Panteon Santa Rosa</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.css">
  
  <!-- MessageBox -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/messagebox.css"/>
<!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-message-box@3.2.2/dist/messagebox.min.css">
  <script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-message-box@3.2.2/dist/messagebox.min.js"></script>-->
  

  
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <!-- /.login-logo -->
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
      <a href="../../index2.html" class="h1"><b>SANTA ROSA</b></a>
    </div>
    <div class="card-body">
        <p class="login-box-msg"><b>S</b>istema <b>I</b>ntegral <b>P</b>anteon <b>S</b>anta<b> R</b>osa</p>
        <c:if test="${not empty error}">
            <div class="error" align="center">${error}</div>
        </c:if>
      <form action="j_spring_security_check" id="frmLogin" commandName="formLogin" method="POST">
        <div class="card-body">  
        <div class="form-group mb-3">
          <label for="loginInputUsuario">Usuario</label>
          <input type="text" id="username" name="j_username" class="form-control" placeholder="Usuario" autofocus>          
        </div>
        <div class="form-group mb-3">
          <label for="loginInputPassword">Contraseña</label>
          <input type="password" id="password" name="j_password" class="form-control" placeholder="Password">          
        </div>
        <div class="row">
          <div class="col-6">
            <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">Recordar</label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-6">
              <button type="submit" id="btnLogin" class="btn btn-primary btn-block">Iniciar Sesi&oacute;n</button>
          </div>
          <!-- /.col -->
        </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          
        </div>  
      </form>

      <p class="mb-1">
          <a href="forgot-password.html">Restaurar Contrase&ntilde;a</a>
      </p>      
    </div>
    <!-- /.card-body -->
  </div>
  <!-- /.card -->
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/messagebox.js" type="text/javascript"></script>
<!--<script src="dist/js/messagebox.min.js" type="text/javascript"></script>-->
<!-- jquery-validation -->
<script src="${pageContext.request.contextPath}/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-validation/additional-methods.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {        
//        $('#btnLogin').click(function(){            
//            var usuario = $("#username").val();
//            var password = $("#password").val(); 
//            
////            alert("usuario.length: "+usuario.length)
////            alert("password.length: "+password.length)
//            
//            $.MessageBox({
//                buttonDone   : "OK",
//                message: "Ingresa un usuario y una contraseña para ingresar..!"
//            }).done(function(){                       
//                    return false;
//                });
//            
//            return false;
//            
////            if((usuario.length == 0) & (password.length == 0)){                
////                $.MessageBox({
////                    buttonDone   : "OK",
////                    message: "Ingresa un usuario y una contraseña para ingresar..!"
////                }).done(function(){                       
////                    return false;
////                });
////                return false
////            }
//        })
        
        
        $.validator.setDefaults({
            submitHandler: function () {
              return true;
            }
        });
        $('#frmLogin').validate({
            rules: {
              j_username: {
                required: true                
              },
              j_password: {
                required: true,
                minlength: 4
              }
            },
            messages: {
              j_username: {
                required: "Favor de introducir el usuario"
              },
              j_password: {
                required: "Favor de introducir la contrasaeña",
                minlength: "La contraseña debe tener minimo 4 caracteres"
              }
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
              error.addClass('invalid-feedback');
              element.closest('.form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
              $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
          $(element).removeClass('is-invalid');
        }
        });
        
        
        
        
        
    });
</script>
</body>
</html>
