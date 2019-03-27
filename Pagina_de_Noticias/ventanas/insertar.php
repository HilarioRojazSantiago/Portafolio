<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>| Responsive Homework | inertar</title>
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
                        <a class="nav-link" href="#">Insertar Noticia <span class="sr-only">(current)</span></a>
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
                <?php
                    $mail = $_POST['correo'];
                    $pass = $_POST['password'];
                    /*  Para conectar a la base de datos*/
                    $servername = "localhost";
                    $username = "root";
                    $password = "";
                    $dbname = "responsivehomeworkdb";
                    /* establece la conexion con la base de datos*/
                    $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
                    /* Prepara consulta */ 
                    $sql = "SELECT * FROM `autores` WHERE `correo` = '$mail';";
                    /* Ejecuta la consulta */
                    $result = $conn->query($sql);
                    if($result &&  $result->num_rows != 0){
                        $usuario = $result->fetch_assoc();
                        if($usuario['pass']==$pass){
                            session_start();
                            $_SESSION['user'] = $usuario['id'];
                            echo'
                                    <h1>Rellene los campos</h1>
                                    <h6>Todos son obligatorios</h6>
                                    <div class="form-group">
                                        <form action="insert.php" method="POST" enctype="multipart/form-data">
                                            Titulo: <br>
                                            <input class="form-control" type="text" name="titulo">
                                            Imagen:<br>
                                            <input class="form-control-file" type="file" accept="image/jpeg" name="imagen">
                                            Categoría:<br>
                                            <select name="categoria" class="form-control">
                                                <option value="1">Tecnología</option>
                                                <option value="2">Deportes</option>
                                                <option value="3">Videojuegos</option>
                                            </select>
                                            Texto:<br>
                                            <textarea class="form-control rounded-0" name="texto"></textarea>
                                            <br>
                                            <input class="btn btn-primary" type="submit" value="Subir Noticia">
                                        </form>
                                    </div>
                                    ';
                        }else{
                            if(session_status()==2){
                                session_destroy();
                            }
                            echo '<h1>El correo/ contraseña no es correcto</h1>
                                <a href="../index.php"><h2>Regresar a Home</h2></a>';
                        }
                    }else{
                        if(session_status()==2){
                            session_destroy();   
                        }
                        echo '<h1>El correo ingresado no está registrado</h1>
                            <a href="../index.php"><h2>Regresar a Home</h2></a>';
                    }
                    $conn->close();
                ?>
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