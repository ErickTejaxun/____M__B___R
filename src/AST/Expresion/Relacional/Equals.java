/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion.Relacional;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.BOOL;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Arreglo.NodoNario;
import AST.Expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Equals implements Expresion
{
    public Expresion izquierdo, derecho;
    public int linea, columna;

    public Equals(Expresion i, Expresion d, int l, int c)
    {
        this.izquierdo = i;
        this.derecho = d;
        this.linea = l;
        this.columna = c;                 
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {        
        Object valorI = izquierdo.getValor(ent);
        Tipo tipoI = izquierdo.getTipo();
        Object valorD = derecho.getValor(ent);
        Tipo tipoD = derecho.getTipo();
        
        if(!(tipoI.isChar() && tipoD.isChar()))
        {
            Utilidades.Singlenton.registrarErrorSemantico("_equals()","Se requiere que las dos variables sean de tipo CHAR", linea, columna);
            return false;
        }
        if(!(valorI instanceof Arreglo))
        {
            Utilidades.Singlenton.registrarErrorSemantico("_equals()","Se requiere que el operando 1 sea de tipo ARREGLO", linea, columna);
            return false;            
        }
        if(!(valorD instanceof Arreglo))
        {
            Utilidades.Singlenton.registrarErrorSemantico("_equals()","Se requiere que el operando 2 sea de tipo ARREGLO", linea, columna);
            return false;            
        }      
        
        Arreglo arregloI = (Arreglo)valorI;
        Arreglo arregloD = (Arreglo)valorD;
        if(arregloI.raiz.hijos.size() != arregloD.raiz.hijos.size())
        {
            return false;
        }
        ArrayList<NodoNario> listaCaracteresI = arregloI.raiz.hijos;
        ArrayList<NodoNario> listaCaracteresD = arregloD.raiz.hijos;
        for(int x = 0 ; x< listaCaracteresI.size(); x++)
        {
            if( ((char)listaCaracteresI.get(x).valor)!=((char)listaCaracteresD.get(x).valor))
            {
                return false;
            }
        }
        return true;                                        
    }

    @Override
    public Tipo getTipo() {
        return new Tipo(BOOL);
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
