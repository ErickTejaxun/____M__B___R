/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Clase;

import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import static AST.Entorno.Simbolo.Rol.*;
import AST.Entorno.Tipo;
import AST.Expresion.Expresion;
import AST.Expresion.Funcion.DeclaracionAtributo;
import AST.Expresion.Funcion.Funcion;
import AST.Instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Fusion extends Simbolo implements Instruccion, Expresion
{            
    public ArrayList<DeclaracionAtributo> listaAtributos;
    public ArrayList<Atributo> atrib;                 
    public Entorno entornoClase;
         
    public Fusion ()
    {
        this.listaAtributos = new ArrayList<>();                                              
        this.rol = CLASE;        
    }
        
    public Fusion(int l, int c)
    {
        this.listaAtributos = new ArrayList<>();                    
        this.linea = l;
        this.columna = c;
        this.rol = CLASE;
    }
    
    public Fusion(String nombre, ArrayList<Funcion> lf)
    {        
        this.id = nombre;             
    }
        
    public void setId(String id)
    {
        this.id = id;
        this.tipo = new Tipo(id);
    }
    

    public void setListaAtributos(ArrayList<DeclaracionAtributo> listaAtributos) 
    {
        this.listaAtributos = listaAtributos;
    }

    public void addAtributo(DeclaracionAtributo atrib)
    {
        this.listaAtributos.add(atrib);
    }

    @Override
    public Object ejectuar(Entorno entorno) 
    {
        /*Tenemos que ir a tomar todas las */
        this.rol = CLASE;
        entorno.insertar(this);
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

    @Override
    public Object getValor(Entorno entorno) 
    {        
        this.entornoClase = entorno;  
        /*Declaracion de cada uno de los atributos*/
        for(DeclaracionAtributo dec: this.listaAtributos)
        {
            dec.ejectuar(entorno);
        }

        return null;
    }
    

    
}
