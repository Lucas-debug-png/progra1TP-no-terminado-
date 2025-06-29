package juego;


import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;
import juego.Personaje.Gondolf;

public class Roca {
	
	private Gondolf gondolf;
	
	private double x;
	private double y;
	private int ancho = 30;
	private int alto = 30;
	private Entorno entorno;
	
	private Image imagen;
	
	
	public Roca(double x, double y , Entorno entorno) {
		
		this.x= x;
		this.y=y;
		this.imagen= new ImageIcon("imagenes/piedrita.png").getImage();
		
		
	
	
	
		
	}
	
	
	public void dibujar(Entorno entorno) {
		
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 0.10);
		//entorno.dibujarRectangulo(x, y, ancho/2, alto/2, 0, Color.getHSBColor(0,0, 1));
	}
	
	public boolean colisionConPj(double pjX, double pjY, int anchoPJ, int altoPJ) {
		
		double rocaIzquierda = this.x - this.ancho/4;
		double rocaDerecha = this.x + this.ancho/2;
		double rocaArriba = this.y - this.alto/4;
		double rocaAbajo = this.y + this.alto/2;
		
		
		double pjIzquierda = pjX - anchoPJ/2;
		double pjDerecha = pjX + anchoPJ;
		double pjArriba = pjY - altoPJ/2;
		double pjAbajo= pjY + altoPJ;
		
		
		boolean colisionX = rocaDerecha > pjIzquierda && rocaIzquierda < pjDerecha;
		boolean colisionY = rocaAbajo > pjArriba && rocaArriba < pjAbajo;
		
		return  colisionX && colisionY;
	}
	
	
	
	
	
	
	//public boolean colisionConPj(Gondolf gondolf) {
    // Centros de los objetos
    //double centroRocaX = this.x + (this.ancho / 2);
    //double centroRocaY = this.y + (this.alto / 2);
    //double centroPjX = gondolf.getX() + (gondolf.getAnchoPJ() / 2);
    //double centroPjY = gondolf.getY() + (gondolf.getAltoPJ() / 2);

    // Distancias entre centros
  //  double distanciaX = Math.abs(centroRocaX - centroPjX);
//    double distanciaY = Math.abs(centroRocaY - centroPjY);

    // Suma de mitades de anchos
    //double sumaMitadesX = (this.ancho / 2) + (gondolf.getAnchoPJ() / 2);
   // double sumaMitadesY = (this.alto / 2) + (gondolf.getAltoPJ() / 2);

    //return distanciaX < sumaMitadesX && distanciaY < sumaMitadesY;

	
	

	public double getX() {return x;}
	public double getY() {return y;}
	
}
