/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Entorno;

import AST.Expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Componente extends Simbolo
{
    public enum tipoComponente {TEXT,TEXTAREA,TEXTPASS, TEXTNUM, ETIQUETA, BOTON,MENSAJE};
    public tipoComponente tipoGui;
    public int ancho = 0, alto =0;
    public String texto = "";
    public int posX = 0, posY;

    public Componente() 
    {
        super();        
    }
    
    public Componente(int l, int c, String id, tipoComponente t)
    {
        super();
        this.id = id;
        this.linea = l;
        this.columna = c;
        this.tipo = new Tipo("Erickgui");
        this.tipoGui = t;     
    }

    
    
    public void setTipoGui(tipoComponente tipoGui) {
        this.tipoGui = tipoGui;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public tipoComponente getTipoGui() {
        return tipoGui;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public String getTexto() {
        return texto;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


    
    
    
}
