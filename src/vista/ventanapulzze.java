package vista;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import Controlador.Controlador;
import modelo.armarPulzze;

public class ventanapulzze extends JFrame{

	panelPulzze panel1;
	panelBotones panel2;
	panelMovimientos panel3;
	
	public ventanapulzze(Controlador ctrl) {
	
		setTitle("Pulzze 8");
		setSize(750, 550);
		setResizable(false);
		setLayout(null);
		
		
		this.getContentPane().setBackground(new Color(239, 147, 27));
		
		panel1 = new panelPulzze();
		panel1.setBounds(30, 70, 300, 300);
		panel1.setBackground(new Color(222, 222, 55));
		this.getContentPane().add(panel1);
		
		panel2 = new panelBotones(ctrl);
		panel2.setBackground(new Color(22, 30, 134));
		panel2.setBounds(400, 70, 300, 300);
		this.getContentPane().add(panel2);
		
		panel3= new panelMovimientos();
		panel3.setBackground(new Color(52, 134, 22));
		panel3.setBounds(30, 400, 670, 100);
		this.getContentPane().add(panel3);
		
		ctrl.conectar(panel1, panel3);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		ventanapulzze ven = new ventanapulzze(new Controlador());
		ven.setVisible(true);
		
	}
	
}
