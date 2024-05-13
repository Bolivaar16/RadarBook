package radarbook;

import org.jsoup.nodes.Element;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import okhttp3.JavaNetCookieJar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.appengine.repackaged.org.apache.http.client.params.CookiePolicy;

import okhttp3.HttpUrl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class mainPruebaScrapeo2  {

	public static void main(String[] args) {

		  OkHttpClient client = new OkHttpClient();
	        
		  String apiUrl = "https://api.scrapingdog.com/scrape";
          String apiKey = "663ebd3d7ea2814fb3640cd2";
          String targetUrl = "https://www.amazon.es/Los-juegos-hambre-SUZANNE-COLLINS/dp/8427202121/ref=asc_df_8427202121/?tag=googshopes-21&linkCode=df0&hvadid=195231592075&hvpos=&hvnetw=g&hvrand=10496118824230249646&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1005414&hvtargid=pla-138232518555&psc=1&mcid=9a48975b411e3d6e9d6bc4ee773710d0";
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
			        
			        System.out.println(aDevolver);

				 }catch (IOException e) {
		            e.printStackTrace();
		        }
		        
    }
		        
	
	
}

