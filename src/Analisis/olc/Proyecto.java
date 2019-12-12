/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis.olc;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author erick
 */
public class Proyecto 
{
    public String nombre;
    public String ruta;
    public String rutaRun;
    public Carpeta carpeta;
    public enum elemento  {RUTA, NOMBRE, CORRER, FECHA_MOD, CONF};      
    public Proyecto()
    {
        
    }
    public Proyecto(String n, String r, String rn)
    {
        this.nombre = n;
        this.ruta = r;
        this.rutaRun =r+"\\\\"+rn;
    }
    
    public NodoArbolGrafico nodoProyecto()
    {     
        Utilidades.Singlenton.pathMainSeleccionado = ruta+"\\"+rutaRun;
        return carpeta.generarNodo(ruta, nombre);        
    }
    
    
}
