package Metodos;

import funciones.ModeloRaicesMultiples;
import javax.swing.table.DefaultTableModel;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 *
 * @author eroda
 */
public class FormRaicesMultiples extends javax.swing.JInternalFrame {
    //INSTANCIO LAS CLASES NECESARIAS PARA PODER MANIPULAR EL FUNCIONAMIENTO DE MI PROGRAMA
    ModeloRaicesMultiples mrm = new ModeloRaicesMultiples();
    DefaultTableModel modelo = new DefaultTableModel();
    DJep Djep = new DJep();
    JEP jep = new JEP();

    public FormRaicesMultiples() {
        initComponents();
    }

    //DECLARO LAS VARIABLES NECESARIAS PARA MANIPULAR EL VALOR DE MIS DATOS
    private double r = 0.0;
    int iterador = 1;
    double Xi;
    double XiR;
    double funXi;
    double funDeriXi;
    double funSegunDeriXi;
    double ETolerancia;

    //METODO QUE EVALUA LA FUNCION INGRESADA Y ENCUENTRA SU DERIVADA
    public void FDerivada() {
        Node nodofuncion;
        Node nododerivada;
        Node difNode;
        Djep = new DJep();

        try {
            Djep.addStandardFunctions();
            Djep.addStandardConstants();
            //se adjuntan los numeros complejos
            Djep.addComplex();
            //se permite el acceso de las variables no definidas
            Djep.setAllowUndeclared(true);
            //se permite el acceso a las variables definidas
            Djep.setAllowAssignment(true);
            //se declara las funciones que incorporan los signos matematicos para la evaluacion
            Djep.setImplicitMul(true);
            //se declara los estandares de diferenciacion para derivadas mas avanzadas
            Djep.addStandardDiffRules();

            //evalaucion y envio de la funcion ya derivada
            nodofuncion = Djep.parse(mrm.getFuncion());
            difNode = Djep.differentiate(nodofuncion, "x");
            nododerivada = Djep.simplify(difNode);

            mrm.setFuncionDerivada(Djep.toString(nododerivada));

        } catch (ParseException e) {
            System.out.println("Error: " + e.getErrorInfo());
        }
    }

    //METODO QUE DETERMINA LA FUNCION YA DERIVADA Y ENCUENTRA SU SEGUNDA DERIVADA
    public void FSegundaDerivada() {
        Node nodofuncion;
        Node nododerivada;
        Node difNode;
        Djep = new DJep();

        try {
            Djep.addStandardFunctions();
            Djep.addStandardConstants();
            Djep.addComplex();
            Djep.setAllowUndeclared(true);
            Djep.setAllowAssignment(true);
            Djep.setImplicitMul(true);
            Djep.addStandardDiffRules();

            nodofuncion = Djep.parse(mrm.getFuncionDerivada());
            difNode = Djep.differentiate(nodofuncion, "x");
            nododerivada = Djep.simplify(difNode);

            mrm.setfSegundaDerivada(Djep.toString(nododerivada));

        } catch (ParseException e) {
            System.out.println("Error: " + e.getErrorInfo());
        }
    }

    //METODO QUE EVALUA LA FUNCION ENVIADA Y EL PARAMETRO A ENCONTRAR
    public double f(double x) {
        jep = new JEP();
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addVariable("x", x);
        jep.parseExpression(mrm.getFuncion());
        r = jep.getValue();

        return r;
    }

    //METODO QUE EVALUA LA FUNCION DERIVADA Y EL PARAMETRO A ENCONTRAR
    public double fderivada(double x) {
        jep = new JEP();
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addVariable("x", x);
        jep.parseExpression(mrm.getFuncionDerivada());
        r = jep.getValue();

        return r;
    }

    //METODO QUE EVALUA LA FUNCION CON SU SEGUNDA DERIVADA Y EL PARAMETRO A ENCONTRAR
    public double fSegundaderivada(double x) {
        jep = new JEP();
        jep.addStandardFunctions();
        jep.addStandardConstants();
        jep.addVariable("x", x);
        jep.parseExpression(mrm.getfSegundaDerivada());
        r = jep.getValue();

        return r;
    }

    //METODO DONDE SE EVALUA LA LOGICA DEL METODO DE RAICES MULTIPLES O NEWTON MEJORADO
    public void Evaluar() {
        do {

            Xi = mrm.getXi();

            funXi = f(Xi);
            funDeriXi = fderivada(Xi);
            funSegunDeriXi = fSegundaderivada(Xi);

            XiR = Xi - ((funXi * funDeriXi) / ((Math.pow(funDeriXi, 2)) - (funXi * funSegunDeriXi)));

            if (iterador == 1) {

            } else {
                ETolerancia = (Math.abs(Xi - XiR));
            }

            modelo = (DefaultTableModel) TablaRaicesMultiples.getModel();
            Object[] ob = new Object[7];
            ob[0] = iterador;
            ob[1] = String.format("%.4f", Xi);
            ob[2] = String.format("%.4f", funXi);
            ob[3] = String.format("%.4f", funDeriXi);
            ob[4] = String.format("%.4f", funSegunDeriXi);
            ob[5] = String.format("%.4f", XiR);
            ob[6] = String.format("%.4f", ETolerancia);
            modelo.addRow(ob);
            TablaRaicesMultiples.setModel(modelo);

            mrm.setXi(XiR);
            iterador++;

        } while (Math.abs(Xi - XiR) >= mrm.getETolerancia());
    }

    //METODO QUE ENVIA LOS DATOS NECESARIOS PARA HACER FUNCIONAR EL PROGRAMA
    public void EnvioDatos() {
        String funcion = txtFuncion.getText();
        double xi = Double.parseDouble(txtXi.getText());
        double et = Double.parseDouble(txtETolerancia.getText());

        mrm.setFuncion(funcion);
        mrm.setXi(xi);
        mrm.setETolerancia(et);
    }
    
    //METODO QUE ENVIA LA RESPUESTA DONDE SE CONTIENE LA SOLUCION DE LA RAIZ
    public void Respuesta(){
        txtResultado.setText(String.format("%.4f", Xi));
    }
    
    //METODO PARA LIMPIAR TODAS LAS CAJAS Y LA TABLA 
    public void Limpiar(){
        txtFuncion.setText(null);
        txtXi.setText(null);
        txtResultado.setText(null);
        txtETolerancia.setText(null);
        
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
        jLabel1 = new javax.swing.JLabel();
        txtFuncion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtXi = new javax.swing.JTextField();
        btnEvaluar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtETolerancia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtResultado = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRaicesMultiples = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("METODO DE RAICES MULTIPLES O NEWTON MEJORADO");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("INGRESE LA FUNCION :");

        txtFuncion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("INGRESE EL VALOR DE \"Xi\" :");

        txtXi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnEvaluar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEvaluar.setText("E V A L U A R");
        btnEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("ERROR DE TOLERANCIA :");

        txtETolerancia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("RESULTADO RAIZ :");

        txtResultado.setEditable(false);
        txtResultado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Basurero.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtResultado))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtETolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtXi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEvaluar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtXi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtETolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEvaluar, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        TablaRaicesMultiples.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TablaRaicesMultiples.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITERACION", "Xi", "f(Xi)", "f´(Xi)", "f¨(Xi)", "Xi + 1", "ErrorTolerancia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaRaicesMultiples);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEvaluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluarActionPerformed
        EnvioDatos();
        FDerivada();
        FSegundaDerivada();
        Evaluar();
        Respuesta();

    }//GEN-LAST:event_btnEvaluarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRaicesMultiples;
    private javax.swing.JButton btnEvaluar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtETolerancia;
    private javax.swing.JTextField txtFuncion;
    private javax.swing.JTextField txtResultado;
    private javax.swing.JTextField txtXi;
    // End of variables declaration//GEN-END:variables
}
