package juego;

import juego.Personaje.Gondolf;
import java.util.List;

import javax.swing.ImageIcon;

import entorno.Entorno;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

public class Items {
	
	private double x;
	private double y;
	private int recuperarVida;
	private  Gondolf gondolf;
	private Murcielago murcielago;
	private int recuperarMana;
	private Image imagen;
	
	private boolean usado=false;
	
	public Items(double x, double y, int vida, int mana) {
		
		this.x= x;
		this.y=y;
		this.recuperarMana= mana;
		
		this.recuperarVida = vida;
		this.imagen= new ImageIcon("imagenes/pocion.png").getImage();
	}
	
	
	public void aplicarEfecto (Gondolf gondolf){
		
		if(!usado) {
			
			if(recuperarVida>0) {
				gondolf.recuperarVida(Math.min(20, recuperarVida));
			}
			
			if(recuperarMana>0) {
				gondolf.recupearMana(Math.min(15, recuperarMana));
				
				System.out.println("Mana +"+ recuperarMana+ "total: "+ gondolf.getMagia());
			}
			
			usado=true;
		}
		
	}
	
	public boolean colisionItemPJ(Gondolf gondolf) {
		
		double distanciaX= this.x - gondolf.getX();
		double distanciaY= this.y - gondolf.getY();
		
		double distancia= Math.sqrt(distanciaX * distanciaX +  distanciaY* distanciaY);
		
		return distancia < (gondolf.getAnchoPJ());
		
	}
	
	
	public void dibujar(Entorno entorno) {
		
		if(!usado) {
			entorno.dibujarImagen(imagen, x, y, 0, 0.05);
		}
		
		
	}
	
	
	
	
	
	

	
	

    public double getX() { return x; }
    public double getY() { return y; }

}
