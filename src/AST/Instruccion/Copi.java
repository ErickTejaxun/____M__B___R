/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion;

import AST.Clase.Acceso;
import AST.Clase.Objeto;
import AST.Entorno.Simbolo;
import AST.Expresion.Expresion;
import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.*;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Arreglo.ExpresionArreglo;
import AST.Expresion.Arreglo.NodoNario;
import AST.Expresion.Variable;
import Utilidades.ErrorC;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Copi implements Instruccion
{    
    public String id;
    public Expresion expresion;
    public int linea, columna;
    public Expresion destino;
    
    public Copi(String id, int l,int c)
    {        
        this.id = id;
        this.linea = l;
        this.columna = c;
    }
    
    public Copi(String id, Expresion e, int l, int c)
    {        
        this.id = id;
        this.expresion = e;
        this.linea = l;
        this.columna = c;        
    }
        
    public Copi(Expresion or, Expresion e, int l, int c)
    {        
        this.destino = or;
        this.expresion = e;
        this.linea = l;
        this.columna = c;        
    }   
        
    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {        
        //Simbolo simbolo = entorno.obtener(this.id);
        Simbolo simbolo = null;        
        if(destino instanceof Variable)
        {
            Variable var = (Variable) destino;
            simbolo = entorno.obtener(var.id);
            /*Primero tenemos que verificar si la asignación es a un arreglo como tal*/
            if(simbolo.rol == Simbolo.Rol.ARREGLO)
            {
                
            }
        }
        else
        {
            /*
            Hay que hacer modificaciones. Cuando se busca una variable, si se utiliza getvalor(entorno) devolverá el valor tal cual. 
            Hay que modificar para obtener el símbolo contenedor del valor, no el valor ímplicito. 
            */
            
            if(this.destino instanceof Acceso)
            {
                Acceso tmpAcceso = (Acceso)this.destino;   
                Object contenedorVariable = tmpAcceso.origen.getValor(entorno);
                if(contenedorVariable instanceof Objeto)
                {
                    Objeto varContenedor = (Objeto)contenedorVariable;
                    String nombreVariableBuscada = "";
                    if(tmpAcceso.destino instanceof Variable)
                    {
                        nombreVariableBuscada = ((Variable)tmpAcceso.destino).id;
                    }
                    // Ahora buscamos la variable. 
                    Object simboloBuscado = varContenedor.entornoObjeto.obtener(nombreVariableBuscada);
                    if(simboloBuscado instanceof Simbolo)
                    {
                        simbolo = (Simbolo)simboloBuscado;
                    }
                }
            }
            
            Object destinoObjeto = this.destino.getValor(entorno);
            
            if(destinoObjeto instanceof Simbolo)
            { 
                simbolo = (Simbolo)destinoObjeto;
            }
        }

        if(simbolo==null)
        {
            Utilidades.Singlenton.registrarError(Utilidades.Singlenton.nombreVariable, "Error asignacion: la variable no ha sido encontrada.", ErrorC.TipoError.SEMANTICO,linea, columna);
            return null;
        }        
        Object valor = null;
        if(expresion!=null)
        {
            valor = expresion.getValor(entorno);
            if(expresion.getTipo().typeprimitive != simbolo.tipo.typeprimitive)
            {
                switch(simbolo.tipo.typeprimitive)
                {
                    case CHAR:
                        if(expresion.getTipo().isString())
                        {
                          simbolo.valor= generarArregloAtravesDeCadena(linea, columna, valor.toString(), entorno);                                                       
                        }
                        break;
//                         Utilidades.Singlenton.registrarError(Utilidades.Singlenton.nombreVariable, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+simbolo.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
//                         return null;                      
                    
                    default:                                
                                Utilidades.Singlenton.registrarErrorSemantico(id, "Función _copi() solo permite copiar caracteres. Tipo del valor:"+simbolo.tipo.nombreTipo(), linea, linea);
                                return null;                                                                           
                }                 
            }  
        }  
        else
        {                            
            switch(simbolo.getTipo().typeprimitive)
            {
                case INT:
                    valor = 0;
                    break;
                case STRING:
                    valor = "";
                    break;                
                case CHAR:
                    valor = ' ';
                    break;                                
                case DOUBLE:
                    valor = 0.00;
                    break;                                
                case BOOL:
                    valor = false;
                    break;                    
            }             
        }
        simbolo.valor = valor;
        /*
        Simbolo s = new Simbolo(simbolo.getTipo(),id,valor,simbolo.linea,simbolo.columna);
        if(!entorno.actualizar(s))
        {
            //System.out.println("Error, variable " +s.id + " ya declarada.");
        }*/
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
    
    
    public Arreglo generarArregloAtravesDeCadena(int valorright, int valorleft, String cadena,Entorno entorno)
    {
        Arreglo tmpArreglo = new Arreglo();
        tmpArreglo.columna = valorright;
        tmpArreglo.linea = valorleft;            
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
            nuevoNodo.linea = valorright;
            nuevoNodo.columna = valorleft;
            raizArreglo.hijos.add(nuevoNodo);
        }
        ExpresionArreglo tmp = new ExpresionArreglo(raizArreglo, valorright, valorleft);
        return  (Arreglo)tmp.getValor(entorno);                
    }    
    
}
