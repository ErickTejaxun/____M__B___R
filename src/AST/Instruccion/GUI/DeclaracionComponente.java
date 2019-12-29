/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.GUI;

import AST.Entorno.Componente;
import AST.Entorno.Entorno;
import AST.Instruccion.Instruccion;

/**
 *
 * @author erick
 */
public class DeclaracionComponente implements Instruccion
{
    public int linea, columna;
    public String id;
    public Componente.tipoComponente tipo;
    
    public DeclaracionComponente(String id, Componente.tipoComponente t, int l, int c)
    {
        this.id = id;
        this.tipo = t;
        this.linea = l;
        this.columna = c;
    }

    public DeclaracionComponente(String nombre, int pright, int pleft, Componente.tipoComponente tipoComponente) {
        this.id = nombre;
        this.linea = pright;
        this.columna = pleft;
        this.tipo = tipoComponente;
    }
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {        
        Componente comp = new Componente(linea, columna, id, tipo);
        entorno.insertar(comp);
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
