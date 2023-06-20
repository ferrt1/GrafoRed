package interfaz;

import java.awt.EventQueue;


import java.awt.Font;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import grafos.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.Panel;


public class Menu {

	private JFrame frame;
	private JTextField textFieldMatrizTamanio;
	private JTextField textFieldNombre;
	private JTextField textFieldProvincia;
	private JTextField textFieldLatitud;
	private JTextField textFieldLongitud;
	private JMapViewer mapa;
	private Color colorFondo = new Color(255, 213, 90);
	private Color colorLetra = new Color(41, 50, 80);
	private Color colorVerde = new Color(109, 212, 126);
	private Font fuente = new Font("Segoe UI", Font.BOLD, 15);
	private Localidad localidad = new Localidad(null, null, 0, 0);
	private	Administracion administrador = new Administracion();

	private JTextField CostoPorKm;
	private JTextField Costo300Km;
	private JTextField CostoProvDistintas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frame.setVisible(true);
	}

	public void labelPreguntaLocalidades() {
		JLabel lblIntroduzcaElNumero_0 = new JLabel("Introduzca el numero de localidades a solicitar: ");
		lblIntroduzcaElNumero_0.setBounds(10, 117, 351, 89);
		lblIntroduzcaElNumero_0.setFont(fuente);
		lblIntroduzcaElNumero_0.setForeground(colorLetra);
		frame.getContentPane().add(lblIntroduzcaElNumero_0);


		JLabel lblIntroduzcaElNumero = new JLabel("Introduzca el costo por kilometro:");
		lblIntroduzcaElNumero.setForeground(new Color(41, 50, 80));
		lblIntroduzcaElNumero.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblIntroduzcaElNumero.setBounds(10, 233, 259, 89);
		frame.getContentPane().add(lblIntroduzcaElNumero);

		JLabel lblIntroduzcaElNumero_1 = new JLabel("Introduzca el porcentaje si supera los 300Km:");
		lblIntroduzcaElNumero_1.setForeground(new Color(41, 50, 80));
		lblIntroduzcaElNumero_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblIntroduzcaElNumero_1.setBounds(10, 333, 351, 89);
		frame.getContentPane().add(lblIntroduzcaElNumero_1);

		JLabel lblIntroduzcaElNumero_2 = new JLabel("Introduzca el costo por provincias distintas:");
		lblIntroduzcaElNumero_2.setForeground(new Color(41, 50, 80));
		lblIntroduzcaElNumero_2.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblIntroduzcaElNumero_2.setBounds(10, 447, 351, 89);
		frame.getContentPane().add(lblIntroduzcaElNumero_2);

	}

	public void textFieldNumeroDeLocalidades() {
		textFieldMatrizTamanio = new JTextField();
		textFieldMatrizTamanio.setBounds(406, 137, 100, 50);
		textFieldMatrizTamanio.setFont(fuente);
		textFieldMatrizTamanio.setColumns(10);
		textFieldMatrizTamanio.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMatrizTamanio.setBorder(null);
		frame.getContentPane().add(textFieldMatrizTamanio);


		CostoPorKm = new JTextField();
		CostoPorKm.setHorizontalAlignment(SwingConstants.CENTER);
		CostoPorKm.setFont(new Font("Segoe UI", Font.BOLD, 15));
		CostoPorKm.setColumns(10);
		CostoPorKm.setBorder(null);
		CostoPorKm.setBounds(406, 251, 100, 50);
		frame.getContentPane().add(CostoPorKm);

		Costo300Km = new JTextField();
		Costo300Km.setHorizontalAlignment(SwingConstants.CENTER);
		Costo300Km.setFont(new Font("Segoe UI", Font.BOLD, 15));
		Costo300Km.setColumns(10);
		Costo300Km.setBorder(null);
		Costo300Km.setBounds(406, 353, 100, 50);
		frame.getContentPane().add(Costo300Km);

		CostoProvDistintas = new JTextField();
		CostoProvDistintas.setHorizontalAlignment(SwingConstants.CENTER);
		CostoProvDistintas.setFont(new Font("Segoe UI", Font.BOLD, 15));
		CostoProvDistintas.setColumns(10);
		CostoProvDistintas.setBorder(null);
		CostoProvDistintas.setBounds(406, 467, 100, 50);
		frame.getContentPane().add(CostoProvDistintas);

	}



	public void limpiarPantalla() {
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}

	public void crearBotonListo() {
		JButton btnSubmit = new JButton("Listo");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarNumero(textFieldMatrizTamanio) && verificarCostos()) {
					limpiarPantalla();
					labelNombre();
					textFieldNombre();
					labelProvincia();
					textFieldProvincia();
					labelLatitud();
					textFieldLatitud();
					labelLongitud();
					textFieldLongitud();
					crearBotonCrearLocalidad();
					nuevoPanelParaMapa();
				}
			}
		});

		btnSubmit.setBounds(212, 569, 197, 79);
		btnSubmit.setBackground(colorVerde);
		btnSubmit.setBorderPainted(false);
		frame.getContentPane().add(btnSubmit);
		nuevoPanelParaMapa();
	}

	public void nuevoPanelParaMapa() {
		Panel panelMapa = new Panel();
		panelMapa.setBounds(563, 0, 586, 724);
		frame.getContentPane().add(panelMapa);
		panelMapa.setLayout(null);
		mapa = crearMapa();
		panelMapa.add(mapa);
	}

	private JMapViewer crearMapa() {
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 679, 724);
		mapa.setLayout(null);
		mapa.setZoomControlsVisible(false);
		enfocarArgentina(mapa);
		return mapa;
	}
	
	public void agregarMarcador(double x, double y, String localidad) {
		Coordinate cordenada = new Coordinate(x, y);
		MapMarker marcador = new MapMarkerDot(localidad, cordenada);
		marcador.getStyle().setBackColor(colorFondo);
		marcador.getStyle().setColor(colorFondo);
		mapa.addMapMarker(marcador);

	}

	public void agregarPoligono(Float x1, Float y1, Float x2, Float y2, String peso) {
		Coordinate cordenada1 = new Coordinate(x1, y1);
		Coordinate cordenada2 = new Coordinate(x2, y2);
		MapPolygon poligono = new MapPolygonImpl(peso, cordenada1, cordenada2, cordenada1);
		poligono.getStyle().setColor(colorLetra);

		mapa.addMapPolygon(poligono);
	}

	public void agregarAGM(Grafo grafo) {
		mapa.removeAllMapPolygons();
		mapa.removeAllMapMarkers();
		for(Localidad localidad:grafo.getLocalidades()) {
			this.agregarMarcador(localidad.getLatitud(), localidad.getLongitud(), localidad.getNombre());
		}
		for (ConectorLocalidades cable : grafo.getAristas()) {
			agregarPoligono(cable.getVertice1().getLatitud(), cable.getVertice1().getLongitud(),
					cable.getVertice2().getLatitud(), cable.getVertice2().getLongitud(),
					cable.getCosto().toString());
		}
	}

	private void enfocarArgentina(JMapViewer mapa) {
		Coordinate coordenadasArg = new Coordinate(-38.4161, -63.6167);
		mapa.setDisplayPosition(coordenadasArg, 5);
	}

	public void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldProvincia.setText("");
		textFieldLatitud.setText("");
		textFieldLongitud.setText("");
	}

	public void labelNombre() {
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setBounds(80, 150, 100, 50);
		lblNombre.setFont(fuente);
		lblNombre.setForeground(colorLetra);
		frame.getContentPane().add(lblNombre);
	}

	public void textFieldNombre() {
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(180, 150, 200, 50);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.setFont(fuente);
		textFieldNombre.setBorder(null);
		textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void labelProvincia() {
		JLabel lblProvincia = new JLabel("PROVINCIA:");
		lblProvincia.setBounds(80, 220, 100, 50);
		lblProvincia.setFont(fuente);
		lblProvincia.setForeground(colorLetra);

		frame.getContentPane().add(lblProvincia);
	}

	public void textFieldProvincia() {
		textFieldProvincia = new JTextField();
		textFieldProvincia.setBounds(180, 220, 200, 50);
		frame.getContentPane().add(textFieldProvincia);
		textFieldProvincia.setColumns(10);
		textFieldProvincia.setFont(fuente);
		textFieldProvincia.setBorder(null);
		textFieldProvincia.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void labelLatitud() {
		JLabel lblLatitud = new JLabel("LATITUD:");
		lblLatitud.setBounds(80, 290, 100, 50);
		lblLatitud.setFont(fuente);
		lblLatitud.setForeground(colorLetra);

		frame.getContentPane().add(lblLatitud);
	}

	public void textFieldLatitud() {
		textFieldLatitud = new JTextField();
		textFieldLatitud.setBounds(180, 290, 200, 50);
		frame.getContentPane().add(textFieldLatitud);
		textFieldLatitud.setColumns(10);
		textFieldLatitud.setFont(fuente);
		textFieldLatitud.setBorder(null);
		textFieldLatitud.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void labelLongitud() {
		JLabel lblLongitud = new JLabel("LONGITUD:");
		lblLongitud.setBounds(80, 360, 100, 50);
		lblLongitud.setFont(fuente);
		frame.getContentPane().add(lblLongitud);
		lblLongitud.setForeground(colorLetra);
	}

	public void textFieldLongitud() {
		textFieldLongitud = new JTextField();
		textFieldLongitud.setBounds(180, 360, 200, 50);
		frame.getContentPane().add(textFieldLongitud);
		textFieldLongitud.setColumns(10);
		textFieldLongitud.setFont(fuente);
		textFieldLongitud.setBorder(null);
		textFieldLongitud.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void crearBotonCrearLocalidad() {
		JButton btnCrearLocalidad = new JButton("Crear Localidad");
		btnCrearLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer maximoInteger = Integer.parseInt(textFieldMatrizTamanio.getText());
				if(verificarStringVacios(textFieldNombre) && 
						verificarStringVacios(textFieldProvincia) 
						&& verificarLatitud(textFieldLatitud) &&
						verificarLongitud(textFieldLongitud)) {
					String nombre = textFieldNombre.getText();
					String provincia = textFieldProvincia.getText();
					float latitud = (float) Double.parseDouble(textFieldLatitud.getText());
					float longitud = (float) Double.parseDouble(textFieldLongitud.getText());
					Localidad nuevaLocalidad = new Localidad(nombre, provincia, latitud, longitud);


					administrador.cargarLocalidad(textFieldNombre.getText(), textFieldProvincia.getText(), Float.parseFloat(textFieldLatitud.getText()), Float.parseFloat(textFieldLongitud.getText()));

					agregarLocalidadAlMapa(nuevaLocalidad);
					JOptionPane.showMessageDialog(frame, "Localidad creada exitosamente!");
					limpiarFormulario();
					localidad.agregarLocalidad(localidad.tamanio()+1, nuevaLocalidad);
				}
				
				if(verificarLocalidadesMaximas(maximoInteger, administrador.getLocalidadesCargadas().size())) {
					JOptionPane.showMessageDialog(frame, "Ha creado sus localidades!");
					limpiarPantalla();
					crearSeleccion();
					panelActualizado();	        

				}
			}

		});

		btnCrearLocalidad.setBounds(200, 500, 150, 70);
		btnCrearLocalidad.setBackground(colorVerde);
		btnCrearLocalidad.setFont(fuente);
		btnCrearLocalidad.setBorderPainted(false);
		frame.getContentPane().add(btnCrearLocalidad);
	}

	public void agregarLocalidadAlMapa(Localidad local) {
		Coordinate coordLocalidad = new Coordinate(local.getLatitud(), local.getLongitud());
		MapMarker marcador = new MapMarkerDot(local.getNombre(), coordLocalidad);
		marcador.getStyle().setColor(Color.YELLOW);
		mapa.addMapMarker(marcador);
	}

	private void panelActualizado() {
		Panel panelMapa = new Panel();
		panelMapa.setBounds(563, 0, 586, 724);
		frame.getContentPane().add(panelMapa);
		panelMapa.setLayout(null);
		panelMapa.add(mapa);
	}

	//	Tercera pantalla

	public void crearSeleccion() {
		JButton botonCalcular = new JButton("CALCULAR COSTO");

		botonCalcular.setBounds(206, 300, 190, 50);
		botonCalcular.setBackground(colorVerde);
		botonCalcular.setBorderPainted(false);
		botonCalcular.setFont(fuente);
		frame.getContentPane().add(botonCalcular);
		botonCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelPrecio();
			}
		});
	}

	public double calcularPrecioYMostrarArbol() {
		Grafo grafo = administrador.obtenerGrafoAGM();
		agregarAGM(grafo);
		return administrador.obtenerCostoTotalAGM();
	}

	public void labelPrecio() {
		JLabel lblSeleccion = new JLabel("El costo del AGM es:"+ calcularPrecioYMostrarArbol());
		lblSeleccion.setBounds(206, 400, 500, 50);
		frame.getContentPane().add(lblSeleccion);

		lblSeleccion.setFont(fuente);
		lblSeleccion.setForeground(colorLetra);
	}

	/* METODOS QUE HACEN VERIFIACIONES*/

	public boolean verificarCostos() {
		boolean resultado = true;
		try {
			administrador.setCostoPorKm(Double.parseDouble(CostoPorKm.getText()));
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "El costo ingresado no es un número válido");
			resultado = false;
		}
		try {
			double porcentaje = Double.parseDouble(Costo300Km.getText());
			if(porcentaje < 0 || porcentaje > 1) {
				JOptionPane.showMessageDialog(null, 
						"El porcentaje ingresado mayor a 300KM debe ser mayor o igual a 0 o menor a 2. Ej: 1.2");
				resultado = false;
			} else {
				administrador.setPorcentajeSupera300Km(porcentaje);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, 
					"El porcentaje ingresado mayor a 300KM no es un número válido");
			resultado = false;
		}
		try {
			administrador.setCostoProvinciaDistinta(Double.parseDouble(CostoProvDistintas.getText()));
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "El costo entre provincias ingresado no es un número válido");
			resultado = false;
		}

		return resultado;
	}

	public boolean verificarNumero(JTextField textField) {
		String valor = textField.getText();
		if (!valor.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "El valor ingresado no es un número válido");
			return false;
		}
		int numero = Integer.parseInt(valor);
		if (numero <= 0 || numero >= 100) {
			JOptionPane.showMessageDialog(null, "El número debe estar entre 1 y 99");
			return false;
		}
		if (numero == 0 || numero == 1) {
			JOptionPane.showMessageDialog(null, "El número no puede ser 0 o 1");
			return false;
		}

		return true;
	}

	public boolean verificarStringVacios(JTextField j) {
		if(j.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede tener nombre o provincia vacios");
			return false;
		}
		return true;
	}

	public boolean verificarLongitud(JTextField txtLongitud) {
		String longitudStr = txtLongitud.getText();
		if (longitudStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese la longitud del punto");
			return false;
		} else {
			try {
				double longitud = Double.parseDouble(longitudStr);
				if (longitud < -180 || longitud > 180) {
					JOptionPane.showMessageDialog(null, "La longitud debe estar entre -180 y 180 grados");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La longitud debe ser un número");
				return false;
			}
		}
		return true;
	}

	public boolean verificarLatitud(JTextField txtLatitud) {
		String latitudStr = txtLatitud.getText();
		if (latitudStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese la latitud del punto");
			return false;
		} else {
			try {
				double latitud = Double.parseDouble(latitudStr);
				if (latitud < -90 || latitud > 90) {
					JOptionPane.showMessageDialog(null, "La latitud debe estar entre -90 y 90 grados");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La latitud debe ser un número");
				return false;
			}
		}
		return true;
	}

	public boolean verificarLocalidadesMaximas(int maximo, int verificar) {
		return maximo == verificar;
	}


	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1165, 763);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(colorFondo);

		// Labels y text fields para los parámetros de la localidad

		labelPreguntaLocalidades();
		textFieldNumeroDeLocalidades();
		crearBotonListo();
	}
}
