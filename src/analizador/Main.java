/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package analizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author Carlos
 */
public class Main {
    private static String sTexto;    
    
   
   public static void main(String[] args) throws IOException, Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            sTexto = br.readLine();
            if(sTexto.equalsIgnoreCase("./TDS")){
                 Interfaz chFile= new Interfaz();
                 chFile.setVisible (true);
            }else if (sTexto.toUpperCase().contains("TDS")) {
                    try {
                        sTexto= sTexto.substring(4);
                        if(sTexto.endsWith(".compi")){
                            Reader reader = new BufferedReader(new FileReader(sTexto));
                            Lexer lexer = new Lexer (reader);
                            parser p=new parser(lexer);
                           //aca llama al visitor
                            Program res = (Program) p.parse().value;
                            VisitorMain v = new VisitorMain();
                            v.visit(res);
                            VisitorType v2 = new VisitorType();
                            v2.visit(res);
                            VisitorCI v3 = new VisitorCI();
                            v3.visit(res);
                            //v3.print();
                            Assembly ass = new Assembly();
                            String rute = sTexto.substring(0,sTexto.lastIndexOf("/"));
                            String name = sTexto.substring(rute.length(), sTexto.length()-".compi".length())+".s";
                            ass.generateAss(rute,name,v3.getListaCI());
                        }else{
                            System.out.println("ERROR: invalid file extension");
                        }
                    }catch(Exception e){                    
                        
                    }
                }else if (sTexto.equals("cptest")) {
                    /* //De momento no esta en funcionamiento
                            String currentDir = System.getProperty("user.dir");
                            System.out.println("Current dir using System:" +currentDir);
                            System.out.println("Test Correctos");
                            File dirC = new File(currentDir+"/test/correct");
                            String[] ficherosC = dirC.list();
                            File dirI = new File(currentDir+"/test/invalid");
                            String[] ficherosI = dirI.list();
                            if (ficherosC == null)
                                System.out.println("No hay ficheros en el directorio especificado");
                            else { 
                                for (int x=0;x<ficherosC.length;x++)
                                    if(ficherosC[x].endsWith(".compi")){
                                        System.out.println("ENTRO TEST: "+ficherosC[x]+" !!!!!!!");
                                        Reader reader = new BufferedReader(new FileReader(dirC.getAbsolutePath()+"/"+ficherosC[x]));
                                        Lexer lexer = new Lexer (reader);
                                        parser p=new parser(lexer);
                                        p.parse();
                                        System.out.println("Test: "+ficherosC[x]+" OK");
                                    }else{
                                        System.out.println("ERROR: invalid file extension archive: "+ficherosC[x]);
                                    }
                            }
                            int countError=0;
                            if (ficherosI == null)
                                System.out.println("No hay ficheros en el directorio especificado");
                            else { 
                                for (int x=0;x<ficherosI.length;x++){
                                    try {
                                        Thread.sleep (1*1000);
                                    } catch (Exception e) {
                                        // Mensaje en caso de que falle
                                    }
                                    System.out.println(ficherosI[x]+" Analisis...");
                                    try{
                                        if(ficherosI[x].endsWith(".compi")){
                                            Reader reader = new BufferedReader(new FileReader(dirI.getAbsolutePath()+"/"+ficherosI[x]));
                                            Lexer lexer = new Lexer (reader);
                                            parser p=new parser(lexer);
                                            p.parse();
                                        }else{
                                            System.out.println("ERROR: invalid file extension archive: "+ficherosI[x]);
                                        }
                                    }catch(Exception e){
                                        countError++;
                                    }                                  
                                }
                                try {
                                    Thread.sleep (1*1000);
                                } catch (Exception e) {
                                    // Mensaje en caso de que falle
                                }
                                System.out.println("Espera "+ ficherosI.length +" ERRORES");
                                System.out.println("Errores registados "+countError);
                                if(ficherosI.length==countError){
                                    System.out.println("TEST DE ERROR SUPERADOS");
                                }
                            }
                            dirC.delete();
                            dirI.delete();*/
                        }else {
                            System.out.println("\nExpected options:\n"+
                                                "<./TDS> If you want to run the GUI\n"+
                                                "<TDS directory-and-file.compi> if you want to run in command line\n"+
                                                "<cptest> if you want to run the test");
                        }
            }
}