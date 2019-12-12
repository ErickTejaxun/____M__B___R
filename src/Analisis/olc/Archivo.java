/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis.olc;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author erick
 */
public class Archivo
{
    String nombre;
    String fecha;
    String pathRun = "";
    
    public Archivo(String n, String f)
    {
        this.nombre = n;
        this.fecha = f;
    }
    public Archivo()
    {
        System.out.println("Creando archivo");
    }
    
    public NodoArbolGrafico generarNodo(String path)
    {
        pathRun = Utilidades.Singlenton.pathMainSeleccionado;
        return new NodoArbolGrafico(path, nombre,true);
    }
}
