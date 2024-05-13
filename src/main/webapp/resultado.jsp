<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="radarbook.Libro.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RadarBook</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Sedan+SC&display=swap" rel="stylesheet">
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
    <main>
        <form id="searchForm" action="/ServletForm" method="GET">
            <input type="text" id="searchInput" placeholder="Ingrese título o ISBN" aria-label="Buscar por título o ISBN">
            <button type="submit">Buscar</button>
        </form>
        <div id="resultados">
	        <h1> RESULTADOS</h1>
			<c:if test="${libro1 != null}">
	  			 <div class="contenedor_libro">
	    		    <!-- Mostrar información del libro 1 -->
	    		    <h2>Resultado de la API de Amazon</h2> <br/>
	        
			        <img src="${libro1.imagen}" /> <br/>
	        
			        <h4>Título: ${libro1.titulo}</h4> <br/>	    
			        <p>Isbn: ${libro1.ISBN}</p> <br/> 
	        
			        <c:forEach var="autor" items="${libro1.autores}">
			            <c:out value="${autor}"/> <br/>
			        </c:forEach>
			
	    		    <p>Editorial: ${libro1.editora}</p> <br/>
	    		    <p>Páginas: ${libro1.paginas}</p> <br/>
	    		    <p>Precio: ${libro1.oferta.precio}</p> <br/>
		    	    <p>¡Compra Ya! <a href="${libro1.oferta.url}"><b>${libro1.oferta.disponibilidad}</b></a></p>
	    		</div>
			</c:if>
			
			<c:if test="${libro2 != null}">
	    		<div class="contenedor_libro">
	        		<h2>Resultado Librería San Pablo</h2> <br/>
	        
	        		<img src="${libro2.imagen}" /> <br/>
	        
	      		  	<h4>Título: ${libro2.titulo}</h4> <br/>	    
	      		  	<p>Isbn: ${libro2.ISBN}</p> <br/> 
		    
	        		<c:forEach var="autor" items="${libro2.autores}">
	        		    <c:out value="${autor}"/> <br/>
	    	    	</c:forEach>
		
	    	    	<p>Editorial: ${libro2.editora}</p> <br/>
		        	<p>Páginas: ${libro2.paginas}</p> <br/>
	        		<p>Precio: ${libro2.oferta.precio}</p> <br/>
	    	    	<p>¡Compra Ya! <a href="${libro2.oferta.url}"><b>${libro2.oferta.disponibilidad}</b></a></p>
	    		</div>
			</c:if>
			<c:if test="${libro3 != null}">
			    <div class="contenedor_libro">
			        <!-- Mostrar información del libro 3 -->
			        <h2>Resultado de eBay</h2> <br/>
			        
			        <img src="${libro3.imagen}" /> <br/>
			        
			        <h4>Título: ${libro3.titulo}</h4> <br/>	    
			        <p>Isbn: ${libro3.ISBN}</p> <br/> 
			        
			        <c:forEach var="autor" items="${libro3.autores}">
			            <c:out value="${autor}"/> <br/>
			        </c:forEach>
			
			        <p>Editorial: ${libro3.editora}</p> <br/>
			        <p>Páginas: ${libro3.paginas}</p> <br/>
			        <p>Precio: ${libro3.oferta.precio}</p> <br/>
			        <p>¡Compra Ya! <a href="${libro3.oferta.url}"><b>${libro3.oferta.disponibilidad}</b></a></p>
			    </div>
			</c:if>
		</div>

		

    </main>
    <footer>
            <p>&copy; 2024 RadarBook SL</p>
            <p><a href="ComoSeHizo.pdf">¿Cómo se hizo?</a></p>
            <p><a href="contacto.html">¿Quiénes somos?</a></p>
    </footer>
</body>
</html>