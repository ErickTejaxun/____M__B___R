/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion.Relacional;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.*;
import AST.Expresion.Expresion;
import Utilidades.ErrorC;

/**
 *
 * @author erick
 */
public class Igual implements Expresion
{
    int linea, columna;
    Tipo tipo;
    Expresion opi, opd;
    
    public Igual(Expresion i, Expresion d, int l, int c)
    {
        this.opi = i;
        this.opd = d;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {               
        Object derecha = opd.getValor(ent);
        Object izquierda = opi.getValor(ent);
        tipo = new Tipo("");
        tipo.typeprimitive = BOOL;
        Tipo t1 = opi.getTipo();
        Tipo t2 = opd.getTipo();
        if( t1.typeprimitive != t2.typeprimitive)
        {
            if(isNumeric(opi) && isNumeric(opd))
            {
                return (opi.getTipo().isChar()? (char)izquierda: opi.getTipo().isInt() ? (int)izquierda:(Double)izquierda) == 
                        (opd.getTipo().isChar()? (char)derecha: opd.getTipo().isInt() ? (int)derecha:(Double)derecha);                
            }
            else
            {
                /*Verificamos si es nulo y objeto*/
                if(opi.getTipo().isNulo() || opd.getTipo().isNulo())
                {
                    if(opi.getValor(ent) == null)
                    {
                        return opi.getTipo().isNulo() ? opd.getValor(ent) == null : opi.getValor(ent) == null;
                    }
                    if(opd.getValor(ent) == null)
                    {
                        return opd.getTipo().isNulo() ? opi.getValor(ent) == null : opd.getValor(ent) == null;
                    }
                }
                else
                {
                    Utilidades.Singlenton.registrarError("Relacional Igual", "No se puede operar entre tipos distintos. " + opi.getTipo().nombreTipo() + " == " +opd.getTipo().nombreTipo() , ErrorC.TipoError.SEMANTICO, linea, columna);            
                    return false;                                                
                }
            }
        }        
        
        switch(opi.getTipo().nombreTipo().toLowerCase())
        {
            case "string":
                return derecha.toString().equals(izquierda.toString());
            case "int":
                return (int)izquierda == (int)derecha;
            case "double":
                return (double)izquierda == (double)derecha;
            case "boolean":
                return (boolean)izquierda == (boolean)derecha;
            case "char":
                return (char)izquierda == (char)derecha;
        }
        return derecha == izquierda;                
    }

    public boolean isNumeric(Expresion e)
    {
        switch(e.getTipo().typeprimitive)
        {
            case INT:
            case DOUBLE:
            case CHAR:
                return true;               
        }
        return false;
    }    
    
    @Override
    public Tipo getTipo() {
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
