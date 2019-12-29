/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.GUI;

import AST.Entorno.Entorno;
import AST.Instruccion.Instruccion;

/**
 *
 * @author erick
 */
public class InstanciarGui implements Instruccion
{
    public int linea, columna;
    public String id;
    
    public InstanciarGui(String i, int l, int c)
    {
        this.id = i;
        this.linea = l;
        this.columna = c;
    }
        
    @Override
    public Object ejectuar(Entorno entorno) 
    {
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
    
}
