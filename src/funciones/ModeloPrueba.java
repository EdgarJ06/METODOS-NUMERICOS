package funciones;

import javax.swing.JOptionPane;

/**
 *
 * @author eroda
 */
public class ModeloPrueba {
    
    int valorA;
    int ValorB;

    public ModeloPrueba() {
    }

    public ModeloPrueba(int valorA, int ValorB) {
        this.valorA = valorA;
        this.ValorB = ValorB;
    }

    public int getValorA() {
        return valorA;
    }

    public void setValorA(int valorA) {
        this.valorA = valorA;
    }

    public int getValorB() {
        return ValorB;
    }

    public void setValorB(int ValorB) {
        this.ValorB = ValorB;
    }
    
    public void MuestraDatos(){
        JOptionPane.showMessageDialog(null, this.valorA);
        JOptionPane.showMessageDialog(null, this.ValorB);
    }    
}
