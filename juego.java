package juego;

import java.awt.Color;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import juego.Personaje.Gondolf;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Gondolf gondolf;
	private Roca roca;
	private Murcielago murcielago;
	private Image fondo, hechizo1,hechizo2;
	
	private int vida = 100;
	private int mana = 100;
	
	private int murvida ;
	
	
	
	private Reinicio reiniciarJuego;
	private CreadorMurcielagos gestorMurcielagos;
	
	private ArrayList<BotonesMouse> botones = new ArrayList<>();
	
	private String hechizoSeleccionado = null;
	
	private ArrayList<Roca> rocas= new ArrayList<>();
	private ArrayList<Murcielago> murcielagosTotal = new ArrayList<>(50); //total de murcielagos que tenemos
	private ArrayList<Murcielago> murcielagosActivos = new ArrayList<> (5); //cantidad de murcielagos que iran apareciendo por oleada 
	// Variables y métodos propios de cada grupo
	// ...
	private int murcielagosmuertos = murcielagosTotal.size() ;
	private final int MURCIELAGOS_X_OLEADA = 5;
	//private int puntuacion = murcielagosmuertos ;
	//private int murcielagosVivos ;
	
	
	
	Juego() {
		// Inicializa el objeto entorno
		
		
		entorno = new Entorno(this, "Gandalf", 800, 600);
		
		Herramientas.loop("sonidos/musicaFondo2.wav");
		
		//Herramientas.play("sonidos/musicaFondo2.wav");
	   
		
		this.fondo = new ImageIcon("imagenes/Fondo.jpg").getImage();
		this.hechizo1 = new ImageIcon("imagenes/hechizo1.png").getImage();
		this.hechizo2 = new ImageIcon("imagenes/hechizo2.png").getImage();
		
		
		//botones del menu 
		botones.add(new BotonesMouse(700, 400, 150, 40, "Hechizo 1"));
		botones.add(new BotonesMouse(700, 450, 150, 40, "Hechizo 2"));
		
		
		
		
		gestorMurcielagos = new CreadorMurcielagos();
		
		rocas.add(new Roca(500, 300, entorno));
		rocas.add(new Roca(300, 200, entorno));
		
		gondolf = new Gondolf(400, 300, roca);
		
		murcielago= new Murcielago(100,200, 1, 2);
		
		reiniciarJuego= new Reinicio(entorno);
		
		
		// aca pueden entrar personajes, enemigos, obstaculos, puntos

		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();

	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {

		//entorno.dibujarImagen(fondo, 300, 300, 0);
		entorno.dibujarImagen(fondo, 300, 300, 0, 1.5);
		entorno.dibujarRectangulo(700, 300, 200, 600, 0, Color.DARK_GRAY); // menu en la part derecha de la pantalla 

		
		if(!reiniciarJuego.perdi()) {
		
		entorno.ancho();
		
		gondolf.mover(0, 0, entorno, roca, rocas);
		
		
		//entorno.escribirTexto("Maná", 620, 100);
		
//		
//		private final int TOTAL_MURCIELAGO= 50;
//		private final int MURCIELAGOS_X_OLEADA = 5;
//		
		// texto de vida y mana del personaje 
		entorno.cambiarFont("Arial", 18, Color.WHITE);
		entorno.escribirTexto("Vida", 620, 50);
		entorno.escribirTexto("Maná", 620, 100);
		
		
		
		//barras de vida y mana 

		entorno.dibujarRectangulo(700, 65, 140, 20, 0, Color.GRAY); 
		entorno.dibujarRectangulo(700, 115, 140, 20, 0, Color.GRAY); 

		entorno.dibujarRectangulo(700 - (70 - gondolf.getVida() * 0.7), 65, gondolf.getVida() * 1.4, 20, 0, Color.RED);
		entorno.dibujarRectangulo(700 - (70 - mana * 0.7), 115, mana * 1.4, 20, 0, Color.CYAN);

		
		entorno.cambiarFont("Arial", 12, Color.WHITE);
		entorno.escribirTexto("murcilagos muertos:" + murcielagosmuertos, 620, 200);
		
		
		int murcielagosVivos = gestorMurcielagos.getMurcielagosActivos().size();
		entorno.escribirTexto("murciélagos vivos: " + murcielagosVivos, 620, 250);
		
		entorno.escribirTexto("oleada:" + (murcielagosmuertos / MURCIELAGOS_X_OLEADA)  , 620, 150);
		
		
		
		
		gestorMurcielagos.actualizar(gondolf, entorno);
		gestorMurcielagos.dibujar(entorno);
		
		gondolf.dibujarPj(entorno);
		
		
		
		
		for(Roca roca : rocas) {
			
			roca.dibujar(entorno);
		}
		
		// Dibujar botones
		for (BotonesMouse boton : botones) {
			boton.dibujar(entorno);

		}


		
		if (gondolf.estaMuerto()) {
		    int oleada = murcielagosmuertos / MURCIELAGOS_X_OLEADA;
		    reiniciarJuego.setEstadisticas(murcielagosmuertos, oleada);
		    reiniciarJuego.setter(true);
		}
		}
		//roca.colisionConPj(gondolf);
		// Procesamiento de un instante de tiempo
		// ...

		else {
			reiniciarJuego.dibujar();
			
			
			if(reiniciarJuego.verificacion()) {
				
				reiniciarTodo();
				
				reiniciarJuego.setter(false);
			}
		}

		

		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			int mx = entorno.mouseX();
			int my = entorno.mouseY();

			boolean hizoClickEnBoton = false;

			for (BotonesMouse boton : botones) {
				if (boton.estaDentro(mx, my)) {
					hizoClickEnBoton = true;
					hechizoSeleccionado = boton.getTexto();
					System.out.println("Hechizo seleccionado: " + hechizoSeleccionado);
					break;
				}
			}

			// Si no clickeó ningún botón y había hechizo seleccionado, lanzar hechizo
			if (!hizoClickEnBoton && hechizoSeleccionado != null) {
				
				
				int radio = 100; // radio del hechizo
				

				ArrayList<Murcielago> activos = gestorMurcielagos.getMurcielagosActivos();
				
				

				for (int i = activos.size() - 1; i >= 0; i--) {
					Murcielago m = activos.get(i);
					double dx = m.getX() - mx;
					double dy = m.getY() - my;
					double distancia = Math.sqrt(dx * dx + dy * dy);
					
					
					if (distancia <= radio) {
						System.out.println("¡Murciélago alcanzado!");
						
						murcielagosmuertos  ++; 

						if (hechizoSeleccionado == "Hechizo 1") {
							//entorno.numeroDeTick() ;
							entorno.dibujarImagen( hechizo1, mx, my, 0, 0.2);
							m.restarVida(20);
							
						} 
							
						if (hechizoSeleccionado == "Hechizo 2" ) {
							//entorno.numeroDeTick() ;
							entorno.dibujarImagen( hechizo2, mx, my, 0, 0.2);
							m.restarVida(40);
						}

						if (murvida <= 0) {
							activos.remove(i);
							System.out.println("Murciélago eliminado");
						}
					}
				}

				// Gasto de maná si corresponde
				if (hechizoSeleccionado =="Hechizo 2") {
					mana = Math.max(mana - 20, 0);
				}

				hechizoSeleccionado = null; // reset
			}
		}
	}

		
	
	public void reiniciarTodo() {
		
		gondolf= new Gondolf(400, 300, roca);
		gestorMurcielagos =new CreadorMurcielagos();
		
		vida = 100;
		mana = 100;
		murcielagosmuertos = 0;
		
		rocas.clear();
		rocas.add(new Roca(500, 300, entorno));
		rocas.add(new Roca(300, 200, entorno));
		
		reiniciarJuego.setter(false);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
