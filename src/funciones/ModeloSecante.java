package funciones;

/**
 *
 * @author eroda
 */
public class ModeloSecante {
    
    String funcion;
    double Xi;
    double Xo;
    double ETolerancia;

    public ModeloSecante() {
    }

    public ModeloSecante(String funcion, double Xi, double Xo, double ETolerancia) {
        this.funcion = funcion;
        this.Xi = Xi;
        this.Xo = Xo;
        this.ETolerancia = ETolerancia;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public double getXi() {
        return Xi;
    }

    public void setXi(double Xi) {
        this.Xi = Xi;
    }

    public double getXo() {
        return Xo;
    }

    public void setXo(double Xo) {
        this.Xo = Xo;
    }

    public double getETolerancia() {
        return ETolerancia;
    }

    public void setETolerancia(double ETolerancia) {
        this.ETolerancia = ETolerancia;
    }
    
    
}
