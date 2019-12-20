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
import static AST.Entorno.Tipo.TypePrimitive.*;
import AST.Expresion.Variable;
import Utilidades.ErrorC;

/**
 *
 * @author erick
 */
public class Asignacion implements Instruccion
{    
    public String id;
    public Expresion expresion;
    public int linea, columna;
    public Expresion destino;
    
    public Asignacion(String id, int l,int c)
    {        
        this.id = id;
        this.linea = l;
        this.columna = c;
    }
    
    public Asignacion(String id, Expresion e, int l, int c)
    {        
        this.id = id;
        this.expresion = e;
        this.linea = l;
        this.columna = c;        
    }
        
    public Asignacion(Expresion or, Expresion e, int l, int c)
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
        if(destino instanceof Simbolo)
        {
            Simbolo simboloTmp = (Simbolo)destino;
            if(simboloTmp.rol == Simbolo.Rol.CONSTANTE)
            {
                Utilidades.Singlenton.registrarErrorSemantico(simboloTmp.id, "No es posible la reasignación a un valor constate.", linea, columna);
                return null;
            }
        }
        if(destino instanceof Variable)
        {
            Variable var = (Variable) destino;
            simbolo = entorno.obtener(var.id);
            
            /*Verificamos si no es constante*/
            if(simbolo.rol == Simbolo.Rol.CONSTANTE)
            {
                Utilidades.Singlenton.registrarErrorSemantico(simbolo.id, "No es posible la reasignación a un valor constate.", linea, columna);
                return null;                
            }
            
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
                
                if(contenedorVariable instanceof Simbolo)
                {
                    Simbolo simboloTmp = (Simbolo)contenedorVariable;
                    if(simboloTmp.rol == Simbolo.Rol.CONSTANTE)
                    {
                        Utilidades.Singlenton.registrarErrorSemantico(simboloTmp.id, "No es posible la reasignación a un valor constate.", linea, columna);
                        return null;
                    }
                }                
                
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
                        if(!expresion.getTipo().isChar())
                        {
                           if(expresion.getTipo().isString())
                           {
                                Utilidades.Singlenton.registrarErrorSemantico("Error asignación", "Debe utilizar la función _copi() para copiar arreglos de caracters",linea, columna);
                                return null;                               
                           }
                            Utilidades.Singlenton.registrarError(Utilidades.Singlenton.nombreVariable, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+simbolo.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                            return null;                           
                        }
                    break;
                    case INT:   
                        switch(expresion.getTipo().typeprimitive)
                        {
                            case DOUBLE:
                                if((double)valor >= Utilidades.Singlenton.maxInt)
                                {
                                    Utilidades.Singlenton.registrarErrorSemantico(valor.toString(),"El valor supera el valor máximo para un contenedor int.", linea, columna);
                                    return null;
                                }
                                if((double)valor <= Utilidades.Singlenton.minInt)
                                {
                                    Utilidades.Singlenton.registrarErrorSemantico(valor.toString(),"El valor es menor al valor mínimo para un contenedor int.", linea, columna);
                                    return null;
                                }                                
                                valor = (int)((double)valor);
                                break;
                            case CHAR:
                                valor =(char)valor+0;
                                break;
                            default:
                                Utilidades.Singlenton.registrarError(Utilidades.Singlenton.nombreVariable, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+simbolo.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                                return null; 
                        }
                    break;
                    case DOUBLE:
                        switch(expresion.getTipo().typeprimitive)
                        {
                            case CHAR:
                                valor =(char)valor+0.0;
                                break;
                            case INT:
                                valor = (double) (int)valor;
                                break;
                            default:
                                Utilidades.Singlenton.registrarError(Utilidades.Singlenton.nombreVariable, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+simbolo.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                                return null;                                
                        }                        
                    break;   
                    default:
                        if(!expresion.getTipo().nombreTipo().equals(simbolo.tipo.nombreTipo()))
                        {
                            if(!expresion.getTipo().nombreTipo().equals("NULO"))
                            {
                                Utilidades.Singlenton.registrarError(Utilidades.Singlenton.nombreVariable, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo " + simbolo.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                                return null;
                            }                                                                                
                        }                    
                    break;                                                         
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
    
}
