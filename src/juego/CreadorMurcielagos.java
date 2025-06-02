package juego;

import java.awt.List;

import java.util.ArrayList;
import java.util.Iterator;


import entorno.Entorno;
import juego.Personaje.Gondolf;

public class CreadorMurcielagos {
	
	private ArrayList<Murcielago> murcielagosTotal;
	private ArrayList<Murcielago> murcielagosActivos;
	private ArrayList<Items> item;
	private Murcielago[] murcielagosActivos2;

	
	private boolean primeraHordaMuerta= false;
	private int oleadasCompletadas= 0;
	private int murcielagosMuertos=0;	
	
	private final int TOTAL_MURCIELAGO= 70;
	private final int MURCIELAGOS_X_OLEADA = 5;
	
	public CreadorMurcielagos() {
		
		murcielagosTotal= new ArrayList<>();
		murcielagosActivos= new ArrayList<>();
		IniciaMurcielago();
	}
	
	private void IniciaMurcielago() {
		
		for(int i =0; i < TOTAL_MURCIELAGO; i++) {
			
			double x= -100;
			double y = -100;
			double angulo = Math.random() * 2 * Math.PI;
			murcielagosTotal.add(new Murcielago(angulo, x, y, 1));
			
		}
		activarOleada();
	}
	
	private void activarOleada() {
		if (murcielagosTotal.isEmpty()) {
			return;
		}
		
		int cantidad= Math.min(5, murcielagosTotal.size());
		
		for(int i= 0; i< cantidad; i++) {
			
			Murcielago m= murcielagosTotal.remove(0);
			posicionesPantalla(m);
			murcielagosActivos.add(m);
		}
	}
	
	
	
	public void posicionesPantalla(Murcielago murcielago) {
		
		int borde = (int) (Math.random()*4);
		
		if(borde==0) {
			murcielago.setPosicion(Math.random()*600,0);  //arriba
		}
		else if(borde==1) {
			murcielago.setPosicion(600, Math.random() *600); //derecha
		}
		else if(borde==2) {
			murcielago.setPosicion(Math.random()*600,600); // abajo
			
		}
		else {
			murcielago.setPosicion(0, Math.random()*600); //izquierda
		}
	}
	
	
	
	
	public void actualizar(Gondolf gondolf, ArrayList<Items> itemsEnPantalla, ArrayList<Roca> rocas) {
		ArrayList <Items> itemsSoltados= new ArrayList<>();
		Iterator<Murcielago> iterador= murcielagosActivos.iterator();
		
		while(iterador.hasNext()) {
			Murcielago m= iterador.next();
			m.mover(gondolf);
			
			if(m.colisionConPJ(gondolf)) {
				
				gondolf.quitarVida(10);
				murcielagosMuertos++;
				System.out.println("murcielagos muertos"+ murcielagosMuertos);
				
				iterador.remove();
				

				
			}

		
		}
		
		if(murcielagosActivos.isEmpty() && !murcielagosTotal.isEmpty()) {
			
			oleadasCompletadas++;
			
			
			double x= 200 + Math.random()*400;
			double y= 150+ Math.random()*300;
			boolean posicionValida= true;
			
			
			for(Roca roca: rocas) {
				
				if(Math.abs(roca.getX()-x)<50 && Math.abs(roca.getY()-y) <50) {
					
					posicionValida = false;
					
				}
			}
			
		
			
			
			
			if(!primeraHordaMuerta || oleadasCompletadas%2==0) {
				
				if(Math.random()< 0.75) {
					
					//pocion de mana tiene 75 porciento de aparecer
					itemsEnPantalla.add(new Items(x,y, 0,10)); 
				}
				else {
					itemsEnPantalla.add(new Items(x,y,15,0));
				}
				
				
				//itemsEnPantalla.add(new Items(x,y,20,0));
				//System.out.println("item generado en"+ x+ ", "+ y);
				//primeraHordaMuerta=true;
			}
			primeraHordaMuerta=true;
			activarOleada();
			System.out.println("oleanda numero: "+ oleadasCompletadas);
		}
	

	}
	
	
	public void eliminarMurcielago( Murcielago murcielago) {
		
		murcielagosActivos.remove(murcielago);
	}
	
	
	
	public boolean estaOcupada(double x , double y) {
		
		for(Murcielago  murcielago : murcielagosActivos) {
			
			double distancia = Math.sqrt((murcielago.getX() + murcielago.getX())* (murcielago.getY()+ murcielago.getY()));
	        if (distancia < 50) return true; // Radio mínimo de separación
	    }
	    return false;
			
		}
	
	
	
	
	public void dibujar(Entorno entorno) {
		for(Murcielago m: murcielagosActivos) {
			m.dibujar(entorno);
		}
	}
	
	
	public ArrayList<Murcielago> getMurcielagosActivos(){
		
		// TODO Auto-generated method stub
		return this.murcielagosActivos;
	}

	
	
}


