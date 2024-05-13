package radarbook;

public class mainPrueba {

	public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	//Prueba scrapping libreria sanPablo
	sanPablo s1 = new sanPablo();
	//s1.devolverLibro("https://libreria.sanpablo.es/busqueda/listaLibros.php?tipoBus=full&palabrasBusqueda=hola");
	Libro l1 = s1.devolverLibro("los juegos del hambre 1");
	
	
	if (l1 == null) {
		System.out.println("Libro no encontrado");
	}
	else {
		System.out.println(l1.toString());
	}
	
	
	//Prueba Api RainForest
	//apiRain a1 = new apiRain();
	//System.out.println((a1.devolverLibro("los juegos del hambre 2")).toString());
	
		
		
		
	//Prueba scrapping ebay
	//ebay e1 = new ebay();
	//System.out.println((e1.devolverLibro("Los juegos del hambre 1").toString()));
	
	}
}
