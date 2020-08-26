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
public class ClienteDAO {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public List Listar() {
        String sql = "Select * from cliente"; //esta variable guarda la sentencia SQL 
        List<Cliente> lista = new ArrayList<>(); // inicializo un variable Lista de tipo empleado (SDTEmpleado)
        try {
            con = cn.Conexion(); //ABRO LA CONEXION A LA BASE DE DATOS
            ps = con.prepareStatement(sql); //AQUI ENTRA COMO PARAMETRO LA CONSULTA 
            rs = ps.executeQuery(); //EJECUTA LA SENTENCIA SQL
            while (rs.next()) {    // UTILIZA EL WHILE PARA HACER LA CONSULTA HASTA Q YA NO DEVUELVA RESULTADOS              
                Cliente cli = new Cliente();
                cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));  // en esta seccion de codigo va llenando el SDT (Lista) con lo q ejecua la sentencia  sql 
                cli.setDir(rs.getString(4));  // Importante lo numero ... ya q identifican la columna del valor que debe tomar
                cli.setEst(rs.getString(5));
                lista.add(cli); // Aqui va agregando todos los items a la lista
            }

        } catch (Exception e) {
        }
        return lista;
    }
    
    public Cliente ListId(int id)  {
        Cliente cli = new Cliente();
        String sql = "Select * from cliente where Idcliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEst(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return cli;
    }
    
    public int Agregar(Cliente cl) {
        String sql = "INSERT INTO cliente (Dni, Nombres, Direccion, Estado) values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni()); //aqui va identificando todas las columans donde debe ingresar los valores
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir()); //utiliza el metodo get para poner los valores dentro de la base de datos 
            ps.setString(4, cl.getEst());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
        public int Actualizar(Cliente cl) {
        String sql = "update cliente set Dni=?,Nombres=?,Direccion=?,Estado=? where IdCliente=? ";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni()); //aqui va identificando todas las columans donde debe ingresar los valores
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir()); //utiliza el metodo get para poner los valores dentro de la base de datos 
            ps.setString(4, cl.getEst());
            ps.setInt(5, cl.getId()); // va al final por el orden de los *?*
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;

    }
            public void delete(int id) {
        String sql = "delete from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
        
}
