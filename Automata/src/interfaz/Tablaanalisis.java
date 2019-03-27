package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.CardLayout;

public class Tablaanalisis extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JScrollPane JS = new JScrollPane();
	static JTable tabla;
	//tabla = new JTable();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablaanalisis frame = new Tablaanalisis();
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
	public Tablaanalisis() {
		setBounds(100, 100, 1194, 868);
		this.setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(32835, 32767));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JScrollPane JS = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		JS.setBorder(null);
		JS.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JS.setViewportBorder(null);
	
		contentPane.add(JS, "name_580684622373");
		
		//tabla = new JTable();
		//JS.setViewportView(tabla);
	}
	
	public static void setNTable (Object [][] coleccion, Object[] columnas) {
		tabla = new JTable(coleccion, columnas);
		TableColumnModel columnModel=tabla.getColumnModel();
		for (int i=0;i<columnModel.getColumnCount();i++)
		{
			columnModel.getColumn(i).setPreferredWidth(1700);
		}
	
	}

}
