/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

import MODULO_INICIAL.Login;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class LogoutSystem {
    public static void logoutSystem(Component component){
        Login login = new Login();
        if(component instanceof JFrame){
            ((JFrame) component).dispose();
        }else{
            if(component instanceof JDialog){
                ((JDialog) component).dispose();
            }
        }
        login.setVisible(true);
    }
}
