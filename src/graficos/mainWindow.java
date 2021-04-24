package graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;




public class mainWindow {
	
	static int cantidadRestricciones;
	
	public mainWindow() {
		JFrame frame = new JFrame();		
		JPanel panel = new JPanel();
		
		
		frame.setBounds(700, 400, 300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue Solver");
		frame.setVisible(true);
		panel.setBackground(Color.black);
		frame.add(panel);
		panel.setVisible(true);
		
		
		JLabel label1 = new JLabel("Clue Solver");
		label1.setBounds(80,10,120,45);
		label1.setFont(new Font("Serif", Font.BOLD, 20));
		label1.setBorder(BorderFactory.createTitledBorder("Clue Solver"));
		label1.setForeground(Color.WHITE);
		label1.setVisible(true);
		panel.add(label1);
		
		JLabel labelRestricciones =  new JLabel("Número de restricciones:");
		labelRestricciones.setBounds(60, 60, 200, 45);
		labelRestricciones.setFont(new Font("Serif", Font.BOLD, 16));
		labelRestricciones.setForeground(Color.WHITE);
		labelRestricciones.setVisible(true);
		panel.add(labelRestricciones);
		
		
		JTextField restriccionesText = new JTextField("0");
		restriccionesText.setBounds(110, 100, 50, 20);
		((AbstractDocument)restriccionesText.getDocument()).setDocumentFilter(new DocumentFilter(){
	        Pattern regEx = Pattern.compile("\\d*");

	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {          
	            Matcher matcher = regEx.matcher(text);
	            if(!matcher.matches()){
	                return;
	            }
	            super.replace(fb, offset, length, text, attrs);
	        }
	    });
		restriccionesText.setVisible(true);
		panel.add(restriccionesText);
		
		JButton botonInicio = new JButton("Iniciar");
		botonInicio.setBounds(98, 160, 85, 30);
		botonInicio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cantidadRestricciones = Integer.parseInt(restriccionesText.getText());
				solverGUI.info();
				frame.setVisible(false);
			}
		});
		botonInicio.setVisible(true);
		panel.add(botonInicio);
		
		panel.repaint();
	}

	public static void main(String[] args) {
		new mainWindow();

	}
	


}
