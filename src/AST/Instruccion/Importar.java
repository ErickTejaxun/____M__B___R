/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instruccion;

import AST.AST;
import AST.Entorno.Entorno;
import Analisis.parser;
import Analisis.scanner;
import Utilidades.ErrorC;
import java.util.StringTokenizer;

/**
 *
 * @author erick
 */
public class Importar implements Instruccion
{
    int linea, columna;
    public String ruta;
    
    public Importar(String r, int l, int c)
    {
        this.ruta = r;
        this.linea  = l;
        this.columna = c;
    }
    
    
    @Override
    public Object ejectuar(Entorno entorno) 
    {
        String pathCarpeta = Utilidades.Singlenton.getArchivoActual();
        
        StringTokenizer tokenPartesPath = new StringTokenizer(pathCarpeta,"\\");
        String[] partesPathActual = new String[tokenPartesPath.countTokens()];  
        int i= 0;
        while(tokenPartesPath.hasMoreTokens()){
            String str=tokenPartesPath.nextToken();
            partesPathActual[i]=str;            
            i++;
        }         
        
        String pathParcial = "";
        for(i = 0 ; i< partesPathActual.length -1 ; i++)
        {
            if(i==0)
            {
                pathParcial += partesPathActual[i];
            }
            else
            {
                pathParcial +="\\" +partesPathActual[i];
            }
        }
        //ruta = pathParcial +"\\"+ ruta;
        
        ruta = calcularPath(pathParcial, ruta);
        
        scanner lexico = null;
        parser sintactico = null;
        try 
        { 
            Utilidades.Singlenton.pilaArchivos.add(ruta);                
            lexico=new scanner(new java.io.FileReader(ruta));                                             
            sintactico = new parser(lexico);                              
            sintactico.parse();         
        } 
        catch (Exception ex) 
        {
            Utilidades.Singlenton.registrarError(ruta, "No se ha encontrado el archivo solicitado." , ErrorC.TipoError.SEMANTICO, linea, columna);
            return null;
        }  
        if(sintactico!=null)
        {
            interpretar(sintactico.raiz,entorno);
        }
        // Interpretamos        
                        
        //imprimirTokens(lexico.listaLexemas);

        
        
        return null;
    }
    
    public void interpretar(AST r, Entorno entorno)
    {        
        if(r!=null)
        {
            ejecutar(r,entorno);
        }        
        //r.ejectuar(new Entorno(new Entorno(null),this));
    }      

    public Object ejecutar(AST r,Entorno entorno)
    {
        r.primerPasada(entorno);
        //segundaPasada(entorno);
        return null;
    }    
    @Override
    public int linea() {
        return linea;
    }

    @Override
    public int columna() {
        return columna;
    }
    
    
    public String calcularPath(String pathRaiz, String path)
    {
        path = path.replace("/", "\\");
        String pathReal = pathRaiz+"\\"+path.replace("/", "\\");
        StringTokenizer tokenPartesPath = new StringTokenizer(pathRaiz,"\\");
        StringTokenizer tokenPartesArchivo = new StringTokenizer(path,"\\");
        
        
        String[] partesPathActual = new String[tokenPartesPath.countTokens()];
        String[] partesPathArchivo = new String[tokenPartesArchivo.countTokens()];
        
        int i=0;
        while(tokenPartesPath.hasMoreTokens()){
            String str=tokenPartesPath.nextToken();
            partesPathActual[i]=str;            
            i++;
        } 
        i=0;
        while(tokenPartesArchivo.hasMoreTokens()){
            String str=tokenPartesArchivo.nextToken();
            partesPathArchivo[i]=str;            
            i++;
        }         
        
        
        int retroceso = 0;
        for(String parte : partesPathArchivo)
        {
            if(parte.equals(".."))
            {
                retroceso++;
            }
        }
        String pathFinal100RealNofake ="";
        for(int c = 0; c < partesPathActual.length - retroceso; c++)
        {
            if(c == 0)
            {
                pathFinal100RealNofake = partesPathActual[c];
            }
            else
            {
                pathFinal100RealNofake += "\\"+partesPathActual[c];
            }
        }
        if(partesPathArchivo[0].equals("."))
        {
            for(int c = 1+retroceso; c < partesPathArchivo.length; c++)
            {
                pathFinal100RealNofake += "\\" + partesPathArchivo[c];
            }
        }
        else
        {
            for(int c = 0+retroceso; c < partesPathArchivo.length; c++)
            {
                pathFinal100RealNofake += "\\" + partesPathArchivo[c];
            }            
        }
        return pathFinal100RealNofake;
    }
    
    
}
