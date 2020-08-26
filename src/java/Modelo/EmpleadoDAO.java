/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class EmpleadoDAO {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Empleado validar(String user, String dni) {
        Empleado em = new Empleado();
        String sql = "select * from empleado where User=? and Dni=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setUser(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }

        } catch (Exception e) {
        }
        return em;
    }

    public List Listar() {
        String sql = "Select * from Empleado"; //esta variable guarda la sentencia SQL 
        List<Empleado> lista = new ArrayList<>(); // inicializo un variable Lista de tipo empleado (SDTEmpleado)
        try {
            con = cn.Conexion(); //ABRO LA CONEXION A LA BASE DE DATOS
            ps = con.prepareStatement(sql); //AQUI ENTRA COMO PARAMETRO LA CONSULTA 
            rs = ps.executeQuery(); //EJECUTA LA SENTENCIA SQL
            while (rs.next()) {    // UTILIZA EL WHILE PARA HACER LA CONSULTA HASTA Q YA NO DEVUELVA RESULTADOS              
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));  // en esta seccion de codigo va llenando el SDT (Lista) con lo q ejecua la sentencia  sql 
                em.setTel(rs.getString(4));  // Importante lo numero ... ya q identifican la columna del valor que debe tomar
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em); // Aqui va agregando todos los items a la lista
            }

        } catch (Exception e) {
        }
        return lista;
    }

    public Empleado ListId(int id) throws SQLException {
        Empleado emp = new Empleado();
        String sql = "Select * from Empleado where IdEmpleado=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));
            }
        } catch (Exception e) {
        }
        return emp;
    }

    public int Agregar(Empleado em) {
        String sql = "INSERT INTO empleado (Dni, Nombres, Telefono, Estado, User) values(?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni()); //aqui va identificando todas las columans donde debe ingresar los valores
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel()); //utiliza el metodo get para poner los valores dentro de la base de datos 
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public int Actualizar(Empleado em) {
        String sql = "update empleado set Dni=?,Nombres=?,Telefono=?,Estado=?,User=? where IdEmpleado=? ";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni()); //aqui va identificando todas las columans donde debe ingresar los valores
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel()); //utiliza el metodo get para poner los valores dentro de la base de datos 
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setInt(6, em.getId()); // va al final por el orden de los *?*
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;

    }

    public void delete(int id) {
        String sql = "delete from empleado where IdEmpleado=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
