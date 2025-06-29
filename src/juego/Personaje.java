 package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Personaje {
	
	private Entorno entorno;
		//clase de crear al gordito del personaje 
	public static class Gondolf{
		private Roca roca;
		private Poderes poder;
		private double x;
		private double y;
		private int vida;
		private int magia;
		private int radio;
		private int anchoPJ =48;
		private int altoPJ = 52;
		private int velocidad;
		
		
		
		private int tiempoDisparo =0;
		private final int COOLDOWN_MAXIMO;
		private final int VIDA_MAXIMA = 100;
		private final int MANA_MAXIMO =100;
		
		
		
		private Image imagenActual, imagenFrente, imagenDerecha, imagenIzquierda, imagenArriba;

		
		
	public Gondolf( double x ,double y ,Roca roca) {
		
		this.COOLDOWN_MAXIMO = 10;
		this.x= x;     //aca tenemos la posicion donde se queda el gordito en (x)
		this.y= y;     //lo mismo pero en (Y)
		
		
		this.roca =roca;
		this.vida=100;
		this.magia=100;
		this.velocidad=5;
		this.imagenArriba = new ImageIcon("imagenes/gondolf_espalda.png").getImage();
		this.imagenFrente = new ImageIcon("imagenes/gondolf_frente.png").getImage(); //cargo la imagen en los parametros de gondolf
		this.imagenDerecha= new ImageIcon("imagenes/gondolf_Dr.png").getImage();
		this.imagenIzquierda= new ImageIcon("imagenes/gondolf_iz.png").getImage();
		
		this.imagenActual = imagenFrente; // esta seria la imagen inicial que es la que mira al frente
		
		
		//cargamos las imagenes necesarias del personaje
		
				
			}
	
	
	public void mover(double movX, double movY, Entorno entorno, Roca piedra, ArrayList<Roca> rocas) {
		
		double Dx = 0;
		double Dy = 0;
		
		entorno.ancho();
		//this.x=x+movX * velocidad; //tenemos los valores de X y lo multiplicamos por la velocidad, eso queda 1*10= 10+ posicion(100)= posicion= 110 en x
		//this.y=y+movY * velocidad;
		
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a')) {
			Dx = -1;
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d')) {
			Dx = 1;
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO) || entorno.estaPresionada('s')) {
			Dy = 1;
		}
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA) || entorno.estaPresionada('w')) {
			Dy = -1;
			// aca tenemos los movimientos WASD del personaje
		}
		
	

		if (Dx != 0 && Dy != 0) {
			double magnitud = Math.sqrt(Dx * Dx + Dy * Dy);
			Dx /= magnitud;
			Dy /= magnitud;

		}
		
		
		double nuevoX = x+ Dx * velocidad; //tenemos los valores de X y lo multiplicamos por la velocidad, eso queda 1*10= 10+ posicion(100)= posicion= 110 en x
		double nuevoY = y+ Dy * velocidad;

		
		if(!colision(nuevoX, nuevoY, entorno)) {
			boolean puedeMover = true;
			
			
			for(Roca roca : rocas) {
				
				if(roca.colisionConPj(nuevoX, nuevoY, anchoPJ, altoPJ)) {
					puedeMover= false;
				}
				
			}
			
			if(puedeMover) {
				x=nuevoX;
				y=nuevoY;
			}
		}
		
		
		
		
		//if (!colision(nuevoX, nuevoY, entorno)) {
		//	if(this.roca== null||!this.roca.colisionConPj(nuevoX, nuevoY, anchoPJ, altoPJ)) {
				
				//x=nuevoX;
			//	y=nuevoY;
		//	}
	//	}
		
		
		
		
		
		if(Dx>0) {
			this.imagenActual = imagenDerecha; //cambia la imagen actual (principal) por una en movimiento
		}
		
		if(Dx<0) {    //se mueve a la izquierda
			this.imagenActual =imagenIzquierda;
		}
		
		if(Dy>0) {
			this.imagenActual = imagenFrente;  //gondolf caminando hacia arriba, (da la espalda)
		}
		if(Dy<0) {
			this.imagenActual = imagenArriba; // gondolf caminando hacia nosotros (se le ve la cara)
		}
		
				
		
	
			}
	
	
	//return x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio;	
	

	
	public void noMover() {
		velocidad=0;
	
	}
	
	
	public int quitarVida( int cantidad) {
		
		if(!estaMuerto()) {
			this.vida-=cantidad;
			
		}
		
		if(this.vida<0) {
			this.vida=0;
		}
		
		if(estaMuerto()) {
			muerto();
		}
		
		return vida;
		
		

	}
	
	
	public void quitarMana(int cantidad) {
		
		this.magia= Math.max(0, this.magia -cantidad);
		
		System.out.println("mana reducido- "+ cantidad +" total"+ this.magia);
		
		
	
		
		
	}
	
	public int getMagia() {
		return this.magia;
	}
	
	public boolean estaMuerto() {
		
		return this.vida <=0;
	}
	
	public boolean sinMana() {
		return this.magia<=0;
	}
	
	private void muerto() {
		
		System.out.println("muerto");
		this.velocidad=0;
	}
	
	public boolean colision(double otroX, double otroY, Entorno entorno) {
		
		

	    boolean chocaIzquierda = otroX - (anchoPJ/2) < 0;
	    boolean chocaDerecha = otroX + (anchoPJ/2) > 600; // evita el panel de la derecha
	    boolean chocaArriba = otroY - (altoPJ/2) < 0;
	    boolean chocaAbajo = otroY + (altoPJ/2) > 600;
		
	
		
		
		
		return chocaIzquierda || chocaDerecha || chocaArriba || chocaAbajo;
		
	
		
		
		//return x<= anchoPJ + anchoPJ/2 || y <= altoPJ/2 || x>= entorno.ancho() - anchoPJ || y >= entorno.alto()- altoPJ;
	}

	
	
	
	
	
	public void dibujarPj(Entorno entorno) {
		
		entorno.dibujarImagen(this.imagenActual, this.x, this.y, 0, 3);
		//entorno.dibujarRectangulo(x, y, anchoPJ, altoPJ, 0, Color.getHSBColor(0,0, 1)); //hitbox
		
		
		
	}
	
	public void recuperarVida(int cantidad) {
		
	
		this.vida= Math.min(VIDA_MAXIMA, this.vida + cantidad);
		
		System.out.println("vida"+ cantidad+ "total:"+ this.vida);
			
			
		
	}
	
	public void recupearMana(int cantidad) {
		
		
		this.magia  = Math.min(100, this.magia * cantidad);
		
		System.out.println("Mana" + cantidad+ "total"+ this.magia);
	}
		
		
		
	
	
	
	
	
	
	
	
	
	
	public double getX( ) {return x;}
	public double getY () {return y;}
	public int getVida( ) {return vida;}
	
	public int getAnchoPJ() {return anchoPJ;}
	public int getAltoPJ() {return altoPJ;}
	
			
			
			
			}
		
		
		}


