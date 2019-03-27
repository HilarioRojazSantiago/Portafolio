package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class tablaText extends JFrame {

	private JPanel contentPane;
	JScrollPane AP = new JScrollPane();
	JScrollPane PE = new JScrollPane();
	static JTextArea errores = new JTextArea();
	static JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablaText frame = new tablaText();
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
	public tablaText() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1054, 868);
		this.setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane AP = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		AP.setBounds(5, 5, 1591, 624);
		//JScrollPane PE = new JScrollPane(errores,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		contentPane.add(AP);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(errores.getText());
		textArea.setBounds(5, 642, 1591, 173);
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		contentPane.add(textArea);
	}
	public static void setNtextArea(String datos) {
		String cadena = "" + datos;
		//for(String i : datos)
			//cadena = cadena + i+" ";
		errores.setText(cadena);
	}
	public static void setNTable (String [][] coleccion, String[] columnas) {
		tabla = new JTable(coleccion, columnas); 
	}
}
