<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="header.jsp" />

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .table-container {
                position: relative; 
                top: -150px;    
                left: 280px;  
                width: 80%;   
                height: 85vh;     
                display: flex;
                justify-content: center; 
                align-items: center;  
            }

            .table {
                width: 100%;     
                height: 100%;       
                table-layout: fixed;
            }
            .button-container2 {
                position: absolute;   
                top: 10%;              
                left: 86%;               
                transform: translateX(-50%);
                z-index: 10;            
                margin-bottom: 20px;     
            }
            .button-container {
                position: absolute;       
                top: 10%;                
                left: 92%;               
                transform: translateX(-50%); 
                z-index: 10;             
                margin-bottom: 20px;      
            }
            
            .combo-container {
    position: absolute;
    top: 10%;
    left: 25%;
    transform: translateX(-50%);
    z-index: 10;
    margin-bottom: 20px;
            .pagination {
                display: flex;
                justify-content: center; 
                margin-top: 20px;
            }

            .pagination a {
                color: #007bff;
                text-decoration: none;
                padding: 8px 16px;
                border: 1px solid #ddd;
                margin: 0 2px;
                border-radius: 4px;
            }

            .pagination a:hover {
                background-color: #f1f1f1;
            }

            .pagination .active a {
                background-color: #007bff;
                color: white;
            }

             .pagination-container {
                position: relative;
                top: 20px; 
            }

            .pagination-container.left {
                left: 20px; 
            }

            .pagination-container.right {
                right: 100px; 
            }

            .pagination-container.center {
                left: 50%;
                transform: translateX(-50%); 
            }
        </style>
    </head>
    <body>
        <!-- Sidebar Start -->
        <jsp:include page="menuSidebar.jsp" />
        <!-- Sidebar End -->
        
         <div class="combo-container">
            <select class="form-select">
                <option selected>Seleccione una opción</option>
                <option value="1">Opción 1</option>
                <option value="2">Opción 2</option>
                <option value="3">Opción 3</option>
            </select>
        </div>
        
 <div class="button-container2">
     <a href="${pageContext.request.contextPath}/agregarContrato">
            <button class="btn btn-primary">Agregar</button>
            </a>
        </div>
            
        <div class="button-container">
            <button class="btn btn-danger">Eliminar</button>
        </div>

        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-bordered table-striped text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Pedido</th>
                            <th>Folio</th>
                            <th>Referencia</th>
                            <th>UUID Pedido</th>
                            <th>Fecha de Alta</th>
                            <th>Usuario de Alta</th>
                            <th>Fecha de Actualización</th>
                            <th>Usuario de Actualización</th>
                         </tr>
                        
                    </thead>
                    <tbody>
                    <tr>
    <td>1</td>
    <td>Pedido 001</td>
    <td>F12345</td>
    <td>Ref-A001</td>
    <td>UUID-123e4567</td>
    <td>2025-01-01</td>
    <td>admin</td>
    <td>2025-01-10</td>
    <td>admin_actualiza</td>
</tr>
<tr>
    <td>2</td>
    <td>Pedido 002</td>
    <td>F67890</td>
    <td>Ref-B002</td>
    <td>UUID-123e4567</td>
    <td>2025-01-05</td>
    <td>usuario1</td>
    <td>2025-01-15</td>
    <td>usuario2</td>
</tr>
<tr>
    <td>3</td>
    <td>Pedido 003</td>
    <td>F11223</td>
    <td>Ref-C003</td>
    <td>UUID-123e4567</td>
    <td>2025-01-08</td>
    <td>usuario3</td>
    <td>2025-01-20</td>
    <td>usuario4</td>
</tr>

                    </tbody>
                </table>
            </div>
        </div>
<div class="pagination-container">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Anterior</a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Siguiente</a></li>
                </ul>
            </div>
        </div>
    </body>
    <footer>
        <jsp:include page="footer.jsp" />
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</html>
