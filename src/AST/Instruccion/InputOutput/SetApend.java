/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.InputOutput;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Expresion;
import AST.Instruccion.Instruccion;

/**
 *
 * @author erick
 */
public class SetApend implements Instruccion
{
    public int linea, columna;
    public Expresion e;
    
    public SetApend(Expresion ex, int l, int c)
    {
        this.e = ex;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {
        Object valor = e.getValor(entorno);
        Tipo tipo = e.getTipo();
        String path = "";
        if(tipo.isString())
        {
           path = valor.toString();
           Utilidades.Singlenton.setPathAppend(path, linea, columna);
           return null;
        }
        if(tipo.isChar())
        {
            if(!(valor instanceof Arreglo))
            {
                Utilidades.Singlenton.registrarErrorSemantico("Write", "Se requiere un valor de tipo cadena o array Char", linea, columna);
                return null;
            }
            Arreglo arreglo= (Arreglo)valor;
            path = arreglo.raiz.getCadena();
            Utilidades.Singlenton.setPathAppend(path, linea, columna);
            return null;
        }
        Utilidades.Singlenton.registrarErrorSemantico("Write", "Se requiere un valor de tipo CHAR array", linea, columna);
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
