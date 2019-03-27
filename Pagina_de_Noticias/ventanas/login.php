<?php
    $accion = ($_GET['accion']);
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>| Responsive Homework | login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
    <!-- Header con menu de navegarion y logotipo -->
    <header class="navbar navbar-expand-sm navbar-expand-md navbar-dark bg-dark">
        <nav class="navbar">
            <a class="navbar-brand" href="../index.php"><img src="../img/logo.png" alt="Responsive Homework, Adaptive for Everyone"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">LogIn <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Contenedor principal -->
    <div class="container">
        <div class="row">
            <div class="col-sm-0 col-md-4"></div>
            <div class="col-sm-12 col-md-4">
                <h1>Ingresar</h1>
                <img src="../img/log.png" alt="" class="img-fluid max-width:100">
                <div class="form-group">
                    <form action="<?php 
                                    if($accion==1){
                                        echo " insertar.php"; 
                                    }else if($accion==2){
                                        echo "eliminar.php"; 
                                    } else if($accion==3){
                                        echo "modificar.php";
                                    } else{ echo "#" ; } ?>" method="post">
                        correo : <br>
                        <input class="form-control" type="email" name="correo">
                        <br>
                        Contraseña:<br>
                        <input class="form-control" type="password" name="password">
                        <br>
                        <input class="btn btn-primary" type="submit" value="Entrar">
                    </form>
                </div>
            </div>
            <div class="col-sm-0 col-md-4"></div>
        </div>
    </div>
    <!-- Footer -->
    <div class="footer-copyright text-center">
        ALberto, Joseph, Nestor, Hilario. Derechos reservados © 2019.
    </div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

</html>