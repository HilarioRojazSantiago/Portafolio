package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
import analizador.AnalizadorLexico;
import gramatica.Gramatica;
import gramatica.Primero;
import gramatica.Siguiente;

public class AbrirArchivoP_S extends JFrame {
	private JPanel contentPane;
	Gramatica gramatica = new Gramatica();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbrirArchivoP_S frame = new AbrirArchivoP_S();
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
	public AbrirArchivoP_S() {
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
		
		JLabel lblNewLabel = new JLabel("Primeros y siguientes");
		lblNewLabel.setBounds(10, 11, 188, 46);
		contentPane.add(lblNewLabel);
		
		JButton Calcular = new JButton("Calcular");
		Calcular.setBackground(new Color(0, 153, 51));
		Calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Primero> primeros = Primeros.calcular(gramatica);
					String cadena = "Primeros\n ";
					for(Primero p : primeros ) {
						cadena = cadena + p +"\n"; 
					}
					cadena = cadena + "Siguientes\n";
					
					List<Siguiente> siguientes = Siguientes.calcular(gramatica,primeros);
					for(Siguiente s : siguientes ) {
						cadena = cadena + s +"\n"; 
					}
					
					PrimerosYsiguientes  mp= new PrimerosYsiguientes();  
					mp.setVisible(true);
					mp.tprimeros.setText(cadena);
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
