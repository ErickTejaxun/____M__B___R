/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.GUI;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import AST.Expresion.Expresion;
import AST.Instruccion.Instruccion;

/**
 *
 * @author erick
 */
public class SetAltoAncho implements Instruccion
{
    public int linea, columna;
    public Expresion ancho, alto;
    
    
    public SetAltoAncho(Expresion e1, Expresion e2, int l, int c)
    {
        this.alto = e1;
        this.ancho = e2;
        this.linea = l;
        this.columna = c;
    }
    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {
        Object valorAncho = ancho.getValor(entorno);
        Tipo tipoAncho = ancho.getTipo();        
        Object valorAlto = alto.getValor(entorno);
        Tipo tipoAlto = alto.getTipo();
        if(!(tipoAncho.isNumeric() && tipoAlto.isNumeric()))
        {
            Utilidades.Singlenton.registrarErrorSemantico("_alto_ancho", " Se esperaban valores de tipo numerico en los dos parametros. ", linea, columna);
            return null;
        }
        
        
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
