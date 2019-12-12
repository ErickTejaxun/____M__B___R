package Analisis.olc;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Analisis.lexema;
import Utilidades.ErrorC;


%%
%{	    
    public ArrayList<ErrorC> listaErrores = new ArrayList(); // Lista para almacenar errores.
    public ArrayList<lexema> listaLexemas = new ArrayList(); // Lista para almacenar el flujo de palabras (tokens).
    public String cadena ="";
    public int linea=0,columna = 0;
    public void adderror(int linea, int columna, String valor)
    {        
        listaErrores.add(new ErrorC(ErrorC.TipoError.LEXICO,valor, linea, columna));
    }

    public void addLexema(String tipo, String valor, int linea, int columna)
    {        
        listaLexemas.add(new lexema(tipo, valor, linea, columna));	            
    } 
    public void Imprimir(String cadena)
    {
        System.out.println(cadena);
    }   
 

%}
%class scannerOlc /*Nombre de la clase a generar.%cupsym simbolos*/
%unicode /*Caracteres unicode*/
%public /*Se generará una clase pública.*/
%cup  /*Implementación con CUP*/
%full
%line   /*Almacenar el número de linea actual.*/
%char   /* Contador de caracteres.%ignorecase Indiferente entre mayusculas y minusculas*/
%eofval{ // Genera el simbolo de cierre.
	return new Symbol(sym.EOF);    
%eofval}

espacio = \t|\f|" "|\r|\n    // ER para capturar espacios, salto de línea, tabulaciones.
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
cadena = (("\"" [^*] ~"\"") | ("\“" [^*] ~"\”") | ("\"\""))

comentario = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"|"/**" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*"+"*"+ [^/*] ~"*/"|"/**"+ [^/*] ~"*/"|"/**"+ [^/*] ~"**/"

%%

<YYINITIAL>
{
[\n] { yychar=0;}

{espacio}
{
    //Imprimir("Salto de linea");
}

{comentario} 
{
    //Imprimir(yytext());
}
/*Palabras reservadas*/
"proyecto"   {return new Symbol(sym.tproyecto, yychar, yyline, yytext());}
"configuracion"   {return new Symbol(sym.tconfiguracion, yychar, yyline, yytext());}
"ruta"   {return new Symbol(sym.truta, yychar, yyline, yytext());}
"nombre"   {return new Symbol(sym.tnombre, yychar, yyline, yytext());}
"correr"   {return new Symbol(sym.tcorrer, yychar, yyline, yytext());}
"archivo"   {return new Symbol(sym.tarchivo, yychar, yyline, yytext());}
"fecha_mod"   {return new Symbol(sym.tfecha, yychar, yyline, yytext());}
"carpeta"   {return new Symbol(sym.tcarpeta, yychar, yyline, yytext());}


/*Palabras reservadas*/
","   {return new Symbol(sym.coma, yychar, yyline, yytext());}
":"   {return new Symbol(sym.dospuntos, yychar, yyline, yytext());}
"{"   {return new Symbol(sym.llavei, yychar, yyline, yytext());}
"}"   {return new Symbol(sym.llaved, yychar, yyline, yytext());}
                                                        
{cadena}  {return new Symbol(sym.cadena, yychar, yyline,yytext().substring(1, yytext().length()-1));}                                                   
.		{
            System.out.println("Caracter ilegal: " + yytext()+" Linea : "+yyline +" Columna: "+yychar); 
            adderror(yyline, yychar, yytext());
        }           	
}