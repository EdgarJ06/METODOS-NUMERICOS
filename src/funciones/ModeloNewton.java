package funciones;

/**
 *
 * @author eroda
 */
public class ModeloNewton {
    
    private String funcion;
    private String funcionDerivada;
    private double Xi;
    private double ETolerancia;

    public ModeloNewton() {
    }

    public ModeloNewton(String funcion, String funcionDerivada, double Xi, double ETolerancia) {
        this.funcion = funcion;
        this.funcionDerivada = funcionDerivada;
        this.Xi = Xi;
        this.ETolerancia = ETolerancia;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getFuncionDerivada() {
        return funcionDerivada;
    }

    public void setFuncionDerivada(String funcionDerivada) {
        this.funcionDerivada = funcionDerivada;
    }

    public double getXi() {
        return Xi;
    }

    public void setXi(double Xi) {
        this.Xi = Xi;
    }

    public double getETolerancia() {
        return ETolerancia;
    }

    public void setETolerancia(double ETolerancia) {
        this.ETolerancia = ETolerancia;
    }    
}
