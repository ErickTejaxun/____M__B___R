/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.CHAR;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Arreglo.NodoNario;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class aTexto  implements Expresion
{
    public Expresion data;
    public int linea, columna;    

    public aTexto(Expresion e, int l, int c)
    {
        this.data = e;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {
        Object valor = data.getValor(ent);
        String cadena = valor.toString();
                

        /*Hay que pasar la cadena a una expresionArreglo */
        Arreglo tmpArreglo = new Arreglo();
        tmpArreglo.columna = columna;
        tmpArreglo.linea = linea;            
        NodoNario raizArreglo = new NodoNario();
        tmpArreglo.raiz = raizArreglo;
        raizArreglo.hijos = new ArrayList<NodoNario>();
        raizArreglo.tipo = new Tipo(Tipo.TypePrimitive.CHAR);            
        char[] caracteres = cadena.toCharArray();
        for(char caracter : caracteres)
        {
            NodoNario nuevoNodo = new NodoNario();
            nuevoNodo.tipo = tmpArreglo.tipo;
            nuevoNodo.valor = caracter;
            nuevoNodo.linea = linea;
            nuevoNodo.columna = columna;
            raizArreglo.hijos.add(nuevoNodo);
        }     
        tmpArreglo.tipo = new Tipo(CHAR);
        tmpArreglo.linea = linea;
        tmpArreglo.columna = columna;
        tmpArreglo.tamaniosDimensiones = new ArrayList<>();
        tmpArreglo.tamaniosDimensiones.add(caracteres.length);
        return tmpArreglo;        
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
