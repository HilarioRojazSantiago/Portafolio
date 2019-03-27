<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <?php
    date_default_timezone_set('America/Mexico_City');
    $nombreC = $_POST['nombreC'];
    $textoC = $_POST['textoC'];
    $idN =  $_GET['id'];
    $fechaN = date('Y.m.d');
    $horaN = date('G:i:s');
    /*  Para conectar a la base de datos*/
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "responsivehomeworkdb";
    if($textoC!="" && $nombreC!=""){
        // Insertar el comentario en la base de datos.
        /* establece la conexion con la base de datos*/
        $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1>");
        /* Prepara consulta */ 
        $sql = "INSERT INTO `comentarios` (`nombre`, `texto`, `idNoticia`, `fecha`, `hora`) VALUES ('$nombreC', '$textoC', '$idN', '$fechaN', '$horaN')";
        /* Ejecuta la consulta */
        $result = $conn->query($sql);
        $conn->close();
    }
        echo '<meta http-equiv="refresh" content="0;URL=notice.php?id='.$idN.'">';
    ?>
</head>

<body>
</body>

</html>