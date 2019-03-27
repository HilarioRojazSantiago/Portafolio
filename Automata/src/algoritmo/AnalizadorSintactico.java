package algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import analizador.TiraTokens;
import analizador.Token;
import automata.Automata;
import gramatica.Arbol;
import gramatica.Atributo;
import gramatica.Gramatica;
import gramatica.Nodo;
import gramatica.Regla;

public class AnalizadorSintactico {
	
	private Gramatica gramatica;
	private ColeccionCanonica coleccionCanonica;
	private List<List<String>> salidaSemantica = new ArrayList<List<String>>();
	private List<List<String>> salidaSintactica = new ArrayList<List<String>>();
	private String resultado;
	private String traduccion;
	private String Errores;
	private Arbol arbol;
	private Integer indice = 0;
	
	protected static final Automata reservadas = Subconjunto.construir(Thomson.expresion(
			"include|stdio.h|int|for|if|while|else|switch|char|float|break|printf|scanf|case|return|default"
		));
	protected static final Automata reservadas2 = Subconjunto.construir(Thomson.expresion("NULL|double"));
	
	protected static final Automata libreria = Subconjunto.construir(Thomson.expresion("letra(letra)*.h"));
	
	
	public AnalizadorSintactico(Gramatica gramatica) {
		this.gramatica = gramatica;
		this.coleccionCanonica = new ColeccionCanonica(gramatica);
		this.arbol = new Arbol();
	}
	
	public void analizar(TiraTokens tiraTokens) {
		Stack<String> pila = new Stack<String>();
		List<Token> tokens = tiraTokens.tokens();
		List<Integer> listaNumeroReglas = new ArrayList<Integer>();
		List<String> valex = new ArrayList<String>();
		Boolean agregarValex = true;
		tokens.add(new Token(0, "$", "$"));
		pila.push("0");
		while(tokens.isEmpty() == false) {
			String simbolo = null, valor = null;
			if(tokens.get(0).token().equals("identificador")) {
				if(tokens.get(0).lexema().equals("main")) { simbolo = tokens.get(0).lexema(); }
				else { 
					simbolo = tokens.get(0).token();
					if(agregarValex == true) { 
						valex.add(tokens.get(0).lexema()); 
					}
				}
			}
			else if(tokens.get(0).token().equals("reservada")) {
				simbolo = tokens.get(0).lexema();
				if(agregarValex == true) { 
					valex.add(tokens.get(0).lexema()); 
				}
			}
			else if(tokens.get(0).token().equals("texto")) {
				simbolo = tokens.get(0).token();
				if(agregarValex == true) { valex.add(tokens.get(0).lexema()); }
			}
			else if(tokens.get(0).token().equals("caracter")) {
				simbolo = tokens.get(0).token();
				if(agregarValex == true) { valex.add(tokens.get(0).lexema()); }
			}
			else if(tokens.get(0).token().equals("libreria")) {
				simbolo = tokens.get(0).token();
				System.out.println(simbolo);
				if(agregarValex == true) { valex.add(tokens.get(0).lexema()); }
			}
			else if(tokens.get(0).token().equals("entero") || tokens.get(0).token().equals("decimal")) {
				simbolo = "constante";
				valor = tokens.get(0).lexema();
				valex.add(tokens.get(0).lexema());
			}
			
			else {
				simbolo = tokens.get(0).token();
			}
			String accion = accion(pila.peek(), simbolo);
			List<String> auxSalida = new ArrayList<String>();
			List<String> auxSalidaSin = new ArrayList<String>();
			String auxCadSal = "";
			System.out.print(pila + " ##### ");
			auxSalida.add(pila+"");
			auxSalidaSin.add(pila+"");
			System.out.print(tokens + " ###### ");
			System.out.print(accion);
			
			auxSalida.add(tokens+"");
			int cont = 0;
			List<String> auxCadSalSin = new ArrayList<String>();
			while(cont< tokens.size()) {
				auxCadSalSin.add(tokens.get(cont).lexema());
				cont++;
			}
			auxSalidaSin.add(auxCadSalSin +"");
			//System.out.print(accion);
			auxCadSal = accion;
			auxSalidaSin.add(accion);
			System.out.println("aux salida Sintactico, " + auxSalidaSin);
			salidaSintactica.add(auxSalidaSin);
			if(accion == null) {
				System.out.print("\nError: se esperaba ");
				Errores = "Error se esperaba :";
				List<String> fila = this.coleccionCanonica.tablaAnalisisSintactico().get(Integer.parseInt(pila.peek()));
				for(Integer w=1; w<fila.size(); w++) {
					String a = fila.get(w);
					if(a != null) {
						String k = this.coleccionCanonica.columnas().get(w);
						if(this.gramatica.noTerminales().contains(k) == false) {
							System.out.print("\'" + this.coleccionCanonica.columnas().get(w) + "\' ");
							Errores = Errores + "\'" + this.coleccionCanonica.columnas().get(w)+ "\'";
						}
					}
				}
				System.out.println();
				tokens.clear();
			}
			else if(accion.substring(0, 1).equals("d")) {
				pila.push(simbolo + (valor != null ? "." + valor : ""));
				pila.push(accion.substring(1));
				tokens.remove(0);				
				agregarValex = true;
				System.out.println();
			}
			else if(accion.substring(0, 1).equals("r")) {
				Integer nRegla = Integer.parseInt(accion.substring(1));
				Regla regla = this.gramatica.regla(nRegla);
				List<Atributo> atributos = null;
				listaNumeroReglas.add(0, nRegla);
				auxCadSal = auxCadSal +"   "+ regla +"     "+atributos;
				System.out.println(" ###### " + regla + " %%%%%%%% " + atributos);
				Integer beta = regla.terminales().size();
				if(beta == 1 && regla.terminales().get(0) == null) { 
					beta = 0; 
				}
				List<String> valores = new ArrayList<String>();
				for(Integer w=0; w<beta*2; w++) {
					String str = pila.pop();
					if(w%2 == 1) {
						if(str.contains(".") == true) {
							str = str.replace(".", "#");
							valores.add(0, str.split("#")[1]);							
						}
					}
				}
				String nuevoValor = null;
				String s = accion(pila.peek(), regla.noTerminal());
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+nuevoValor);
				pila.push(regla.noTerminal() + (nuevoValor != null ? "." + nuevoValor : ""));
				pila.push(s);
				agregarValex = false;
			}
			else if(accion.equals("aceptacion")) {
				//System.out.println("\nResultado = "+valorfinal);
				//System.out.println("\nAceptado");
				//resultado = valorfinal;
				tokens.remove(0);
			}
			auxSalida.add(auxCadSal);
			System.out.println(auxSalida);
			salidaSemantica.add(auxSalida);
		}
		//if(this.gramatica.accionesSemanticas() != null) {
			this.arbol.colocarRaiz(this.gramatica.regla(listaNumeroReglas.get(0)).noTerminal());
			System.out.println("Reglas : " + listaNumeroReglas);
			System.out.println("Valex : " + valex);
			this.generarArbol(this.arbol.raiz(), listaNumeroReglas, valex, this.gramatica.noTerminales());
			Nodo.mostrar(arbol.raiz(), 0);
			this.traducirArbol(this.arbol.raiz(), listaNumeroReglas);
			traduccion = ""+this.arbol.raiz().atributo("trad");
		//}
	}
	
	private String calcularValor(Atributo atributo, List<String> valores) {
		
		if(valores.size() > 1 ) {
			Integer a = Integer.valueOf(valores.get(0));
			Integer b = Integer.valueOf(valores.get(1));
			//System.out.println(atributo.construccion());
			String operacion = atributo.construccion().get(1);
			if(operacion.equals("+")) { return String.valueOf(a+b); }
			if(operacion.equals("-")) { return String.valueOf(a-b); }
			if(operacion.equals("*")) { return String.valueOf(a*b); }
			if(operacion.equals("/")) { return String.valueOf(a/b); }
		}
		return valores.get(0);
	}
	
	private void generarArbol(Nodo nodo, List<Integer> listaNumeroReglas, List<String> valex, List<String> noTerminales) {
		if(indice < listaNumeroReglas.size()) {
			Regla regla = this.gramatica.regla(listaNumeroReglas.get(this.indice));
			for(Integer i = regla.terminales().size()-1; i >= 0; i--) {
				String simbolo = regla.terminales().get(i);
				nodo.agregarHijo(simbolo);
				if(simbolo != null && (simbolo.equals("caracter") || simbolo.equals("texto") || simbolo.equals("identificador") || simbolo.equals("constante") || reservadas.verificarString(simbolo) || reservadas2.verificarString(simbolo) || simbolo.equals("libreria"))) {
					nodo.hijo(simbolo).nuevoAtributo("valex", valex.remove(valex.size()-1));
				}
				if(noTerminales.contains(simbolo) == true) {
					this.indice = this.indice + 1;
					this.generarArbol(nodo.hijo(simbolo), listaNumeroReglas, valex, noTerminales);
				}
			}
		}
	}
	
	public List<List<String>> tablaAnalisis(){
		return this.coleccionCanonica.tablaAnalisisSintactico();
	}
	
	public List<List<String>> transiciones(){
		return this.coleccionCanonica.transiciones();
	}
	
	public List<String> getCol (){
		return this.coleccionCanonica.columnas();
	}
	
	public List<List<String>> getSalidaSemantica (){
		return this.salidaSemantica;
	}
	
	public List<List<String>> getSalidaSintactica(){
		return this.salidaSintactica;
	}
	
	public String getResultado (){
		return this.resultado;
	}
	
	public String getErrores() {
		return this.Errores;
	}
	
	public String getTraduccion(){
		return this.traduccion;
	}
	
	private void traducirArbol(Nodo nodo, List<Integer> listaNumeroReglas) {
		Integer nRegla = listaNumeroReglas.remove(0);
		Regla regla = this.gramatica.regla(nRegla);
		for(Integer i = regla.terminales().size()-1; i >= 0; i--) {
			String simbolo = regla.terminales().get(i);
			if(this.gramatica.noTerminales().contains(simbolo) == true) {
				this.traducirArbol(nodo.hijo(simbolo), listaNumeroReglas);
			}
		}
		List<Atributo> atributos = regla.atributos();
		for(Atributo atributo : atributos) {
			String valorAtributo = "";
			List<String> construccion = atributo.construccion();
			if(atributo.construccion().contains("?") == true) {
				String separar[] = atributo.construccion().get(0).replace(".", "#").split("#");
				String noTerminal = separar[0];
				String nombreAtributo = separar[1];
				String signoComparacion = atributo.construccion().get(1);
				String cadena = atributo.construccion().get(2).replace("\"", "");
				Boolean iguales = nodo.hijo(noTerminal).atributo(nombreAtributo).equals(cadena);
				Integer indice = atributo.construccion().indexOf("#");
				if(signoComparacion.equals("==") == true) { 
					if(iguales == true) { construccion = atributo.construccion().subList(4, indice); }
					else { construccion = atributo.construccion().subList(indice+1, atributo.construccion().size()); }		
				}
				else if(signoComparacion.equals("!=") == true) { 
					if(iguales == false) { construccion = atributo.construccion().subList(4, indice); }
					else { construccion = atributo.construccion().subList(indice+1, atributo.construccion().size()); }
				}
			}
			for(String string : construccion) {
				if(string.contains("\"") == false && string.contains(".") == true) {
					String partir[] = string.replace(".", "#").split("#");
					if(partir[0].equals("") == true) { 
						valorAtributo += nodo.atributo(partir[1].replace(" ", "")); 
					}
					else { 
						String nombreHijo = partir[0].replace(" ", "");
						String nombreAtributo = partir[1].replace(" ", "");
						String cadena = nodo.hijo(nombreHijo).atributo(nombreAtributo);
						valorAtributo += cadena;
					}
				}
				else {
					String str = string.replace("\"", "");
					if(str.equals("\\n") == true) { valorAtributo += "\n"; } 
					else if(str.equals("\\t") == true) { valorAtributo += "    "; } 
					else { valorAtributo += str; }
				}
			}
			nodo.nuevoAtributo(atributo.nombre(), valorAtributo);
		}
	}
	
	
	private String accion(String estado, String simbolo) {
		Integer i = Integer.parseInt(estado);
		Integer w = this.coleccionCanonica.columnas().indexOf(simbolo);
		if(w >= 0) {
			return this.coleccionCanonica.tablaAnalisisSintactico().get(i).get(w);
		}
		System.out.println("\'" + simbolo + "\' no está definido");
		return null;
	}
	
	public Arbol arbol() {
		return this.arbol;
	}
	
}