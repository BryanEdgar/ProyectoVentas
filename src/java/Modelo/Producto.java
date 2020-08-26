/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class Producto {
    int Id;
    String desPro;
    double  prePro;
    double StockPro;
    String EstPro;

    public Producto() {
    }

    public Producto(int Id, String desPro, double prePro, double StockPro, String EstPro) {
        this.Id = Id;
        this.desPro = desPro;
        this.prePro = prePro;
        this.StockPro = StockPro;
        this.EstPro = EstPro;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDesPro() {
        return desPro;
    }

    public void setDesPro(String desPro) {
        this.desPro = desPro;
    }

    public double getPrePro() {
        return prePro;
    }

    public void setPrePro(double prePro) {
        this.prePro = prePro;
    }

    public double getStockPro() {
        return StockPro;
    }

    public void setStockPro(double StockPro) {
        this.StockPro = StockPro;
    }

    public String getEstPro() {
        return EstPro;
    }

    public void setEstPro(String EstPro) {
        this.EstPro = EstPro;
    }
    
    
}
