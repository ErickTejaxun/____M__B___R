/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis.olc;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Carpeta
{
    String nombre;
    ArrayList<Archivo> archivos;
    ArrayList<Carpeta> subcarpetas;
    String pathRun = "";
    
    public Carpeta(String n, ArrayList<Archivo> l)
    {
        this.nombre = n;
        this.archivos = l;
    }
    public Carpeta()
    {
        this.archivos = new ArrayList<>();
        this.subcarpetas = new ArrayList<>();        
    }
    
    public NodoArbolGrafico generarNodo(String path, String n)
    {
        pathRun = Utilidades.Singlenton.pathMainSeleccionado;
        path = path + (nombre==null ? "":"\\\\"+nombre);              
        NodoArbolGrafico raiz = new NodoArbolGrafico(path, nombre == null ? n: nombre);
        for(Archivo archivo : archivos)
        {
            raiz.add(archivo.generarNodo(path));
        }
        for(Carpeta carpeta : subcarpetas)
        {
            raiz.add(carpeta.generarNodo(path, n));
        }
        return raiz;
    }
}