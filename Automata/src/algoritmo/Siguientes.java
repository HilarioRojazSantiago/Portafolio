package algoritmo;

import java.util.ArrayList;
import java.util.List;
import gramatica.Gramatica;
import gramatica.Primero;
import gramatica.Siguiente;
import gramatica.Regla;

public class Siguientes {
	
	public static List<Siguiente> calcular(Gramatica gramatica, List<Primero> primeros){
		List<String> calcular = new ArrayList<String>();
		calcular.addAll(gramatica.noTerminales());
		List<Siguiente> siguientes = new ArrayList<Siguiente>();
		auxiliar(gramatica, siguientes, primeros, calcular, null);
		return siguientes;
	}
	
	private static void auxiliar(Gramatica gramatica, List<Siguiente> siguientes, List<Primero> primeros, List<String> calcular, String siguiente){
		if(calcular.isEmpty() == false) {
			String noTerminal = null;
			if(siguiente != null) { noTerminal = siguiente; calcular.remove(siguiente); }
			else { noTerminal = calcular.remove(0); }
			List<Regla> producciones = gramatica.producciones(noTerminal);
			Siguiente nuevoSiguiente = new Siguiente(noTerminal);
			if(noTerminal.equals(gramatica.regla(1).noTerminal()) == true) {
				nuevoSiguiente.agregarSiguiente("$");
			}
			for(Regla regla : producciones) {
				Integer indice = regla.terminales().indexOf(noTerminal);
				if(indice+1 == regla.terminales().size()) {
					if(regla.noTerminal().equals(noTerminal) == false) {
						List<String> listaSiguientes = siguientes(siguientes, regla.noTerminal());
						if(listaSiguientes == null) {
							auxiliar(gramatica, siguientes, primeros, calcular, regla.noTerminal());
							listaSiguientes = siguientes(siguientes, regla.noTerminal());
						}
						nuevoSiguiente.agregarSiguiente(listaSiguientes);
					}
				}
				else {
					String beta = regla.terminales().get(indice+1);
					if(gramatica.noTerminales().contains(beta) == true) {
						List<String> primerosBeta = primeros(primeros, beta);
						if(primerosBeta.contains(null) == true) {
							nuevoSiguiente.agregarSiguiente(primerosBeta);
							nuevoSiguiente.siguientes().remove(null);
							if(regla.noTerminal().equals(noTerminal) == false) {
								List<String> listaSiguientes = siguientes(siguientes, regla.noTerminal());
								if(listaSiguientes == null) {
									auxiliar(gramatica, siguientes, primeros, calcular, regla.noTerminal());
									listaSiguientes = siguientes(siguientes, regla.noTerminal());
								}
								nuevoSiguiente.agregarSiguiente(listaSiguientes);
							}
						}
						else { nuevoSiguiente.agregarSiguiente(primerosBeta); }
					}
					else { nuevoSiguiente.agregarSiguiente(beta); }
				}
			}
			siguientes.add(nuevoSiguiente);
			if(siguiente == null) { auxiliar(gramatica, siguientes, primeros, calcular, null); }
		}
	}
	
	private static List<String> primeros(List<Primero> primeros, String noTerminal){
		for(Primero primero : primeros) {
			if(primero.noTerminal().equals(noTerminal)) { return primero.primeros(); }
		}
		return null;
	}
	
	private static List<String> siguientes(List<Siguiente> siguientes, String noTerminal){
		for(Siguiente siguiente : siguientes) {
			if(siguiente.noTerminal().equals(noTerminal)) { return siguiente.siguientes(); }
		}
		return null;
	}
	
}