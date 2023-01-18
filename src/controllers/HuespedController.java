package controllers;

import java.util.List;
import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huespedes;

public class HuespedController {
    private HuespedDAO huespedDAO;

    public HuespedController() {
        ConnectionFactory factory = new ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
    }
    public List<Huespedes> listar() {
        return this.huespedDAO.listar();
    }
    
    public void guardar(Huespedes huesped) { 
        huespedDAO.guardar(huesped);
    }

    public Integer obtenerID (){
        return this.huespedDAO.obtenerID();
    } 
    public int modificar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono) {
        return huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono);
    }

    public int eliminar(Integer id) {
        return huespedDAO.eliminar(id);
    }
    
	public List<Huespedes> listarOnly(String txtFiltado) {
		return this.huespedDAO.listarOnly(txtFiltado);
	}
}
