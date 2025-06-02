package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class MenuPrincipal {
	
	private Entorno entorno;
	
	private BotonesMouse botonStart , botonSalir;
	private Image imagenFondo;
	
	
	public MenuPrincipal(Entorno entorno) {
		
		this.entorno= entorno;
		this.botonStart = new BotonesMouse(400,300,200,50, "INICIAR");
		
		this.botonSalir = new BotonesMouse(400,400,200,50, "Salir");
		this.imagenFondo=Herramientas.cargarImagen("9285000.jpg");
	}
	
	public void dibujar(Entorno entorno){
		
		entorno.dibujarImagen(imagenFondo, entorno.ancho(), entorno.alto(), 0);
		
		entorno.cambiarFont("Arial", 40, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto("El camino de Gondolf", entorno.ancho()/2, 100);
		botonStart.dibujar(entorno);
		botonSalir.dibujar(entorno);
		
	}
	
	public boolean inputs() {
		
		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			
			int posX= entorno.mouseX();
			int posY= entorno.mouseY();
			
			if(botonStart.estaDentro(posX, posY)) {
				return true;
			}
			else if(botonSalir.estaDentro(posX, posY)) {
				
				System.exit(0);
			}
		}
		return false;
	}

}
