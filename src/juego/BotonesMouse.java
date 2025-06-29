package juego;


import entorno.Entorno;
import java.awt.Color;

public class BotonesMouse {
	
	private int x, y, ancho, alto; // Posición y tamaño del botón
	private String texto;          // Texto que muestra el botón
	private boolean seleccionado;  // Indica si el botón está seleccionado (para cambiar el color)

	// Constructor: inicializa el botón con posición, tamaño y texto
	public BotonesMouse(int x, int y, int ancho, int alto, String texto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.texto = texto;
		this.seleccionado = false;
	}

	// Dibuja el botón en pantalla, con diferente color si está seleccionado
	public void dibujar(Entorno entorno) {
		//Color color = seleccionado ? Color.YELLOW : Color.LIGHT_GRAY; // Amarillo si está seleccionado
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN); // Dibuja el botón
		entorno.cambiarFont("Arial", 16, Color.BLACK, 0); // Fuente para el texto
		entorno.escribirTexto(this.texto, x - ancho / 3, y + 5); // Dibuja el texto del botón
	}

	// Verifica si el mouse está dentro del área del botón
	public boolean estaDentro(int mx, int my) {
		
		
		return mx > x - ancho / 2 && mx < x + ancho / 2 &&
		       my > y - alto / 2 && my < y + alto / 2;
	}

	// Cambia el estado de selección del botón
	public void setSeleccionado(boolean valor) {
		this.seleccionado = valor;
	}

	// Devuelve el texto del botón
	public String getTexto() {
		return texto;
	}
}
	


