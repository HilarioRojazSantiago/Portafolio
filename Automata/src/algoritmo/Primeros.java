package algoritmo;

import java.util.ArrayList;
import java.util.List;

import gramatica.Gramatica;
import gramatica.Primero;
import gramatica.Regla;

public class Primeros {
	
	public static List<Primero> calcular(Gramatica gramatica){
		List<String> calcular = new ArrayList<String>();
		calcular.addAll(gramatica.noTerminales());
		List<Primero> primeros = new ArrayList<Primero>();
		auxiliar(gramatica, primeros, calcular, null);
		return primeros;
	}
	
	private static void auxiliar(Gramatica gramatica, List<Primero> primeros, List<String> calcular, String primero) {
		if(calcular.isEmpty() == false) {
			String noTerminal = null;
			if(primero != null) { noTerminal = primero; calcular.remove(primero); }
			else { noTerminal = calcular.remove(0); }
			Primero nuevoPrimero = new Primero(noTerminal);
			List<Regla> reglasNoTerminal = gramatica.reglas(noTerminal);
			for(Regla regla : reglasNoTerminal) {
				for(String terminal : regla.terminales()) {
					if(terminal == null || terminal.equals(noTerminal) == false) {
						if(gramatica.noTerminales().contains(terminal) == true) {
							List<String> listaPrimeros = primeros(primeros, terminal);
							if(listaPrimeros == null) {
								auxiliar(gramatica, primeros, calcular, terminal);
								listaPrimeros = primeros(primeros, terminal);
							}
							nuevoPrimero.agregarPrimero(listaPrimeros);
							if(listaPrimeros.contains(null) == true) {
								if(regla.terminales().lastIndexOf(terminal)+1 != regla.terminales().size()) {
									nuevoPrimero.primeros().remove(null);
								}
								continue;
							}
							break;
						}
						else {
							nuevoPrimero.agregarPrimero(terminal);
							break;
						}
					}
					else { break; }
				}
			}
			primeros.add(nuevoPrimero);
			if(primero == null) { auxiliar(gramatica, primeros, calcular, null); };
		}
	}

	private static List<String> primeros(List<Primero> primeros, String noTerminal){
		for(Primero primero : primeros) {
			if(primero.noTerminal().equals(noTerminal)) { return primero.primeros(); }
		}
		return null;
	}
	
}