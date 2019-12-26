/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.InputOutput;

import AST.Entorno.Entorno;
import AST.Instruccion.Instruccion;

/**
 *
 * @author erick
 */
public class CerrarArchivo implements Instruccion
{
    public int linea, columna;
    
    public CerrarArchivo(int l, int c)
    {
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {
        Utilidades.Singlenton.cerrarWrite(linea, columna);
        return null;
    }

    @Override
    public int linea() {
        return columna;
    }

    @Override
    public int columna() {
        return linea;
    }
    
}
