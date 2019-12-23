/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Clase;

import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Entorno.Tipo;
import AST.Expresion.Expresion;
import AST.Expresion.Funcion.Constructor;
import AST.Expresion.Funcion.ParametroFormal;
import AST.Expresion.Variable;
import Utilidades.ErrorC;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Instancia implements Expresion
{
    public Expresion size;     
    public int linea, columna;
    public Tipo tipo;
    public String idStruct="";
    
    public void setTipo(Tipo t)
    {
        this.tipo = t;
    }
    
    public void setNombre(String t)
    {
        this.idStruct = t;
    }
    
    public Instancia(Expresion s, int l, int c)
    {                
        this.linea = l;
        this.columna = c;
        this.size = s;
    }        
    
    @Override
    public Object getValor(Entorno entorno) 
    {
        /*Hay que buscar */
        Simbolo sim = entorno.getGlobal().obtener(this.tipo.nombreTipo());
        if(sim==null)
        {
            Utilidades.Singlenton.registrarError(this.tipo.nombreTipo(),"No se ha encontrado la clase.", ErrorC.TipoError.SEMANTICO, linea, columna);
            return null;
        }
        if(sim.rol != Simbolo.Rol.CLASE)
        {
            Utilidades.Singlenton.registrarErrorSemantico(tipo.nombreTipo(),"No se ha encontrado la clase.", linea, columna);
            return null;            
        }
        Object valor;
        Tipo tipoTmp = new Tipo(Tipo.TypePrimitive.INT);
        if(size instanceof Variable)
        {
            Variable nombreDeFusion = (Variable)size;
            String nombreFusion = nombreDeFusion.id;
            Simbolo s = entorno.obtener(nombreFusion);
            if(s.rol != Simbolo.Rol.CLASE)
            {
                Utilidades.Singlenton.registrarErrorSemantico(tipo.nombreTipo(),"No se ha encontrado la clase.", linea, columna);
                return null;
            }
            Fusion fusionActual = (Fusion)s;
            valor = fusionActual.listaAtributos.size();            
        }
        else
        {
            valor = size.getValor(entorno);
            tipoTmp = size.getTipo();
        }                        
        if(!tipoTmp.isNumeric())
        {
            Utilidades.Singlenton.registrarErrorSemantico("_reservar","El argumento debe ser de tipo numerico.", linea, columna);
            return null;              
        }
        Fusion simboloF = (Fusion)sim;
        switch(tipoTmp.typeprimitive)
        {
            case INT:
                if((int)valor < simboloF.listaAtributos.size())
                {
                    Utilidades.Singlenton.registrarErrorSemantico("_reservar","El tamaño solicitado es menor al tamaño de la estructura "+simboloF.id, linea, columna);
                    return null;                     
                }
                break;
            case DOUBLE:
                if((double)valor < simboloF.listaAtributos.size())
                {
                    Utilidades.Singlenton.registrarErrorSemantico("_reservar","El tamaño solicitado es menor al tamaño de la estructura "+simboloF.id, linea, columna);
                    return null;                     
                }                
                break;
            case CHAR:
                if((char)valor < simboloF.listaAtributos.size())
                {
                    Utilidades.Singlenton.registrarErrorSemantico("_reservar","El tamaño solicitado es menor al tamaño de la estructura "+simboloF.id, linea, columna);
                    return null;                     
                }                
                break;
        }        
        /*Verificamos que sea una clase*/
        if(sim instanceof Fusion)
        {
            Fusion clase = (Fusion)sim;
            clase.entornoClase = new Entorno(entorno.getGlobalClase(),entorno.ventana);
            clase.getValor(clase.entornoClase);
            Objeto nuevaInstancia = new Objeto();
            nuevaInstancia.entornoObjeto = clase.entornoClase;
            nuevaInstancia.setClaseOrigen(this.tipo.nombreTipo());
            nuevaInstancia.linea = this.linea;
            nuevaInstancia.columna = this.columna;
            nuevaInstancia.valor = nuevaInstancia;
            nuevaInstancia.tipo = new Tipo(clase.id);
            
            /*Ahora buscamos el contrusctor, si no hay constructor, mandamos un null ;) */
            String firma = clase.id;
            ArrayList<Object> resultados = new ArrayList<>();
            ArrayList<Tipo> tipos = new ArrayList<>()  ;              
            /*Buscamos la función*/            
            Simbolo sfuncion = nuevaInstancia.entornoObjeto.obtener(firma);
            if(sfuncion==null)
            {
                Utilidades.Singlenton.registrarError(clase.id, "No se ha encontrado el constructor solicitado "+firma , ErrorC.TipoError.SEMANTICO, linea, columna);               
                return null;
            }
            if(sfuncion instanceof Constructor)
            {
                Constructor constructor = (Constructor)sfuncion;
                int cont = 0;                
                for(Object item:resultados)
                {
                    /*String nombre = constructor.parametrosFormales.get(cont).id;
                    Simbolo s = nuevaInstancia.entornoObjeto.obtener(nombre);
                    s.valor = item;*/                    
                    ParametroFormal formal = constructor.parametrosFormales.get(cont);                    
                    formal.valor =item;
                    cont++;
                }                
                /*Cremos un nuevo entorno*/
                constructor.getValor(new Entorno(nuevaInstancia.entornoObjeto,entorno.ventana));
                
            }
            return nuevaInstancia;            
        }                
        return null;
    }

    @Override
    public Tipo getTipo() 
    {
        return tipo;
    }

    @Override
    public int linea() 
    {
        return linea;
    }

    @Override
    public int columna() 
    {
        return columna;
    }
    
}
