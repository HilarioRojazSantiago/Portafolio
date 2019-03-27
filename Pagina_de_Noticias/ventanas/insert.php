<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>| Responsive Homework | insertar</title>
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
                    session_start();
                    date_default_timezone_set('America/Mexico_City');
                    $tituloN = $_POST['titulo'];
                    $textoN = $_POST['texto'];
                    $catN =  $_POST['categoria'];
                    $fechaN = date('Y.m.d');
                    $horaN = date('G:i:s');
                    $autorN = $_SESSION['user'];
                    /*  Para conectar a la base de datos*/
                    $servername = "localhost";
                    $username = "root";
                    $password = "";
                    $dbname = "responsivehomeworkdb";
                    /* Para subir la imagen*/
                    $nombreArchivo = $_FILES['imagen']['name'];
                    $tipoArchivo = $_FILES['imagen']['type'];
                    $tamanoArchivo = $_FILES['imagen']['size'];
                    $hola = "123";
                    $uno = 1;
                    //  Si alguno de los atributos es vacio.
                    if($textoN=="" || $tituloN==""){
                        echo "<h1>Rellene todos los campos</h1";
                    }else{
                        // Comprobar que el archico sea una imagen.
                        if(!(strpos($tipoArchivo,"gif") || strpos($tipoArchivo,"jpg") || strpos($tipoArchivo,"jpeg") || strpos($tipoArchivo,"png")) && $tamanoArchivo>5000000){
                            echo "<h1>El tamaño o la extension den archivo no es admitido</h1";
                        }else{
                            if(move_uploaded_file($_FILES['imagen']['tmp_name'], $nombreArchivo)){
                                $contenidoImagen = file_get_contents($nombreArchivo);
                                $imagenBase64 = base64_encode($contenidoImagen);
                                // Insertar la noticia en la base de datos.
                                /* establece la conexion con la base de datos*/
                                $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
                                /* Prepara consulta */ 
                                $sql = "INSERT INTO `noticias` (`id`, `texto`, `imagen`, `titulo`, `idAutor`, `fecha`, `hora`, `likes`, `categoria`) VALUES (NULL, '$textoN','$imagenBase64','$tituloN', '$autorN','$fechaN', '$horaN', '0', '$catN')";
                                /* Ejecuta la consulta */
                                $result = $conn->query($sql);
                                $sql->execute(array('textos'=>$textoN,
                                                             'imagens'=>$imagenBase64,
                                                             'titulos'=>$tituloN,
                                                             'idAutors'=>$autorN,
                                                             'fechas'=>$fechaN,
                                                             'horas'=>$horaN,
                                                             'likess'=>0,
                                                             'categorias'=>$catN));
                                //$result = $sql->fetchAll();
                                if($sql){
                                    echo "<h1>Noticia subida exitosamente!</h1";
                                }else{
                                    echo "<h1>No se pudo subir la noticia, intentalo de nuevo</h1";
                                }
//                                $sql->close();
                                //$conn->close();
                            }else{
                                echo("<h1>Ocurrióun problema al subir la imagen</h1");
                            }
                        }
                    }
                ?>
                <br><a href="../index.php">
                    <h2>Regresar a Home</h2>
                </a>
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