package projeto.criptografia;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1510028941
 */
public class Cesar {

    public static void main(String[] args) {

        String alfabeto1 = "abcdefghijklmnopqrstuvxwyz";        
        String msg = "";      
        int chave = 0;

        String alfabeto2 = alfabetoCodificado(alfabeto1, chave);
        System.out.println("Alfabeto deslocado: \n" + alfabeto2 + "\n");

        String msgCod = codificaCesar(msg, alfabeto1, alfabeto2);
        System.out.println("Mensagem Codificada: \n" + msgCod + "\n");

        String msgDecod = decodificaCesar(msgCod, alfabeto2, alfabeto1);
        System.out.println("Mensagem decodificada: \n" + msgDecod + "\n");

    }

    public static String alfabetoCodificado(String alfabeto, int chave) {        

        String codificar = alfabeto.substring(chave, alfabeto.length()) + alfabeto.substring(0, chave);

        return codificar;

    }

    public static String codificaCesar(String msg, String alfabeto1, String alfabeto2) {      
        

        String msgCod = "";

        for (int i = 0; i < msg.length(); i++) {
            int local = alfabeto1.indexOf(msg.charAt(i));

            if (local >= 0) {

                msgCod += alfabeto2.charAt(local);
            }

        }
        return msgCod;
    }

    public static String decodificaCesar(String msgCod, String alfabeto2, String alfabeto1) {

        String msgDecod = "";

        for (int i = 0; i < msgCod.length(); i++) {
            int local = alfabeto2.indexOf(msgCod.charAt(i));

            if (local >= 0) {

                msgDecod += alfabeto1.charAt(local);
            }

        }
        return msgDecod;
    }

}
