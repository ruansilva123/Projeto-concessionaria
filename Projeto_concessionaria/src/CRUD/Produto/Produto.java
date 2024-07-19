/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CRUD.Produto;

import CRUD.ClienteDialog;
import CRUD.Estoque.EstoqueDialog;
import CRUD.FornecedorDialog;
import CRUD.UsuarioDialog;
import CRUD.Venda.VendasDialog;
import MODULO_INICIAL.Home;
import UTILS.DataBase;
import UTILS.LogoutSystem;
import UTILS.AlterPage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import UTILS.User;


public class Produto extends javax.swing.JFrame {
    private User user;
    DataBase bd = new DataBase();
    Connection connection;
    ResultSet rs;
    PreparedStatement getProdutos;
    
    
    public Produto(User user) {
        this.user = user;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTable();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeResources();
                System.exit(0);
            }
        });
     }
    
    
    public void setTable() {
        if (bd.getConnection()) {
            try {
                connection = bd.connection;
                String query = "SELECT * FROM produto";
                getProdutos = connection.prepareStatement(query);
                rs = getProdutos.executeQuery();
                DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutos.getModel();
                while (rs.next()){
                    ClasseProduto produto = new ClasseProduto(
                        rs.getInt("id_produto"),
                        rs.getString("nome_produto"),
                        rs.getString("marca_produto"),
                        rs.getDouble("valor_unitario_produto"),
                        rs.getInt("km_produto"),
                        rs.getInt("ano_produto")
                    );
                    inserirNaTabela(produto);
                }
                rs.close();
                connection.close();
            } catch (SQLException erro) {
                System.out.println("Erro ao pesquisar: " + erro.toString());
            }
        }
    }
    
    private void inserirNaTabela(ClasseProduto produto) {
        DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutos.getModel();
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
    
    private void initTable() {
        DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutos.getModel();
        tabela.setRowCount(0);
    }
    
    private void SalvarProduto(ClasseProduto produto) {
        if (bd.getConnection()) {
            try {
                String query = "INSERT INTO produto (nome_produto, marca_produto, valor_unitario_produto, "
                            + "km_produto, ano_produto) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement insertProductStatement = bd.connection.prepareStatement(query);
                insertProductStatement.setString(1, produto.getNomeProduto());
                insertProductStatement.setString(2, produto.getMarcaProduto());
                insertProductStatement.setString(3, String.valueOf(produto.getValorUnitarioProduto()));
                insertProductStatement.setString(4, String.valueOf(produto.getKmProduto()));
                insertProductStatement.setString(5, String.valueOf(jTAno.getText()));

                insertProductStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Dados gravados com sucesso");

                insertProductStatement.close();
                bd.connection.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de gravação no banco: " + erro.toString());
            }
        }
    }
    
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (getProdutos != null) getProdutos.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.toString());
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
        jPanel3 = new javax.swing.JPanel();
        jTMarca = new javax.swing.JTextField();
        jTKm = new javax.swing.JTextField();
        jTValorUnit = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaProdutos = new javax.swing.JTable();
        jLMaisOpcoes = new javax.swing.JLabel();
        JBCancelar = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTNome = new javax.swing.JTextField();
        jTAno = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLNome = new javax.swing.JLabel();
        jLMarca = new javax.swing.JLabel();
        jLValorUnit = new javax.swing.JLabel();
        jLKm = new javax.swing.JLabel();
        jLAno = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1482, 226, -1, -1));

        jTMarca.setBackground(new java.awt.Color(235, 235, 235));
        jTMarca.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jTMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 240, -1));

        jTKm.setBackground(new java.awt.Color(235, 235, 235));
        jTKm.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTKm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTKm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTKmActionPerformed(evt);
            }
        });
        jPanel1.add(jTKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 240, -1));

        jTValorUnit.setBackground(new java.awt.Color(235, 235, 235));
        jTValorUnit.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTValorUnit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTValorUnit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jTValorUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 240, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTabelaProdutos.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(jTabelaProdutos);

        jLMaisOpcoes.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLMaisOpcoes.setForeground(new java.awt.Color(102, 102, 102));
        jLMaisOpcoes.setText("Mais opções");
        jLMaisOpcoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLMaisOpcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLMaisOpcoesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMaisOpcoes)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLMaisOpcoes)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 610, 510));

        JBCancelar.setBackground(new java.awt.Color(204, 204, 204));
        JBCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        JBCancelar.setForeground(new java.awt.Color(102, 102, 102));
        JBCancelar.setText("Cancelar");
        JBCancelar.setBorder(null);
        jPanel1.add(JBCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, 90, 40));

        jBSalvar.setBackground(new java.awt.Color(10, 60, 150));
        jBSalvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBSalvar.setForeground(new java.awt.Color(255, 255, 255));
        jBSalvar.setText("Salvar");
        jBSalvar.setBorder(null);
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(jBSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 90, 40));

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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 390, 10));

        jTNome.setBackground(new java.awt.Color(235, 235, 235));
        jTNome.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jTNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 240, -1));

        jTAno.setBackground(new java.awt.Color(235, 235, 235));
        jTAno.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTAno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTAno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jTAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 240, -1));

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

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 800, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Cadastro de Produtos:");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jLNome.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLNome.setForeground(new java.awt.Color(102, 102, 102));
        jLNome.setText("Nome:");
        jLNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLNomeMouseClicked(evt);
            }
        });
        jPanel1.add(jLNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLMarca.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLMarca.setForeground(new java.awt.Color(102, 102, 102));
        jLMarca.setText("Marca:");
        jLMarca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLMarcaMouseClicked(evt);
            }
        });
        jPanel1.add(jLMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        jLValorUnit.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLValorUnit.setForeground(new java.awt.Color(102, 102, 102));
        jLValorUnit.setText("Valor Unitario:");
        jLValorUnit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLValorUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLValorUnitMouseClicked(evt);
            }
        });
        jPanel1.add(jLValorUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        jLKm.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLKm.setForeground(new java.awt.Color(102, 102, 102));
        jLKm.setText("Km:");
        jLKm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLKm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLKmMouseClicked(evt);
            }
        });
        jPanel1.add(jLKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        jLAno.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLAno.setForeground(new java.awt.Color(102, 102, 102));
        jLAno.setText("Ano:");
        jLAno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLAno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLAnoMouseClicked(evt);
            }
        });
        jPanel1.add(jLAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1220, 720));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produtos");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Vendas");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Home");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel3)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        ClasseProduto produto = new ClasseProduto(
                0,
                jTNome.getText(),
                jTMarca.getText(),
                Double.parseDouble(jTValorUnit.getText()),
                Integer.parseInt(jTKm.getText()),
                Integer.parseInt(jTAno.getText())
        );
        
        SalvarProduto(produto); 
        initTable();
        setTable();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        VendasDialog vendas = new VendasDialog(null, false, user);
        AlterPage.alterPage(user.getIsSeller(), this, vendas);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Home home = new Home(user);
        this.dispose();
        home.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        EstoqueDialog estoque = new EstoqueDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, estoque);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        ClienteDialog cliente = new ClienteDialog(null, false, user);
        AlterPage.alterPage(user.getIsSeller(), this, cliente);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        FornecedorDialog fornecedor = new FornecedorDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, fornecedor);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        UsuarioDialog usuario = new UsuarioDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, usuario);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        LogoutSystem.logoutSystem(this);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLMaisOpcoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLMaisOpcoesMouseClicked
        ProdutoDialog crudProduto = new ProdutoDialog(null, false, user);
        AlterPage.alterPage(user.getIsManager(), this, crudProduto);        // TODO add your handling code here:
    }//GEN-LAST:event_jLMaisOpcoesMouseClicked

    private void jLAnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLAnoMouseClicked

    private void jTKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTKmActionPerformed

    private void jLKmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLKmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLKmMouseClicked

    private void jLValorUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLValorUnitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLValorUnitMouseClicked

    private void jLMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLMarcaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLMarcaMouseClicked

    private void jLNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLNomeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLNomeMouseClicked

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
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User user = new User(0,0,0,"No User");
                new Produto(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBCancelar;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JLabel jLAno;
    private javax.swing.JLabel jLKm;
    private javax.swing.JLabel jLMaisOpcoes;
    private javax.swing.JLabel jLMarca;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLValorUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTAno;
    private javax.swing.JTextField jTKm;
    private javax.swing.JTextField jTMarca;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTValorUnit;
    private javax.swing.JTable jTabelaProdutos;
    // End of variables declaration//GEN-END:variables

   
    
}