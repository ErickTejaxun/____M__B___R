
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analisis.olc;

import java_cup.runtime.Symbol;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import AST.AST;
import AST.Instruccion.Instruccion;
import Utilidades.ErrorC;
import Analisis.olc.Proyecto.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parserOlc extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parserOlc() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parserOlc(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parserOlc(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\030\000\002\002\004\000\002\002\005\000\002\003" +
    "\007\000\002\004\004\000\002\004\003\000\002\005\003" +
    "\000\002\005\003\000\002\005\003\000\002\005\003\000" +
    "\002\020\006\000\002\006\006\000\002\007\006\000\002" +
    "\010\007\000\002\011\005\000\002\011\003\000\002\012" +
    "\003\000\002\012\003\000\002\013\007\000\002\014\004" +
    "\000\002\014\003\000\002\015\003\000\002\015\003\000" +
    "\002\016\005\000\002\017\010" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\071\000\004\016\004\001\002\000\004\004\010\001" +
    "\002\000\004\002\006\001\002\000\004\002\001\001\002" +
    "\000\004\017\073\001\002\000\004\015\011\001\002\000" +
    "\004\016\012\001\002\000\012\005\017\006\013\007\022" +
    "\010\020\001\002\000\004\015\070\001\002\000\014\005" +
    "\ufffc\006\ufffc\007\ufffc\010\ufffc\017\ufffc\001\002\000\014" +
    "\005\ufffa\006\ufffa\007\ufffa\010\ufffa\017\ufffa\001\002\000" +
    "\014\005\017\006\013\007\022\010\020\017\066\001\002" +
    "\000\004\015\033\001\002\000\004\015\030\001\002\000" +
    "\014\005\ufffb\006\ufffb\007\ufffb\010\ufffb\017\ufffb\001\002" +
    "\000\004\015\025\001\002\000\014\005\ufffd\006\ufffd\007" +
    "\ufffd\010\ufffd\017\ufffd\001\002\000\014\005\ufff9\006\ufff9" +
    "\007\ufff9\010\ufff9\017\ufff9\001\002\000\004\020\026\001" +
    "\002\000\004\014\027\001\002\000\022\005\ufff7\006\ufff7" +
    "\007\ufff7\010\ufff7\011\ufff7\012\ufff7\013\ufff7\017\ufff7\001" +
    "\002\000\004\020\031\001\002\000\004\014\032\001\002" +
    "\000\014\005\ufff6\006\ufff6\007\ufff6\010\ufff6\017\ufff6\001" +
    "\002\000\004\016\034\001\002\000\006\011\036\013\042" +
    "\001\002\000\006\014\ufff2\017\ufff2\001\002\000\004\015" +
    "\053\001\002\000\006\014\ufff1\017\ufff1\001\002\000\006" +
    "\014\050\017\052\001\002\000\006\014\ufff3\017\ufff3\001" +
    "\002\000\004\015\043\001\002\000\004\016\044\001\002" +
    "\000\004\007\022\001\002\000\006\011\036\013\042\001" +
    "\002\000\006\014\050\017\047\001\002\000\006\014\uffea" +
    "\017\uffea\001\002\000\006\011\036\013\042\001\002\000" +
    "\006\014\ufff4\017\ufff4\001\002\000\014\005\ufff5\006\ufff5" +
    "\007\ufff5\010\ufff5\017\ufff5\001\002\000\004\016\054\001" +
    "\002\000\006\007\022\012\057\001\002\000\010\007\uffec" +
    "\012\uffec\017\uffec\001\002\000\010\007\uffee\012\uffee\017" +
    "\uffee\001\002\000\004\015\064\001\002\000\010\007\uffed" +
    "\012\uffed\017\uffed\001\002\000\010\007\022\012\057\017" +
    "\062\001\002\000\006\014\ufff0\017\ufff0\001\002\000\010" +
    "\007\uffef\012\uffef\017\uffef\001\002\000\004\020\065\001" +
    "\002\000\010\007\uffeb\012\uffeb\017\uffeb\001\002\000\004" +
    "\017\uffff\001\002\000\014\005\ufffe\006\ufffe\007\ufffe\010" +
    "\ufffe\017\ufffe\001\002\000\004\020\071\001\002\000\004" +
    "\014\072\001\002\000\014\005\ufff8\006\ufff8\007\ufff8\010" +
    "\ufff8\017\ufff8\001\002\000\004\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\071\000\004\002\004\001\001\000\004\003\006\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\016\004\015\005" +
    "\022\006\020\007\014\010\023\020\013\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\014\005" +
    "\066\006\020\007\014\010\023\020\013\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\012" +
    "\011\037\012\040\013\034\017\036\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\006\044\001\001\000\012\011\045\012\040\013\034" +
    "\017\036\001\001\000\002\001\001\000\002\001\001\000" +
    "\010\012\050\013\034\017\036\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\012\006\057\014" +
    "\060\015\055\016\054\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\010\006" +
    "\057\015\062\016\054\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parserOlc$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parserOlc$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parserOlc$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


	   	
	public ArrayList<ErrorC> listaErrores = new ArrayList();	
	public ArrayList<Instruccion> ast = new ArrayList<Instruccion>();
	public Proyecto proyecto = null;
    
    public elemento tipoElemento;
	/*@Override
	public void syntax_error(Symbol cur_token) 
	{        
		List<Integer> listaIdTokens = expected_token_ids();
		LinkedList<String> listaNombres = new LinkedList<String>();
		for (Integer expected : listaIdTokens) 
		{			
			listaNombres.add(symbl_name_from_id(expected));
		}		
		Utilidades.Singlenton.registrarError(String.valueOf(cur_token.value), 
			String.valueOf(cur_token.value) + ". Se esperaba :"+listaNombres.toString(), ErrorC.TipoError.LEXICO, cur_token.right+1, cur_token.left+1);
		/*listaErrores.add(
					listaErrores.add(new ErrorC(ErrorC.TipoError.SINTACTICO,
								String.valueOf(cur_token.value) + ". Se esperaba :"+listaNombres.toString(),
								cur_token.right+1,
								cur_token.left+1));
                
	}*/


    public void report_error(String message, Object info) 
    {
        int linea = 0;
        int columna = 0;
        java_cup.runtime.Symbol s = null;
        StringBuilder m = new StringBuilder("Error Sintactico");

        if (info instanceof java_cup.runtime.Symbol) 
        {
            s = ((java_cup.runtime.Symbol) info);
            if (s.left >= 0) 
            {                
                columna = s.left+1;
                if (s.right>= 0)
                {
                    linea = s.right+ 1;
                }                    
            }
        }

        m.append(" Se esperaba: "+message);
        //System.err.println(m.toString());
        //System.out.println("Error");
        //System.out.println("Error linea:"+linea+", col:"+columna);
        LinkedList<String> toks = new LinkedList();

        if(!expected_token_ids().isEmpty())
        {
            Imprimir("No esta vacia "+ expected_token_ids().size());		  
            for(int w=0; w<expected_token_ids().size(); w++)
            { 
                if(expected_token_ids().get(w) !=sym.error)
                {
                    int tok = (int)expected_token_ids().get(w);                        
                    toks.add( symbol_name_from_id(tok) );
                }
            }
        }

        Imprimir(expected_token_ids().size());
      
        for(int w=0; w<expected_token_ids().size(); w++)
        { 
            if(expected_token_ids().get(w)!=sym.error)
            {
                int tok = (int)expected_token_ids().get(w);
                toks.add( symbol_name_from_id(tok) );
            }
        }
        String esperados = "";
        for(String id : toks)
        {
            if(!esperados.equals(""))
            {
                esperados += ", ";
            }
            esperados += id;
            
        }        
        Utilidades.Singlenton.registrarError(s.value.toString(), "Se esperaba .. " +esperados, ErrorC.TipoError.SINTACTICO, linea, columna);		                  
    }	

    public String symbol_name_from_id(int id){
        return sym.terminalNames[id];
    }

	/*public void addError(Symbol s)
	{
		listaErrores.add(new ErrorC("Sintactico",s.rights.right+1,ConvertirObjectToString(s.value)));
	}*/
	public void Imprimir(Object cad)
	{
		System.out.println(cad.toString());
	}	



/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parserOlc$actions {
  private final parserOlc parser;

  /** Constructor */
  CUP$parserOlc$actions(parserOlc parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parserOlc$do_action_part00000000(
    int                        CUP$parserOlc$act_num,
    java_cup.runtime.lr_parser CUP$parserOlc$parser,
    java.util.Stack            CUP$parserOlc$stack,
    int                        CUP$parserOlc$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parserOlc$result;

      /* select the action based on the action number */
      switch (CUP$parserOlc$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		RESULT = start_val;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parserOlc$parser.done_parsing();
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= llavei PROYECTO llaved 
            {
              Object RESULT =null;
		int proleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int proright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Proyecto pro = (Proyecto)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		 Imprimir("Correcto OLC."); proyecto = pro; 
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("INICIO",0, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // PROYECTO ::= tproyecto dospuntos llavei CONTENIDO llaved 
            {
              Proyecto RESULT =null;
		int proyectoleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int proyectoright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Proyecto proyecto = (Proyecto)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		RESULT = proyecto;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("PROYECTO",1, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-4)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // CONTENIDO ::= CONTENIDO ITEM 
            {
              Proyecto RESULT =null;
		int proleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int proright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Proyecto pro = (Proyecto)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		int proyTmpleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int proyTmpright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Proyecto proyTmp = (Proyecto)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		
                RESULT = pro;
                switch(tipoElemento)
                {
                    case RUTA:
                        pro.ruta = proyTmp.ruta;
                        break;
                    case NOMBRE:
                        pro.nombre = proyTmp.nombre;
                        break;
                    case CORRER:
                        pro.rutaRun = proyTmp.rutaRun;
                        break;
                    case CONF:
                        pro.carpeta = proyTmp.carpeta;
                        break;
                }
            
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("CONTENIDO",2, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // CONTENIDO ::= ITEM 
            {
              Proyecto RESULT =null;
		int proleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int proright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Proyecto pro = (Proyecto)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		 RESULT= pro;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("CONTENIDO",2, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // ITEM ::= RUTA 
            {
              Proyecto RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = new Proyecto(); RESULT.ruta = v; tipoElemento= Proyecto.elemento.RUTA;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ITEM",3, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // ITEM ::= NOMBRE 
            {
              Proyecto RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = new Proyecto(); RESULT.nombre = v; tipoElemento = Proyecto.elemento.NOMBRE;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ITEM",3, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // ITEM ::= CORRER 
            {
              Proyecto RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = new Proyecto(); RESULT.rutaRun = v; tipoElemento = Proyecto.elemento.CORRER;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ITEM",3, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // ITEM ::= CONFIGURACION 
            {
              Proyecto RESULT =null;
		int carpetaleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int carpetaright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Carpeta carpeta = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = new Proyecto(); RESULT.carpeta = carpeta; tipoElemento = Proyecto.elemento.CONF;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ITEM",3, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // RUTA ::= truta dospuntos cadena coma 
            {
              String RESULT =null;
		int cadleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int cadright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		String cad = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		 RESULT = cad; tipoElemento = Proyecto.elemento.RUTA;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("RUTA",14, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-3)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // NOMBRE ::= tnombre dospuntos cadena coma 
            {
              String RESULT =null;
		int cadleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int cadright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		String cad = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		 
            RESULT = cad; 
            tipoElemento = Proyecto.elemento.NOMBRE;
            Imprimir("Nombre elemento \t"+cad);
        
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("NOMBRE",4, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-3)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // CORRER ::= tcorrer dospuntos cadena coma 
            {
              String RESULT =null;
		int cadleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int cadright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		String cad = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		 RESULT = cad; tipoElemento = Proyecto.elemento.CORRER;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("CORRER",5, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-3)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // CONFIGURACION ::= tconfiguracion dospuntos llavei ELEMENTOS llaved 
            {
              Carpeta RESULT =null;
		int carpetaleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int carpetaright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Carpeta carpeta = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		
            RESULT = carpeta;
        
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("CONFIGURACION",6, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-4)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // ELEMENTOS ::= ELEMENTOS coma ELEMENTO 
            {
              Carpeta RESULT =null;
		int carpetaleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)).left;
		int carpetaright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)).right;
		Carpeta carpeta = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)).value;
		int carpeta2left = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int carpeta2right = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Carpeta carpeta2 = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		
                RESULT = carpeta;
                for(Carpeta car : carpeta2.subcarpetas)
                {
                    RESULT.subcarpetas.add(car);
                }
                for(Archivo arch : carpeta2.archivos)
                {
                    RESULT.archivos.add(arch);
                }
            
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ELEMENTOS",7, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // ELEMENTOS ::= ELEMENTO 
            {
              Carpeta RESULT =null;
		int carpetaleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int carpetaright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Carpeta carpeta = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		 RESULT = carpeta;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ELEMENTOS",7, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // ELEMENTO ::= ARCHIVO 
            {
              Carpeta RESULT =null;
		int archivoleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int archivoright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Archivo archivo = (Archivo)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = new Carpeta(); RESULT.archivos.add(archivo);
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ELEMENTO",8, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // ELEMENTO ::= CARPETA 
            {
              Carpeta RESULT =null;
		int carpetaleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int carpetaright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		Carpeta carpeta = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = new Carpeta(); RESULT.subcarpetas.add(carpeta);
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ELEMENTO",8, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // ARCHIVO ::= tarchivo dospuntos llavei PROPSARCHIVO llaved 
            {
              Archivo RESULT =null;
		int archivoleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int archivoright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Archivo archivo = (Archivo)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		 RESULT = archivo;
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("ARCHIVO",9, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-4)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // PROPSARCHIVO ::= PROPSARCHIVO PROPARCHIVO 
            {
              Archivo RESULT =null;
		int archivoleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int archivoright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Archivo archivo = (Archivo)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		 
                    RESULT = archivo;
                    if(tipoElemento == Proyecto.elemento.NOMBRE)
                    {
                        RESULT.nombre = v;
                    }
                    if(tipoElemento == Proyecto.elemento.FECHA_MOD)
                    {
                        RESULT.fecha =v;
                    }                                    
                
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("PROPSARCHIVO",10, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // PROPSARCHIVO ::= PROPARCHIVO 
            {
              Archivo RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		 
                    RESULT = new Archivo(); 
                    if(tipoElemento == Proyecto.elemento.NOMBRE)
                    {
                        RESULT.nombre = v;
                    }
                    if(tipoElemento == Proyecto.elemento.FECHA_MOD)
                    {
                        RESULT.fecha =v;
                    }                                    
                
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("PROPSARCHIVO",10, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // PROPARCHIVO ::= NOMBRE 
            {
              String RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		 RESULT = v ; tipoElemento =Proyecto.elemento.NOMBRE;  Imprimir("Nombre\t"+v);
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("PROPARCHIVO",11, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // PROPARCHIVO ::= FECHA 
            {
              String RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		 RESULT = v ; tipoElemento =Proyecto.elemento.FECHA_MOD; Imprimir("Fecha\t"+v);
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("PROPARCHIVO",11, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // FECHA ::= tfecha dospuntos cadena 
            {
              String RESULT =null;
		int cadleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).left;
		int cadright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()).right;
		String cad = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.peek()).value;
		RESULT = cad; 
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("FECHA",12, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // CARPETA ::= tcarpeta dospuntos llavei NOMBRE ELEMENTOS llaved 
            {
              Carpeta RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-2)).value;
		int carpetaleft = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).left;
		int carpetaright = ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).right;
		Carpeta carpeta = (Carpeta)((java_cup.runtime.Symbol) CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-1)).value;
		              
            RESULT = carpeta;
            RESULT.nombre = n;
        
              CUP$parserOlc$result = parser.getSymbolFactory().newSymbol("CARPETA",13, ((java_cup.runtime.Symbol)CUP$parserOlc$stack.elementAt(CUP$parserOlc$top-5)), ((java_cup.runtime.Symbol)CUP$parserOlc$stack.peek()), RESULT);
            }
          return CUP$parserOlc$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parserOlc$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parserOlc$do_action(
    int                        CUP$parserOlc$act_num,
    java_cup.runtime.lr_parser CUP$parserOlc$parser,
    java.util.Stack            CUP$parserOlc$stack,
    int                        CUP$parserOlc$top)
    throws java.lang.Exception
    {
              return CUP$parserOlc$do_action_part00000000(
                               CUP$parserOlc$act_num,
                               CUP$parserOlc$parser,
                               CUP$parserOlc$stack,
                               CUP$parserOlc$top);
    }
}

}