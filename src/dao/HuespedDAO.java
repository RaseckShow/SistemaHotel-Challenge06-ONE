package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;

public class HuespedDAO {
    private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huespedes huesped) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO huespedes "
                        + "(nombre, apellido, fechaNacimiento, nacionalidad, telefono , reservacionID)"
                        + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();
                try (resultSet) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Huespedes> listar() {
        List<Huespedes> resultado = new ArrayList<>();
        try {
            String sql = "SELECT HuespedID, Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, ReservacionID FROM huespedes";            
            final PreparedStatement statement = con.prepareStatement(sql);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huespedes(
                                resultSet.getInt("HuespedID"),
                                resultSet.getString("Nombre"),
                                resultSet.getString("Apellido"),
                                resultSet.getString("FechaNacimiento"),
                                resultSet.getString("Nacionalidad"),
                                resultSet.getString("Telefono"),
                                resultSet.getInt("ReservacionID")));
                    }return resultado;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Integer obtenerID(){
        try {
            String sqlID = "SELECT max(reservacionid) FROM reservaciones";
            
            final PreparedStatement statement = con.prepareStatement(sqlID);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery(); 
                try (resultSet) {
                    if (resultSet.next()) {
                    }
                    return resultSet.getInt(1) + 1;
                }
            }
        } catch (SQLException e) {  
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET "
                    + " HuespedID = ?,"
                    + " Nombre = ?,"
                    + " Apellido = ?,"
                    + " FechaNacimiento = ?,"
                    + " Nacionalidad = ?,"
                    + " Telefono = ?"
                    + " WHERE HuespedID = ?");

            
            try (statement) {
            	statement.setInt(1, id);
                statement.setString(2, nombre);
                statement.setString(3, apellido);
                statement.setString(4, fechaNacimiento);
                statement.setString(5, nacionalidad);
                statement.setString(6, telefono);
                statement.setInt(7, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE HuespedID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> listarOnly(String txtFiltado) {
        List<Huespedes> resultado = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM huespedes WHERE HuespedID='"+ txtFiltado +"' or Nombre='"+txtFiltado+"' or Apellido='"+txtFiltado+"' or Nacionalidad='"+txtFiltado+"' or Telefono='"+txtFiltado+"'" ;
            final PreparedStatement statement = con.prepareStatement(sql);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huespedes(
                                resultSet.getInt("HuespedID"),
                                resultSet.getString("Nombre"),
                                resultSet.getString("Apellido"),
                                resultSet.getString("FechaNacimiento"),
                                resultSet.getString("Nacionalidad"),
                                resultSet.getString("Telefono"), 
                                null));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
