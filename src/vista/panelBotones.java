package vista;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controlador.Controlador;
import modelo.armarPulzze;

public class panelBotones extends JPanel implements ActionListener{

	private JButton pintarmatriz, jugar;
	private Controlador ctrl;
	
	public panelBotones(Controlador ctrl) {
		
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Opciones" ) ) );
	    setLayout(null);
		
	    this.ctrl=ctrl;
	    
		pintarmatriz = new JButton("Cargar matriz");
		pintarmatriz.addActionListener(this);
		pintarmatriz.setBounds(20, 60, 260, 30);
		add(pintarmatriz);
		
		jugar = new JButton("Solucionar");
		jugar.addActionListener(this);
		jugar.setBounds(20, 120, 260, 30);
		add(jugar);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		 String accion = e.getActionCommand( );
		
		 if (accion.equals("Cargar matriz")) {
			
			 try {
				ctrl.cargarArchivo();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}
		 
		 
		 if (accion.equals("Solucionar")) {
			
			 ctrl.actualizar();
			 
		 }
	}
	
}
