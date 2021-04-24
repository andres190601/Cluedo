package graficos;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class resBruteForce {
	
	private JFrame frame;
	private static boolean resuelto = false;
	public static String tiempoRes = "";
	public static StringBuilder historialResultado = new StringBuilder();
	public static String solucionSugerida[] = new String[5];
	public static String solucion[] = solverGUI.solucion;
	public static String restricciones[][] = solverGUI.restricciones;
	private static long start;
	public static String arrayPiezas [][] = solverGUI.arrayPiezas;
	
	
	public static void info() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resBruteForce window = new resBruteForce();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public resBruteForce() {
		 resolverBrute();
		 initialize();
		

	}
	
	
	public void initialize(){
		frame = new JFrame();
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		frame.setVisible(true);
		frame.setBounds(1000, 200, 700, 650);
		frame.getContentPane().setLayout(null);
		
		JTextArea display = new JTextArea();
	    display.setEditable(false); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane(display);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(10, 100, 680, 400);
	    display.setText(historialResultado.toString());
	    frame.add(scroll);
	    
	    JLabel res = new JLabel(tiempoRes + " en llegar a su conclusión");
	    res.setBounds(40, 570, 500, 21);
	    res.setFont(new Font("Arial", Font.BOLD, 16));
	    frame.add(res);
	}
	
	
	
	
	
	private static void loopBrute(int index) {
		for (int i = 0; i < arrayPiezas[index].length; i++) {
			solucionSugerida[index] = arrayPiezas[index][i];
			if(index < 4) {
				loopBrute(index + 1);
			}
			historialResultado.append("Solución Sugerida: " + solucionSugerida[0] + "," + solucionSugerida[1] + "," + solucionSugerida[2] + "," + solucionSugerida[3] + "," + solucionSugerida[4]+System.lineSeparator());
			if (Arrays.equals(solucion, solucionSugerida)){
				if(validarRespuesta()) {
					//historialResultado = historialResultado + "Solución Sugerida: " + solucionSugerida[0] + "," + solucionSugerida[1] + "," + solucionSugerida[2] + "," + solucionSugerida[3] + "," + solucionSugerida[4]+System.lineSeparator();
					historialResultado.append("Solución encontrada"+System.lineSeparator());
					tiempoRes = "El algoritmo duró: " + convertmillis(System.currentTimeMillis() - start);
					resuelto = true;
					
					return;
				}
				else {
					historialResultado.append("Solución no encontrada debido a restricciones");
					tiempoRes = "El algoritmo duró: " + convertmillis(System.currentTimeMillis() - start);
					resuelto = true;
					return;
				}
			}	
			
		}
	}
	
	
	
	
	
	private static void resolverBrute() {
		start = System.currentTimeMillis();
		while(true) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < arrayPiezas[i].length; j++) {
					solucionSugerida[i] = arrayPiezas[i][j];
					loopBrute(i + 1);
					if (resuelto) return;
				}
			}
		}
	}
	
	
	
	
	
	
	
	private static boolean validarRespuesta() {
		for (String[] restriccion : restricciones) {
			if(Arrays.asList(solucion).contains(restriccion[0]) && Arrays.asList(solucion).contains(restriccion[1])) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	public static String convertmillis(long input) {
		int days = 0, hours = 0, minutes = 0, seconds = 0, millis = 0;
			        
		int day = 86400000;
		int hour = 3600000;
		int minute = 60000;
		int second = 1000;
			    
			       
		if(input >= day) {
		     days = (int) (input / day);
		     millis = (int) (input % day);
		} else 
			millis = (int) input;
			           
		if(millis >= hour) {
		     hours = millis / hour;
		     millis = millis% hour;
		}
			       
		if(millis >= minute) {
			 minutes = millis / minute;
			 millis = millis % minute;
		}
		
		if(millis >= second) {
			seconds = millis / second;
			millis = millis % second;
		}
			      
		return (seconds + "s y " + millis + "ms");
	}

}
