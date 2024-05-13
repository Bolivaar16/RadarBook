package radarbook;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (
		name = "ServletForm",
		urlPatterns = {"/ServletForm"}
)

public class ServletForm extends HttpServlet {
	
	//Para evitar problemas de compatibilidad de versiones
	private static final long serialVersionUID = 1L;
	
	apiRain api= new apiRain();
	sanPablo scrapper_san_pablo = new sanPablo();
	String busqueda_nombre="";
	String busqueda_ISBN="";
	Libro libro1 = new Libro();
	Libro libro2 = new Libro();
	//Libro libro3 = new Libro();
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Búsqueda de libros basado en una entrada del usuario
        busqueda_nombre = request.getParameter("searchNameInput");
        
        //Obtener libro 1 con la api de amazon
        /*try {
        	libro1 = api.devolverLibro(busqueda_nombre);
        	
        } catch (Exception e) {
			e.printStackTrace();
		}*/
        
        //Obtener libro2 con el scrapper san Pablo
        try {
        	libro2 = scrapper_san_pablo.devolverLibro(busqueda_nombre);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //Agrego los libros al request
        //request.setAttribute("libro1", libro1);
        request.setAttribute("libro2", libro2);
        
        response.setContentType("text/plain:charset=UTF-8");
        
        // Se manda la solicitud al JSP
        RequestDispatcher despachador = request.getRequestDispatcher("/resultado.jsp");
        
        try {
        	despachador.forward(request, response);
        } catch(ServletException e){
        	e.printStackTrace();
        }
        libro1=null;
        libro2=null;
        api = null;
        scrapper_san_pablo = null;
        busqueda_nombre = "";
        busqueda_ISBN = "";
     }
}