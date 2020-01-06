/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresion;

import AST.Clase.Fusion;
import AST.Clase.Objeto;
import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Entorno.Tipo;
import static AST.Entorno.Tipo.TypePrimitive.INT;
import AST.Expresion.Arreglo.Arreglo;

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
        Simbolo simboloBuscado = ent.obtener(nombre);        
        if(simboloBuscado==null)
        {
            Utilidades.Singlenton.registrarErrorSemantico(nombre, "Estructura no encontrada.", linea, columna);
            return null;
        }
        
        /*Caso 1, arreglos. Se devuelve el número de dimensiones*/
        if(simboloBuscado.rol == Simbolo.Rol.VAR)
        {
            if(simboloBuscado.tipo.isPrimitivo())
            {                
                return 1;
            }
            else
            {
                if(simboloBuscado instanceof Objeto)
                {
                    Objeto obtTmp = (Objeto)simboloBuscado;
                    return obtTmp.listaAtributos.size();
                }
            }
        }
                        
        if(simboloBuscado.rol == Simbolo.Rol.ARREGLO)
        {
            Arreglo arreglo = (Arreglo)(simboloBuscado.valor);
            return arreglo.tamaniosDimensiones.size();
        }
        
        Fusion fusionTmp = (Fusion)simboloBuscado;
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
