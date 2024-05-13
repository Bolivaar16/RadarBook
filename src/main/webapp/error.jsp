<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RadarBook</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Sedan+SC&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 40px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 10px;
            width: 80%;
            max-width: 600px;
            text-align: center;
        }

        .container h1 {
            color: #d32f2f;
        }

        .container p {
            color: #666;
            line-height: 1.6;
        }

        .container a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }

        .container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <header>
        <img id="logo_principal" src="Imagenes/logoHeader.png"></img>
        <div id="titulo_header">
            <h1 id="titulo">RadarBook</h1>
            <h2 id="subtitulo">el mejor comparador de libros</h2>
        </div>
        <nav id="menu_header">
            <ul>
                <li class="opcion_menu"><a href="index.html">Inicio</a></li>
                <li class="opcion_menu"><a href="index.jsp">Libros Recomendados</a></li>
            </ul>
        </nav>
    </header>
<body>
    <div class="container">
        <h1>Se ha producido un error</h1>
        <p>Lo sentimos mucho, pero algo no ha salido como esperábamos. Aquí está el detalle del error:</p>
        <p><%= request.getAttribute("error") != null ? request.getAttribute("error") : "No se proporcionó más información del error." %></p>
        <a href="index.html">Intentar nuevamente</a>
        <p>O si prefieres, puedes <a href="contacto.html">contactarnos</a> si necesitas ayuda.</p>
    </div>
</body>
<footer>
            <p>&copy; 2024 RadarBook SL</p>
            <p><a href="ComoSeHizo.pdf">¿Cómo se hizo?</a></p>
            <p><a href="contacto.html">¿Quiénes somos?</a></p>
</footer>
</html>
