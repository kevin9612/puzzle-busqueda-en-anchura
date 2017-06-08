package vista;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class panelPulzze extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel [][] pulzze;
	private String temp;
	public panelPulzze() {
		
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Movimientos" ) ) );
		setLayout(new GridLayout(3, 3));
		
		pulzze = new JLabel[3][3];
		
		for (int i = 0; i < pulzze.length; i++) {
			for (int j = 0; j < pulzze.length; j++) {
			
				pulzze[i][j]=new JLabel();
				pulzze[i][j].setEnabled(false);
				pulzze[i][j].setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "" ) ) );
		    	pulzze[i][j].setHorizontalAlignment(JLabel.CENTER);
		    	pulzze[i][j].setVerticalAlignment(JLabel.CENTER);
		    	pulzze[i][j].setFont( new Font("myFont", Font.BOLD, 13) );
				add(pulzze[i][j]);
			}
			
		}
		
	}
	
	public void pintar(String pul[][]){
		for (int i = 0; i < pulzze.length; i++) {
			for (int j = 0; j < pulzze.length; j++) {
				String temp=pul[i][j];	
				pulzze[i][j].setText(temp);
			}
		}
	}
	
	public void moverPuzzle(String[][] matriz, int i, int j, char pul) {
		switch (pul) {
		case 'U':
			temp=matriz[i-1][j];
			matriz[i-1][j]=matriz[i][j];
			matriz[i][j]=temp;
		
			break;
		case 'R':
			temp=matriz[i][j+1];
			matriz[i][j+1]=matriz[i][j];
			matriz[i][j]=temp;
			
			break;
		case 'D':
			temp=matriz[i+1][j];
			matriz[i+1][j]=matriz[i][j];
			matriz[i][j]=temp;
			break;
		case 'L':
			temp=matriz[i][j-1];
			matriz[i][j-1]=matriz[i][j];
			matriz[i][j]=temp;
			break;
		default:
			break;
		}
		pintar(matriz);
	}
	
}
