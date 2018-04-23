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
public class MonoAlfabetico {

    public static void main(String[] args) {

        
        String x = "";
        String normal = "ABCDEFGHIJKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwyz";
        String embaralhado = "PXVRBTYJMZCISWLAFUDNHOGQEKpxvrbtyjmzciswlafudnhogqek";
        String msgCod = "";
        String msgDecod = "";

        

        msgCod = codificar(x, normal, embaralhado);
        System.out.println("Mensagem codificada: ");
        System.out.println(msgCod);

        msgDecod = decodificar(msgCod, embaralhado, normal);
        System.out.println("Mensagem decodificada: ");
        System.out.println(msgDecod);

    }

    public static String codificar(String x, String normal, String embaralhado) {

        char temp[] = new char[x.length()];

        for (int i = 0; i < x.length(); i++) {
            temp[i] = x.charAt(i);
        }
        for (int j = 0; j < x.length(); j++) {
            for (int k = 0; k < normal.length(); k++) {
                if (x.charAt(j) == normal.charAt(k)) {
                    temp[j] = embaralhado.charAt(k);
                }
            }
        }
        String codificada = new String(temp);
        return codificada;
    }

    public static String decodificar(String msgCod, String embaralhado, String normal) {

        char temp[] = new char[msgCod.length()];

        for (int i = 0; i < msgCod.length(); i++) {
            temp[i] = msgCod.charAt(i);
        }
        for (int j = 0; j < msgCod.length(); j++) {
            for (int k = 0; k < embaralhado.length(); k++) {
                if (msgCod.charAt(j) == embaralhado.charAt(k)) {
                    temp[j] = normal.charAt(k);
                }
            }
        }
        String codificada = new String(temp);
        return codificada;
    }

}
