package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;

import vista.panelPulzze;

public class armarPulzze implements Serializable{
	

	private String [][] estadoinicial,inicio, matriztemporal, matriz,
						estadoFinalMatriz;
	private int filas=0, columnas=0, inciofila=0, finfila=2, iniciocolumna=0, fincolumna=2;
	private char valor[];
	private String temp, movimiento, estado="", estadofinal="123-456-78null-";
	private boolean fila, columna, mitad, llegafinal=false, valido;	
	private HashMap<Integer, String> estados;
	private Queue<Vertices> cola;
	private Grafo grafo;
	
		
	public armarPulzze(BufferedReader archivo) throws IOException {
		
		estadoinicial =  new String[3][3];
		estadoFinalMatriz = new String[3][3];
		estadoFinalMatriz[0][0] = "1";
		estadoFinalMatriz[0][1] = "2";
		estadoFinalMatriz[0][2] = "3";
		estadoFinalMatriz[1][0] = "4";
		estadoFinalMatriz[1][1] = "5";
		estadoFinalMatriz[1][2] = "6";
		estadoFinalMatriz[2][0] = "7";
		estadoFinalMatriz[2][1] = "8";
		estadoFinalMatriz[2][2] = null;
		estados= new HashMap<>();
		cola = new LinkedList<>();
		armarEstadoInicial(archivo);
		inicio = estadoinicial;
		armarPuzzle();
		System.out.println("Secuencia______________________: "+recorrerGrafo());
	}
	
	public void armarEstadoInicial(BufferedReader archivo) throws IOException {
		
		String cadena;
		grafo = new Grafo();
		while ((cadena = archivo.readLine())!=null) {
			
			columnas=0;	
			
			valor=cadena.toCharArray();
			System.out.println(valor.length);
		
			for (int i = 0; i < cadena.length(); i++) {
				
				if (i%2==0) {
					
					if (valor[i]==' ') {
						
						estadoinicial[filas][columnas]=null;
					}else{
					
					estadoinicial[filas][columnas]=valor[i]+"";
					
					}
					
					columnas++;
					
				}
				
			}
			
			filas++;
			
		}		
	//	
		
		grafo.addVertice(estadoinicial);
		cola.add(grafo.getVertice(estadoinicial));		
		matriz=Serialization.copy(cola.peek().getDato());
		concatenarMatriz();
	 
	}
	
	public void armarPuzzle(){

		while (!llegafinal) {
				
			if (cola.peek() != null) {
				
				matriz=Serialization.copy(cola.peek().getDato());
				moverPuzzle();	
			}else{
				
			}
			
			
			
		}
		
		System.out.println("Terminamos");
		//ystem.out.println("Secuencia "+recorrerGrafo());
	}
	
	
	public String recorrerGrafo() {
		

	        Queue<Vertices> colaAux = new LinkedBlockingDeque();
	        Map<Vertices, Integer> visitados = new HashMap();
	        String secuenciaMovimientos = "";
	        Vertices rutaPuzzle = null;
	        boolean encontrado = false;
	        Vertices inicial = grafo.getVertice(inicio);
	        colaAux.add(inicial);
	        
	        while (colaAux.size() > 0 && !encontrado) {
	            
	            if (inicio.equals(estadofinal)) {
	                secuenciaMovimientos += "No se ha movido, estados iguales";
	                return secuenciaMovimientos;
	            }else {
	                 
	            Vertices visitando = colaAux.remove();
	            
	            visitados.put(visitando, visitados.size()+1);

	                 
	            for (Vertices vb : visitando.getAdyacentes()) {
	                if (visitados.get(vb) == null) {
	                    if (!colaAux.contains(vb)) {
	                        
	                        if (Arrays.deepEquals(vb.getDato(), estadoFinalMatriz)) {
	                            vb.setAnterior(visitando);
	                            rutaPuzzle = vb;
	                            //vb.setGrado(visitando.getGrado()+ 1);
	                            encontrado = true;
	                            break;
	                        } else {
	                            vb.setAnterior(visitando);
	                           // vb.setGrado(visitando.getGrado() + 1);
	                            colaAux.add(vb);
	                        // visitados.put(vb,vb.getGrado());
	                        }
	                    }
	                }
	            }
	        }
	        }
	      
	        String mov = "";
	        while (rutaPuzzle.getAnterior() != null) {
	        	Vertices anterior = rutaPuzzle.getAnterior();
	        	for (Vertices v : anterior.getAdyacentes()) {
	        		if (v.equals(rutaPuzzle)) {
						mov += v.mapaAdyacentes.get(anterior);
						break;
					}
				}
				rutaPuzzle = rutaPuzzle.getAnterior();
			}
	        
	        StringBuilder builder = new StringBuilder(mov);
	        secuenciaMovimientos = builder.reverse().toString();

		
		return secuenciaMovimientos;
	}
	
	
	public void moverPuzzle(){
	
			
			fila=true;
			columna=false;
			mitad=false;
			
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					
					if (matriz[i][j]==null) {
						
						if (fila) {
							
							switch (i) {
							case 0:
								if (j==iniciocolumna) {
									
									System.out.println("solo puedo mover a derecha, abajo");
									
									
									moverDerecha(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									moverAbajo(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									cola.poll();
									
									
								}else {
									

									if (j==fincolumna) {
										
										System.out.println("solo puedo mover a izquierda, abajo");
										
									
										moverIzquierda(i, j);	
										matriz=Serialization.copy(cola.peek().getDato());
										moverAbajo(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										cola.poll();
										

										
									}else{
										
										System.out.println("solo puedo mover derecha, izquierda, abajo");
										
										
										moverDerecha(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										moverIzquierda(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										moverAbajo(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										cola.poll();
									}
								}
								
								
								break;
							
							case 2:
								
								if (j==iniciocolumna) {
									
									System.out.println("solo puedo mover a arriba, derecha");
									
									moverArriba(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									moverDerecha(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									cola.poll();
								}else {
									

									if (j==fincolumna) {
										
										System.out.println("solo puedo mover a izquierda, arriba");
										
										
										moverIzquierda(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										moverArriba(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										cola.poll();
									}else{
										
										System.out.println("solo puedo mover derecha, izquierda, arriba");
										
										
										moverIzquierda(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										moverDerecha(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										moverArriba(i, j);
										matriz=Serialization.copy(cola.peek().getDato());
										cola.poll();
									}
								}
								
								
								break;
							default:
								
								columna=true;
								break;
							}
							
							if (columna) {
								
								switch (j) {
								case 0:
									
									System.out.println("mover hacia derecha, arriba, abajo");

								
								moverArriba(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								moverDerecha(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								moverAbajo(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								cola.poll();
									
									break;

								case 2:
									
									System.out.println("mover hacia izquierda, arriba, abajo");
									
								
									moverIzquierda(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									moverAbajo(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									moverArriba(i, j);
									matriz=Serialization.copy(cola.peek().getDato());
									cola.poll();
									
									
									break;
									
								default:
									
									mitad=true;
									
									break;
								}
								
							}
							
							if (mitad) {
								
								System.out.println("se puede mover hacia arriba, abajo, derecha, izquierda");
								
								moverIzquierda(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								moverDerecha(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								moverArriba(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								moverAbajo(i, j);
								matriz=Serialization.copy(cola.peek().getDato());
								cola.poll();
								
							}
						}
						}
					}
				}	
		}
		
	
	
	public void moverDerecha(int i, int j){
		
		temp=matriz[i][j+1];
		matriz[i][j+1]=matriz[i][j];
		matriz[i][j]=temp;
		movimiento="R";
		matriztemporal=matriz.clone();
		concatenarMatriz();		
		
	}
	
	public void moverAbajo(int i, int j){
		

		temp=matriz[i+1][j];
		matriz[i+1][j]=matriz[i][j];
		matriz[i][j]=temp;
		movimiento="D";
		matriztemporal=matriz.clone();
		concatenarMatriz();
		
	}
	
	public void moverIzquierda(int i, int j){
		
		temp=matriz[i][j-1];
		matriz[i][j-1]=matriz[i][j];
		matriz[i][j]=temp;
		movimiento="L";
		this.matriztemporal=matriz.clone();
		concatenarMatriz();
		
	}
	
	public void moverArriba(int i, int j){
		
		
		temp=matriz[i-1][j];
		matriz[i-1][j]=matriz[i][j];
		matriz[i][j]=temp;
		movimiento="U";
		this.matriztemporal=matriz.clone();
		concatenarMatriz();
		
	}
	

	
	public void concatenarMatriz(){		
		
		estado="";
		
		for (int i = 0; i < estadoinicial.length; i++) {
			for (int j = 0; j < estadoinicial.length; j++) {
				
				estado+=matriz[i][j];
			}
			
			estado+="-";
		}
		
		guardarEstados();
		
	}
	
	public void guardarEstados(){
		
		
		for (String value : estados.values()) {
			
			if (value.equals(estado)) {
				
				valido=false;
				break;
			}else{
				
				valido=true;
				
			}
			
		}
		
		if (valido) {
			
			estados.put(estados.size()+1, estado);
			grafo.addVertice(matriztemporal);
			estadoinicial=cola.peek().getDato();
			grafo.addArista(estadoinicial, matriztemporal, movimiento);
			cola.add(grafo.getVertice(matriztemporal));
			
			
		}
		
		if (estados.size()==0) {
			
			estados.put(estados.size()+1, estado);
		}
		
		
		if (estado.equals(estadofinal)) {
			
			llegafinal=true;
			System.out.println("llegaste");
			
		}
		
		
	}
	
	
	public String[][] getEstadoinicial() {
		return inicio;
	}

	public void setEstadoinicial(String[][] estadoinicial) {
		this.inicio = estadoinicial;
	}
	
}
