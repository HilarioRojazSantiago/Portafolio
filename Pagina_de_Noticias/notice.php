<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>| Responsive Homework |</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
   <!-- Header con menu de navegarion y logotipo -->
    <header class="navbar navbar-expand-sm navbar-expand-md navbar-dark bg-dark">
        <nav class="navbar">
            <a class="navbar-brand" href="index.php"><img src="img/logo.png" alt="Responsive Homework, Adaptive for Everyone"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.php">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Noticias
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="notice.php">Tecnología</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="notice.php">Deportes</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="notice.php">Videojuegos</a>
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
                <section class="container mostViewed">
                    <h1 class="noticeTitle">Titulo de la noticia aqui</h1>
                    <picture class="noticeImg">
                        <img class="img-fluid mostImg max-width:100%" src="img/noti11.jpg" alt="Most viewed Img">
                    </picture>
                    <p class="noticeAutor">Autor. Fecha y hora</p>
                    <div class="dropdown-divider"></div>
                    <div class="textContainer text-wrap text-justify">
                        dadsadsadsadsadoiassdaso idjiosadioassdasjdioa di a djd iadsjisadhioasdhushduadh uysao dshduih douiashuiosahdouiadhouisa duasid uias doiuasshduioas dhuioash d ousiad aouid huoaisdhaosuidhsaiuodhsauiod hsaouid usoaia oisad as duioashdoiusah dosahdouasydh asodhsaodsaydh sa sad asdh housahsaoudhsaydas dsa dhsaoduyh sadohasoudy sadh saoyhsaouyhsaoysadhosad sad sa yusdadhousadyhsado dsahdsaousdoaysdaasday osda iaukm
                    </div>
                </section>
            </div>
            
            <div class="container col-sm-0 col-md-2"></div>
        </div>
        <!-- Contenedor de las siguientes más vistas -->
        <div class="row">
            <!-- Contenedor -->
            <div class="col-sm-12 col-md-4">
                <section class="container moreViewwed">
                    <picture class="moreImg">
                        <img src="img/noti11.jpg" alt="More viewev Img" class="img-fluid moreImg max-width:100%">
                        <h3 class="moreTitle">Titulo nocicia</h3>
                        <div class="dropdown-divider"></div>
                    </picture>
                </section>
            </div>
            <div class="col-sm-12 col-md-4">
                <section class="container moreViewwed">
                    <picture class="moreImg">
                        <img src="img/noti11.jpg" alt="More viewev Img" class="img-fluid moreImg max-width:100%">
                        <h3 class="moreTitle">Titulo nocicia</h3>
                        <div class="dropdown-divider"></div>
                    </picture>
                </section>
            </div>
            <div class="col-sm-12 col-md-4">
                <section class="container moreViewwed">
                    <picture class="moreImg">
                        <img src="img/noti11.jpg" alt="More viewev Img" class="img-fluid moreImg max-width:100%">
                        <h3 class="moreTitle">Titulo nocicia</h3>
                        <div class="dropdown-divider"></div>
                    </picture>
                </section>
            </div>
            <div class="col-sm-12 col-md-4">
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