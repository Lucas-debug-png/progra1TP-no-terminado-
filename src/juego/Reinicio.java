package juego;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Reinicio {
	
	private Entorno entorno;
	
	private Image imagen;
	private boolean perder;
	
	private int oleadaAlcanzada;
	private int murcielagosMuertos;
	
	public Reinicio(Entorno entorno) {
		
		this.entorno = entorno;
		this.perder = false;	
		this.imagen= new  ImageIcon("imagenes/gameover2.png").getImage();
	}
	
	public void dibujar(Entorno entorno) {
		//mientras perder sea distinto de false, mientras sea true se va a activar la pantalla de perder
		 verificarFinPorOleada();
		
		if(!perder) {
			return;
		
		}
		
		
		
		entorno.dibujarImagen(imagen, 400, 250, 0, 2);
		
		
		
		entorno.cambiarFont("Arial", 30, Color.WHITE);
		entorno.escribirTexto("Presiona R para reiniciar", 220, 370);
		
		entorno.cambiarFont("Arial", 20, Color.WHITE);
		entorno.escribirTexto("Murciélagos derrotados: " + murcielagosMuertos, 100, 100);
		entorno.escribirTexto("Oleada alcanzada: " + oleadaAlcanzada, 100, 150);
		
		if (oleadaAlcanzada ==10) {
			entorno.cambiarFont("Negrita", 30, Color.RED);
	        entorno.escribirTexto("¡Ganaste! Has superado todas las oleadas.", 150, 300);
		
		}
	}
	
	
	public void setEstadisticas(int murcielagosMuertos, int oleadaAlcanzada) {
	    this.murcielagosMuertos = murcielagosMuertos;
	    this.oleadaAlcanzada = oleadaAlcanzada;
	 
	}
	
	
	
	
	public boolean verificacion() {
		
		if(entorno.sePresiono('r') || entorno.sePresiono(entorno.TECLA_ENTER)) {
			return true;
		}
		
		return false;
		
		// aca verifica si perder es true y la tecla presionada es la R, esto sirve para que cuando aparezca la pantalla de reinicio y toquemos la letra R, iniciara todo el codigo
		
	}
	
	public void setter(boolean perder) {
		
		//perder es true
		this.perder= perder;
	}
	
	
	public boolean perdi() {
		
		//esto sirve para poder cambiar el valor de la variable en otros metodos
		return perder;
		
	}
	
	
	
	
	public void verificarFinPorOleada() {
	    if (oleadaAlcanzada >= 9) {
	        this.perder = true;
	    }
	}
	
	
	
	
	
	
	
	public int getOleadaAlcanzada() { return oleadaAlcanzada; }
	
	
	
}
