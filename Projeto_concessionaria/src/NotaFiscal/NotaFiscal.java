package NotaFiscal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

//Data de venda
//Dados do cliente
//Produtos comprados
//Valor unutário
//Valor total

public class NotaFiscal {
     
    public void gerarNotaFiscal(){
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH-mm-ss dd-MM-yyyy");
        String dataFormatada = timeNow.format(formato);
        
        Document notaFiscal = new Document();
        
        try {
            PdfWriter.getInstance(notaFiscal, new FileOutputStream("NotaFiscal - "+dataFormatada+".pdf"));
            
            notaFiscal.open();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotaFiscal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notaFiscal.close();
    }
}
