<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<?php
    $idNoticia = $_GET['id'];
    /*  Para conectar a la base de datos*/
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "responsivehomeworkdb";
    /* establece la conexion con la base de datos*/
    $conn = new mysqli($servername, $username, $password, $dbname) or die("<h1>no pudo conectarse con la base de datos</h1");
    /* Prepara consulta */ 
    $sql = "UPDATE `noticias` SET `likes`=`likes`+1 WHERE id='$idNoticia';";
    /* Ejecuta la consulta */
    $result = $conn->query($sql);
    if($result){
        echo '
        <meta http-equiv="refresh" content="0;URL=/ventanas/notice.php?id='.$idNoticia.'">
        ';
    }
    $conn->close();
?>
</head>
<body>
</body>
</html>