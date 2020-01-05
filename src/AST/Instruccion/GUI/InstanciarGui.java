/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion.GUI;

import AST.Entorno.Componente;
import AST.Entorno.Entorno;
import AST.Entorno.Simbolo;
import AST.Expresion.Expresion;
import AST.Instruccion.Instruccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author erick
 */
public class InstanciarGui implements Instruccion
{
    public int linea, columna;
    public String id;
    
    public InstanciarGui(String i, int l, int c)
    {
        this.id = i;
        this.linea = l;
        this.columna = c;
    }
        
    @Override
    public Object ejectuar(Entorno entorno) 
    {
        Simbolo s = entorno.obtener(id);
        if(s==null)
        {
            Utilidades.Singlenton.registrarErrorSemantico(id, "No se ha encontrado el componente", linea, columna);
            return null;
        }
        if(!(s instanceof Componente))
        {
            Utilidades.Singlenton.registrarErrorSemantico(id, "La variable no es un componente.", linea, columna);
            return null;
        }
        Componente componente = (Componente)s;
        switch(componente.tipoGui)
        {
            case TEXT:
                JTextField textField = new JTextField();
                textField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                textField.setBounds(0, 0, 10,10);
                componente.valor = textField;
                break;
            case TEXTAREA:
                JTextArea textArea = new JTextArea();
                textArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                textArea.setBounds(0, 0, 10,10);
                componente.valor = textArea;
                break;
            case TEXTPASS:
                JPasswordField textPassword = new JPasswordField ();
                textPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                textPassword.setBounds(0, 0, 10,10);
                componente.valor = textPassword;
                break;
            case TEXTNUM:
                JSpinner spinner = new JSpinner();
                spinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                spinner.setBounds(0, 0, 10,10);
                componente.valor = spinner;
                break;
            case ETIQUETA:
                JLabel etiqueta = new JLabel();
                etiqueta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                etiqueta.setBounds(0, 0, 10,10);
                componente.valor = etiqueta;
                break;
            case BOTON:
                JButton boton = new JButton();
                boton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                boton.setBounds(0, 0, 10,10);
                componente.valor = boton;
                if(s instanceof Componente)
                {
                    Componente compTmp = (Componente)s;
                    if(compTmp.evento != null)
                    {
                        boton.addActionListener
                        (
                            new ActionListener() 
                            {
                                @Override
                                public void actionPerformed(ActionEvent e) 
                                {
                                    compTmp.evento.ejectuar(entorno);
                                }
                            }
                        ); 
                        
                        boton.addMouseListener
                        (
                                new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                            @Override
                            public void mousePressed(MouseEvent e) 
                            {                                                
                                ((Expresion)compTmp.evento).getValor(entorno);
                            }                                                                
                        }
                        );

                    }
                }                
                break;              
        }        
        return componente;
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
