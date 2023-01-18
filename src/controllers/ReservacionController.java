package controllers;

import java.util.List;

import dao.ReservacionDAO;
import factory.ConnectionFactory;
import modelo.Reservaciones;
public class ReservacionController {
    private ReservacionDAO reservacionDAO;

    public ReservacionController() {
        ConnectionFactory factory = new ConnectionFactory();
        this.reservacionDAO = new ReservacionDAO(factory.recuperaConexion());
    }

    public List<Reservaciones> listar() {
        return this.reservacionDAO.listar();
    }
    
    public void guardar(Reservaciones reservacion) {
        reservacionDAO.guardar(reservacion);
    }
    public int eliminar(Integer id) {
        return reservacionDAO.eliminar(id);
    }
    
    public int modificar(Integer id, String fechaEntrada ,String fechaSalida,Integer valor,String formaPago) {
        return reservacionDAO.modificar(id, fechaEntrada ,fechaSalida,valor,formaPago);
    }

	public List<Reservaciones> listarOnly(String txtFiltado) {
        return this.reservacionDAO.listarOnly(txtFiltado);
	}
}
