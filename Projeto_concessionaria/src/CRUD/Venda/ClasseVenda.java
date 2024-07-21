/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Venda;

/**
 *
 * @author moc3jvl
 */
public class ClasseVenda {
    private int idVenda;
    private java.sql.Date dataVenda;
    private java.math.BigDecimal comissaoVenda;
    private int clienteIdCliente;
    private int usuarioIdUsuario;

    public ClasseVenda(int idVenda, java.sql.Date dataVenda, java.math.BigDecimal comissaoVenda, int clienteIdCliente, int usuarioIdUsuario) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.comissaoVenda = comissaoVenda;
        this.clienteIdCliente = clienteIdCliente;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    // Getters e Setters
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public java.sql.Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(java.sql.Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public java.math.BigDecimal getComissaoVenda() {
        return comissaoVenda;
    }

    public void setComissaoVenda(java.math.BigDecimal comissaoVenda) {
        this.comissaoVenda = comissaoVenda;
    }

    public int getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(int clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }
}
