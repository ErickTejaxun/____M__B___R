/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.InputOutput;

import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Expresion.Expresion;
import AST.Instruccion.Instruccion;
import java.awt.HeadlessException;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class Read implements Instruccion
{
    public int linea, columna;
    public Expresion e1;
    public String id;
    
    public Read(Expresion e, String i, int l, int c)
    {
        this.e1 = e;
        this.id = i;
        this.linea = l;
        this.columna = c;
    }

    @Override
    public Object ejectuar(Entorno entorno) 
    {
        String path = e1.getValor(entorno).toString();
        String dataARchivo = obtenerDataArchivo(path);
        Simbolo s = entorno.obtener(id);
        if(s== null)
        {
            return null;
        }
        if(!(s.tipo.isString()))
        {
            Utilidades.Singlenton.registrarErrorSemantico(id,"Se requiere de una variable de tipo RSTring ", linea, columna);
            return null;
        }
        s.valor = dataARchivo;        
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }

    @Override
    public int columna() {
        return columna;
    }
    
    public String obtenerDataArchivo(String path)
    {
        String data = null;
        if(path==null){return null;}
        path = path.replace("\\\\","\\");
        try 
        {
            File archivo= new File(path); 
            if(!archivo.exists())
            {
                Utilidades.Singlenton.registrarErrorSemantico(path, "El archivo no ha sido encontrado", 0, 0);                               
                return data;
            }
            Scanner sc = new Scanner(archivo);
            data = "";
            while (sc.hasNextLine())
            {
                if(data.equals(""))
                {
                    data = sc.nextLine();
                }
                else
                {
                    data = data + "\n" + sc.nextLine();
                }
            }            
        } 
        catch (HeadlessException | FileNotFoundException e) 
        {
            /*Si no existe el archivo, marcar error semantico.*/
                Utilidades.Singlenton.registrarErrorSemantico(path, "El archivo no ha sido encontrado", 0, 0);                           
                return data;           
        }
        return data;
    }    
    
}
