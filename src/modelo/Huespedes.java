package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;

public class Huespedes {
    private Integer id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer idReserva;
    
    private List<Huespedes> huesped = new ArrayList<>();
	// private Integer reservaID;

	public Huespedes(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }
	
	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
			"{ id: %d, nombre: %s, apellido: %s, fechaNacimiento: %s, nacionalidad: %s, telefono: %s}",
			this.id, this.nombre,this.apellido, this.fechaNacimiento, this.nacionalidad, this.telefono);
    }

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Huespedes> getHuesped() {
		return this.huesped;
	}
	// public void setHuesped(List<Huespedes> huesped) {
	// 	this.huesped = huesped;
	// }

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Integer getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

}
