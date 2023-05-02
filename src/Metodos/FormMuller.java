package Metodos;

import funciones.ModeloMuller;
import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.JEP;

/**
 *
 * @author eroda
 */
public class FormMuller extends javax.swing.JInternalFrame {
    //INSTANCIO LAS CLASES NECESARIAS PARA PODER MANIPULAR EL FUNCIONAMIENTO DE MI PROGRAMA
    ModeloMuller muller = new ModeloMuller();
    DefaultTableModel modelo = new DefaultTableModel();
    
    //CON ESTE PARSEADOR, ME SIRVE PARA SUSTITUIR LOS VALORES EN MI FUNCION
    JEP jep = new JEP();
    /**
     * Creates new form FormMuller
     */
    public FormMuller() {
        initComponents();
    }
    //DECLARO LAS VARIABLES NECESARIAS PARA MANIPULAR EL VALOR DE MIS DATOS
    private double r;
    int iterador = 1;
    double xo, x1, x2, fxo, fx1, fx2, ho, h1, d0, d1, a, b, c, x3, ETolerancia;
    
    //METODO QUE EVALUA LA FUNCION ENVIADA Y EL PARAMETRO A ENCONTRAR
    public double f(double x){
        jep = new JEP();
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addVariable("x", x);
        jep.parseExpression(muller.getFuncion());
        r = jep.getValue();
        
        return r;
    }
    
    //METODO DONDE SE EVALUA LA LOGICA DE LA BISECCION
    public void Evaluar(){
        do{
            //SE OBTIENE LOS DATOS ENVIADOS DESDE EL FORMULARIO Y SE ALMACENAN EN LAS VARIABLES CORRESPONDIENTES
            xo = muller.getXo();
            x1 = muller.getX1();
            x2 = muller.getX2();
            
            //SE EVALUA LAS FUNCIONES QUE SOLICITA EL METODO
            fxo = f(xo);
            fx1 = f(x1);
            fx2 = f(x2);
            
            // SE EVALUA LOS SIGUIENTES METODOS
            ho = x1 - xo;
            h1 = x2 - x1;
            d0 = ((fx1) - (fxo))/(x1 - xo);
            d1 = ((fx2) - (fx1))/(x2 - x1);
            
            a = ((d1 - (d0)) / (h1 + ho));
            b = (h1 * a) + d1;
            c = fx2;
            
            //CONDICION PARA DETERMINAR QUE RAIZ UTILIZAR
            if (b >= 0) {
                x3 = x2 + ((-2*c)) / (b + Math.sqrt((Math.pow(b, 2) - 4 * (a * c))));
            } else {
                x3 = (x2 + (-2*c)) / (b - Math.sqrt((Math.pow(b, 2) - 4 * (a * c))));
            }
            
            //CONDICION PARA EVALUAR EL ERROR DE TOLERANCIA EN LA SEGUNDA ITERACION EN ADELANTE
            if (iterador > 1) {
                ETolerancia = (Math.abs((x3 - x2) / x3));
            }
            
            //SE INGRESAN LOS DATOS A LA TABLA
            modelo = (DefaultTableModel) TablaMuller.getModel();
            Object[] ob = new Object[16];
            ob[0] = iterador;
            ob[1] = String.format("%.4f", xo);
            ob[2] = String.format("%.4f", x1);
            ob[3] = String.format("%.4f", x2);
            ob[4] = String.format("%.4f", fxo);
            ob[5] = String.format("%.4f", fx1);
            ob[6] = String.format("%.4f", fx2);
            ob[7] = String.format("%.4f", ho);
            ob[8] = String.format("%.4f", h1);
            ob[9] = String.format("%.4f", d0);
            ob[10] = String.format("%.4f", d1);
            ob[11] = String.format("%.4f", a);
            ob[12] = String.format("%.4f", b);
            ob[13] = String.format("%.4f", c);
            ob[14] = String.format("%.4f", x3);
            ob[15] = String.format("%.4f", ETolerancia);
            modelo.addRow(ob);
            TablaMuller.setModel(modelo);
            
            //SE MODIFICAN LOS VALORES PARA EVALUAR LAS DEMAS ITERACIONES
            muller.setXo(x1);
            muller.setX1(x2);
            muller.setX2(x3);
            
            iterador++;
        }while(Math.abs((x3 - x2) / x3) >= muller.getETolerancia());
    }
    
    //METODO PARA EL ENVIO DE LOS DATOS 
    public void EnvioDatos(){
        String funcion = txtFuncion.getText();
        double Vxo = Double.parseDouble(txtXo.getText());
        double Vx1 = Double.parseDouble(txtX1.getText());
        double Vx2 = Double.parseDouble(txtX2.getText());
        double et = Double.parseDouble(txtTolerancia.getText());
        
        muller.setFuncion(funcion);
        muller.setXo(Vxo);
        muller.setX1(Vx1);
        muller.setX2(Vx2);
        muller.setETolerancia(et);
    }
    
    //METODO QUE ENVIA LA RESPUESTA DONDE SE CONTIENE LA SOLUCION DE LA RAIZ
    public void Respuesta(){
        txtRespuesta.setText(String.format("%.4f", x3));
    }
    
    //METODO PARA LIMPIAR TODAS LAS CAJAS Y LA TABLA 
    public void Limpiar(){
        txtFuncion.setText(null);
        txtXo.setText(null);
        txtX1.setText(null);
        txtX2.setText(null);
        txtTolerancia.setText(null);
        txtRespuesta.setText(null);
        
        for (int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i=i-1;
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFuncion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtXo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtX1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtX2 = new javax.swing.JTextField();
        btnEvaluar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtRespuesta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTolerancia = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMuller = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("METODO DE MULLER");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 153, 255), new java.awt.Color(0, 153, 255)));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        jLabel1.setText("INGRESE FUNCION :");

        jLabel2.setText("INGRESE Xo :");

        jLabel3.setText("INGRESE X1 :");

        jLabel4.setText("INGRESE X2 :");

        btnEvaluar.setText("E V A L U A R");
        btnEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluarActionPerformed(evt);
            }
        });

        jLabel5.setText("INGRESE TOLERANCIA :");

        txtRespuesta.setEditable(false);

        jLabel6.setText("RESPUESTA :");

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Basurero.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTolerancia))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnEvaluar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtX2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtX1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtXo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRespuesta)
                                .addGap(68, 68, 68)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtXo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtX1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEvaluar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        TablaMuller.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "i", "Xo", "X1", "X2", "f(Xo)", "f(X1)", "f(X2)", "ho", "h1", "do", "d1", "a", "b", "c", "X3", "Error Tolerancia"
            }
        ));
        jScrollPane1.setViewportView(TablaMuller);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEvaluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluarActionPerformed
        EnvioDatos();
        Evaluar();
        Respuesta();
    }//GEN-LAST:event_btnEvaluarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaMuller;
    private javax.swing.JButton btnEvaluar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFuncion;
    private javax.swing.JTextField txtRespuesta;
    private javax.swing.JTextField txtTolerancia;
    private javax.swing.JTextField txtX1;
    private javax.swing.JTextField txtX2;
    private javax.swing.JTextField txtXo;
    // End of variables declaration//GEN-END:variables
}
