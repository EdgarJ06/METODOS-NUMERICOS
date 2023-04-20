package funciones;

/**
 *
 * @author eroda
 */
public class ModeloRaicesMultiples {
    
    private String funcion;
    private String funcionDerivada;
    private String fSegundaDerivada;
    private double Xi;
    private double ETolerancia;

    public ModeloRaicesMultiples() {
    }

    public ModeloRaicesMultiples(String funcion, String funcionDerivada, String fSegundaDerivada, double Xi, double ETolerancia) {
        this.funcion = funcion;
        this.funcionDerivada = funcionDerivada;
        this.fSegundaDerivada = fSegundaDerivada;
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

    public String getfSegundaDerivada() {
        return fSegundaDerivada;
    }

    public void setfSegundaDerivada(String fSegundaDerivada) {
        this.fSegundaDerivada = fSegundaDerivada;
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
