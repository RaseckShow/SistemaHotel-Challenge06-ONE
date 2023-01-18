package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reservaciones;

public class ReservacionDAO {
    private Connection con;

    public ReservacionDAO(Connection con) {
        this.con = con;
    }
    
    public List<Reservaciones> listar() {
        List<Reservaciones> resultado = new ArrayList<>();
        try {
            String sql = "SELECT ReservacionID, FechaEntrada, FechaSalida, Valor, FormaPago FROM reservaciones";
            final PreparedStatement statement = con.prepareStatement(sql);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                // statement.execute();
                // final ResultSet resultSet = statement.getResultSet();
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reservaciones(
                                resultSet.getInt("ReservacionID"),
                                resultSet.getString("FechaEntrada"),
                                resultSet.getString("FechaSalida"),
                                resultSet.getInt("Valor"),
                                resultSet.getString("FormaPago")));
                    }return resultado;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void guardar(Reservaciones reservacion) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO reservaciones "
                        + "(fechaEntrada, fechaSalida, valor, formaPago)"
                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, reservacion.getFechaEntrada());
                statement.setString(2, reservacion.getFechaSalida());
                statement.setInt(3, reservacion.getValor());
                statement.setString(4, reservacion.getFormaPago());
    
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        reservacion.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer id, String fechaEntrada ,String fechaSalida,Integer valor,String formaPago) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE reservaciones SET "
                    + " ReservacionID = ?,"
                    + " FechaEntrada = ?,"
                    + " FechaSalida = ?,"
                    + " Valor = ?,"
                    + " FormaPago = ?"
                    + " WHERE ReservacionID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.setString(2, fechaEntrada);
                statement.setString(3, fechaSalida);
                statement.setInt(4, valor);
                statement.setString(5, formaPago);
                statement.setInt(6, id);
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
            final PreparedStatement statement = con.prepareStatement("DELETE FROM reservaciones WHERE ReservacionID = ?");

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
    
    public List<Reservaciones> listarOnly(String txtFiltado) {
        List<Reservaciones> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservaciones WHERE ReservacionID='"+ txtFiltado +"' or Valor='"+txtFiltado+"' or FormaPago='"+txtFiltado+"' ";
            final PreparedStatement statement = con
                    .prepareStatement(sql);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reservaciones(
                                resultSet.getInt("ReservacionID"),
                                resultSet.getString("fechaEntrada"),
                                resultSet.getString("fechaSalida"),
                                resultSet.getInt("valor"),
                                resultSet.getString("formaPago")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
