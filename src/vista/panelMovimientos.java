package vista;



import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class panelMovimientos extends JPanel{

	private JTextField movi;
	
	public panelMovimientos() {
		
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Movimientos" ) ) );
	    setLayout(null);
	    
	    movi = new JTextField();
	    movi.setBounds(30, 30, 620, 40);
	    movi.setEnabled(false);
	    movi.setFont(new Font("myFont", Font.BOLD, 13));
	    add(movi);
		
	}
	
	public void pintarMovimientos(String movimientos){
		this.movi.setText(movimientos);
	}
}
