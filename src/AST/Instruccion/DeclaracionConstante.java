/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion;

import AST.Clase.Instancia;
import AST.Clase.Objeto;
import AST.Entorno.Simbolo;
import AST.Expresion.Expresion;
import AST.Entorno.Entorno;
import static AST.Entorno.Simbolo.Rol.CONSTANTE;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.CHAR;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Arreglo.ExpresionArreglo;
import AST.Expresion.Arreglo.NodoNario;
import Utilidades.ErrorC;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class DeclaracionConstante implements Instruccion
{
    public Tipo tipo;
    public String id;
    public Expresion expresion;
    public int linea, columna;
    public int dimensiones;
    public ArrayList<Dec> declaraciones;
    
    
    public DeclaracionConstante(Tipo t, String id, int d, int l,int c)
    {
        this.tipo = t;
        this.id = id;
        this.linea = l;
        this.columna = c;
        this.dimensiones = d;
    }  
    
    public DeclaracionConstante(Tipo t, ArrayList<Dec> lista, int l, int c)
    {
        this.tipo = t;
        this.declaraciones = lista;
        this.linea = l;
        this.columna = c;
    }
    
    
    public DeclaracionConstante(Tipo t, String id, int d, Expresion e, int l,int c)
    {
        this.tipo = t;
        this.id = id;
        this.linea = l;
        this.columna = c;
        this.dimensiones = d;
        this.expresion = e;
    }    
    
    public DeclaracionConstante(Tipo t, String id, int l,int c)
    {
        this.tipo = t;
        this.id = id;
        this.linea = l;
        this.columna = c;
        this.dimensiones = 0;
    }
    
    public DeclaracionConstante(Tipo t, String id, Expresion e, int l, int c)
    {
        this.tipo = t;
        this.id = id;
        this.expresion = e;
        this.linea = l;
        this.columna = c;        
    }
        
    @Override
    public Object ejectuar(Entorno entorno) 
    {        
        if(declaraciones!=null)
        {
            for(Dec d:declaraciones)
            {
                declaraciones(entorno, d.id, d.valor, d.Sizedimensiones);
            }
            return null;
        }
        else
        {
            return null;
        }
    }

    
    
    public Object declaraciones(Entorno entorno, String id, Expresion expresion, ArrayList<Expresion> dimensiones)
    {        
        Object valor = null;
        /*Verificamos si se le ha asignado un valor inicial.*/
        if(entorno.getGlobal().obtener(id)!=null)
        {
            Utilidades.Singlenton.registrarErrorSemantico(id,"Constante ya declarada", linea, columna);
            return null;
        }
        if(expresion!=null)
        {     
            this.tipo = expresion.getTipo();
            if(expresion instanceof Instancia)
            {
                Instancia e = (Instancia)expresion;
                e.setTipo(tipo);
            }
            
            if(expresion instanceof ExpresionArreglo)
            {
                if(((ExpresionArreglo)expresion).tipo == null)
                {
                    ((ExpresionArreglo)expresion).tipo = tipo;
                }
            }            
            valor = expresion.getValor(entorno);     
            if(valor instanceof Arreglo)
            {
                if(((Arreglo)valor).tipo==null)
                {
                    ((Arreglo)valor).tipo = expresion.getTipo();
                }               
            }
            if(expresion.getTipo().typeprimitive != tipo.typeprimitive)
            {
                switch(this.tipo.typeprimitive)
                {
                    case CHAR:
                        if(!expresion.getTipo().isChar())
                        {
                            if(expresion.getTipo().isInt())
                            {
                                int a = (int)valor;
                                if(0<=a && a <=255)
                                {
                                    valor = (char)a;   
                                }   
                                else
                                {
                                    valor = 0;
                                    Utilidades.Singlenton.registrarError(id, 
                                            "Valor no válido para caracter, debe ser positivo y menor a 255. ", 
                                            ErrorC.TipoError.SEMANTICO,linea, columna);
                                }
                            }
                            else
                            {
                                Utilidades.Singlenton.registrarError(id, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+this.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                                return null;                                
                            }
                        }
                    break;
                    case INT:   
                        switch(expresion.getTipo().typeprimitive)
                        {
                            case CHAR:
                                valor =(char)valor+0;
                                break;
                            default:
                                Utilidades.Singlenton.registrarError(id, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+this.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
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
                                Utilidades.Singlenton.registrarError(id, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo "+this.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                                return null; 
                        }                        
                    break;   
                    default:
                        if(expresion.getTipo() != this.tipo)
                        {
                            Utilidades.Singlenton.registrarError(id, "No se le puede asignar un valor de tipo "+ expresion.getTipo().nombreTipo() +" a un tipo " + this.tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                            return null;                        
                        }                    
                    break;                                                         
                }                 
            }  
        }  
        /*Si no se le asigna un valor de inicio, hay que inicializar */
        else
        if(dimensiones.size() ==0)
        {                            
            switch(this.tipo.typeprimitive)
            {
                case INT:
                    valor = 0;                    
                    break;
                case STRING:
                    valor = "";
                    break;                
                case CHAR:
                    valor = "";
                    break;                                
                case DOUBLE:
                    valor = 0.00;
                    break;                                
                case BOOL:
                    valor = false;
                    break;                    
            }             
        }
        
        if(valor instanceof Arreglo)
        {
            Arreglo arregloTmp = (Arreglo)valor;
            System.out.println(arregloTmp.getCadena());
            if(arregloTmp.tamaniosDimensiones.size() != dimensiones.size())
            {
                Utilidades.Singlenton.registrarError(id, "No coincide el número de las dimensiones del valor a asignar", ErrorC.TipoError.SEMANTICO,linea, columna);
                return this;
            }   
            /*Ahora verificamos que el valor coinicida con lo enviado.*/            
        }
        
        /*Verificamos que sean del mismo tipo*/
        if(valor instanceof Objeto)
        {
            Objeto valorObjeto = (Objeto)valor;
            valorObjeto.rol = CONSTANTE;
            /*Ahora verificamos que ambos sean del mismo tipo*/
            if(!(valorObjeto.getTipo().nombreTipo().toLowerCase().equals(this.tipo.nombreTipo().toLowerCase())))
            {
                /*Error*/
                Utilidades.Singlenton.registrarError(id, "No se puede convertir un valor de tipo "+valorObjeto.getTipo().nombreTipo() + " a "+tipo.nombreTipo(), ErrorC.TipoError.SEMANTICO,linea, columna);
                return null;
            }
            valorObjeto.id = this.id;
            if(this.id == null)
            {
                valorObjeto.id = this.declaraciones.get(0).id;
            }
            
            entorno.insertarConstante(valorObjeto);
            return null;
            /*Recordar que si no coinciden los tipos hay que verificar si es necesario hacer un casteo implicito.*/            
            /*Coincidieron los tipos.*/            
        }
        
        /*Aquí vamos a ver si el arreglo está vacio.
        Tenemos que ver que los arreglos estén llenos.                
        */
        /*-----------------------------------------------------------------------------------DECLARACION DE ARREGLOS--------------*/
        if(dimensiones!=null && valor==null)
        {
            if(dimensiones.size() != 0)
            {
                /*Aquí deberíamos */
                Expresion tmp = new ExpresionArreglo(tipo,dimensiones,linea,columna);
                valor = tmp.getValor(entorno);
                Simbolo s = this.dimensiones == 0 ? 
                    new Simbolo(tipo,id,valor,linea,columna):
                    new Simbolo(tipo,id,valor,dimensiones ,linea,columna);   
                s.rol = CONSTANTE;
                entorno.insertarConstante(s);
            } 
            else // en este caso es una declaración de una estructura con valor nulo (a huevos, solo es declaración). 
            {
                Simbolo s = new Simbolo(tipo,id,valor,linea,columna);
                s.rol = CONSTANTE;
                entorno.insertarConstante(s);
            }
        }
        else
        {
            /*Verificamos los tamaños de cada una de las dimensiones*/
            if(dimensiones.size() == 0)
            {
                entorno.insertarConstante(new Simbolo(tipo,id,valor,linea,columna));
            }
            else
            {
                Arreglo arrTmp = (Arreglo)valor;
                NodoNario nodoTmp = arrTmp.raiz;                
                for(int x = 0 ; x < dimensiones.size(); x++)
                {
                    Expresion valorDimension = dimensiones.get(x);
                    if(valorDimension == null){break;}
                    Object tmp = valorDimension.getValor(entorno);
                    Tipo tipoTmp = valorDimension.getTipo();
                    if(!tipoTmp.isNumeric())
                    {
                        Utilidades.Singlenton.registrarErrorSemantico("Dimensión " +x,"Debe contener un valor ", linea, columna);
                        return null;
                    }
                    int tamañoDimension = tipoTmp.isInt()? (int)tmp : tipoTmp.isDouble()? (int)(double)tmp: (char)tmp;
                    /*Ahora verificamos que sean del mismo tamaño*/
                    
                    if(tamañoDimension < nodoTmp.hijos.size())
                    {                        
                        Utilidades.Singlenton.registrarErrorSemantico("Advertencia: "+id+": Dimensión "+x, "No coinciden los tamaños de las dimensiones con el valor a asignar.", linea, columna);                                                
                        ArrayList<NodoNario> listaNueva = new ArrayList<NodoNario>();
                        for(int y = 0; y< tamañoDimension; y++)
                        {                                                 
                            listaNueva.add(nodoTmp.hijos.get(y));
                        }
                        nodoTmp.hijos = listaNueva;
                    }
                    if(tamañoDimension > nodoTmp.hijos.size())
                    {                        
                        Utilidades.Singlenton.registrarErrorSemantico("Advertencia: "+id+": Dimensión "+x, "No coinciden los tamaños de las dimensiones con el valor a asignar.", linea, columna);                                                
                        ArrayList<NodoNario> listaNueva = new ArrayList<NodoNario>();
                        for(int y = 0; y< tamañoDimension; y++)
                        {                                                 
                            listaNueva.add(nodoTmp.hijos.get(y));
                        }
                        nodoTmp.hijos = listaNueva;
                    }                    
                    nodoTmp = nodoTmp.hijos.get(0);                    
                }
                
                /*Verificamos lo los números de elementos de las dimensiones.*/       
                Simbolo simboloNuevo = new Simbolo(tipo,id,valor,new ArrayList<Expresion>() ,linea,columna);
                simboloNuevo.rol = CONSTANTE;
                entorno.insertarConstante(simboloNuevo);
            }                                                    
        }
        return this;
            
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
