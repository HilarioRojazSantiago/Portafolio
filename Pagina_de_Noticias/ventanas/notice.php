<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>| Responsive Homework |</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../js/maslike.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
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
                        <a href="ventanas/view.php" class="nav-link">Listar Noticias</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Noticias
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="../princiap.php?cat=1">Tecnología</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="../princiap.php?cat=2">Deportes</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="../princiap.php?cat=3">Videojuegos</a>
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
            <!-- Contenedor de noticia la noticia -->
            <div class="container col-sm-0 col-md-2"></div>
            <div class="col-sm-12 col-md-8">
               <?php
                $idNoticia = $_GET['id'];
                /*  Para conectar a la base de datos*/
                $servername = "localhost";
                $username = "root";
                $password = "";
                $dbname = "responsivehomeworkdb";
                $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
                // Prepara consulta
                $sql = "SELECT * FROM `noticias` WHERE `id`=".$idNoticia.";";
                // Ejecuta la consulta likes
                $result = $conn->query($sql);
                if($result &&  $result->num_rows != 0){
                    $noticia = $result->fetch_assoc();
                    $base64Img = $noticia['imagen'];
                    $base64Img = base64_decode($base64Img);
                    file_put_contents('../img/notiImg'.$noticia['id'].'.png', $base64Img);
                    $sql2 = "SELECT * FROM `autores` WHERE `id`=".$noticia['idAutor'].";";
                    $result2 = $conn->query($sql2);
                    $autor = $result2->fetch_assoc();
                    echo '
                        <section class="container mostViewed">
                            <h1 class="noticeTitle">'.$noticia['titulo'].'</h1>
                            <picture class="noticeImg">
                                <img class="img-fluid notiImg max-width:100%" src="../img/notiImg'.$noticia['id'].'.png" alt="noticeImg">
                            </picture>
                            <p class="noticeAutor">por '.$autor['nombre'].' '.$autor['apellido'].' el '.$noticia['fecha'].' a las '.$noticia['hora'].'hrs.</p>
                            <div class="dropdown-divider"></div>
                            <div class="textContainer text-wrap text-justify">'.$noticia['texto'].'</div>
                        </section>
                        <button type="button" class="btn btn-success" onclick="like('.$noticia['id'].');">('.$noticia['likes'].')like++</button>
                        ';
                }
                $conn->close();
            ?>
            </div>
            
            <div class="container col-sm-0 col-md-2"></div>
        </div>
        <!-- Comentarios -->
        <div class="row">
            <div class="container col-sm-0 col-md-2"></div>
            <div class="container col-sm-0 col-md-8">
                <h3>Comentarios</h3>
                <form action="comment.php?id=<?php echo $noticia['id'];?>" class="form-group" method="post">
                    <label for="nameCom">Nombre</label>
                    <input type="text" class="form-control" name="nombreC">
                    <label for="textCom">Deja tu comentario</label>
                    <textarea name="textoC" class="form-control" rows="3"></textarea>
                    <button type="submit" class="btn btn-primary">Comentar</button>
                </form>
                <div class="dropdown-divider"></div>
                <?php
                    $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
                    // Prepara consulta
                    $sql = "SELECT * FROM `comentarios` WHERE `idNoticia`=".$noticia['id']." ORDER BY `fecha` DESC, `hora` DESC LIMIT 5;";
                    // Ejecuta la consulta 
                    $result = $conn->query($sql);
                    if($result &&  $result->num_rows != 0){
                        while($comentarios = $result->fetch_assoc()){
                            echo '
                                <div class="comment">
                                    <h6>'.$comentarios['nombre'].', '.$comentarios['fecha'].' @ '.$comentarios['hora'].'</h6>
                                    <p>'.$comentarios['texto'].'</p>
                                    <div class="dropdown-divider"></div>
                                </div>
                                ';
                        }
                    }
                    $conn->close();
                ?>
            </div>
            <div class="container col-sm-0 col-md-2"></div>
        </div>
        <!-- Contenedor de las siguientes más vistas -->
        <div class="row"><h3>Las más recientes...</h3></div>
        <div class="row">
            <!-- Contenedor -->
            <?php
                $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
                // Prepara consulta
                $sql = "SELECT * FROM `noticias` WHERE `categoria`=".$noticia['categoria']." AND `id`<>".$noticia['id']." ORDER BY `id` DESC LIMIT 3;";
                // Ejecuta la consulta 
                $result = $conn->query($sql);
                if($result &&  $result->num_rows != 0){
                    while($noticias = $result->fetch_assoc()){
                        // Imagen de los mas recientes.
                        $base64Img = $noticias['imagen'];
                        $base64Img = base64_decode($base64Img);
                        file_put_contents('../img/latestImg'.$noticias['id'].'.png', $base64Img);
                        echo '
                            <div class="col-sm-12 col-md-4">
                                <section class="container moreViewwed">
                                    <picture class="moreImg">
                                        <img src="../img/latestImg'.$noticias['id'].'.png" alt="Latestview'.$noticias['id'].'" class="img-fluid moreImg max-width:100%">
                                        <h4 class="moreTitle"><a href="notice.php?id='.$noticias['id'].'">'.$noticias['titulo'].'</a></h4>
                                        <div class="dropdown-divider"></div>
                                    </picture>
                                </section>
                            </div>
                            ';
                    }
                }
                $conn->close();
            ?>
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