/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.INT;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Arreglo.NodoNario;

/**
 *
 * @author erick
 */
public class aEntero implements Expresion
{
    public Expresion expresion;
    public int linea, columna;
    
    public aEntero(Expresion e, int l, int c)
    {
        this.expresion = e;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {
        Object valor = expresion.getValor(ent);
        if(valor == null)
        {
            Utilidades.Singlenton.registrarErrorSemantico("AEntero","No se puede convertir un valor nulo a entero", linea, columna);
            return null;
        }
        String cadena = "";
        if(valor instanceof Arreglo)
        {
            Arreglo arreglo = (Arreglo)valor;
            for(NodoNario nodo : arreglo.raiz.hijos)
            {
                cadena += nodo.valor;
            }
        }
        else
        {
            cadena = valor.toString();
        }
        
        if(cadena.contains("."))
        {
            try 
            {
                double valorDouble = Double.valueOf(cadena.toString().trim());
                return (int)valorDouble;
            } catch (NumberFormatException e) 
            {
                Utilidades.Singlenton.registrarErrorSemantico("AEntero","Formato incorrecto.", linea, columna);
                return null;                
            }
        }
        else
        {
            try 
            {
                int valorInt = Integer.valueOf(cadena.toString().trim());
                return valorInt;
            } catch (NumberFormatException e) 
            {
                Utilidades.Singlenton.registrarErrorSemantico("AEntero","Formato incorrecto.", linea, columna);
                return null;                  
            }            
        }
                
    }

    @Override
    public Tipo getTipo() {
        return new Tipo(INT);
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
