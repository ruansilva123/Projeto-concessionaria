/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD.Produto;

import CRUD.ClienteDialog;
import CRUD.Estoque.EstoqueDialog;
import CRUD.FornecedorDialog;
import CRUD.Produto.ClasseProduto;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author moc3jvl
 */
public class ProdutoDialog extends javax.swing.JDialog {

    private User user;
    DataBase bd = new DataBase();
    Connection connection;
    ResultSet rs;
    PreparedStatement getProdutos;
    String editId;
    
    
    public ProdutoDialog(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        this.user = user;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        initTable();
        setTable();
        initSearchListener();
    }

    
    private void initTable() {
        DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutosDialog.getModel();
        tabela.setRowCount(0);
    }
    
    
    public void setTable() {
        if (bd.getConnection()) {
            try {
                connection = bd.connection;
                String query = "SELECT * FROM produto";
                getProdutos = connection.prepareStatement(query);
                rs = getProdutos.executeQuery();
                DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutosDialog.getModel();
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
        DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutosDialog.getModel();
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
    
    
    private void delete() {
        if (bd.getConnection()) {
            try {
                String query = "DELETE FROM produto WHERE id_produto = ?";
                PreparedStatement smtp = bd.connection.prepareStatement(query);
                Object value = jTabelaProdutosDialog.getModel().getValueAt(jTabelaProdutosDialog.getSelectedRow(), 0);
                String index = value.toString();
                smtp.setString(1, index);
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    int resultado = smtp.executeUpdate();
                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível remover o Produto!");
                    }
                    smtp.close();
                    bd.connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro no SQL: " + e.toString());
            }
        }
    }


    private void setEdit(){
        int selectedRow = jTabelaProdutosDialog.getSelectedRow();
        if(selectedRow != -1){
            editId = jTabelaProdutosDialog.getValueAt(selectedRow, 0).toString();
            jTNomeEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 1).toString());
            jTMarcaEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 2).toString());
            jTValorUnitEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 3).toString());
            jTKmEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 4).toString());
            jTAnoEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 5).toString()); 
        }
    }
    
    
    private void initTableListener(){
        jTabelaProdutosDialog.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int selectedRow = jTabelaProdutosDialog.getSelectedRow();
                if(selectedRow != -1){
                    editId = jTabelaProdutosDialog.getValueAt(selectedRow, 0).toString();
                    jTNomeEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 1).toString());
                    jTMarcaEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 2).toString());
                    jTValorUnitEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 3).toString());
                    jTKmEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 4).toString());
                    jTAnoEditar.setText(jTabelaProdutosDialog.getValueAt(selectedRow, 5).toString()); 
                }
            }
        });
    }
    
    
    private void edit(){
        if(bd.getConnection()){
            try{
                String query = "UPDATE produto SET nome_produto=?, marca_produto=?, valor_unitario_produto=?, "+
                                "km_produto=?, ano_produto=? WHERE id_produto = ? ";
                PreparedStatement updateProductStatement = bd.connection.prepareStatement(query);
                updateProductStatement.setString(1, jTNomeEditar.getText());
                updateProductStatement.setString(2, jTMarcaEditar.getText());
                updateProductStatement.setString(3, jTValorUnitEditar.getText());
                updateProductStatement.setString(4, jTKmEditar.getText());
                updateProductStatement.setString(5, jTAnoEditar.getText());
                updateProductStatement.setString(6, editId);
                updateProductStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Dados atualizados! ");
                updateProductStatement.close();
                bd.connection.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro no SQL: "+e.getMessage());
            }
        }
    }
    
    
    private void initSearchListener() {
        jTPesquisaDialog.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                pesquisar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                pesquisar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                pesquisar();
            }
        });
    }
    
    
    private void pesquisar() {
        if (bd.getConnection()) {
            try {
                connection = bd.connection;
                String pesquisa = jTPesquisaDialog.getText();
                String query = "SELECT * FROM produto WHERE nome_produto LIKE ? OR marca_produto LIKE ?";
                PreparedStatement searchStatement = connection.prepareStatement(query);
                searchStatement.setString(1, "%" + pesquisa + "%");
                searchStatement.setString(2, "%" + pesquisa + "%");
                rs = searchStatement.executeQuery();
                DefaultTableModel tabela = (DefaultTableModel) jTabelaProdutosDialog.getModel();
                initTable();
                while (rs.next()) {
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
                searchStatement.close();
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao pesquisar: " + e.toString());
            }
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

        jTNome1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTNomeEditar = new javax.swing.JTextField();
        jLNome = new javax.swing.JLabel();
        jLMarca = new javax.swing.JLabel();
        jTMarcaEditar = new javax.swing.JTextField();
        jLValorUnit = new javax.swing.JLabel();
        jTValorUnitEditar = new javax.swing.JTextField();
        jLKm = new javax.swing.JLabel();
        jTKmEditar = new javax.swing.JTextField();
        jLAno = new javax.swing.JLabel();
        jTAnoEditar = new javax.swing.JTextField();
        jLCampoDeEdicao = new javax.swing.JLabel();
        jBSalvarDialog = new javax.swing.JButton();
        jTPesquisaDialog = new javax.swing.JTextField();
        jLabelPesquisar = new javax.swing.JLabel();
        jPanelProdutosDialog = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabelaProdutosDialog = new javax.swing.JTable();
        JBCancelarDialog = new javax.swing.JButton();
        jBDeletar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
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

        jTNome1.setBackground(new java.awt.Color(235, 235, 235));
        jTNome1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTNome1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTNome1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1482, 226, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTNomeEditar.setBackground(new java.awt.Color(235, 235, 235));
        jTNomeEditar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTNomeEditar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTNomeEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLNome.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLNome.setForeground(new java.awt.Color(102, 102, 102));
        jLNome.setText("Nome:");
        jLNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLNomeMouseClicked(evt);
            }
        });

        jLMarca.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLMarca.setForeground(new java.awt.Color(102, 102, 102));
        jLMarca.setText("Marca:");
        jLMarca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLMarcaMouseClicked(evt);
            }
        });

        jTMarcaEditar.setBackground(new java.awt.Color(235, 235, 235));
        jTMarcaEditar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTMarcaEditar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTMarcaEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLValorUnit.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLValorUnit.setForeground(new java.awt.Color(102, 102, 102));
        jLValorUnit.setText("Valor Unitario:");
        jLValorUnit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLValorUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLValorUnitMouseClicked(evt);
            }
        });

        jTValorUnitEditar.setBackground(new java.awt.Color(235, 235, 235));
        jTValorUnitEditar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTValorUnitEditar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTValorUnitEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLKm.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLKm.setForeground(new java.awt.Color(102, 102, 102));
        jLKm.setText("Km:");
        jLKm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLKm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLKmMouseClicked(evt);
            }
        });

        jTKmEditar.setBackground(new java.awt.Color(235, 235, 235));
        jTKmEditar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTKmEditar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTKmEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTKmEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTKmEditarActionPerformed(evt);
            }
        });

        jLAno.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLAno.setForeground(new java.awt.Color(102, 102, 102));
        jLAno.setText("Ano:");
        jLAno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLAno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLAnoMouseClicked(evt);
            }
        });

        jTAnoEditar.setBackground(new java.awt.Color(235, 235, 235));
        jTAnoEditar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTAnoEditar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTAnoEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLCampoDeEdicao.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLCampoDeEdicao.setForeground(new java.awt.Color(102, 102, 102));
        jLCampoDeEdicao.setText("Campo de Edição");
        jLCampoDeEdicao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLCampoDeEdicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLCampoDeEdicaoMouseClicked(evt);
            }
        });

        jBSalvarDialog.setBackground(new java.awt.Color(10, 60, 150));
        jBSalvarDialog.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBSalvarDialog.setForeground(new java.awt.Color(255, 255, 255));
        jBSalvarDialog.setText("Salvar");
        jBSalvarDialog.setBorder(null);
        jBSalvarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarDialogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLCampoDeEdicao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jBSalvarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTNomeEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLNome)
                            .addComponent(jLMarca)
                            .addComponent(jTMarcaEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTValorUnitEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLValorUnit)
                            .addComponent(jLKm)
                            .addComponent(jTKmEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLAno)
                            .addComponent(jTAnoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLCampoDeEdicao)
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTNomeEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLNome))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMarca)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTMarcaEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTValorUnitEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLValorUnit))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLKm)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTKmEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLAno)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTAnoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jBSalvarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 310, 530));

        jTPesquisaDialog.setBackground(new java.awt.Color(235, 235, 235));
        jTPesquisaDialog.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jTPesquisaDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTPesquisaDialog.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jTPesquisaDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 650, -1));

        jLabelPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPesquisar.setForeground(new java.awt.Color(153, 153, 153));
        jLabelPesquisar.setText("Pesquisar");
        jLabelPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPesquisarMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jPanelProdutosDialog.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabelaProdutosDialog.setModel(new javax.swing.table.DefaultTableModel(
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
        jTabelaProdutosDialog.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setViewportView(jTabelaProdutosDialog);

        JBCancelarDialog.setBackground(new java.awt.Color(204, 204, 204));
        JBCancelarDialog.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        JBCancelarDialog.setForeground(new java.awt.Color(102, 102, 102));
        JBCancelarDialog.setText("Cancelar");
        JBCancelarDialog.setBorder(null);

        jBDeletar.setBackground(new java.awt.Color(204, 0, 0));
        jBDeletar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBDeletar.setForeground(new java.awt.Color(255, 255, 255));
        jBDeletar.setText("Deletar");
        jBDeletar.setBorder(null);
        jBDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeletarActionPerformed(evt);
            }
        });

        jBEditar.setBackground(new java.awt.Color(10, 60, 150));
        jBEditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBEditar.setForeground(new java.awt.Color(255, 255, 255));
        jBEditar.setText("Editar");
        jBEditar.setBorder(null);
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProdutosDialogLayout = new javax.swing.GroupLayout(jPanelProdutosDialog);
        jPanelProdutosDialog.setLayout(jPanelProdutosDialogLayout);
        jPanelProdutosDialogLayout.setHorizontalGroup(
            jPanelProdutosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosDialogLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanelProdutosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelProdutosDialogLayout.createSequentialGroup()
                        .addComponent(jBDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(402, 402, 402)
                        .addComponent(JBCancelarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanelProdutosDialogLayout.setVerticalGroup(
            jPanelProdutosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosDialogLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelProdutosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutosDialogLayout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(JBCancelarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanelProdutosDialogLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelProdutosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1.add(jPanelProdutosDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 750, 530));

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        VendasDialog vendas = new VendasDialog(null, false, user, null);
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

    private void jLabelPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPesquisarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelPesquisarMouseClicked

    private void jBSalvarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarDialogActionPerformed
        edit();
        initTable();
        setTable();
    }//GEN-LAST:event_jBSalvarDialogActionPerformed

    private void jBDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeletarActionPerformed
        delete(); 
        initTable();
        setTable();
    }//GEN-LAST:event_jBDeletarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        setEdit();
        initTableListener();
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jLNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLNomeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLNomeMouseClicked

    private void jLMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLMarcaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLMarcaMouseClicked

    private void jLValorUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLValorUnitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLValorUnitMouseClicked

    private void jLKmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLKmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLKmMouseClicked

    private void jTKmEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTKmEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTKmEditarActionPerformed

    private void jLAnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLAnoMouseClicked

    private void jLCampoDeEdicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCampoDeEdicaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLCampoDeEdicaoMouseClicked

    
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
            java.util.logging.Logger.getLogger(ProdutoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User user = new User(0,0,0,"No User");
                ProdutoDialog dialog = new ProdutoDialog(new javax.swing.JFrame(), true, user);
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
    private javax.swing.JButton jBDeletar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBSalvarDialog;
    private javax.swing.JLabel jLAno;
    private javax.swing.JLabel jLCampoDeEdicao;
    private javax.swing.JLabel jLKm;
    private javax.swing.JLabel jLMarca;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLValorUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelPesquisar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelProdutosDialog;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTAnoEditar;
    private javax.swing.JTextField jTKmEditar;
    private javax.swing.JTextField jTMarcaEditar;
    private javax.swing.JTextField jTNome1;
    private javax.swing.JTextField jTNomeEditar;
    private javax.swing.JTextField jTPesquisaDialog;
    private javax.swing.JTextField jTValorUnitEditar;
    private javax.swing.JTable jTabelaProdutosDialog;
    // End of variables declaration//GEN-END:variables
}
