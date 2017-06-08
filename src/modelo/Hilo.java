package modelo;

import Controlador.Controlador;

public class Hilo extends Thread {

	Controlador ctrl;
	int contador = 0;
	private String mov = "";
	private String[][] matriz = null;
	private boolean iniciar, romper = false;

	public Hilo(Controlador ctrl) {
		this.ctrl = ctrl;
	}

	public String getMov() {
		return this.mov;
	}

	public void setMov(String mov) {
		this.mov = mov;
	}

	public String[][] getMatriz() {
		return this.matriz;
	}

	public void setMatriz(String[][] matriz) {
		this.matriz = matriz;
	}

	public void star() {
		iniciar = true;
	}

	public void stopT() {
		iniciar = false;
	}

	@Override
	public void run() {

		while (iniciar) {
			try {
				while (contador != mov.length()) {
					char movimiento = mov.charAt(contador);
					for (int i = 0; i < matriz.length; i++) {
						for (int j = 0; j < matriz[0].length; j++) {
							if (matriz[i][j] == null) {
								ctrl.getPnlPuzzle().moverPuzzle(matriz, i, j, movimiento);
								romper = true;
								break;
							}
							
						}
						if (romper) {
							break;
						}
					}
					romper = false;
					contador++;
					sleep(3000);
				}
				stopT();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		super.run();
	}

}
