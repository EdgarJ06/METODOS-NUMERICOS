/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

/**
 *
 * @author eroda
 */
public class ModeloBiseccion {
    private String funcion;
    private double a;
    private double b;
    private double ETolerancia;

    public ModeloBiseccion() {
    }

    public ModeloBiseccion(String funcion, double a, double b, double ETolerancia) {
        this.funcion = funcion;
        this.a = a;
        this.b = b;
        this.ETolerancia = ETolerancia;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getETolerancia() {
        return ETolerancia;
    }

    public void setETolerancia(double ETolerancia) {
        this.ETolerancia = ETolerancia;
    }
    
    
    
}
