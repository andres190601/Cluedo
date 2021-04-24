package graficos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.Arrays;

import graficos.mainWindow;

import javax.swing.*;



public class solverGUI {
	
	private JFrame frame;
	JLabel veredictoLabel = new JLabel("Hola");
	JLabel restriccionContradiccion = new JLabel();
	static int cantidadRestricciones = mainWindow.cantidadRestricciones;
	public static String arrayPiezas [][]= {{"El/la mejor amigo(a)","El/la novio(a)","El/la vecino(a)","El mensajero","El extraño","El/la hermanastro(a)","El/la colega de trabajo"},
	{"Pistola","Cuchillo","Machete","Pala","Bate","Botella","Tubo","Cuerda"},
	{"Venganza","Celos","Dinero","Accidente","Drogas","Robo"},
	{"Cabeza","Pecho","Abdomen","Espalda","Piernas","Brazos"},
	{"Sala","Comedor","Baño","Terraza","Cuarto","Garage","Patio","Balcón","Cocina"}};
	public static String solucion[] = new String[5];
	public static String restricciones[][] = new String [cantidadRestricciones][2];
	
	/**
	 * Launch the application.
	 * @param cantidadRestricciones2 
	 */
	public static void info() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					solverGUI window = new solverGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public solverGUI() {
		definirRespuesta();
		definirRestricciones();
		validarRespuesta();
		initialize();
		

	}
	
	
	
	private void initialize() {
		
		
		frame = new JFrame();
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		frame.setVisible(true);
		frame.setBounds(200, 200, 700, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Clue Solver");
		label1.setBounds(278,30,120,45);
		label1.setFont(new Font("Serif", Font.BOLD, 20));
		label1.setBorder(BorderFactory.createTitledBorder("Clue Solver"));
		label1.setForeground(Color.BLACK);
		frame.add(label1);
		
		JLabel labelSolucion = new JLabel("Solución:");
		labelSolucion.setBounds(120, 90, 120, 45);
		labelSolucion.setFont(new Font("Serif", Font.BOLD, 25));
		frame.add(labelSolucion);
		
		JLabel solucionMostrada = new JLabel("<html>Asesino: "+solucion[0]+", arma: "+solucion[1]+", motivo: "+solucion[2]+",<br />                                  "
				+ "parte del cuerpo: "+solucion[3]+", lugar: "+ solucion[4]+"</html>", SwingConstants.CENTER);
		solucionMostrada.setBounds(0, 160, 690, 40);
		solucionMostrada.setFont(new Font("Arial", Font.BOLD, 15));
		frame.add(solucionMostrada);
		
		JLabel antesVeredicto = new JLabel("Esta solución, tomando en cuenta las restricciones, es: ", SwingConstants.CENTER);
		antesVeredicto.setBounds(38, 230, 600, 20);
		antesVeredicto.setFont(new Font("Arial", Font.PLAIN, 16));
		frame.add(antesVeredicto);
		
		veredictoLabel.setBounds(280, 270, 150, 20);
		veredictoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(veredictoLabel);
		
		restriccionContradiccion.setBounds(270,310,360,20);
		restriccionContradiccion.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(restriccionContradiccion);
		
		
		JButton botonBrute = new JButton("Brute force");
		botonBrute.setBounds(150, 350, 110, 30);
		botonBrute.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resBruteForce.info();
			}
		});
		botonBrute.setVisible(true);
		frame.add(botonBrute);
		
		JButton botonBT = new JButton("Backtrack");
		botonBT.setBounds(375, 350, 110, 30);
		botonBT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resBacktrack.info();
			}
		});
		botonBrute.setVisible(true);
		frame.add(botonBrute);
		frame.add(botonBT);
		
		frame.repaint();
		

	}
	
	private String[] definirRespuesta(){
		Random rand = new Random(); 
		for(int i = 0; i <= 4; i++) {
			int random = rand.nextInt(arrayPiezas[i].length);
			solucion[i] = arrayPiezas[i][random];
		}
		
		return solucion;
	}
	
	private String[][] definirRestricciones(){
		Random rand = new Random(); 
		for(int i = 0; i < cantidadRestricciones; i++) {
			int rand1 = rand.nextInt(5);			
			int rand2 = rand.nextInt(arrayPiezas[rand1].length);
			int rand3 = rand.nextInt(5);
			int rand4 = rand.nextInt(arrayPiezas[rand3].length);
			restricciones[i] = new String[] {arrayPiezas[rand1][rand2],arrayPiezas[rand3][rand4]};
		}
		return restricciones;
	}
	
	private boolean validarRespuesta() {
		for (String[] restriccion : restricciones) {
			if(Arrays.asList(solucion).contains(restriccion[0]) && Arrays.asList(solucion).contains(restriccion[1])) {
				veredictoLabel.setText("Contradictoria");
				restriccionContradiccion.setText(restriccion[0]+", "+restriccion[1]);
				return false;
			}
		}
		veredictoLabel.setText("Válida");
		return true;
	}


}
