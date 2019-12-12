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
public class NodoArbolGrafico extends DefaultMutableTreeNode
{
    public String path;
    public String nombre;
    public boolean esArchivo;
    public String pathRun ;
    public NodoArbolGrafico(String p, String n)
    {
        super(n);
        nombre = n;
        this.path = p + (n.contains(".")? "\\\\"+ n:"");
    }
    
    public NodoArbolGrafico(String p, String n, boolean t)
    {
        super(n);                
        esArchivo = true;
        nombre = n;
        this.path = p + (n.contains(".")? "\\\\"+ n:"");             
         pathRun = Utilidades.Singlenton.pathMainSeleccionado;
    } 
    
}
