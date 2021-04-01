package palabras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

public class ContarPalabras {
	/**
	 * 
	 * @param strParalabra
	 * @return Palabra sin ningun signo de escritura
	 */
	public static String sanearString(String strParalabra){
		if(!strParalabra.equals("") && !strParalabra.equals(" ")){
			return strParalabra.toUpperCase().replace(".", " ").replace(",", "").replace("-", " ").replace(";", " ").replace("  ", " ").replace("?", " ").replace("¿", " ").replace("¡", " ").replace("!", " ").replace(":", " "); //.replaceAll("\\.$|,|;|']|-|:|¡|!|¿|?|", " "); //
		}
		return "";
	}
	
	public static void main(String[] args) {
		long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
		TInicio = System.currentTimeMillis();
		
		HashMap<String, Integer> mapaDePalabras = new HashMap<>();
		ArrayList<String>  arrDiscards = new ArrayList<String>();
		
		//Cargamos los archivos de textos
	    File inputFile    = new File("./src/palabras/pg2000.txt");
	    File inputDiscard = new File("./src/palabras/discard_es.txt");
	    
	    try {
	        Scanner readerBook 	  = new Scanner(inputFile, "UTF-8");
	        Scanner readerDiscard = new Scanner(inputDiscard, "UTF-8");
	        
	        //Cargamos el listado de palabras a Excluir
	        while (readerDiscard.hasNextLine()) {
	        	arrDiscards.add(readerDiscard.nextLine().toUpperCase());
	        }
	        
	        //comenzamos a procesar el archivo para contar las palabras
	        while (readerBook.hasNextLine()) {
	            String data = sanearString(readerBook.nextLine());
	            String[] palabras = data.split(" ");
	            	            
	            for (String palabra : palabras) {
	            	//Validamos que no hayan espacios en blanco y que no hayan palabras excluidas
	            	if(!palabra.equals("") && !arrDiscards.contains(palabra) ){
	            		
	            		//Contamos las palabras
	            		if (mapaDePalabras.containsKey(palabra)) {//Si existe aunmentamos su cantidad 
		                	mapaDePalabras.replace(palabra, mapaDePalabras.get(palabra) + 1);
		                } else {//
		                	mapaDePalabras.put(palabra, 1);
		                }
	            	}
	            }
	        }
	        readerBook.close();
	        
	        //Ordeamos el Listado de palabras para tenerlas en orden descendente segun la cantidad 
	        Set<Entry<String, Integer>> set = mapaDePalabras.entrySet();
	        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
	                set);
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                    Map.Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
	        
	        //Imprimimos las 10 palabras con mayor ocurrencia
	        for(int i =0; i < 10; i++){
	        	System.out.println(list.get(i));	
	        }
	        	        
	        
	    } catch (FileNotFoundException e) {
	        System.out.println("scanner error");
	        e.printStackTrace();
	    }
	    
	    TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
	    tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
	    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); 

	}

}
