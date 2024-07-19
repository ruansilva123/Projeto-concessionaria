/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

public class User{
    private int idUser;
    private int isManager;
    private int isSeller;
    private String nameUser;
    
    public User(int idUser, int isManager, int isSeller, String nameUser){
        this.idUser = idUser;
        this.isManager = isManager;
        this.isSeller = isSeller;
        this.nameUser = nameUser;
    }
    
    public int getIdUser(){
        return idUser;
    }
    
    public int getIsManager(){
        return isManager;
    }
    
    public int getIsSeller(){
        return isSeller;
    }
    
    public String getNameUser(){
        return nameUser;
    }
}