/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion;

import AST.Expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Dec 
{
    public String id;
    public int dimensiones;
    public ArrayList<Expresion> Sizedimensiones  = new ArrayList<Expresion>();
    public Expresion valor;
    public int linea, columna;
    public Dec(String i , int l, int c)
    {
        this.id = i;
        this.dimensiones=0;
        this.linea = l;
        this.columna = c;
        this.Sizedimensiones  = new ArrayList<Expresion>();
        
    }
    public Dec(String i, int d, int l, int c)
    {
        this.id = i;
        this.dimensiones = d;
        this.linea = l;
        this.columna = c;        
    }
    
    public Dec(String i, ArrayList<Expresion> l, Expresion e, int linea, int col)
    {
        this.id = i;
        this.Sizedimensiones = l;
        this.linea = linea;
        this.columna = col;
        this.valor = e;
    }
    
    public Dec(String i, ArrayList<Expresion> l,  int linea, int col)
    {
        this.id = i;
        this.Sizedimensiones = l;
        this.linea = linea;
        this.columna = col;
        //this.valor = e;
    }    
        
    public Dec(String i, Expresion e, int l, int c)
    {
        this.id = i;
        dimensiones=0;
        this.valor =e;
        this.linea = l;
        this.columna = c;        
        
    }
    public Dec(String i, int d, Expresion e, int l, int c)
    {
        this.id = i;
        this.dimensiones = d;
        this.valor =e;
        this.linea = l;
        this.columna = c;        
    }    
}
