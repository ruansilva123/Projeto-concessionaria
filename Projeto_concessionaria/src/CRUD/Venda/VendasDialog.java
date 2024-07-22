/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD.Venda;

import CRUD.ClienteDialog;
import CRUD.ClienteDialog;
import CRUD.Estoque.EstoqueDialog;
import CRUD.FornecedorDialog;
import CRUD.FornecedorDialog;
import CRUD.Produto.AdicionarProdutos;
import CRUD.Produto.ClasseProduto;
import CRUD.Produto.Produto;
import CRUD.Produto.ProdutoDialog;
import CRUD.UsuarioDialog;
import CRUD.UsuarioDialog;
import MODULO_INICIAL.Home;
import UTILS.LogoutSystem;
import UTILS.User;
import UTILS.AlterPage;
import UTILS.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

        
public class VendasDialog extends javax.swing.JDialog {
    private User user;
    DataBase bd = new DataBase();
    Connection connection;
    ResultSet rs;

    public VendasDialog(java.awt.Frame parent, boolean modal, User user, ClasseProduto produto) {
        super(parent, modal);
        this.user = user;
        initComponents();
        initTable();
        setLocationRelativeTo(null);
        if (produto != null) {
            inserirNaTabela(produto);
            atualizarTotal();
        }
    }

    
    private void initTable() {
        DefaultTableModel tabela = (DefaultTableModel) jTabelaVendasDialog.getModel();
        tabela.setRowCount(0);
        if (tabela.getRowCount() < 25){
            int numTableRows = tabela.getRowCount()+(25-tabela.getRowCount());
            tabela.setRowCount(numTableRows);
        } else {
            tabela.setRowCount(tabela.getRowCount());
        }
    }
   
    
    private void inserirNaTabela(ClasseProduto produto) {
        DefaultTableModel tabela = (DefaultTableModel) jTabelaVendasDialog.getModel();
        int rowCount = tabela.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            Object id = tabela.getValueAt(i, 0);
            if (id == null) {
                tabela.removeRow(i);
            }
        }
        tabela.addRow(new Object[]{
            produto.getIdProduto(),
            produto.getNomeProduto(),
            produto.getMarcaProduto(),
            produto.getValorUnitarioProduto(),
            produto.getKmProduto(),
            produto.getAnoProduto()
        });
        if (tabela.getRowCount() < 25){
            int numTableRows = tabela.getRowCount()+(25-tabela.getRowCount());
            tabela.setRowCount(numTableRows);
        } else {
            tabela.setRowCount(tabela.getRowCount());
        }
    }
    
    
    private void cadastrarVenda() {
        if (bd.getConnection()) {
            try {
                String query = "INSERT venda(cliente_id_cliente,total_venda) VALUES(?,?)";
                PreparedStatement smtp = connection.prepareStatement(query);
                // smtp.setString(1, jTIdCliente.getText());
                
                double total = 0.0;
                for (int i = 0; i < jTabelaVendasDialog.getRowCount(); i++) {
                    double valorUnitario = (double) jTabelaVendasDialog.getValueAt(i, 5);
                    total += valorUnitario;
                }
                smtp.setDouble(2, total);

                smtp.executeUpdate();
                JOptionPane.showMessageDialog(null, "dados gravados");
                smtp.close();
                connection.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "erro de gravação no Banco:" + erro.toString());
            }
        }
    }

    
    private void AdicionarProduto() {
        AdicionarProdutos buscarProdutos = new AdicionarProdutos(null, true, user);
        buscarProdutos.setVisible(true);

        ClasseProduto produto = buscarProdutos.getProdutoSelecionado();
        if (produto != null) {
            inserirNaTabela(produto);
        }
        buscarProdutos.dispose();
    }
    
    
    private void atualizarTotal() {
    try {
        double total = 0.0;
        for (int i = 0; i < jTabelaVendasDialog.getRowCount(); i++) {
            Object valorUnitarioObj = jTabelaVendasDialog.getValueAt(i, 3);
            if (valorUnitarioObj != null) {
                double valorUnitario = (double) valorUnitarioObj;
                total += valorUnitario;
            }
        }
        jLValorTotal.setText(String.valueOf(total));
    } catch (Exception e) {
        System.out.println(e);
    }
}
    
    
    /* private void excluirProdutoDaTabela(){
        DefaultTableModel tabela = (DefaultTableModel) jTabelaVendasDialog.getModel();
        if (tabela.getSelectedRow() != -1) {
            int rowIndex = tabela.getSelectedRow();
            Object indexObj = tabela.getValueAt(rowIndex, 0);
            if (indexObj != null) {
                int index = Integer.parseInt(indexObj.toString());
                tabela.removeRow(rowIndex);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível obter o índice da linha selecionada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada para exclusão.");
        }
    } */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaVendasDialog = new javax.swing.JTable();
        jBCompletarVendas = new javax.swing.JButton();
        JBCancelarDialog = new javax.swing.JButton();
        jBAdicionarProduto1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLTotal = new javax.swing.JLabel();
        jLValorTotal = new javax.swing.JLabel();
        jBAdicionarProduto2 = new javax.swing.JButton();
        jBAdicionarProduto3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Vendas");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Produtos");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("Home");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Estoque");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Clientes");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Fornecedor");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Usuários");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/sair-do-usuario.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel19))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 62, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(44, 44, 44))
        );

        jLabel8.setText("Vendas");

        jPanel7.setForeground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(10, 60, 150));
        jPanel5.setForeground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Cadastrar Venda:");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabelaVendasDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Produto", "Marca", "Valor", "Km", "Ano"
            }
        ));
        jTabelaVendasDialog.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(jTabelaVendasDialog);

        jBCompletarVendas.setBackground(new java.awt.Color(0, 153, 255));
        jBCompletarVendas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBCompletarVendas.setForeground(new java.awt.Color(255, 255, 255));
        jBCompletarVendas.setText("Complete");
        jBCompletarVendas.setBorder(null);
        jBCompletarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCompletarVendasActionPerformed(evt);
            }
        });

        JBCancelarDialog.setBackground(new java.awt.Color(204, 204, 204));
        JBCancelarDialog.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        JBCancelarDialog.setForeground(new java.awt.Color(102, 102, 102));
        JBCancelarDialog.setText("Cancelar");
        JBCancelarDialog.setBorder(null);

        jBAdicionarProduto1.setBackground(new java.awt.Color(10, 60, 150));
        jBAdicionarProduto1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBAdicionarProduto1.setForeground(new java.awt.Color(255, 255, 255));
        jBAdicionarProduto1.setText("Add");
        jBAdicionarProduto1.setBorder(null);
        jBAdicionarProduto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAdicionarProduto1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLTotal.setForeground(new java.awt.Color(153, 153, 153));
        jLTotal.setText("Total:");
        jLTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLValorTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLValorTotal.setForeground(new java.awt.Color(153, 153, 153));
        jLValorTotal.setText("0");
        jLValorTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLValorTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLValorTotalMouseClicked(evt);
            }
        });

        jBAdicionarProduto2.setBackground(new java.awt.Color(0, 51, 255));
        jBAdicionarProduto2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBAdicionarProduto2.setForeground(new java.awt.Color(255, 255, 255));
        jBAdicionarProduto2.setText("Delete");
        jBAdicionarProduto2.setBorder(null);
        jBAdicionarProduto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAdicionarProduto2ActionPerformed(evt);
            }
        });

        jBAdicionarProduto3.setBackground(new java.awt.Color(0, 51, 204));
        jBAdicionarProduto3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBAdicionarProduto3.setForeground(new java.awt.Color(255, 255, 255));
        jBAdicionarProduto3.setText("Clear");
        jBAdicionarProduto3.setBorder(null);
        jBAdicionarProduto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAdicionarProduto3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(JBCancelarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLTotal)
                                .addGap(18, 18, 18)
                                .addComponent(jLValorTotal))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBCompletarVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jBAdicionarProduto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAdicionarProduto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAdicionarProduto3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jBCompletarVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBAdicionarProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBAdicionarProduto2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBAdicionarProduto3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLTotal)
                            .addComponent(jLValorTotal))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(JBCancelarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 315, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel10)
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        LogoutSystem.logoutSystem(this);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        FornecedorDialog fornecedor = new FornecedorDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, fornecedor);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        EstoqueDialog estoque = new EstoqueDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, estoque);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        ClienteDialog cliente = new ClienteDialog(null, false, user);
        AlterPage.alterPage(user.getIsSeller(), this, cliente);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        Produto produto = new Produto(user);
        AlterPage.alterPage(user.getIsManager(), this, produto);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        UsuarioDialog usuario = new UsuarioDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, usuario);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Home home = new Home(user);
        this.dispose();
        home.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jBCompletarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCompletarVendasActionPerformed
        cadastrarVenda();
        initTable();
        atualizarTotal();
    }//GEN-LAST:event_jBCompletarVendasActionPerformed

    private void jBAdicionarProduto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAdicionarProduto1ActionPerformed
        AdicionarProduto();
        atualizarTotal();
    }//GEN-LAST:event_jBAdicionarProduto1ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLValorTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLValorTotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLValorTotalMouseClicked

    private void jBAdicionarProduto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAdicionarProduto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAdicionarProduto2ActionPerformed

    private void jBAdicionarProduto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAdicionarProduto3ActionPerformed
        initTable();
    }//GEN-LAST:event_jBAdicionarProduto3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User user = new User(0,0,0,"No User");
                VendasDialog dialog = new VendasDialog(new javax.swing.JFrame(), true, user, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBCancelarDialog;
    private javax.swing.JButton jBAdicionarProduto1;
    private javax.swing.JButton jBAdicionarProduto2;
    private javax.swing.JButton jBAdicionarProduto3;
    private javax.swing.JButton jBCompletarVendas;
    private javax.swing.JLabel jLTotal;
    private javax.swing.JLabel jLValorTotal;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaVendasDialog;
    // End of variables declaration//GEN-END:variables
}
