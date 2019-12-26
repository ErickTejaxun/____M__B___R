/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import AST.Instruccion.Instruccion;
import Analisis.olc.Proyecto;
import java.awt.HeadlessException;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.fife.ui.rtextarea.GutterIconInfo;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author erick
 */
public class Singlenton 
{
    /*MBR*/
    public static LinkedList<Proyecto> proyectos = new LinkedList<Proyecto>();
    public static ArrayList<ErrorC> listaErrores = new ArrayList<ErrorC>();
    public static LinkedList<String> pilaArchivos = new LinkedList<String>(); 
    public static LinkedList<Instruccion> pilaCiclos = new LinkedList<Instruccion>();
    public static long maxInt = 2147483647;
    public static long minInt = -2147483648;
    public static int linea=0;
    public static int columna=0;
    public static boolean continuarEjecucion =true;
    public static String nombreVariable = "";
    public static ArrayList<Integer> breakPoints = new ArrayList<Integer>();
    public static ArrayList<GutterIconInfo> puntos = new ArrayList<>();
    public static boolean primeraPasadaFlag = true;
    public static int contadorMain = 0;
    public static String pathMainSeleccionado = "";
    public static File archivoDeEscritura;
    public static boolean modoImpresion = false;
    
    public static String pathWrite ="";
    
  
    
    public static void setPathAppend(String s, int linea, int col)
    {
        String path = pathWrite = s;   
        modoImpresion= false;
        String data = null;
        if(path==null){return ;}
        path = path.replace("\\\\","\\");
        try 
        {
            archivoDeEscritura= new File(path); 
            if(!archivoDeEscritura.exists())
            {
                try {
                    archivoDeEscritura.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Singlenton.class.getName()).log(Level.SEVERE, null, ex);
                }
                Utilidades.Singlenton.registrarErrorSemantico("Warning :v", path +"El archivo no ha sido encontrado", linea, col);
                archivoDeEscritura = null;
            }
        
        } 
        catch (HeadlessException e) 
        {
            Utilidades.Singlenton.registrarErrorSemantico(path, "El archivo no ha sido encontrado", linea, col);   
            archivoDeEscritura = null;
        }                        
    }    
    
    
    public static void setPathWrite(String s, int linea, int col)
    {
        String path = pathWrite = s;   
        modoImpresion= false;
        String data = null;
        if(path==null){return ;}
        path = path.replace("\\\\","\\");
        try 
        {
            archivoDeEscritura= new File(path); 
            if(!archivoDeEscritura.exists())
            {
                try {
                    archivoDeEscritura.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Singlenton.class.getName()).log(Level.SEVERE, null, ex);
                }
                Utilidades.Singlenton.registrarErrorSemantico("Warning :v", path +"El archivo no ha sido encontrado", linea, col);
                archivoDeEscritura = null;
            }
        
        } 
        catch (HeadlessException e) 
        {
            Utilidades.Singlenton.registrarErrorSemantico(path, "El archivo no ha sido encontrado", linea, col);   
            archivoDeEscritura = null;
        }                        
    }
    public static void cerrarWrite(int linea, int columna)
    {
        if(pathWrite.equals(""))
        {
            Utilidades.Singlenton.registrarErrorSemantico("Close()", "No se ha abierto ningún archivo para su escritura.", linea, columna);
            archivoDeEscritura = null;            
        }
        pathWrite = "";        
        archivoDeEscritura= null;
    }
    
    public static void writeLinea(String data, int linea, int col)
    {
       if(archivoDeEscritura!=null)
       {
            BufferedWriter bw = null;
            FileWriter fw = null;
            try {                
                File file = new File(archivoDeEscritura.getAbsolutePath());
                // Si el archivo no existe, se crea!
                if (!file.exists()) {
                    file.createNewFile();
                }
                // flag true, indica adjuntar información al archivo.
                fw = new FileWriter(file.getAbsoluteFile(), modoImpresion);
                modoImpresion = true;
                bw = new BufferedWriter(fw);
                bw.write(data);
                //System.out.println("información agregada!");
            } catch (IOException e) 
            {
            } finally 
            {
                try {
                                //Cierra instancias de FileWriter y BufferedWriter
                    if (bw != null)
                        bw.close();
                    if (fw != null)
                        fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }          
       }
       Utilidades.Singlenton.registrarErrorSemantico(pathMainSeleccionado, "El archivo no ha sido encontrado", linea, col);         
    }
    
    
    public static void addPunto(GutterIconInfo p)
    {
        puntos.add(p);
    }        
    public static void setVariable(String d){nombreVariable = d;}   
    
    public static boolean isBreakPoint(int i)
    {
        for(Integer e: breakPoints)
        {
            if(e==i)
            {
                return true;
            }
        }
        return false;
    }
    
    public static void apliarCiclo(Instruccion e)
    {
        pilaCiclos.push(e);
    }
    
    public static void desaplicarCliclo()
    {
        pilaCiclos.pop();
    }
    
    public static Instruccion getCicloActual()
    {
        return pilaCiclos.getLast();
    }
    
    public static void addArchivo(String s)
    {
        pilaArchivos.push(s);
    }
    
    public static void quitarArchivo()
    {
        pilaArchivos.pop();
    }
    
    public static String getArchivoActual()
    {
        if(pilaArchivos.size()==0)
        {
            return "";
        }
        return pilaArchivos.getLast();
    }
            
    public static void registrarError(String id, String desc, ErrorC.TipoError t, int l, int c)
    {
        //Error(ErrorC.Tipo tipo, String descripcion, String archivo, int linea, int columna)
        listaErrores.add(new ErrorC( id, t, desc, getArchivoActual(), l, c));
    }
    
    public static void registrarErrorSemantico(String id, String desc, int l, int c)
    {        
        listaErrores.add(new ErrorC( id, ErrorC.TipoError.SEMANTICO, desc, getArchivoActual(), l, c));
    }    
}
