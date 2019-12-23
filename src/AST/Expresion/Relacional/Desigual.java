/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion.Relacional;

import AST.Clase.Acceso;
import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.BOOL;
import AST.Expresion.Expresion;
import Utilidades.ErrorC;

/**
 *
 * @author erick
 */
public class Desigual implements Expresion
{
    int linea, columna;
    Tipo tipo;
    Expresion opi, opd;
    
    public Desigual(Expresion i, Expresion d, int l, int c)
    {
        this.opi = i;
        this.opd = d;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object getValor(Entorno ent) 
    {       
        Object derecha = null;// = opd.getValor(ent);
        Object izquierda = null;// = opi.getValor(ent);
        if(opd instanceof Acceso)
        {
            Acceso accesoDerecha = (Acceso)opd;
            derecha = accesoDerecha.getValorMemoria(ent);
        }
        else
        {
            derecha = opd.getValor(ent);
        }
        if(opi instanceof Acceso)
        {
            Acceso accesoDerecha = (Acceso)opi;
            izquierda = accesoDerecha.getValorMemoria(ent);
        }        
        else
        {
            izquierda = opi.getValor(ent);
        }
        tipo = new Tipo("");
        tipo.typeprimitive = BOOL;
        if(derecha==null && izquierda==null)
        {
            return false;            
        }
        if( opi.getTipo().typeprimitive != opd.getTipo().typeprimitive)
        {
            if(isNumeric(opi) && isNumeric(opd))
            {
                return (opi.getTipo().isChar()? (char)izquierda: opi.getTipo().isInt() ? (int)izquierda:(Double)izquierda) != 
                        (opd.getTipo().isChar()? (char)derecha: opd.getTipo().isInt() ? (int)derecha:(Double)derecha);
            }
            else
            {
                /*Verificamos si es nulo y objeto*/
                if(opi.getTipo().isNulo() || opd.getTipo().isNulo())
                {
                    if(opi.getValor(ent) == null)
                    {
                        return opi.getTipo().isNulo() ? opd.getValor(ent) != null : opi.getValor(ent) != null;
                    }
                    if(opd.getValor(ent) == null)
                    {
                        return opd.getTipo().isNulo() ? opi.getValor(ent) != null : opd.getValor(ent) != null;
                    }
                }
                else
                {
                    Utilidades.Singlenton.registrarError("Relacional Desigual", "No se puede operar entre tipos distintos. " + opi.getTipo().nombreTipo() + " != " +opd.getTipo().nombreTipo() , ErrorC.TipoError.SEMANTICO, linea, columna);            
                    return false;                                                
                }
            }
        }
        
        Tipo tmpTipo = opd.getTipo();
        if(tmpTipo.isNumeric())
        {
            return tmpTipo.isDouble()? ((double)derecha != (double)izquierda) : (tmpTipo.isInt()? (int)derecha != (int)izquierda: (char) derecha != (char) izquierda) ;
        }
        
        return  derecha != izquierda;
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
