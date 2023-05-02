package Metodos;

import funciones.ModeloBiseccion;
import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.JEP;

/**
 *
 * @author eroda
 */
public class FormReglaFalsa extends javax.swing.JInternalFrame {
    
    //INSTANCIO LAS CLASES NECESARIAS PARA PODER MANIPULAR EL FUNCIONAMIENTO DE MI PROGRAMA
    JEP jep = new JEP();
    ModeloBiseccion mb = new ModeloBiseccion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    /**
     * Creates new form FormReglaFalsa
     */
    public FormReglaFalsa() {
        initComponents();
    }
    
    //DECLARO LAS VARIABLES NECESARIAS PARA MANIPULAR EL VALOR DE MIS DATOS
    private double r = 0.0;
    int iterador = 1;
    double a;
    double b;
    double Xr;
    double funA;
    double funB;
    double funXr;
    double ETolerancia;
    
    //METODO QUE EVALUA LA FUNCION ENVIADA Y EL PARAMETRO A ENCONTRAR
    public double f(double x){
        jep = new JEP();
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addVariable("x", x);
        jep.parseExpression(mb.getFuncion());
        r = jep.getValue();
        
        return r;
    }
    
    //METODO DONDE SE APLICA LA LOGICA PARA RESOLVER EL METODO DE REGLA FALSA
    public void Evaluar(){
        do {            
            a = mb.getA();
            b = mb.getB();
            funA = f(a);
            funB = f(b);
            
            Xr = ((a*funB)-(b*funA))/((funB)-(funA));
            
            funXr = f(Xr);
            
            if (iterador == 1) {
                
            } else {
                ETolerancia = Math.abs(a - Xr);
            }
            
            modelo = (DefaultTableModel) TablaRFalsa.getModel();    
            Object[] ob = new Object[8];
            ob[0] = iterador;
            ob[1] = String.format("%.4f", a);
            ob[2] = String.format("%.4f", b);
            ob[3] = String.format("%.4f", funA);
            ob[4] = String.format("%.4f", funB);
            ob[5] = String.format("%.4f", Xr);
            ob[6] = String.format("%.4f", funXr);
            ob[7] = String.format("%.4f", ETolerancia);
            modelo.addRow(ob);
            TablaRFalsa.setModel(modelo);
            
            if (funXr >= 0) {
                mb.setB(Xr);
            } else {
                mb.setA(Xr);
            }
            iterador++;
                    
        } while (Math.abs(a-Xr) >= mb.getETolerancia());
    }
    
    //METODO QUE ENVIA LA RESPUESTA DONDE SE CONTIENE LA SOLUCION DE LA RAIZ
    public void Respuesta(){
        txtResultado.setText(String.format("%.4f", Xr));
    }
    
    //METODO QUE ENVIA LOS DATOS NECESARIOS PARA HACER FUNCIONAR EL PROGRAMA
    public void EnvioDatos(){
        String funcion = txtFuncion.getText();
        double a = Double.parseDouble(txtValorA.getText());
        double b = Double.parseDouble(txtValorB.getText());
        double Et = Double.parseDouble(txtErrorTolerancia.getText());
        
        mb.setFuncion(funcion);
        mb.setA(a);
        mb.setB(b);
        mb.setETolerancia(Et);
    }
    
    //METODO PARA LIMPIAR TODAS LAS CAJAS Y LA TABLA 
    public void Limpiar(){
        txtFuncion.setText(null);
        txtValorA.setText(null);
        txtValorB.setText(null);
        txtResultado.setText(null);
        txtErrorTolerancia.setText(null);
        
        for (int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFuncion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValorA = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValorB = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtErrorTolerancia = new javax.swing.JTextField();
        btnEvaluar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtResultado = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRFalsa = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("METODO DE REGLA FALSA");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 255), new java.awt.Color(0, 51, 204)));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("INGRESAR FUNCION :");

        txtFuncion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuncionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("INGRESE EL VALOR DE \"A\" :");

        txtValorA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("INGRESE EL VALOR DE \"B\" :");

        txtValorB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ERROR DE TOLERANCIA :");

        txtErrorTolerancia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnEvaluar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEvaluar.setText("E V A L U A R");
        btnEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("RESULTADO RAIZ :");

        txtResultado.setEditable(false);
        txtResultado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtValorA)
                                    .addComponent(txtValorB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtErrorTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtResultado))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnEvaluar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValorA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtErrorTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtValorB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEvaluar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        TablaRFalsa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TablaRFalsa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITERACION", "a", "b", "f(a)", "f(b)", "Xr", "f(Xr)", "ErrorTolerancia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaRFalsa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEvaluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluarActionPerformed
        EnvioDatos();
        Evaluar();
        Respuesta();
    }//GEN-LAST:event_btnEvaluarActionPerformed

    private void txtFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuncionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRFalsa;
    private javax.swing.JButton btnEvaluar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtErrorTolerancia;
    private javax.swing.JTextField txtFuncion;
    private javax.swing.JTextField txtResultado;
    private javax.swing.JTextField txtValorA;
    private javax.swing.JTextField txtValorB;
    // End of variables declaration//GEN-END:variables
}
