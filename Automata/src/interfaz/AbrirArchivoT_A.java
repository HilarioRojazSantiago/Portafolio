package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algoritmo.AnalizadorSintactico;
import algoritmo.Primeros;
import algoritmo.Siguientes;
import gramatica.Gramatica;
import gramatica.Primero;
import gramatica.Siguiente;

public class AbrirArchivoT_A extends JFrame{
	private JPanel contentPane;
	Gramatica gramatica = new Gramatica();
	private AnalizadorSintactico an;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbrirArchivoT_A frame = new AbrirArchivoT_A();
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
	public AbrirArchivoT_A() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 410, 196);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGramatica = new JButton("Gramatica");
		btnGramatica.setBackground(new Color(51, 153, 204));
		btnGramatica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(contentPane);
				if(seleccion ==JFileChooser.APPROVE_OPTION);
					File fichero = fc.getSelectedFile();
					try(FileReader fr = new FileReader(fichero)){
						String cadena=" ";
						int valor = fr.read();
						while(valor != -1) {
							cadena = cadena +(char)valor;
							valor = fr.read();
						}
						
						gramatica = new Gramatica(fichero.getAbsolutePath());
						an = new AnalizadorSintactico(gramatica);
	
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
			
				
	});
		btnGramatica.setBounds(10, 95, 178, 31);
		contentPane.add(btnGramatica);
		
		JLabel lblNewLabel = new JLabel("Tabla Analisis Sintactico");
		lblNewLabel.setBounds(10, 11, 188, 46);
		contentPane.add(lblNewLabel);
		
		JButton Calcular = new JButton("Calcular");
		Calcular.setBackground(new Color(0, 153, 51));
		Calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<List<String>> tabla =an.tablaAnalisis();
					int cont = 0;
					for(List<String> tempSal : tabla) {
						cont++;
					}
					List<String> columnasAn =an.getCol() ;
					int cont2 = 0;
					for(String tempSal : columnasAn) {
						cont2++;
					}
					Object[][] coleccion = new Object[cont][cont2];
					cont = 0;
					for(List<String> tempSal : tabla) {
						coleccion[cont] = new Object [cont2];
						for(int j=0; j< tempSal.size(); j++) {
							coleccion[cont][j] = tempSal.get(j);
						}
						cont++;
					}
					Object []columnas = new Object[cont2];
					cont = 0;
					for(String tempSal : columnasAn) {
						columnas[cont] = tempSal;
						cont++;
					}
					Tablaanalisis.setNTable(coleccion, columnas);
					Tablaanalisis tablaanalisis = new Tablaanalisis();
					tablaanalisis.setVisible(true);
					
					tablaanalisis.getContentPane().add(tablaanalisis.JS);
				}
				catch(Exception e1) {
					System.out.println("Error al escribir" + e1);
					
				}
				
			}
		});
		
		Calcular.setBounds(253, 95, 120, 31);
		contentPane.add(Calcular);
	}
}
