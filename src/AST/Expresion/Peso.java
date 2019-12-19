/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion;

import AST.Clase.Fusion;
import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.INT;

/**
 *
 * @author erick
 */
public class Peso implements Expresion
{
    public String nombre;
    public int linea, columna;
    
    public Peso(String n, int l, int c)
    {
        this.nombre = n;
        this.linea = l;
        this.columna = c;
    }

    @Override
    public Object getValor(Entorno ent) 
    {
        Simbolo simboloF = ent.obtener(nombre);
        if(simboloF==null)
        {
            Utilidades.Singlenton.registrarErrorSemantico(nombre, "Estructura no encontrada.", linea, columna);
            return null;
        }
        if(simboloF.rol != Simbolo.Rol.CLASE)
        {
            Utilidades.Singlenton.registrarErrorSemantico(nombre, "No es una estructura", linea, columna);
            return null;            
        }
        Fusion fusionTmp = (Fusion)simboloF;
        return fusionTmp.listaAtributos.size();                
    }

    @Override
    public Tipo getTipo() {
        return new Tipo(INT);
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
