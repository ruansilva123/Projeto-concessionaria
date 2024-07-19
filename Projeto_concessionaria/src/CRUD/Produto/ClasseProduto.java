/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Produto;

/**
 *
 * @author moc3jvl
 */

public class ClasseProduto {
    private int idProduto;
    private String nomeProduto;
    private String marcaProduto;
    private double valorUnitarioProduto;
    private int kmProduto;
    private int anoProduto;

    // Construtor padrão
    public ClasseProduto() {}

    // Construtor com parâmetros
    public ClasseProduto(int idProduto, String nomeProduto, String marcaProduto, double valorUnitarioProduto, int kmProduto, int anoProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.marcaProduto = marcaProduto;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.kmProduto = kmProduto;
        this.anoProduto = anoProduto;
    }

    // Getters and Setters

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public double getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setValorUnitarioProduto(double valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
    }

    public int getKmProduto() {
        return kmProduto;
    }

    public void setKmProduto(int kmProduto) {
        this.kmProduto = kmProduto;
    }

    public int getAnoProduto() {
        return anoProduto;
    }

    public void setAnoProduto(int anoProduto) {
        this.anoProduto = anoProduto;
    }
}

