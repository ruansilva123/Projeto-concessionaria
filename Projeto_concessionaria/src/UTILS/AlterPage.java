/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AlterPage {
    public static void alterPage(int function, Component atualPage, Component nextPage){
        if(function == 1){
            if(atualPage instanceof JFrame){
                ((JFrame)atualPage).dispose();
            }else{
                if(atualPage instanceof JDialog){
                    ((JDialog)atualPage).dispose();
                }
            }
            nextPage.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Você não possui permissão para acessar esta aba!");
        }
    }
}
