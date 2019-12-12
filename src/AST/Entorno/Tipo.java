/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Entorno;

/**
 *
 * @author erick
 */
public class Tipo 
{    
    public enum TypePrimitive {INT,CHAR,BOOL,STRING,DOUBLE,OBJETO,CLASE,NULO};
    public Tipo.TypePrimitive typeprimitive;
    public String typeClass ="";
    public int linea, columna;
    
    public Tipo(Tipo.TypePrimitive t)
    {
        typeprimitive = t;
    }
    
    public Tipo(Tipo.TypePrimitive t , int l, int c)
    {
        typeprimitive = t;
        this.linea = l;
        this.columna = c;
    }    
    
    public Tipo(Tipo.TypePrimitive t, String c)
    {
        typeprimitive = t;
        typeClass=c;
    }
    
    public String nombreTipo()
    {
        return typeClass.equals("")? typeprimitive.toString():typeClass;
    }
    
    public Tipo(String clase)
    {
        typeClass = clase;
        typeprimitive = Tipo.TypePrimitive.OBJETO;
    }
    
    public Tipo(String clase, int l, int c)
    {
        typeClass = clase;
        typeprimitive = Tipo.TypePrimitive.OBJETO;
        this.linea = l;
        this.columna = c;
    }    
    
    public boolean isInt()
    {
        return this.typeprimitive == Tipo.TypePrimitive.INT;
    }
    
    public boolean isDouble()
    {
        return this.typeprimitive == Tipo.TypePrimitive.DOUBLE;
    }   
    
    public boolean isString()
    {
        return this.typeprimitive == Tipo.TypePrimitive.STRING;
    }
    
    public boolean isChar()
    {
        return this.typeprimitive == Tipo.TypePrimitive.CHAR;
    }    
    
    public boolean isBoolean()
    {
        return this.typeprimitive == Tipo.TypePrimitive.BOOL;
    }
    
    public boolean isNulo()
    {
        return this.typeprimitive == Tipo.TypePrimitive.NULO;
    }
    public boolean isObject()
    {
        return this.typeprimitive == Tipo.TypePrimitive.OBJETO;
    }
    public boolean isClass()
    {
        return this.typeprimitive == Tipo.TypePrimitive.CLASE;
    }  
    
    public boolean isNumeric()
    {
        return isChar()||isInt()||isDouble();
    }
    
    public boolean isPrimitivo()
    {
        return !typeClass.equals("");
    }    
}
