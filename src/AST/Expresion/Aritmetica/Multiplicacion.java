/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion.Aritmetica;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.*;
import AST.Expresion.Expresion;
import Utilidades.ErrorC;
import java.text.DecimalFormat;

/**
 *
 * @author erick
 */
public class Multiplicacion implements Expresion
{
    public int linea, columna;
    public Tipo tipo;
    public Expresion operadori, operadord;
    public Object valor;
    public Multiplicacion(Expresion i, Expresion d, int l, int c)
    {
        this.operadori = i;
        this.operadord = d;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {
        Object valori = operadori.getValor(ent);
        Object valord = operadord.getValor(ent);
        tipo = new Tipo("");
        tipo.typeprimitive = determinarTipo(operadori.getTipo().typeprimitive, operadord.getTipo().typeprimitive);
        if(tipo!=null)
        {
            switch(tipo.typeprimitive)
            {
                case DOUBLE:                    
                    Tipo tipoi = operadori.getTipo();
                    Tipo tipod = operadord.getTipo();

                    if(operadori.getTipo().typeprimitive == CHAR)
                    {
                        valor = (char)valori * (double)valord;
                    }
                    else
                    if(operadord.getTipo().typeprimitive == CHAR)
                    {
                        valor = (double)valori * (char)valord;
                    }
                    else 
                    if(tipoi.isInt())
                    {
                       valor = (int)valori * (double)valord;
                    }
                    else 
                    if(tipod.isInt())
                    {
                        valor = (double)valori * (int)valord;
                    }
                    else
                    {
                        
                        //valor = Double.parseDouble(valori.toString()) * Double.parseDouble(valord.toString());
                        valor = (double)valori * (double)valord;
                    }                                        
                break;
                case INT:
                case CHAR:  
                    if(operadori.getTipo().typeprimitive == CHAR)
                    {
                        valor = (char)valori * (int)valord;
                    }
                    else
                    if(operadord.getTipo().typeprimitive == CHAR)
                    {
                        valor = (int)valori * (char)valord;
                    }
                    else
                    {
                        valor = (int)valori * (int)valord;
                    }    
                break;
            }            
        }
        else
        {
            Utilidades.Singlenton.registrarError("Multiplicación", "No se puede operar tipos " + operadori.getTipo() + " * " +operadord.getTipo() , ErrorC.TipoError.SEMANTICO, linea, columna);
        }
        if(valor instanceof Double)
        {
            double tmp = (double)valor;
            DecimalFormat df = new DecimalFormat("#.#####");
            String cad = df.format(tmp);
            valor = Double.parseDouble(cad);
        }
        return valor;
    }

    
    public Tipo.TypePrimitive determinarTipo(Tipo.TypePrimitive tipo1, Tipo.TypePrimitive tipo2)
    {
        if(tipo1 == STRING || tipo2 == STRING)
        {
            return null;
        }
        else
        if(tipo1 == DOUBLE || tipo2 == DOUBLE)
        {
            if(isNumeric(tipo1)&& isNumeric(tipo2))
            {
                return DOUBLE;
            }
            else
            {
                return null;
            }
        }
        else
        if(tipo1 == INT || tipo2 == INT)
        {
            if(isNumeric(tipo1)&& isNumeric(tipo2))
            {                
                return INT;
            }
            else
            {
                return null;
            }
        } 
        else
        if(tipo1==CHAR && tipo2 ==CHAR)
        {
            return CHAR;
        }
        else
        {
            return null;
        }        
    }
    
    public boolean isNumeric(Tipo.TypePrimitive e)
    {
        switch(e)
        {
            case INT:
            case DOUBLE:
            case CHAR:
                return true;               
        }
        return false;
    }        
    @Override
    public Tipo getTipo() 
    {        
        return tipo;
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
