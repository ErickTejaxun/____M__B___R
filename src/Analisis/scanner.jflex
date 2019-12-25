package Analisis;
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
%class scanner /*Nombre de la clase a generar.%cupsym simbolos*/
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
numero = ([0-9][0-9]*)       // ER para capturar números.
decimal= {numero}"."{numero} // Expresión 
letra = ([a-zA-Z]|"ñ"|"á"|"é"|"í"|"ó"|"ú")
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
id = (({letra}|"_")({letra}|{numero}|"_")*)
/*cadena = (("\"" [^*] ~"\"") | ("\“" [^*] ~"\”") | ("\"\""))*/
comilla = ("\"")
cadCaracter = ("'" [^*] ~"'")|("‘" [^*] ~"’")
direccionWindows= ("\"" ({letra}":"("\\"({id}|{espacio}|"_"|"-"|{numero})+)+"."{id}) "\"")

comentario = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"|"/**" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*"+"*"+ [^/*] ~"*/"|"/**"+ [^/*] ~"*/"|"/**"+ [^/*] ~"**/"
si = ("if")
sino = ("else")
sinosi={sino}({comentario}|{espacio})*(si)
%state cad1, cad2

%%

<cad1>
{
    [^]
    {  
        switch(yytext())
        {
            case "\\":          
                yybegin(cad2);
            break;
            case "\"":      
                yybegin(YYINITIAL);
                return new Symbol(sym.cadena, columna, linea ,cadena);             
            default:
                cadena += yytext();
            break;                
        }                  
    } 
}

<cad2>
{
    [^]
    {
        switch(yytext())
        {
            case "\'":
                cadena += "\'";
            break;            
            case "\"":
                cadena += "\"";
            break;  
            case "?":
                cadena += "?";
            case "%":
                cadena += "%";
            break;
            case "\\":
                cadena += "\\";
            break;  
            case "0":                
            break;  
            case "a":                
            break;   
            case "b":                
                cadena = cadena.substring(0, cadena.length()-1);
            break; 
            case "v":   
                String partes[] = cadena.split("\n");
                String subcadena = "";
                for(int i = 0 ; i < partes.length-1; i++)
                {
                    subcadena += partes[i];
                }         
                String tabulaciones[] = partes[partes.length].split("\t");
                for(int i = 0; i< tabulaciones.length; i++)
                {
                    subcadena += "\t";
                }
                cadena = subcadena;                                                       
            break;             
            case "n":                
                cadena = cadena + "\n";
            break; 
            case "t":                
                cadena = cadena + "\t";
            break;             
            case "r":       
                partes = cadena.split("\n");
                subcadena = "";
                for(int i = 0 ; i < partes.length-1; i++)
                {
                    subcadena += partes[i];
                }                                       
                cadena = subcadena;
            break;                                                                                                       
            default:
                Utilidades.Singlenton.registrarError("\\"+yytext(), "Carácter ilegal. Ha sido eliminado.", ErrorC.TipoError.SEMANTICO,yyline, yychar);
            break;                
        }
        yybegin(cad1);                  
    }
}

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
"true" {return new Symbol(sym.booleano,yychar, yyline, true);}
"false" {return new Symbol(sym.booleano,yychar, yyline, false);}
"ent"   {return new Symbol(sym.tint, yychar, yyline, yytext());}
"dec"   {return new Symbol(sym.tdouble, yychar, yyline, yytext());}        
"chr"  {return new Symbol(sym.tchar, yychar, yyline, yytext());}
"bul"   {return new Symbol(sym.tbool, yychar, yyline, yytext());} 
"nlo"  {return new Symbol(sym.nulo, yychar, yyline, yytext());}
"#definir"  {return new Symbol(sym.tdefinir, yychar, yyline, yytext());}
"importar" {return new Symbol(sym.importar, yychar, yyline, yytext());}
"regresar" {return new Symbol(sym.retorno, yychar, yyline, yytext());}
"fusion"   {return new Symbol(sym.tfusion, yychar, yyline, yytext());}
"_imp"  {return new Symbol(sym.print, yychar, yyline, yytext());}
"_msn"  {return new Symbol(sym.msn, yychar, yyline, yytext());}
"_copi"  {return new Symbol(sym.tcopi, yychar, yyline, yytext());}
"zro"  {return new Symbol(sym.tvoid, yychar, yyline, yytext());}
"_conc"  {return new Symbol(sym.tconcatenar, yychar, yyline, yytext());}
"_atxt"  {return new Symbol(sym.tatexto, yychar, yyline, yytext());}
"_aent"  {return new Symbol(sym.taentero, yychar, yyline, yytext());}
"_adec"  {return new Symbol(sym.tadecimal, yychar, yyline, yytext());}
"_pesode"  {return new Symbol(sym.tpeso, yychar, yyline, yytext());}
"_reservar"  {return new Symbol(sym.treservar, yychar, yyline, yytext());}
"_eqls"  {return new Symbol(sym.teql, yychar, yyline, yytext());}
"_write"  {return new Symbol(sym.twrite, yychar, yyline, yytext());}
"_wf"  {return new Symbol(sym.twriteend, yychar, yyline, yytext());}
"_close"  {return new Symbol(sym.tclose, yychar, yyline, yytext());}

"&&"   {return new Symbol(sym.and, yychar, yyline, yytext());}
"||"   {return new Symbol(sym.or, yychar, yyline, yytext());}
/*Palabras reservadas de sentencias de control*/
"while"  {return new Symbol(sym.mientras, yychar, yyline, yytext());}
"hacer"     {return new Symbol(sym.hacer, yychar, yyline, yytext());}
"for"      {return new Symbol(sym.para, yychar, yyline, yytext());}
"repetir"   {return new Symbol(sym.repetir, yychar, yyline, yytext());}
"switch"    {return new Symbol(sym.switch_, yychar, yyline, yytext());}
"case"      {return new Symbol(sym.caso, yychar, yyline, yytext());}
"romper"    {return new Symbol(sym.romper, yychar, yyline, yytext());}
"siga"    {return new Symbol(sym.continuar, yychar, yyline, yytext());}
"repeat"    {return new Symbol(sym.hacer, yychar, yyline, yytext());}
"default"    {return new Symbol(sym.defecto, yychar, yyline, yytext());}
{si} {return new Symbol(sym.si, yychar, yyline, yytext());}
{sino} {return new Symbol(sym.sino, yychar, yyline, yytext());}

"++"   {return new Symbol(sym.aumento, yychar, yyline, yytext());}
"--"   {return new Symbol(sym.decremento, yychar, yyline, yytext());}

/*Palabras reservadas*/
";"   {return new Symbol(sym.puntocoma, yychar, yyline, yytext());}
":"   {return new Symbol(sym.dospuntos, yychar, yyline, yytext());}
","   {return new Symbol(sym.coma, yychar, yyline, yytext());}
"."   {return new Symbol(sym.punto, yychar, yyline, yytext());}
"=="  {return new Symbol(sym.igualigual, yychar, yyline, yytext());}
"<>"  {return new Symbol(sym.desigual, yychar, yyline, yytext());}
"<="  {return new Symbol(sym.menorigual, yychar, yyline, yytext());}
">="  {return new Symbol(sym.mayorigual, yychar, yyline, yytext());}
"<"   {return new Symbol(sym.menor, yychar, yyline, yytext());}
">"   {return new Symbol(sym.mayor, yychar, yyline, yytext());}
"*"   {return new Symbol(sym.multi, yychar, yyline, yytext());}
"/"   {return new Symbol(sym.div, yychar, yyline, yytext());}
"^"   {return new Symbol(sym.potencia, yychar, yyline, yytext());}
"%"   {return new Symbol(sym.modulo, yychar, yyline, yytext());}
"+"   {return new Symbol(sym.suma, yychar, yyline, yytext());}
"-"   {return new Symbol(sym.menos, yychar, yyline, yytext());}
"("   {return new Symbol(sym.pari, yychar, yyline, yytext());}
")"   {return new Symbol(sym.pard, yychar, yyline, yytext());}
"{"   {return new Symbol(sym.llavei, yychar, yyline, yytext());}
"}"   {return new Symbol(sym.llaved, yychar, yyline, yytext());}
"["   {return new Symbol(sym.corchetei, yychar, yyline, yytext());}
"]"   {return new Symbol(sym.corcheted, yychar, yyline, yytext());}
"="   {return new Symbol(sym.igual, yychar, yyline, yytext());}
"!"   {return new Symbol(sym.not, yychar, yyline, yytext());}
{comilla}  
        {  
            cadena = "";                    
            yybegin(cad1);
            linea = yyline;
            columna = yychar;
        }                                                  
{id}    {  
            
            addLexema("Identificador", yytext(), yyline, yychar);  	        
            return new Symbol(sym.id, yychar, yyline, yytext().toLowerCase());             
        }         
{cadCaracter}  
        {  
            
            addLexema("Identificador", yytext(), yyline, yychar);  	        
            return new Symbol(sym.caracter, yychar, yyline,yytext().substring(1, yytext().length()-1).charAt(0));             
        }     
{numero}  {  
            
            addLexema("Identificador", yytext(), yyline, yychar);  	  
            if(Long.parseLong(yytext())> Utilidades.Singlenton.maxInt && Long.parseLong(yytext()) < Utilidades.Singlenton.minInt )
            {
                Utilidades.Singlenton.registrarError("Entero", "Número entero demasiado grande", ErrorC.TipoError.SEMANTICO,yyline, yychar);
                return new Symbol(sym.entero, yychar, yyline,0);             
            }   
            return new Symbol(sym.entero, yychar, yyline,Integer.parseInt(yytext()));                            
        }  
{decimal}  
        {  
            
            addLexema("Identificador", yytext(), yyline, yychar);  	        
            return new Symbol(sym.decimal, yychar, yyline,Double.parseDouble(yytext()));             
        }                       
.		{
            System.out.println("Caracter ilegal: " + yytext()+" Linea : "+yyline +" Columna: "+yychar); 
            adderror(yyline, yychar, yytext());
        }           	
}