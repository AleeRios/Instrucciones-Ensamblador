/*
	UNIVERSIDAD AUTONOMA DEL ESTADO DE MEXICO
	INGENIERIA EN COMPUTACION
	ARQUITECTURA DE COMPUTADORAS
	
	PROYECTO: LENGUAJE ENSAMBLADOR A LENGUAJE MAQUINA
	
	BECKHAM ALEJANDRO RIOS CAMPUSANO
	EMMANUEL VAZQUEZ BRAVO
	BLADIMIR AXLEY GARDUÑO SANCHEZ
	LEONARDO VILLANUEVA MEDINA
	
	NOTA: Para la ejecucion del codigo es necesario 
	contar con la version de Java 17

*/
package AppEnsamblador;

import java.util.*;
import java.io.*;
public class Principal {
    public static void main(String argvs[]) throws IOException{
    	Scanner x = new Scanner(System.in);
	    Covertidor c = new Covertidor();
	    File f1 = new File("C:/Users/alebe/Downloads/Ins.txt");
	    if(f1.exists())
	    	f1.delete();
	    if(!f1.exists())
    			if(f1.createNewFile())
    				System.out.println("Archivo creado");
    	while(true){
    		BufferedWriter bw = new BufferedWriter(new FileWriter(f1,true));
	        System.out.print("\nEscribe una instruccion en ensamblador: ");
	        String cad = x.nextLine();
	        c.setIns(cad);
	        System.out.println("\nLa equivalencia en binario es: " + c.evaluar());
	        bw.write(c.evaluar());
	        bw.newLine();
	        bw.close();
    	}
    	
    }
}
