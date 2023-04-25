package funciones;

/**
 *
 * @author eroda
 */
public class ModeloMuller {
    
    private String funcion;
    private double Xo;
    private double X1;
    private double X2;
    private double ETolerancia;

    public ModeloMuller() {
    }

    public ModeloMuller(String funcion, double Xo, double X1, double X2, double ETolerancia) {
        this.funcion = funcion;
        this.Xo = Xo;
        this.X1 = X1;
        this.X2 = X2;
        this.ETolerancia = ETolerancia;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public double getXo() {
        return Xo;
    }

    public void setXo(double Xo) {
        this.Xo = Xo;
    }

    public double getX1() {
        return X1;
    }

    public void setX1(double X1) {
        this.X1 = X1;
    }

    public double getX2() {
        return X2;
    }

    public void setX2(double X2) {
        this.X2 = X2;
    } 

    public double getETolerancia() {
        return ETolerancia;
    }

    public void setETolerancia(double ETolerancia) {
        this.ETolerancia = ETolerancia;
    }
}
