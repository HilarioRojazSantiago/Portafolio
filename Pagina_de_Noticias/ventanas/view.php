<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>| Responsive Homework | listar noticias</title>
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
                        <a class="nav-link" href="../index.php">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a href="view.php" class="nav-link">Listar Noticias</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Noticias
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="../principal.php?cat=1">Tecnología</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="../principal.php?cat=2">Deportes</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="../principal.php?cat=3">Videojuegos</a>
                        </div>
                    </li>
                    <!-- Para ingresar -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Ingresar
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="login.php?accion=1">insertar Noticia</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="login.php?accion=2">eliminar Noticia</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="login.php?accion=3">modificar Noticia</a>
                        </div>
                    </li>

                </ul>
            </div>
        </nav>
    </header>

    <!-- Contenedor principal -->
    <div class="container">
        <div class="row">
            <!-- Contenedor de noticia más vista -->
            <div class="col-sm-12 col-md-12">
                <table class="table table-striped table-light">
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Hora</th>
                            <th scope="col">Likes</th>
                        </tr>
                    </thead>
                    <?php
                        $nrow=1;
                        /*  Para conectar a la base de datos*/
                        $servername = "localhost";
                        $username = "root";
                        $password = "";
                        $dbname = "responsivehomeworkdb";
                        /* establece la conexion con la base de datos*/
                        $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
                        /* Prepara consulta */ 
                        $sql = "SELECT * FROM `noticias`;";
                        /* Ejecuta la consulta */
                        $result = $conn->query($sql);
                        // Imprime todo
                         if ($result->num_rows > 0){
                              echo "<tbody>";
                              while($row = $result->fetch_assoc()){
                               echo '<tr>
                               <td> ' . $row["id"]. '</td>
                               <td> <a href="notice.php?id='.$row["id"].'">' . $row["titulo"]. '</a></td>
                               <td> ' . $row["fecha"]. '</td>
                               <td> ' . $row["hora"]. '</td>
                               <td> ' . $row["likes"]. '</td>
                               </tr>';
                              }
                              echo "</tbody>";
                             }
                        $conn->close();
                    ?>
                </table>
            </div>
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