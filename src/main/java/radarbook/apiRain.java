package radarbook;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class apiRain {
	
	String lugar = "Amazon";
	Libro libroA = new Libro();
	
	public Libro devolverLibro(String aBuscar) {
		
		String urlAPI = "https://api.rainforestapi.com/request?api_key=EEFCD7EE6E2B44B1B284F81D22E0E527&type=search&amazon_domain=amazon.es&search_term=" 
				+ aBuscar.replaceAll("\\s+","+") + "&category_id=599365031&currency=eur";
		int codigoRespuesta = 0;
		Oferta o = new Oferta();
		
		try {
						
			//Inicio de la conexion
			URL url = new URL(urlAPI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			
			//Solicitar la conexión a través de GET y realizar conexion
			conn.setRequestMethod("GET");
			conn.connect();
			
			//Comprobamos que la conexión haya sido válida si el código de respuesta es el 200
			codigoRespuesta = conn.getResponseCode();
			
			
			//Si el código de respuesta es 200, continuamos con el proceso
			if (codigoRespuesta == 200) {
				
				//Es necesario leer la respuesta de la solicitud HTTP y almacenarla en una cadena de texto
				//Scaner para poder leer la respuesta
				BufferedReader scaner = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				
				StringBuilder respuesta = this.respuesta(scaner);
				
		            /*//Prueba 1
		            JOptionPane.showMessageDialog(null, respuesta.toString(), "Respuesta del Servidor", JOptionPane.INFORMATION_MESSAGE);*/
	            
	            //Construimos el objeto json
	            JSONObject objetoJSON = new JSONObject(respuesta.toString());
				
				// En el json devuelto los libros se encuentran en search_results, asique accedemos a ese array
				JSONArray libros = objetoJSON.getJSONArray("search_results");
				
				//Ahora para el primer libro del array vamos a crear su Objeto oferta, y su objeto Libro
				JSONObject primerResultado = libros.getJSONObject(0);
				
					/*//Prueba 2
					System.out.println(primerResultado.toString());*/
				   
				//Creamos la oferta
				o = this.construirOferta(primerResultado);

					/*//Prueba 3
					System.out.println(o.toString());*/
				
				//Construyo el libro
				libroA = this.construirLibro(primerResultado, o);
				
			}
			
		} catch (Exception e) {
			//Si el connect falla, se lanza un IOException
			e.printStackTrace();
		}
		
		//Devuelvo el libro
		return libroA;
		
	}
	
/***********************************METODOS INTERNOS A LA CLASE********************************************************/
		
	//Método para generar y leer respuesta
	private StringBuilder respuesta(BufferedReader scaner) {
		
		StringBuilder respuesta = new StringBuilder();
		
		try {
			
			//Cadena de texto que almacena cada línea de la respuesta
	        String linea;
	        
	        //Por cada línea que se lea y no esté vacía, se almacena en el response
	        while ((linea = scaner.readLine()) != null) {
	            respuesta.append(linea);
	        }
	        
	        //Se cierra el scaner al terminar
	        scaner.close();	
	        
		} catch (Exception e) {
			//Si el connect falla, se lanza un IOException
			e.printStackTrace();
		}
		
		return respuesta;
	}
	
	//Método para construir la oferta
	private Oferta construirOferta(JSONObject primerResultado) {
		
		//Completamos oferta
		Oferta o = new Oferta();
		
		//Obtenemos price
		JSONObject price = primerResultado.getJSONObject("price");
		double priceValue = price.getDouble("value");
		String priceString = Double.toString(priceValue);
		String priceSymbol = price.getString("symbol");
		
		//Obtenemos link
		String link = primerResultado.getString("link");
		
		//Añadimos atributos de la oferta
		o.setUrl(link);
		o.setPrecio(priceString + priceSymbol);
		o.setLugar(lugar);
		//Los resultados de esta api siempre van a estar disponibles, sino, no se muestra nada
		o.setDisponibilidad("Disponible");
		
		//Devolvemos la oferta
		return o;
	}
	
	//Método para establecer la conexión y scrapear dentro del libro
	private Document scrapeoLibro(String url) {
		
		OkHttpClient client = new OkHttpClient();
	        
		String apiUrl = "https://api.scrapingdog.com/scrape";
        String apiKey = "663ebd3d7ea2814fb3640cd2";
        String targetUrl = url;
        boolean dynamic = false;
        
        // Construct the query parameters
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("url", targetUrl);
        params.put("dynamic", String.valueOf(dynamic));
        
        // Build the query URL
        StringBuilder query = new StringBuilder(apiUrl);
        query.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            query.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        
        String queryUrl = query.toString().substring(0, query.length() - 1);
		         
		  Request request = new Request.Builder().url(queryUrl).build();
		        
		  Document doc = null;
		        
		        try (Response response = client.newCall(request).execute()) {
		        	String html = response.body().string();
			        doc = Jsoup.parse(html);
			        
			        Elements si = doc.select("dpx-books-ppd_csm_instrumentation_wrapper");

				 }catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		        return doc;
		
	}
	
	//Método para obtener las reseñas
	private ArrayList<Reseña> obtenerReseñas(Oferta oferta) {
		
		//Creo array donde almacenaré 3 reseñas
		ArrayList<Reseña> reseñas = new ArrayList<>();
		
		//Obtengo el documento html
		Document doc = this.scrapeoLibro(oferta.getUrl());
		
		//Obtengo los logos filtrando los que terminan en .gif
		Elements logosE = doc.select(".a-profile-avatar img");
		ArrayList<String> logos = new ArrayList<>();
		for (Element e: logosE) {
			String src = e.attr("src");
			if (!src.endsWith(".gif") && !src.isEmpty()) {
				logos.add(e.attr("src"));
			}
		}
		
		//Obtengo los autores de cada reseña
		Elements autoresE = doc.select(".a-profile-name");
		ArrayList<String> autores = new ArrayList<>();
		for (Element e: autoresE) {
			String aut = e.text();
			if (!aut.contains("Amazon Customer") && !aut.isEmpty()) {
				autores.add(aut);
			}
		}
		
		//Obtengo el titulo de cada reseña
		Elements titles = doc.select("a[data-hook=review-title]");
		ArrayList<String> titulos = new ArrayList<>();
		for (Element tit : titles) {
		    Element ultimoSpan = tit.select("span").last();
		    if (ultimoSpan != null) {
		        String textoUltimoSpan = ultimoSpan.text();
		        titulos.add(textoUltimoSpan);
		    }
		}
		
		//Obtengo el texto de cada reseña
        Elements texts = doc.select("span[data-hook=review-body]"); 
        ArrayList<String> textos = new ArrayList<>();
        for (Element tex : texts) {
            String textoReview = tex.text(); // Obtiene el texto dentro del <span>
            textoReview = textoReview.replace("Leer más", "");
            textos.add(textoReview);
        }
        
        //Obtengo las estrellas de cada reseña
        Elements stars = doc.select("a[data-hook=review-title] span.a-icon-alt"); 
        ArrayList<String> estrellas = new ArrayList<>();
        for (Element st : stars) {
            String textoStars = st.text(); // Obtiene el texto dentro del <span>
            estrellas.add(textoStars);
        }
        
        //De las 8 reseñas que he sacado, almaceno las 4 primeras
        for (int i=0; i<titles.size(); i++) {
        	Reseña reseña = new Reseña();
        	reseña.setLogo(logos.get(i));
        	reseña.setWho(autores.get(i));
        	reseña.setTituloReseña(titulos.get(i));
        	reseña.setTexto(textos.get(i));
        	reseña.setEstrellas(estrellas.get(i));
        	
        	reseñas.add(reseña);
        }
		
        return reseñas;
	}
	
	//Método para obtener la fecha del libro
	private String obtenerFecha(Document doc) {
        Element date = doc.selectFirst("span#productSubtitle");
        String fecha = date.text();
        String[] partesFecha = fecha.split("–");

        if (partesFecha.length > 1) {
            fecha = partesFecha[1].trim();         
        } 
		
		return fecha;
	}
	
	//Método para obtener los autores
	private ArrayList<String> obtenerAutores(Document doc) {
		Elements authors = doc.select("span.author.notFaded");
		ArrayList<String> autores = new ArrayList<>();
		for (Element au : authors) {
			String autor = au.text().trim();
			autor = autor.replace(" (Autor)", "");
			autor = autor.replace(",", "");
			autores.add(autor);
		}
		
		return autores;
	}
	
	//Método para obtener la editorial y el número de páginas
	private ArrayList<String> obtenerEditorialFecha (Document doc) {
		
		ArrayList<String> aDevolver = new ArrayList<>();
        Elements listaElementos = doc.select("#detailBullets_feature_div #detailBulletsWrapper_feature_div ul li");
        String textoCompleto = "";
        String editorial = "";
        String paginas = "";

        for (Element e : listaElementos) {
        	textoCompleto = e.selectFirst("span:not(.a-text-bold)").text();
        	if (textoCompleto.contains("Editorial")) {
        		editorial = textoCompleto;
        		String[] partes = editorial.split(":");
        		if (partes.length > 1) {
        			editorial = partes[1].trim().replaceFirst("\\s", "");
        			String[] partesInternas = editorial.split(";");
        			if (partesInternas.length > 1) {
        				editorial = partesInternas[0].trim();
        			}
        		}
        	}
        	
        	if (textoCompleto.contains("páginas")) {
        		paginas = textoCompleto;
        		String[] partes = paginas.split(":");
        		if (partes.length > 1) {
        			paginas = partes[1].trim().replaceFirst("\\s", "");
        		}
        	}
             
        }
        
        aDevolver.add(editorial);
        aDevolver.add(paginas);
        
        return aDevolver;
	}
	
	//Método para construir el libro y obtener algunos atributos
	private Libro construirLibro (JSONObject primerResultado, Oferta o) {
				
		//Obtengo el titulo
		String titulo = primerResultado.getString("title");
		
		//Obtengo el isbn
		String isbnNum = primerResultado.getString("asin");
		String isbn = "978" + isbnNum;
		
		//Obtengo la imagen
		String imagen = primerResultado.getString("image");
		
		//Obtengo la valoracion
		double rating = primerResultado.getDouble("rating");
		String valoracion = String.valueOf(rating) + " estrellas";
		
		//Obtengo las reseñas
		ArrayList<Reseña> reseñas = this.obtenerReseñas(o);
		
		
		//Inicio scrapeo
		Document doc = this.scrapeoLibro(o.getUrl());
		
	    //Obtengo los autores
		ArrayList<String> autores = this.obtenerAutores(doc);

				
		//Obtengo las páginas y la editorial, que se almacenan en un array, posicion 1 editorial, posición 2 páginas
		String editorial = this.obtenerEditorialFecha(doc).get(0);
		String paginas = this.obtenerEditorialFecha(doc).get(1);
		
		//Obtengo la fecha
        String fecha = this.obtenerFecha(doc);
	
		//Completamos libro
		Libro l2 = new Libro(titulo, isbn, autores, editorial, fecha, paginas, o.getUrl(), imagen, valoracion, o, reseñas);
		
		//Devuelvo el libro
		return l2;
		
	}
	
}
