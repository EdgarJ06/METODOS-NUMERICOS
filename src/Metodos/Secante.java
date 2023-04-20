
package Metodos;

import funciones.ModeloSecante;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.JEP;

/**
 *
 * @author eroda
 */
public class Secante {

    private FormSecante fs;
    JEP jep = new JEP();
    DefaultTableModel modelo = new DefaultTableModel();
    ModeloSecante ms = new ModeloSecante();
    
    //DECLARO LAS VARIABLES NECESARIAS PARA MANIPULAR EL VALOR DE MIS DATOS
    private double r = 0.0;
    int iterador = 1;
    private double Xo;
    private double Xi;
    private double funXo;
    private double funXi;
    private double XiR;
    private double ETolerancia;

    public void prueba(){
        
        JOptionPane.showMessageDialog(null, ms.getFuncion());
        JOptionPane.showMessageDialog(null, ms.getXi());
        JOptionPane.showMessageDialog(null, ms.getXo());
    }
    //METODO QUE EVALUA LA FUNCION ENVIADA Y EL PARAMETRO A ENCONTRAR
    public double f(double x) {
        jep = new JEP();
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addVariable("x", x);
        jep.parseExpression(ms.getFuncion());
        r = jep.getValue();

        return r;
    }

    //METODO DONDE SE APLICA LA LOGICA NECESARIA PARA RESOLVER EL METODO DE LA SECANTE
    public void Evaluar() {
        do {

            Xi = ms.getXi();
            Xo = ms.getXo();

            funXi = f(Xi);
            funXo = f(Xo);

            XiR = Xi - ((funXi) * (Xo - Xi) / (funXo - funXi));

            if (iterador == 1) {

            } else {
                ETolerancia = (Math.abs(Xi - XiR));
            }

            modelo = (DefaultTableModel) fs.TablaSecante.getModel();
            Object[] ob = new Object[7];
            ob[0] = iterador;
            ob[1] = String.format("%.4f", Xo);
            ob[2] = String.format("%.4f", Xi);
            ob[3] = String.format("%.4f", funXo);
            ob[4] = String.format("%.4f", funXi);
            ob[5] = String.format("%.4f", XiR);
            ob[6] = String.format("%.4f", ETolerancia);
            modelo.addRow(ob);
            fs.TablaSecante.setModel(modelo);

            ms.setXo(Xi);
            Xo = ms.getXo();

            ms.setXi(XiR);
            Xi = ms.getXi();

            iterador++;
        } while (Math.abs(Xo-Xi) >= 0.0010);
    }

    //METODO DONDE ENVIA LOS DATOS NECESARIOS PARA HACER FUNCIONAR EL PROGRAMA
    public void EnvioDatos() {
        String funcion = fs.txtFuncion.getText();
        double xi = Double.parseDouble(fs.txtValorXi.getText());
        double xo = Double.parseDouble(fs.txtValorXo.getText());

        ms.setFuncion(funcion);
        ms.setXi(xi);
        ms.setXo(xo);
    }
}
