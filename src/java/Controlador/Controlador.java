/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    int ide;
    Producto pr = new Producto();
    ProductoDAO epr = new ProductoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        System.out.println("MENU**" + menu);
        System.out.println("ACCION**" + accion);
        

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List lista = epr.Listar(); //cargo la variable con la lista de empleados
                    request.setAttribute("producto", lista); //envio la variable lista
                    System.out.println("RECURSOS"+lista.size());
                    break;

                case "Agregar":
                    String desPro = request.getParameter("txtDes");
                    double precio = Double.parseDouble(request.getParameter("txtPre"));
                    double stock = Double.parseDouble(request.getParameter("txtStock"));
                    String est = request.getParameter("txtEst");

                    pr.setDesPro(desPro);
                    pr.setPrePro(precio);
                    pr.setStockPro(stock);
                    pr.setEstPro(est); //AQUI AGREGO LOS VALORES A LA VARIABLE PRODUCTO (SDT)

                    epr.Agregar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response); // en esta linea manda a regrescar los datos en la grilla
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Producto p = epr.ListaId(ide);
                    request.setAttribute("productos", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String des1 = request.getParameter("txtDes");
                    double pre1 = Double.parseDouble(request.getParameter("txtPre"));
                    double stock1 = Double.parseDouble(request.getParameter("txtStock"));
                    String est1 = request.getParameter("txtEst");

                    pr.setDesPro(des1);
                    pr.setPrePro(pre1);
                    pr.setStockPro(stock1);
                    pr.setEstPro(est1); //AQUI AGREGO LOS VALORES A LA VARIABLE EMPLEADO (SDT)
                    pr.setId(ide);
                    epr.Actualizar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    epr.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();

            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("RegVenta")) {
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }

        if (menu.equals("Empleado")) {
            try {
                switch (accion) {
                    case "Listar":
                        List lista = edao.Listar(); //cargo la variable con la lista de empleados
                        request.setAttribute("empleados", lista); //envio la variable lista
                        break;

                    case "Agregar":
                        String dni = request.getParameter("txtDni");
                        String nom = request.getParameter("txtNombre");
                        String tel = request.getParameter("txtTelefono");
                        String est = request.getParameter("txtEstado");
                        String usu = request.getParameter("txtUsuario");

                        em.setDni(dni);
                        em.setEstado(est);
                        em.setNom(nom);
                        em.setTel(tel); //AQUI AGREGO LOS VALORES A LA VARIABLE EMPLEADO (SDT)
                        em.setUser(usu);

                        edao.Agregar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response); // en esta linea manda a regrescar los datos en la grilla
                        break;
                    case "Editar":
                        ide = Integer.parseInt(request.getParameter("id"));
                        Empleado e = edao.ListId(ide);
                        request.setAttribute("empleado", e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1 = request.getParameter("txtDni");
                        String nom1 = request.getParameter("txtNombre");
                        String tel1 = request.getParameter("txtTelefono");
                        String est1 = request.getParameter("txtEstado");
                        String usu1 = request.getParameter("txtUsuario");

                        em.setDni(dni1);
                        em.setEstado(est1);
                        em.setNom(nom1);
                        em.setTel(tel1); //AQUI AGREGO LOS VALORES A LA VARIABLE EMPLEADO (SDT)
                        em.setUser(usu1);
                        em.setId(ide);
                        edao.Actualizar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "delete":
                        ide = Integer.parseInt(request.getParameter("id"));
                        edao.delete(ide);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();

                }

                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
