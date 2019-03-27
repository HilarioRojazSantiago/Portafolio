package interfaz;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import gramatica.Gramatica;
//import gramatica.Gramatica;
import algoritmo.AnalizadorSintactico;
//import algoritmo.ColeccionC;
import gramatica.Primero;
import gramatica.Siguiente;
import algoritmo.Siguientes;
import analizador.AnalizadorLexico;
import analizador.Error;
import analizador.Simbolo;
import analizador.Token;
import algoritmo.Primeros;
import javax.swing.JTextPane;
import java.awt.Font;
import automata.Automata_T;

public class compilador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panel = new JPanel();
	private Gramatica g;
	private AnalizadorLexico lexico2;
	private JTable tabla;
	private static List<List<String>> Analisis = new ArrayList<List<String>>(); 
	private AnalizadorSintactico an;
	AnalizadorLexico a = new AnalizadorLexico();
	String cadena_T = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					compilador frame = new compilador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public compilador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Compilador Equipo 5");
		setBounds(100, 100, 527, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTabbedPane Principal = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(Principal);
		
		final JPanel thompson = new JPanel();
		thompson.setBackground(new Color(255, 255, 255));
		Principal.addTab("Thompson", null, thompson, null);
		Principal.setBackgroundAt(0, new Color(0, 153, 102));
		Principal.setForegroundAt(0, new Color(0, 0, 0));
		thompson.setLayout(null);
		
		JButton abrir_1 = new JButton("Abrir archivo");
		abrir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(thompson);
				if(seleccion ==JFileChooser.APPROVE_OPTION);
					File fichero = fc.getSelectedFile();
					try(FileReader fr = new FileReader(fichero)){
					
						int valor = fr.read();
						while(valor != -1) {
							cadena_T = cadena_T +(char)valor;
							valor = fr.read();
						}
						System.out.println(cadena_T);
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		abrir_1.setBounds(282, 53, 182, 25);
		abrir_1.setBackground(new Color(0, 153, 204));
		thompson.add(abrir_1);
		
		JButton ejecutar = new JButton("Realizar Automata");
		ejecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Automata_T automata = Automata_T.expresion(cadena_T);
				String[] columnas = automata.stringAlfabeto();
				Object[][] datos = automata.transiciones();
				Tablaanalisis.setNTable(datos, columnas);
				Tablaanalisis tablaanalisis = new Tablaanalisis();
				tablaanalisis.setVisible(true);
				tablaanalisis.getContentPane().add(tablaanalisis.JS);
			}
		});
		ejecutar.setBackground(new Color(0, 153, 51));
		ejecutar.setBounds(282, 91, 182, 25);
		thompson.add(ejecutar);
		
		JButton logo = new JButton("");
		logo.setIcon(new ImageIcon("logo.png"));
		logo.setBounds(27, 26, 145, 128);
		thompson.add(logo);
		
		final JPanel lexico = new JPanel();
		lexico.setBackground(new Color(255, 255, 255));
		Principal.addTab("Analizador Lexico", null, lexico, null);
		Principal.setBackgroundAt(1, new Color(0, 153, 102));
		Principal.setForegroundAt(1, new Color(0, 0, 0));
		lexico.setLayout(null);
		
		JButton abrirA = new JButton("Abrir Archivo");
		abrirA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(lexico);
				if(seleccion ==JFileChooser.APPROVE_OPTION);
					File fichero = fc.getSelectedFile();
					try(FileReader fr = new FileReader(fichero)){
						String cadena=" ";
						int valor = fr.read();
						while(valor != -1) {
							cadena = cadena +(char)valor;
							valor = fr.read();
						}
						
						a.procesarArchivo(fichero.getAbsolutePath());
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		abrirA.setBackground(new Color(0, 153, 51));
		abrirA.setBounds(12, 48, 175, 25);
		lexico.add(abrirA);
		
		JButton tokens = new JButton("Tira de Tokens");
		tokens.setBackground(new Color(0, 153, 153));
		tokens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.tiraTokens().imprimir();
				List<Token> tempSalida = new ArrayList<Token>();
				tempSalida = a.tiraTokens().tokens();
				int cont = 0;
				for(Token tempSal : tempSalida) {
					cont++;
				}
				
				Object[][] datosTabla = new Object[cont][3];
				System.out.println("cont "+ cont);
				cont = 0;
				for(Token tempSal : tempSalida) {
					datosTabla[cont] = new Object [3];
					datosTabla[cont][0]=tempSal.linea();
					datosTabla[cont][1]=tempSal.lexema();
					datosTabla[cont][2]=tempSal.token();
					cont++;
				}
				Object[] cabeceraTabla = {"# LINEA", "LEXEMA", "TOKEN"};
				Tablaanalisis.setNTable(datosTabla, cabeceraTabla);
				Tablaanalisis tablaanalisis = new Tablaanalisis();
				tablaanalisis.setVisible(true);
				tablaanalisis.getContentPane().add(tablaanalisis.JS);
				
				a.tablaErrores().imprimir();
				a.tablaSimbolos().imprimir();
			}
		});
		tokens.setBounds(286, 48, 175, 25);
		lexico.add(tokens);
		
		JTextPane txtpnSeleccionaElCodigo = new JTextPane();
		txtpnSeleccionaElCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnSeleccionaElCodigo.setText("Selecciona el codigo");
		txtpnSeleccionaElCodigo.setBounds(12, 13, 175, 22);
		lexico.add(txtpnSeleccionaElCodigo);
		
		JTextPane txtpnAnalizarCodigo = new JTextPane();
		txtpnAnalizarCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnAnalizarCodigo.setText("Analizar Codigo");
		txtpnAnalizarCodigo.setBounds(286, 13, 175, 22);
		lexico.add(txtpnAnalizarCodigo);
		
		JButton simbolos = new JButton("Tabla de Simbolos");
		simbolos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Simbolo> tempSalida = new ArrayList<Simbolo>();
				tempSalida = a.tablaSimbolos().simbolos();
				int cont = 0;
				for(Simbolo tempSal : tempSalida) {
					cont++;
				}
				
				Object[][] datosTabla = new Object[cont][3];
				System.out.println("cont "+ cont);
				cont = 0;
				for(Simbolo tempSal : tempSalida) {
					datosTabla[cont] = new Object [3];
					datosTabla[cont][0]=tempSal.identificador();
					datosTabla[cont][1]=tempSal.valor();
					datosTabla[cont][2]=tempSal.funcion();
					cont++;
				}
				Object[] cabeceraTabla = {"ID", "VALOR", "FUNCION"};
				Tablaanalisis.setNTable(datosTabla, cabeceraTabla);
				Tablaanalisis tablaanalisis = new Tablaanalisis();
				tablaanalisis.setVisible(true);
				tablaanalisis.getContentPane().add(tablaanalisis.JS);
			}
		});
		simbolos.setBackground(new Color(0, 153, 153));
		simbolos.setBounds(286, 86, 175, 25);
		lexico.add(simbolos);
		
		JButton errores = new JButton("Tabla de Errores");
		errores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Error> tempSalida = new ArrayList<Error>();
				tempSalida = a.tablaErrores().errores();
				int cont = 0;
				for(Error tempSal : tempSalida) {
					cont++;
				}
				if (cont==0) {
					cont++;
				}
				Object[][] datosTabla = new Object[cont][3];
				System.out.println("cont "+ cont);
				cont = 0;
				for(Error tempSal : tempSalida) {
					datosTabla[cont] = new Object [2];
					datosTabla[cont][0]=tempSal.linea();
					datosTabla[cont][1]=tempSal.descripcion();
					cont++;
				}
				if (cont == 0) {
					datosTabla[0][0]= "";
					datosTabla[0][0]= "No hay errores";
				}
				Object[] cabeceraTabla = {"# LINEA", "DESCRIPCION"};
				Tablaanalisis.setNTable(datosTabla, cabeceraTabla);
				Tablaanalisis tablaanalisis = new Tablaanalisis();
				tablaanalisis.setVisible(true);
				tablaanalisis.getContentPane().add(tablaanalisis.JS);
			}
		});
		errores.setBackground(new Color(0, 153, 153));
		errores.setBounds(286, 124, 175, 25);
		lexico.add(errores);
		
		final JPanel sintactico = new JPanel();
		sintactico.setBackground(new Color(255, 255, 255));
		Principal.addTab("Analizador Sintactico", null, sintactico, null);
		Principal.setForegroundAt(2, new Color(0, 0, 0));
		Principal.setBackgroundAt(2, new Color(0, 153, 102));
		sintactico.setLayout(null);
		
		/*
		getContentPane().setLayout(null);
		panel.setBorder(new LineBorder(new Color(0,0,0),1,true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 30, 325, 317);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		*/
		
		JButton primerosysiguientes = new JButton("Primeros y Siguientes");
		primerosysiguientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirArchivoP_S abrir = new AbrirArchivoP_S();
				abrir.setVisible(true);
			}
		});
		primerosysiguientes.setBackground(new Color(0, 153, 204));
		primerosysiguientes.setBounds(241, 11, 220, 23);
		sintactico.add(primerosysiguientes);
		
		JButton coleccioncanonica = new JButton("Coleccion Canonica");
		coleccioncanonica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirArchivoC_C abrir = new AbrirArchivoC_C();
				abrir.setVisible(true);
			}
		});
		coleccioncanonica.setBackground(new Color(0, 153, 204));
		coleccioncanonica.setBounds(241, 45, 220, 23);
		sintactico.add(coleccioncanonica);
		
		JButton tanalisis = new JButton("Tabla de  Analisis Sintactico");
		tanalisis.setBackground(new Color(0, 153, 204));
		tanalisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirArchivoT_A abrir = new AbrirArchivoT_A();
				abrir.setVisible(true);
			}
		});
		tanalisis.setBounds(241, 79, 220, 23);
		sintactico.add(tanalisis);
		
		JButton analisissintactico = new JButton("Analisis Sintactico");
		analisissintactico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Abrirarchivo abrir = new Abrirarchivo();
				abrir.setVisible(true);
			}
		});
		analisissintactico.setBackground(new Color(0, 153, 204));
		analisissintactico.setBounds(241, 113, 220, 23);
		sintactico.add(analisissintactico);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("logo.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(41, 11, 145, 128);
		sintactico.add(button);
		
		JPanel semantico = new JPanel();
		semantico.setBackground(new Color(255, 255, 255));
		Principal.addTab("Analizador Semantico", null, semantico, null);
		semantico.setLayout(null);
		
		JButton gramatica = new JButton("Gramatica");
		gramatica.setBackground(new Color(0, 153, 204));
		gramatica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(sintactico);
				if(seleccion ==JFileChooser.APPROVE_OPTION);
					File fichero = fc.getSelectedFile();
					try(FileReader fr = new FileReader(fichero)){
						String cadena=" ";
						int valor = fr.read();
						while(valor != -1) {
							cadena = cadena +(char)valor;
							valor = fr.read();
						}
						g = new Gramatica(fichero.getAbsolutePath());
						
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		gramatica.setBounds(12, 61, 180, 25);
		semantico.add(gramatica);
		
		JButton codigo = new JButton("programa");
		codigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(sintactico);
				if(seleccion ==JFileChooser.APPROVE_OPTION);
					File fichero = fc.getSelectedFile();
					try(FileReader fr = new FileReader(fichero)){
						String cadena=" ";
						int valor = fr.read();
						while(valor != -1) {
							cadena = cadena +(char)valor;
							valor = fr.read();
						}
						lexico2= new AnalizadorLexico();
						lexico2.procesarArchivo(fichero.getAbsolutePath());
					//	g = new Gramatica(fichero.getAbsolutePath());
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		codigo.setBackground(new Color(0, 153, 204));
		codigo.setBounds(12, 137, 180, 25);
		semantico.add(codigo);
		
		JTextPane Archivos = new JTextPane();
		Archivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Archivos.setText("Seleccionar Archivos");
		Archivos.setBounds(12, 13, 180, 22);
		semantico.add(Archivos);
		
		JButton analizar = new JButton("Analisis Semantico");
		analizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnalizadorSintactico sintactico2 = new AnalizadorSintactico(g);
				sintactico2.analizar(lexico2.tiraTokens());
				List<List<String>> tempSalida = new ArrayList<List<String>>();
				tempSalida = sintactico2.getSalidaSemantica();
				int cont = 0;
				for(List<String> tempSal : tempSalida) {
					cont++;
				}
				String[][] datosTabla = new String[cont][3];
				cont = 0;
				for(List<String> tempSal : tempSalida) {
					datosTabla[cont] = new String [3];
					for(int j=0; j< tempSal.size(); j++) {
						datosTabla[cont][j] = tempSal.get(j);
					}
					cont++;
				}
				String[] cabeceraTabla = {"PILA", "ENTRADA", "ACCION"};
				List<Error> ListaErrores = lexico2.tablaErrores().errores();
				String cadenaErrores = "Errores Lexicos:\n";
				for(Error errorAux : ListaErrores) {
					cadenaErrores += errorAux;
				}
				if(ListaErrores.isEmpty()) {
					cadenaErrores += "No hay errores lexicos";
				}
				cadenaErrores += "\nErrores semanticos:";
				if(sintactico2.getErrores() == null) {
					cadenaErrores += "\nNo hay errores semanticos";
				}
				else {
					cadenaErrores+= "\n" + sintactico2.getErrores();
				}
				tablaText.setNTable(datosTabla, cabeceraTabla);
				tablaText.setNtextArea(cadenaErrores );
				
				tablaText tablaTexto = new tablaText();
				tablaTexto.setVisible(true);
				tablaTexto.getContentPane().add(tablaTexto.AP);
				tablaTexto.getContentPane().add(tablaTexto.PE);
				System.out.print(sintactico2.getTraduccion());
				Salida traduccion = new Salida(sintactico2.getTraduccion());
				
			}
		});
		analizar.setBackground(new Color(0, 153, 0));
		analizar.setBounds(268, 61, 214, 25);
		semantico.add(analizar);
		Principal.setForegroundAt(3, new Color(0, 0, 0));
		Principal.setBackgroundAt(3, new Color(0, 153, 102));
	}
}
