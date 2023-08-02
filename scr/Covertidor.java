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

public class Covertidor {
   private String ins, eq;
   
   public Covertidor(){

   }
   
   public void setIns(String ins){
       this.ins = ins;
   }
   public String getIns(){
       return this.ins;
   }
   
   public void setEq(String eq){
       this.eq = eq;
   }
   public String getEq(){
       return this.eq;
   }
   
   public String evaluar(){

	       setIns(ins.toUpperCase());
	       String aux = recorrer(ins, ' ');
	       setIns(ins.replaceAll(aux + " 0X", ""));
	       String aux2 = recorrer(ins, ',');
	       setIns(ins.replaceAll(aux2 + ",", ""));
		   
	       
	       if(ins.contains(" "))
	            setIns(ins.strip());
	       
	       //if(ins.contains(";"))
	       //     setIns(ins.replaceAll(";", ""));
	       
	       switch(aux){
	           case "ADDWF" -> eq = "000111" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "ANDWF" -> eq = "000101" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "CLRF" -> eq = "0000011" + recorrimiento(hexaBinario(aux2), 7);
			   case "CLRW" -> eq = "00000100000000";
			   case "COMF" -> eq = "001001" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "DECF" -> eq = "000011" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "DECFSZ" -> eq = "001011" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "INCF" -> eq = "001010" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "INCFS" -> eq = "001111" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "IORWF" -> eq = "000100"+ hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "MOVF" -> eq = "001000"+ hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "MOVWF" -> eq = "0000001" + recorrimiento(hexaBinario(aux2), 7);
			   case "NOP" -> eq = "00000000000000";
			   case "RLF" -> eq = "001101" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "RRF" -> eq = "001100" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "SUBWF" -> eq = "000010" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "SWAPF" -> eq = "001110" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   case "XORWF" -> eq = "000110" + hexaBinario2(ins) + recorrimiento(hexaBinario(aux2), 7);
			   
			   case "BCF" -> eq = "0100" + recorrimiento(hexaBinario2(ins), 3) + recorrimiento(hexaBinario(aux2), 7);
			   case "BSF" -> eq = "0101" + recorrimiento(hexaBinario2(ins), 3) + recorrimiento(hexaBinario(aux2), 7);
			   case "BTFSC" -> eq = "0110" + recorrimiento(hexaBinario2(ins), 3) + recorrimiento(hexaBinario(aux2), 7);
			   case "BTFSS" -> eq = "0111" + recorrimiento(hexaBinario2(ins), 3) + recorrimiento(hexaBinario(aux2), 7);
			   
			   case "ADDLW" -> eq = "111110" + recorrimiento(hexaBinario(aux2), 8);
			   case "ANDLW" -> eq = "111001" + recorrimiento(hexaBinario(aux2), 8);
			   case "CALL" -> eq = "100" + recorrimiento(hexaBinario(aux2), 11);
			   case "CLRWDT" -> eq = "00000001100100";
			   case "GOTO" -> eq = "101" + recorrimiento(hexaBinario(aux2), 11);
			   case "IORLW" -> eq = "111000" + recorrimiento(hexaBinario(aux2), 8);
			   case "MOVLW" -> eq = "110000" + recorrimiento(hexaBinario(aux2), 8);
			   case "RETFIE" -> eq = "00000000001001";
			   case "RETLW" -> eq = "110100" + recorrimiento(hexaBinario(aux2), 8);
			   case "RETURN" -> eq = "00000000001000";
			   case "SLEEP" -> eq = "00000001100011";
			   case "SUBLW" -> eq = "111100" + recorrimiento(hexaBinario(aux2), 8);
			   case "XORLW" -> eq = "111010" + recorrimiento(hexaBinario(aux2), 8);
			  
	           /*default -> {
	                   System.out.println("Instruccion no reconocida...");
	                   eq = "null";
	           }*/
	       }
     	return eq;
   }
   
   public String recorrer(String cad, char c1){
       String c = String.valueOf(c1);
       String aux = "";
       for(int i=0; i<cad.length(); i++){
           if(String.valueOf(cad.charAt(i)).equals(c))
               break;
           else
              aux += String.valueOf(cad.charAt(i)); 
       }
       return aux;
   }
   
   public String hexaBinario(String hexa){
       return Integer.toBinaryString(Integer.parseInt(hexa, 16));
   }
   public String hexaBinario2(String hexa){
	   	if(hexa.equals("F") || hexa.equals("1"))
	       return "1";
	   	else return "0";
   }
   
   public String recorrimiento(String cad, int bit){
	   String aux = "";
	   
	   if(cad.length() == bit)
		   return cad;
	   else{
		   int tam = bit - cad.length();
		   for(int i = 0; i < tam; i++)
			   aux += "0";
		   return aux + cad;  
	   }  
   }
}
