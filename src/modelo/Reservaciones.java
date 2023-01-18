package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.Reservaciones;

public class Reservaciones {
    private Integer id;

    private String fechaEntrada;

    private String fechaSalida;

    private Integer valor;
    
    private String formaPago;

	private List<Huespedes> huesped = new ArrayList<>();

	public Reservaciones(Integer id, String fechaEntrada, String fechaSalida, Integer valor, String formaPago) {
		this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida ;
        this.valor = valor;
        this.formaPago = formaPago;
    }
	public Reservaciones(Integer id){
		this.id = id;
	}

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Override
    public String toString() {
        return String.format(
                "{ id: %d, fechaEntrada: %s, fechaSalida: %s, valor: %d, formaPago: %s }",
                this.id, this.fechaEntrada, this.fechaSalida, this.valor, this.formaPago);
    }

	public void agregar(Huespedes huesped) {
        this.huesped.add(huesped);
    }

	public List<Huespedes> getHuesped() {
		return this.huesped;
	}

}
