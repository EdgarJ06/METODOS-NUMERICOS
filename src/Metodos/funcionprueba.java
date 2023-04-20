package Metodos;

import Vista.Prueba;
import funciones.ModeloPrueba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author eroda
 */
public class funcionprueba implements ActionListener{
    
    private Prueba p;
    private ModeloPrueba mp;

    public funcionprueba(Prueba p, ModeloPrueba mp) {
        this.p = p;
        this.mp = mp;
        this.p.btnEnviar.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        mp.setValorA(Integer.parseInt(p.txtValorA.getText()));
        mp.setValorB(Integer.parseInt(p.txtValorB.getText()));
        
        mp.MuestraDatos();
    }
        
}
