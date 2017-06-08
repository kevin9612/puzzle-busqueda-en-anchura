package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class cargarArchivo {

	private JFileChooser select;
	private FileNameExtensionFilter restriruta;
	private File ruta;
	private FileReader rutareader;
	private BufferedReader archi;
	
	public cargarArchivo() {
		
	}
	
	public void cargarArchivo(){
		
		select = new JFileChooser();
		select.setDialogTitle("Seleccione el archivo");
		restriruta=new FileNameExtensionFilter("Archivo", "txt");
		select.setFileFilter(restriruta);
		
		try {
			
			if (select.showSaveDialog(null)==select.APPROVE_OPTION) {
				
				ruta = select.getSelectedFile().getAbsoluteFile();				
				rutareader = new FileReader(ruta);
				archi = new BufferedReader(rutareader);
				
			
			}
		} catch (Exception e) {
			
		}
		
		
		
	}
	

	public BufferedReader getArchi() {
		return archi;
	}

	public void setArchi(BufferedReader archi) {
		this.archi = archi;
	}

	
}
