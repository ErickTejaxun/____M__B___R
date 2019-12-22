/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion;

import AST.Entorno.Entorno;
import AST.Entorno.Tipo;
import AST.Expresion.Arreglo.Arreglo;
import AST.Expresion.Expresion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author erick
 */
public class Message implements Instruccion
{
    public int linea, columna;
    public Expresion valor;
    public boolean salto;
    public ArrayList<Expresion> listaValores;
    public Message(Expresion v, int l, int c)
    {
        this.valor = v;
        this.linea = l;
        this.columna = c;
        this.salto = true;
    }
       
    public Message(Expresion v, int l, int c, ArrayList<Expresion> list )
    {
        this.valor = v;
        this.linea = l;
        this.columna = c;
        this.salto = true;
        this.listaValores = list;
    }
    
    public Message(Expresion v, boolean s, int l, int c)
    {
        this.valor = v;
        this.linea = l;
        this.columna = c;
        this.salto = s;
    }    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {
        Object exp = valor.getValor(entorno);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        //System.out.println(exp);        
        if(entorno.ventana!=null)
        {
            if(exp!=null)
            {
                Object valorTmp = exp;//.toString()
                if(valorTmp instanceof Arreglo)
                {
                    Imprimir( entorno,((Arreglo)valorTmp).getCadena());
                }
                else
                {
                    String cadenaCompleta = valorTmp.toString();
                    String[] partes =cadenaCompleta.split("%");
                    ArrayList<String> partesFinales = new ArrayList<>();
                    partesFinales.add(partes[0]);
                    /*Hay que verificar cuales son los % que si son modos de impresión.*/
                                                
                    for(int x = 1 ; x < partes.length; x++)
                    {
                        String cadenaActual = partes[x];
                        char caracter = partes[x].charAt(0);
                        switch(caracter)
                        {
                            case 'd':
                            case 'e':
                            case 'b':
                            case 'c':
                            case 's':
                                /**
                                 * Significa que tenemos que dejar esta parte ya que representa un modo de impresión.
                                 */
                                partesFinales.add(cadenaActual);
                                break;
                            default:
                                /*Significa que tenemos que mostrar la cadena completa ya que no hace referencia a algún modo de impresión.*/
                                if(partesFinales.isEmpty())
                                {
                                    partesFinales.add("%"+cadenaActual);
                                }
                                else
                                {                                    
                                    partesFinales.set(partesFinales.size() -1 , partesFinales.get(partesFinales.size()-1) + "%"+ cadenaActual );
                                }
                                break;
                        }                            
                    }

                    if(partesFinales.size()-1 == listaValores.size())
                    {
                        String cadena = partesFinales.get(0);
                        for(int x = 1; x < (partesFinales.size()) ; x++)
                        {
                            Object valor = listaValores.get(x-1).getValor(entorno);
                            Tipo tipo = listaValores.get(x-1).getTipo();
                            String parteCadena = partesFinales.get(x).substring(1, partesFinales.get(x).length());
                            char mode = partesFinales.get(x).charAt(0);  
                            System.out.println("Modo de impresión \t"+mode);
                            switch(mode)
                            {
                                case 'd':
                                    if(tipo.isNumeric())
                                    {
                                        if(tipo.isInt())
                                        {
                                            //cadena += Double.parseDouble(Integer.valueOf(valor.toString()).toString()); 
                                            cadena += (double)((int)valor);                                                    
                                        }
                                        if(tipo.isChar())
                                        {
                                            //cadena += Double.parseDouble(Integer.valueOf(valor.toString()).toString());
                                            cadena += (double)((char)valor);
                                        }
                                        if(tipo.isDouble())
                                        {
                                            cadena += (double)valor;
                                            //cadena += Double.parseDouble(valor.toString());
                                        }
                                    }
                                    else
                                    {
                                        Utilidades.Singlenton.registrarErrorSemantico("Impresion %d", "Formato de valor inválido", linea, columna);
                                    }
                                    break;
                                case 'e':
                                    if(tipo.isNumeric())
                                    {
                                        if(tipo.isChar())
                                        {
                                            cadena += ((int)(char)valor);
                                        }
                                        if(tipo.isInt())
                                        {
                                            cadena += (int)valor;
                                        }
                                        if(tipo.isDouble())
                                        {
                                            cadena += ((Double)valor).intValue();
                                            //cadena += Integer.valueOf(valor.toString());
                                        }                                        
                                    }
                                    else
                                    {
                                        Utilidades.Singlenton.registrarErrorSemantico("Impresion %e", "Formato de valor inválido", linea, columna);
                                    }                                    
                                    break;
                                case 'c':
                                    if(tipo.isNumeric())
                                    {
                                        char tmp = 0;
                                        if(tipo.isInt())
                                        {
                                            tmp = (char)((int)valor);
                                        }else
                                        if(tipo.isChar())
                                        {
                                            tmp = (char)valor;
                                        }else
                                        if(tipo.isDouble())
                                        {
                                            tmp = ((char)(double)valor);
                                        }                                        
                                        cadena += tmp;
                                    }
                                    else
                                    {
                                        Utilidades.Singlenton.registrarErrorSemantico("Impresion %c", "Formato de valor inválido", linea, columna);
                                    }                                       
                                    break;
                                case 'b':
                                    if(tipo.isBoolean())
                                    {
                                        cadena += valor;
                                    }
                                    else
                                    if(tipo.isNumeric())
                                    {                                        
                                        if(tipo.isInt())
                                        {
                                            if((int)valor == 0)
                                            {
                                                cadena += "false";                                                
                                            }
                                            else
                                            {
                                                cadena += true;
                                            }
                                        }
                                        if(tipo.isDouble())
                                        {
                                            if((Double)valor == 0)
                                            {
                                                cadena += "false";                                                
                                            }
                                            else
                                            {
                                                cadena += true;
                                            }
                                        } 
                                        if(tipo.isChar())
                                        {
                                            if((char)valor == 0)
                                            {
                                                cadena += "false";                                                
                                            }
                                            else
                                            {
                                                cadena += true;
                                            }
                                        }                                                                                
                                    }
                                    else
                                    {
                                        Utilidades.Singlenton.registrarErrorSemantico("Impresion %b", "Formato de valor inválido", linea, columna);
                                    }
                                    break;
                                case 's':
                                    if(tipo.isString())
                                    {
                                        cadena += valor;
                                    }
                                    else
                                    if(tipo.isChar())
                                    {
                                        if(valor instanceof Arreglo)
                                        {
                                            Arreglo arregloConCadena = (Arreglo)valor;
                                            /*String nuevaCadenaAImprimir = "";
                                            for(NodoNario nodo : arregloConCadena.raiz.hijos)                                                
                                            {
                                                nuevaCadenaAImprimir += nodo.valor;
                                            }*/
                                            cadena += arregloConCadena.raiz.getCadena();
                                        }
                                    }
                                    else
                                    {
                                      Utilidades.Singlenton.registrarErrorSemantico("Impresion %s", "Formato de valor inválido", linea, columna);  
                                    }
                                    break;
                            }
                            cadena += parteCadena;
                        }
                        LocalDateTime despues = LocalDateTime.now(); 
                        System.out.println(despues +"\n"+now);
                        Imprimir(entorno,cadena);
                    }
                    else
                    {
                        Utilidades.Singlenton.registrarErrorSemantico("Imprimir", "Cadena con formato incorrecto, el número de los parametros no coincide con los valores.", linea, columna);
                        Imprimir(entorno,cadenaCompleta);
                    }                    
                }                
            }                           
            else
            {
                Imprimir(entorno,"NULO");
            }
        }
        return null;
    }
    
    public void Imprimir(Entorno e, String c)
    {
        JOptionPane.showMessageDialog(null, "Mensaje", c, 0);
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
