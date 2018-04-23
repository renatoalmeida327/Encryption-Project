/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.criptografia;

import java.io.IOException;
import javax.swing.JOptionPane;
import static projeto.criptografia.Steganografia.decodeSteganography;
import static projeto.criptografia.Steganografia.encodeSteganography;

/**
 *
 * @author 1510028941
 */
public class ProjetoCriptografia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Cesar cesar = new Cesar();
        MonoAlfabetico mono = new MonoAlfabetico();
        Steganografia steg  = new Steganografia();
        
        
        String msg = JOptionPane.showInputDialog("Digite uma mensagem em Ingles para ser gravada: ");
        int chave = Integer.parseInt(JOptionPane.showInputDialog("Digite uma chave: "));
        String alfabeto1 = "abcdefghijklmnopqrstuvxwyz";
        String normal = "ABCDEFGHIJKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwyz";
        String embaralhado = "PXVRBTYJMZCISWLAFUDNHOGQEKpxvrbtyjmzciswlafudnhogqek";
        
        

        final String fileIn = "C:\\Users\\Renato Almeida\\Desktop\\ADS\\5º Semestre\\SAI\\Projeto Criptografia\\src\\projeto\\criptografia\\Brasil.png";
        final String fileOut = "C:\\Users\\Renato Almeida\\Desktop\\ADS\\5º Semestre\\SAI\\Projeto Criptografia\\src\\projeto\\criptografia\\NewBrasil.png";
        
        cesar.alfabetoCodificado(alfabeto1, chave);
        System.out.println(cesar.codificaCesar(msg, alfabeto1, cesar.alfabetoCodificado(alfabeto1, chave)));         
        System.out.println(mono.codificar(cesar.codificaCesar(msg, alfabeto1, cesar.alfabetoCodificado(alfabeto1, chave)), normal, embaralhado));         
        steg.encodeSteganography(mono.codificar(cesar.codificaCesar(msg, alfabeto1, cesar.alfabetoCodificado(alfabeto1, chave)), normal, embaralhado), fileIn, fileOut);
        
        steg.decodeSteganography(fileOut);
        System.out.println(mono.decodificar(steg.decodeSteganography(fileOut), embaralhado, normal));
        System.out.println(cesar.decodificaCesar(mono.codificar(steg.decodeSteganography(fileOut), embaralhado, normal), cesar.alfabetoCodificado(alfabeto1, chave), alfabeto1));
        
    }

}
