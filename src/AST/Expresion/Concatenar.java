/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion;

import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.CHAR;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Arreglo.NodoNario;

/**
 *
 * @author erick
 */
public class Concatenar implements Expresion
{
    public Expresion destino, nueva;
    public int linea,  columna;
    
    public Concatenar(Expresion e1, Expresion e2, int l, int c)
    {
        this.destino  = e1;
        this.nueva = e2;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {
        Object destino = this.destino.getValor(ent);
        if(!(destino instanceof Arreglo))
        {
            Utilidades.Singlenton.registrarErrorSemantico("concatenar", "El destino debe ser una arreglo de caracteres.", linea, columna);
            return null;
        }
        Arreglo arregloDestino = (Arreglo)destino;
        if(!arregloDestino.tipo.isChar())
        {
            Utilidades.Singlenton.registrarErrorSemantico("concatenar", "El destino debe ser una arreglo de caracteres.", linea, columna);
            return null;            
        }
        //Aquí ya estamos seguros del caracteres.
        Object nueva = this.nueva.getValor(ent);
        if(!(nueva instanceof Arreglo))
        {
            Utilidades.Singlenton.registrarErrorSemantico("concatenar", "El valor a concatenar debe ser un arreglo de caracteres.", linea, columna);
            return null;            
        }
        Arreglo arregloNuevo = (Arreglo)nueva;
        if(!arregloNuevo.tipo.isChar())
        {
            Utilidades.Singlenton.registrarErrorSemantico("concatenar", "El valor a concatenar debe ser un arreglo de caracteres.", linea, columna);
            return null;                
        }
        
        int indiceInicioCaracterVacio = 0;
        for(NodoNario nodo: arregloDestino.raiz.hijos)
        {
            char valorNodo = (char)nodo.valor;
            if(valorNodo!=0){indiceInicioCaracterVacio++;}
        }
        
        for(int x = 0; x<arregloNuevo.raiz.hijos.size(); x++)
        {
            arregloDestino.raiz.hijos.set(x+indiceInicioCaracterVacio, arregloNuevo.raiz.hijos.get(x) );
        }
        return arregloDestino;       
    }

    @Override
    public Tipo getTipo() {
        return new Tipo(CHAR);
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
