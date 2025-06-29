package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import entorno.Entorno;
import juego.Personaje.Gondolf;

public class Murcielago {
	
	private Entorno entorno;
	private Personaje personaje;
	private Gondolf gondolf;
	private Items item;
	
	private ArrayList<Murcielago> murcielagosTotal = new ArrayList<>(50); //total de murcielagos que tenemos
	private ArrayList<Murcielago> murcielagosActivos = new ArrayList<> (5);
	private ArrayList<Items> ItemSoltados= new ArrayList<>();
	
	

	private double x;
	private double y;
	private int velocidad;
	private double angulo;
	private double ancho;
	private int alto;
	private int vida;
	private int murcielagoMuerto =0;
	private boolean estaVivo;
	private Image imagen;

	public Murcielago(double angulo, double x, double y, int velocidad) {
		
	
		this.estaVivo= false;
		this.vida= 100;
		this.angulo= angulo;
		this.x=x;
		this.y=y;
		this.velocidad= 2;
		this.imagen = new ImageIcon("imagenes/imagenmurcielago.png").getImage();
		
		
	}
	
	  public void setPosicion(double x, double y) {
	        this.x = x;
	        this.y = y;
	    }
	
	public void calcularAnguloHaciaGondolf(Gondolf gondolf) {
	    this.angulo = Math.atan2(
	        gondolf.getY() - this.y,
	        gondolf.getX() - this.x
	    );
	}
	
	
	
	public boolean mover(Gondolf gondolf) {
		
		calcularAnguloHaciaGondolf(gondolf);
		
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
		
		
	
		if(colisionConPJ(gondolf)) {
			gondolf.quitarVida(1);
			System.out.println("choco");
			this.murcielagoMuerto++;
			this.vida=0;
			return true;
		}
		return false;
	}
	
	
	
	//colision con los bordes del mapa
	public boolean colision(double otroX, double otroY, Entorno entorno) {
		
		
	    boolean chocaIzquierda = otroX - (ancho ) < 10;
	    boolean chocaDerecha = otroX + (ancho ) > 590; // evita el panel de la derecha
	    boolean chocaArriba = otroY - (alto ) < 0;
	    boolean chocaAbajo = otroY + (alto) > 570;
		
		return chocaIzquierda || chocaDerecha || chocaArriba || chocaAbajo;
		
	}
	
	
	public void MurcielagoMov(Gondolf gondolf) {
		
		double Xpj= gondolf.getX();
		double Ypj= gondolf.getY();
	
		
		//obtenemos el angulo que debe seguir el mucielago

		double seguimiento = Math.atan2(Ypj-this.y , Xpj-this.x ); 
		
	//ahora normalizamos el angulo a movimiento 
		
		double velocidadX = Math.cos(seguimiento) *velocidad;
		double velocidadY= Math.sin(seguimiento) * velocidad;
		
		this.x += velocidadX;
		this.y+= velocidadY;
		
		
		if(colisionConPJ(gondolf)) {
			
			System.out.println("choco con pj");
		}
		
		

	}
	
	public boolean colisionConPJ(Gondolf gondolf) {
		
		boolean colisionX= (this.x < gondolf.getX() + gondolf.getAnchoPJ()) && (this.x + this.ancho> gondolf.getX());
		
		boolean colisionY= (this.y< gondolf.getY() + gondolf.getAltoPJ()) && (this.y + this.alto > gondolf.getY());
		
		
		
	
		return colisionX && colisionY;
		

		
		
	}
	
	
	// Le resta vida al murciélago y lo elimina si llega a 0
	public void restarVida(int cantidad) {
		if (!estaVivo()) return;

			vida -= cantidad;
			System.out.println("Vida del murciélago: " + vida);
			if (vida <= 0) {
				vida = 0;
				estaVivo = false;
				
				System.out.println("Murciélago eliminado");
				
				
				
			}
		}
	
	
	

		// Devuelve si el murciélago sigue vivo
		public void sett_estaVivo(boolean estaVivo) {
			
			this.estaVivo = estaVivo;
		}
		
		public boolean estaVivo() {
			
			return estaVivo;
		}
		
		
		
	
	
	public void acelerar() {
		velocidad += 1.0;
		
	}
	
	
	public Items dropItem() {
		
		double posX= this.x;
		double posY= this.y;
	
		double probabilidad= Math.random();
		
		
		if(probabilidad< 0.5) {
			return new Items(posX, posY, 15,0);
			
		}
		else {
			return new Items(posX, posY, 0,10);
		}
	}
	
	

	
	
	
	
	
	
		
	
	public void dibujar(Entorno entorno) {
		
		entorno.dibujarImagen(imagen, x, y, angulo, 0.05);
		
		if(this.imagen ==null) {
			System.out.println("la imagen esta vacia");
		}
		
		//entorno.dibujarImagen(imagen, x, y, angulo);
		
	}
	public double getX( ) {return x;}
	public double getY () {return y;}
	
	public int getMurcielagoMuerto() { return murcielagoMuerto;}
	public int getVida() {return vida;}
		// TODO Auto-generated method stub
		
	}
	

