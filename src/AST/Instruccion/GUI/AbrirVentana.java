/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.GUI;

import AST.Entorno.Componente;
import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Entorno.Tipo;
import AST.Expresion.Expresion;
import AST.Expresion.Funcion.Llamada;
import AST.Instruccion.Bloque;
import AST.Instruccion.Instruccion;
import AST.Nodo;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author erick
 */
public class AbrirVentana implements Instruccion
{
    public int linea, columna;
    public String id;
    
    public AbrirVentana(String i, int l, int c)
    {
        this.id = i;
        this.linea = l;
        this.columna = c;
    }

    @Override
    public Object ejectuar(Entorno entorno) 
    {                    
        Simbolo s = entorno.obtener("ventana__"+id+"$");
        if(s== null)
        {
            Utilidades.Singlenton.registrarErrorSemantico(id, "No se ha encontrado la ventana.", linea, columna);
            return null;            
        }
        if(!s.tipo.isVentana())
        {
            Utilidades.Singlenton.registrarErrorSemantico(id, "No es una ventana", linea, columna);
            return null;                        
        }                        
        Object vObject = s.valor;
        if(vObject == null)
        {
            if(s instanceof Iniciar_Ventana )
            {   
                Iniciar_Ventana ventana = (Iniciar_Ventana)s;
                JFrame frame = new JFrame();
                frame.setLayout(null);
                frame.setTitle(id);
                frame.setBounds(0,0,600,600);    
                frame.show();  
                if(ventana.instrucciones != null)
                {
                    Bloque instrucciones = (Bloque)ventana.instrucciones;
                    for(Nodo inst : instrucciones.instrucciones)
                    {
                        Object compTmp = null;
                        if(inst instanceof Instruccion)
                        {
                           if(inst instanceof SetAltoAncho)
                           {
                               SetAltoAncho instruccionTamaño = (SetAltoAncho)inst;
                               Object objAlto = instruccionTamaño.alto.getValor(entorno);
                               Tipo tipoAlto = instruccionTamaño.alto.getTipo();
                               Object objAncho = instruccionTamaño.ancho.getValor(entorno);
                               Tipo tipoAncho = instruccionTamaño.ancho.getTipo();
                               if( !(tipoAlto.isNumeric() && tipoAncho.isNumeric()))
                               {
                                   Utilidades.Singlenton.registrarErrorSemantico("SetAlto y ancho", "Los argumentos de la función deben ser númericos.", instruccionTamaño.linea, instruccionTamaño.columna);
                                   return null;
                               }                                                          
                               frame.setBounds(0, 0,
                                       tipoAlto.isDouble() ? (int)((double)objAlto) : tipoAlto.isInt() ? (int)objAlto : (char)objAlto,
                                       tipoAncho.isDouble() ? (int)((double)objAncho) : tipoAncho.isInt() ? (int)objAncho : (char)objAncho);
                           }
                           else
                           {
                               compTmp = ((Instruccion)inst).ejectuar(entorno);
                           }
                           
                        }
                        else
                        {
                            if(inst instanceof Llamada)
                            {
                                Llamada llamada = (Llamada)inst;
                                Object objComponente = llamada.origen.getValor(entorno);
                                compTmp = objComponente;
                                Tipo tipoComponente = llamada.origen.getTipo();
                                if(objComponente == null)
                                {
                                    Utilidades.Singlenton.registrarErrorSemantico("Componente","El componente no existe", llamada.linea, llamada.columna);
                                    return null;
                                }
                                if(!tipoComponente.nombreTipo().toLowerCase().equals("erickgui"))
                                {
                                    Utilidades.Singlenton.registrarErrorSemantico("Componente","El símbolo no representa un componente gui", llamada.linea, llamada.columna);
                                    return null;                                    
                                }                          
                                switch(llamada.nombreMetodo.toLowerCase())
                                {
                                    case "settexto":    
                                        if(llamada.parametros.size()!=1)
                                        {
                                            compTmp = ((Expresion)inst).getValor(entorno);
                                        }     
                                        else
                                        {
                                            Object objTexto = llamada.parametros.get(0).getValor(entorno);
                                            compTmp = objTexto;
                                            if(objComponente instanceof  JTextField)
                                            {
                                                ((JTextField)objComponente).setText(objTexto.toString());                                                
                                            }
                                            if(objComponente instanceof  JTextArea)
                                            {
                                                ((JTextArea)objComponente).setText(objTexto.toString());                                                
                                            }                                            
                                            if(objComponente instanceof  JLabel)
                                            {
                                                ((JLabel)objComponente).setText(objTexto.toString());                                                
                                            } 
                                            if(objComponente instanceof  JPasswordField)
                                            {
                                                ((JPasswordField)objComponente).setText(objTexto.toString());                                                
                                            }           
                                            if(objComponente instanceof  JButton)
                                            {
                                                ((JButton)objComponente).setText(objTexto.toString());                                                
                                            }                                                
                                            if(objComponente instanceof  JSpinner)
                                            {
                                                Tipo tipo = llamada.parametros.get(0).getTipo();
                                                if(tipo.isNumeric())
                                                {
                                                    ((JSpinner)objComponente).setValue(vObject);
                                                }                                                
                                            }                                             
                                        }
                                        break;
                                    case "setancho":                                       
                                        if(llamada.parametros.size()!=1)
                                        {
                                            compTmp = ((Expresion)inst).getValor(entorno);
                                        }     
                                        else
                                        {
                                            Object objAlto = llamada.parametros.get(0).getValor(entorno);
                                            Tipo tipoAlto = llamada.parametros.get(0).getTipo();
                                            if(!tipoAlto.isNumeric())
                                            {
                                                Utilidades.Singlenton.registrarErrorSemantico("Set alto", " Se esperaba un valor númerico", llamada.linea, llamada.columna);
                                                return null;
                                            }
                                            if(objComponente instanceof  JTextField)
                                            {
                                                JTextField textField = (JTextField)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), textField.getWidth(), 
                                                        tipoAlto.isDouble() ? (int)(double) objAlto : tipoAlto.isInt() ? (int)objAlto : (char) objAlto);
                                            }
                                            if(objComponente instanceof  JTextArea)
                                            {
                                                JTextArea textField = (JTextArea)objComponente;                                                
                                                textField.setBounds(textField.getX(),textField.getY(), textField.getWidth(), 
                                                        tipoAlto.isDouble() ? (int)(double) objAlto : tipoAlto.isInt() ? (int)objAlto : (char) objAlto);
                                            }                                            
                                            if(objComponente instanceof  JLabel)
                                            {
                                                JLabel textField = (JLabel)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), textField.getWidth(), 
                                                        tipoAlto.isDouble() ? (int)(double) objAlto : tipoAlto.isInt() ? (int)objAlto : (char) objAlto);
                                            }                                           
                                             if(objComponente instanceof  JButton)
                                            {
                                                JButton textField = (JButton)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), textField.getWidth(), 
                                                        tipoAlto.isDouble() ? (int)(double) objAlto : tipoAlto.isInt() ? (int)objAlto : (char) objAlto);
                                            }  
                                             if(objComponente instanceof  JPasswordField)
                                            {
                                                JPasswordField textField = (JPasswordField)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), textField.getWidth(), 
                                                        tipoAlto.isDouble() ? (int)(double) objAlto : tipoAlto.isInt() ? (int)objAlto : (char) objAlto);
                                            }           
                                             if(objComponente instanceof  JSpinner)
                                            {
                                                JSpinner textField = (JSpinner)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), textField.getWidth(), 
                                                        tipoAlto.isDouble() ? (int)(double) objAlto : tipoAlto.isInt() ? (int)objAlto : (char) objAlto);
                                            }                                              
                                        }                                                                                
                                        break;
                                    case "setalto":
                                        if(llamada.parametros.size()!=1)
                                        {
                                            compTmp = ((Expresion)inst).getValor(entorno);
                                        }     
                                        else
                                        {
                                            Object objAncho = llamada.parametros.get(0).getValor(entorno);
                                            Tipo tipoAncho = llamada.parametros.get(0).getTipo();
                                            if(!tipoAncho.isNumeric())
                                            {
                                                Utilidades.Singlenton.registrarErrorSemantico("Set ancho", " Se esperaba un valor númerico", llamada.linea, llamada.columna);
                                                return null;
                                            }
                                            if(objComponente instanceof  JTextField)
                                            {
                                                JTextField textField = (JTextField)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), 
                                                        tipoAncho.isDouble() ? (int)(double) objAncho : tipoAncho.isInt() ? (int)objAncho : (char) objAncho,
                                                        textField.getHeight()
                                                );
                                            }
                                            if(objComponente instanceof  JTextArea)
                                            {
                                                JTextArea textField = (JTextArea)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), 
                                                        tipoAncho.isDouble() ? (int)(double) objAncho : tipoAncho.isInt() ? (int)objAncho : (char) objAncho,
                                                        textField.getHeight()
                                                );
                                            }                                            
                                            if(objComponente instanceof  JLabel)
                                            {
                                                JLabel textField = (JLabel)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), 
                                                        tipoAncho.isDouble() ? (int)(double) objAncho : tipoAncho.isInt() ? (int)objAncho : (char) objAncho,
                                                        textField.getHeight()
                                                );
                                            }                                           
                                             if(objComponente instanceof  JButton)
                                            {
                                                JButton textField = (JButton)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), 
                                                        tipoAncho.isDouble() ? (int)(double) objAncho : tipoAncho.isInt() ? (int)objAncho : (char) objAncho,
                                                        textField.getHeight()
                                                );
                                            }  
                                             if(objComponente instanceof  JPasswordField)
                                            {
                                                JPasswordField textField = (JPasswordField)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), 
                                                        tipoAncho.isDouble() ? (int)(double) objAncho : tipoAncho.isInt() ? (int)objAncho : (char) objAncho,
                                                        textField.getHeight()
                                                );
                                            }           
                                             if(objComponente instanceof  JSpinner)
                                            {
                                                JSpinner textField = (JSpinner)objComponente;
                                                textField.setBounds(textField.getX(),textField.getY(), 
                                                        tipoAncho.isDouble() ? (int)(double) objAncho : tipoAncho.isInt() ? (int)objAncho : (char) objAncho,
                                                        textField.getHeight()
                                                );
                                            }                                              
                                        }                                                                                                                                                                
                                        break;                                        
                                    case "setpos":
                                        if(llamada.parametros.size()!=2)
                                        {
                                            compTmp = ((Expresion)inst).getValor(entorno);
                                        }     
                                        else
                                        {
                                            Object objX = llamada.parametros.get(0).getValor(entorno);
                                            Tipo tipoX = llamada.parametros.get(0).getTipo();
                                            Object objY = llamada.parametros.get(1).getValor(entorno);
                                            Tipo tipoY = llamada.parametros.get(1).getTipo();                                            
                                            if(!(tipoX.isNumeric() && tipoY.isNumeric()))
                                            {
                                                Utilidades.Singlenton.registrarErrorSemantico("Set posición", " Se esperaba un valores númericos", llamada.linea, llamada.columna);
                                                return null;
                                            }
                                            if(objComponente instanceof  JTextField)
                                            {
                                                JTextField textField = (JTextField)objComponente;
                                                textField.setBounds( 
                                                        tipoX.isDouble() ? (int)(double) objX : tipoX.isInt() ? (int)objX : (char) objX,
                                                        tipoY.isDouble() ? (int)(double) objX : tipoY.isInt() ? (int)objY : (char) objY,
                                                        textField.getHeight(), textField.getWidth()                                                   
                                                );
                                            }
                                            if(objComponente instanceof  JTextArea)
                                            {
                                                JTextArea textField = (JTextArea)objComponente;
                                                textField.setBounds( 
                                                        tipoX.isDouble() ? (int)(double) objX : tipoX.isInt() ? (int)objX : (char) objX,
                                                        tipoY.isDouble() ? (int)(double) objX : tipoY.isInt() ? (int)objY : (char) objY,
                                                        textField.getHeight(), textField.getWidth()                                                   
                                                );
                                            }                                            
                                            if(objComponente instanceof  JLabel)
                                            {
                                                JLabel textField = (JLabel)objComponente;
                                                textField.setBounds( 
                                                        tipoX.isDouble() ? (int)(double) objX : tipoX.isInt() ? (int)objX : (char) objX,
                                                        tipoY.isDouble() ? (int)(double) objX : tipoY.isInt() ? (int)objY : (char) objY,
                                                        textField.getHeight(), textField.getWidth()                                                   
                                                );
                                            }                                           
                                             if(objComponente instanceof  JButton)
                                            {
                                                JButton textField = (JButton)objComponente;
                                                textField.setBounds( 
                                                        tipoX.isDouble() ? (int)(double) objX : tipoX.isInt() ? (int)objX : (char) objX,
                                                        tipoY.isDouble() ? (int)(double) objX : tipoY.isInt() ? (int)objY : (char) objY,
                                                        textField.getHeight(), textField.getWidth()                                                   
                                                );
                                            }  
                                             if(objComponente instanceof  JPasswordField)
                                            {
                                                JPasswordField textField = (JPasswordField)objComponente;
                                                textField.setBounds( 
                                                        tipoX.isDouble() ? (int)(double) objX : tipoX.isInt() ? (int)objX : (char) objX,
                                                        tipoY.isDouble() ? (int)(double) objX : tipoY.isInt() ? (int)objY : (char) objY,
                                                        textField.getHeight(), textField.getWidth()                                                   
                                                );
                                            }           
                                             if(objComponente instanceof  JSpinner)
                                            {
                                                JSpinner textField = (JSpinner)objComponente;
                                                textField.setBounds( 
                                                        tipoX.isDouble() ? (int)(double) objX : tipoX.isInt() ? (int)objX : (char) objX,
                                                        tipoY.isDouble() ? (int)(double) objX : tipoY.isInt() ? (int)objY : (char) objY,
                                                        textField.getHeight(), textField.getWidth()                                                   
                                                );
                                            }                                              
                                        }
                                        break;                                        
                                }
                                
                            }
                            else
                            {
                                compTmp=  ((Expresion)inst).getValor(entorno);
                            }                            
                        }
                        
                        if(compTmp instanceof Componente)
                        {
                            Componente comp = (Componente)compTmp;
                            frame.add((Component)comp.valor);                               
                        }
                        frame.repaint();
                    }              
                    /*Tomamos las instrucciones y las ejecutamos.*/
                    s.valor = frame;
                    //frame.show();  
                } 
            }            
        }
        return null;
    }

    @Override
    public int linea() {
        return columna;
    }

    @Override
    public int columna() {
        return linea;
    }
}
