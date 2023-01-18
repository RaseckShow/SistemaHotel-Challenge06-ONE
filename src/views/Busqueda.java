package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import controllers.ReservacionController;
import controllers.HuespedController;
import modelo.Reservaciones;
import modelo.Huespedes;




public class Busqueda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes, tbReservas;
	private DefaultTableModel modeloH, modeloR;
	private JLabel labelAtras, labelExit;
	private JButton btnEditar, btnEliminar, btnBuscar;
	
	public ReservacionController reservacionController = new ReservacionController();
	private HuespedController huespedController = new HuespedController();
	public String seleccion = "";
	public String seleccionTable;
	
	int xMouse, yMouse;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {     
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		
		btnEliminar = new JButton();
		btnEliminar.setForeground(new Color(33, 54, 53));
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(33, 54, 53));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setBackground(new Color(33, 54, 53));
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(new Color(255, 255, 255));
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);


		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionTable = "Reservaciones";
			}
		});
		panel.addTab("Reservaciones", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modeloR = (DefaultTableModel) tbReservas.getModel();
		modeloR.addColumn("Numero de Reserva");
		modeloR.addColumn("Fecha Check In");
		modeloR.addColumn("Fecha Check Out");
		modeloR.addColumn("Valor");
		modeloR.addColumn("Forma de Pago");
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionTable = "Reservaciones";
			}
		});
		
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);   
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
        labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				btnexit.setBackground(Color.white);
        labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		btnBuscar = new JButton();
		btnBuscar.setLayout(null);
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setBounds(748, 125, 122, 35);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnBuscar.add(lblBuscar);
		
		btnEditar = new JButton();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		setResizable(false);
		
		configurarAccionesDelFormulario();
		cargarTabla();
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
    xMouse = evt.getX();
    yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void configurarAccionesDelFormulario() {
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				limpiarTabla();
				cargarTabla();
				System.out.println("BOTON EDITAR");
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				limpiarTabla();
				cargarTabla();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				filtrar();
			}
		});
	}

	private void limpiarTabla() {
		modeloH.setRowCount(0);
		modeloR.setRowCount(0);
	}

    private void cargarTabla() {

		ReservacionController reservacionController = new ReservacionController();
        List<Reservaciones> reservaciones = reservacionController.listar();
        reservaciones.forEach(reservacion-> modeloR.addRow(
            new Object[] {
                reservacion.getId(),
                reservacion.getFechaEntrada(),
                reservacion.getFechaSalida(),
                reservacion.getValor(),
                reservacion.getFormaPago() 
                }
		));

		HuespedController huespedController = new HuespedController();
		List<Huespedes> huespedes = huespedController.listar();
		huespedes.forEach(huesped-> modeloH.addRow(
			new Object[] {
				huesped.getId(),
				huesped.getNombre(),
				huesped.getApellido(),
				huesped.getFechaNacimiento(), 
				huesped.getNacionalidad(), 
				huesped.getTelefono(),
				huesped.getIdReserva()
			}
		));
	}	 

	private boolean tieneFilaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() != 0 || tabla.getSelectedColumnCount() != 0;
	}

	private void modificar() {
		if (tbHuespedes.isVisible()) {
			if (!tieneFilaElegida(tbHuespedes) || tbHuespedes.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, selecciona un item!");
				tbHuespedes.clearSelection();
				tbReservas.clearSelection();
				return;
			} else {
				Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				String nombre = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
				String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2);
				String fechaNacimiento = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3);
				String nacionalidad = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4);
				String telefono = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5);
				if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar?") == 0) {
					huespedController.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono);
					modeloH.setValueAt(tbHuespedes,tbHuespedes.getSelectedRowCount(),tbHuespedes.getSelectedColumnCount());
					JOptionPane.showMessageDialog(this, "Edicion exitosa!");
				}
			}
		}

		if (tbReservas.isVisible()) {
			if (!tieneFilaElegida(tbReservas) || tbReservas.getSelectedRowCount() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, selecciona un item!");
				tbHuespedes.clearSelection();
				tbReservas.clearSelection();
				return;
			} else {
				Integer id = Integer.valueOf(modeloR.getValueAt(tbReservas.getSelectedRow(), 0).toString());
				String fechaEntrada = (String) modeloR.getValueAt(tbReservas.getSelectedRow(), 1);
				String fechaSalida = (String) modeloR.getValueAt(tbReservas.getSelectedRow(), 2);
				Integer valor = Integer.valueOf(modeloR.getValueAt(tbReservas.getSelectedRow(), 3).toString());
				String formaPago = (String) modeloR.getValueAt(tbReservas.getSelectedRow(), 4);
				if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar?") == 0) {
					reservacionController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
					modeloR.setValueAt(tbReservas,tbReservas.getSelectedRowCount(),tbReservas.getSelectedColumnCount());
					JOptionPane.showMessageDialog(this, "Edicion exitosa!");
				}
			}
		} 
	}

	private void eliminar() {
		if (tbHuespedes.isVisible()) {
			if (!tieneFilaElegida(tbHuespedes) || tbHuespedes.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, selecciona un item!");
				tbHuespedes.clearSelection();
				tbReservas.clearSelection();
				return;
			} else {
				int filaSeleccionada = tbHuespedes.getSelectedRow();
				int idHuesped = Integer.parseInt(tbHuespedes.getValueAt(filaSeleccionada, 0).toString());
				if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar?") == 0) {
					huespedController.eliminar(idHuesped);
					modeloH.removeRow(tbHuespedes.getSelectedRow());
					JOptionPane.showMessageDialog(this, "Se eliminó con éxito");
				}
			}
		}

		if (tbReservas.isVisible()) {
			if (!tieneFilaElegida(tbReservas) || tbReservas.getSelectedRowCount() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, selecciona un item!");
				tbHuespedes.clearSelection();
				tbReservas.clearSelection();
				return;
			} else {
				int filaSeleccionada = tbReservas.getSelectedRow();
				int idReserva = Integer.parseInt(tbReservas.getValueAt(filaSeleccionada, 0).toString());
				if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar?") == 0) {
					reservacionController.eliminar(idReserva);
					modeloR.removeRow(tbReservas.getSelectedRow());
					JOptionPane.showMessageDialog(this, "Se eliminó con éxito");
				}
			}
		}
	}

	private void filtrar() {    	
		String txtFiltado = txtBuscar.getText();
		Boolean validar = false;
		if (txtFiltado.length() != 0) {
			limpiarTabla();
			validar = true;
			List<Reservaciones> reservaCoincidencia = this.reservacionController.listarOnly(txtFiltado);
			reservaCoincidencia.forEach(reserva-> modeloR.addRow(
				new Object[] {
					reserva.getId(),
					reserva.getFechaEntrada(),
					reserva.getFechaSalida(),
					reserva.getValor(),
					reserva.getFormaPago() 
				}
			));
	
			List<Huespedes> huespedesCoincidencia = this.huespedController.listarOnly(txtFiltado);    
			huespedesCoincidencia.forEach(huesped-> modeloH.addRow(	        		
				new Object[] {
					huesped.getId(),
					huesped.getNombre(),
					huesped.getApellido(),
					huesped.getFechaNacimiento(), 
					huesped.getNacionalidad(), 
					huesped.getTelefono(),
					huesped.getIdReserva()
				}
			));
			return;
		}
		if (!validar) {
			cargarTabla();
		}
	}	    
}
