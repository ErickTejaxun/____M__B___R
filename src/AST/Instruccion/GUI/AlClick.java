/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.GUI;

import AST.Entorno.Componente;
import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Entorno.Tipo;
import AST.Expresion.Expresion;
import AST.Instruccion.Bloque;
import AST.Instruccion.Instruccion;

/**
 *
 * @author erick
 */
public class AlClick implements Instruccion, Expresion
{
    public int linea, columna;
    public Instruccion instrucciones;
    public String id;
    
    
    public AlClick(String i, Instruccion inst, int l , int c )
    {
        this.id = i;
        this.linea = l;
        this.columna = c;
        this.instrucciones = inst;
    }
    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {        
        Simbolo simb = entorno.obtener(id);
        if(simb instanceof Componente)
        {
            Componente componente = (Componente)simb;
            if(componente.tipoGui == Componente.tipoComponente.BOTON)
            {                
                componente.evento = this;                
            }
            else
            {
                Utilidades.Singlenton.registrarErrorSemantico(id, "No es un botón", linea, columna);
                return null;
            }
        }
        else
        {
            Utilidades.Singlenton.registrarErrorSemantico(id, "No es un botón", linea, columna);
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

    @Override
    public Object getValor(Entorno ent) 
    {
        if(instrucciones instanceof Bloque)
        {
            try 
            {
                Bloque bloque = (Bloque)instrucciones;
                bloque.crearHilo();
                Object valor =  bloque.ejectuar(new Entorno(ent, Utilidades.Singlenton.ventana));
                Utilidades.Singlenton.ventana.mostrarErrores();
                return valor;                
            } 
            catch (Exception e) 
            {
                Utilidades.Singlenton.ventana.mostrarErrores();
                return null;
            }
        }        
        return null;
    }

    @Override
    public Tipo getTipo() {
        return new Tipo(Tipo.TypePrimitive.NULO);
    }
    
}
