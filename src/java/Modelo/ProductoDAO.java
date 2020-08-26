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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ProductoDAO {
     conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
     public List Listar() {
        String sql = "Select * from producto"; //esta variable guarda la sentencia SQL 
        List<Producto> lista = new ArrayList<>(); // inicializo un variable Lista de tipo empleado (SDTEmpleado)
        try {
            con = cn.Conexion(); //ABRO LA CONEXION A LA BASE DE DATOS
            ps = con.prepareStatement(sql); //AQUI ENTRA COMO PARAMETRO LA CONSULTA 
            rs = ps.executeQuery(); //EJECUTA LA SENTENCIA SQL
            while (rs.next()) {    // UTILIZA EL WHILE PARA HACER LA CONSULTA HASTA Q YA NO DEVUELVA RESULTADOS              
                Producto pro = new Producto();
                pro.setId(rs.getInt(1));
                pro.setDesPro(rs.getString(2));
                pro.setPrePro(rs.getDouble(3));  // en esta seccion de codigo va llenando el SDT (Lista) con lo q ejecua la sentencia  sql 
                pro.setStockPro(rs.getDouble(4));  // Importante lo numero ... ya q identifican la columna del valor que debe tomar
                pro.setEstPro(rs.getString(5));
                lista.add(pro); // Aqui va agregando todos los items a la lista
            }

        } catch (Exception e) {
        }
        return lista;
    }
    
     public Producto ListaId(int id){
     String sql = "Select * from producto where IdProducto="+id;
     Producto pro = new Producto();
     
         try {
             con = cn.Conexion();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
             while (rs.next()) {                 
                 pro.setDesPro(rs.getString(2));
                 pro.setPrePro(rs.getDouble(3));
                 pro.setStockPro(rs.getDouble(4));
                 pro.setEstPro(rs.getString(5));
             }
         } catch (Exception e) {
         }
     return pro;
     }
    
     public int Agregar(Producto pr){ // aqui es como parametro de entrada un sdt de tipo producto
     String sql ="Insert into producto (Nombres,Precio,Stock,Estado) values(?,?,?,?)";
         try {
             con = cn.Conexion();
             ps = con.prepareCall(sql);
             ps.setString(1, pr.getDesPro()); //aqui va identificando todas las columans donde debe ingresar los valores
            ps.setDouble(2, pr.getPrePro());
            ps.setDouble(3, pr.getStockPro()); //utiliza el metodo get para poner los valores dentro de la base de datos 
            ps.setString(4, pr.getEstPro());
            ps.executeUpdate();
         } catch (Exception e) {
         }
     return r;
     }
     
     public int Actualizar (Producto pr){ // aqui es como parametro de entrada un sdt de tipo producto
     String sql ="update producto set Nombres =?,Precio=?,Stock=?,Estado=? where IdProducto = ?";
         try {
             con = cn.Conexion();
             ps = con.prepareCall(sql);
            ps.setString(1, pr.getDesPro()); //aqui va identificando todas las columans donde debe ingresar los valores
            ps.setDouble(2, pr.getPrePro());
            ps.setDouble(3, pr.getStockPro()); //utiliza el metodo get para obtener los datos del objeto Producto PR
            ps.setString(4, pr.getEstPro());
            ps.setInt(5, pr.getId());
            ps.executeUpdate();
         } catch (Exception e) {
         }
     return r;
     }
     
         public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
