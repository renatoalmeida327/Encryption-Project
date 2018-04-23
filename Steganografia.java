package projeto.criptografia;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Steganografia {


	public static void encodeSteganography(String msg, String fileIn, String fileOut) throws IOException {
		
		BufferedImage img = ImageIO.read(new File(fileIn));
		msg = msg + "***";
		int contBits = 0, cont = 0;
		char charAtual = msg.charAt(0);
		char aux;
		int l, c, pixel[] = { 0, 0, 0, 0 };
		WritableRaster raster = img.getRaster();
		for (l = 0; l < img.getHeight() && cont < msg.length(); l++)
			for (c = 0; c < img.getWidth() && cont < msg.length(); c++) {
				raster.getPixel(c, l, pixel); // obtem um pixel
				if (contBits < 8) {
					aux = charAtual;
					/*
					 * vai deslocando o aux utilizando o contBits, que se desloca 8 vezes, pegando
					 * assim todos os bits do caracter e salva cada bit do caracter em um bit menos
					 * significativo do pixel da imagem.
					 */
					if ((char) (aux << (contBits) + 8) >> (7 + 8) == 1) // desloca pra direita e pega o bit pelo contBits
						pixel[0] = pixel[0] | 1;
					else
						pixel[0] = pixel[0] & (~1);
					raster.setPixel(c, l, pixel);
					contBits++;
				}

				else {
					// após 8 bits significa que terminou de gravar um caracter
					cont++;
					contBits = 0;
					if (cont < msg.length())
						charAtual = msg.charAt(cont); // pega o próximo caracter a ser gravado na imagem
				}
				
			}
		ImageIO.write(img, "png", new File(fileOut));
	}

	public static String decodeSteganography(String fileIn) throws IOException {
		String msg = "", aux = "";
		try {
		BufferedImage img = ImageIO.read(new File(fileIn));
		
		int qtdChar = 0, contBits = 0, cont = 0, mascara = 1, tam, l, c, pixel[] = { 0, 0, 0, 0 };
		char buffer = 0, char1, char2, char3;
		WritableRaster raster = img.getRaster();
		for (l = 0; l < img.getHeight(); l++)
			for (c = 0; c < img.getWidth(); c++) {
				raster.getPixel(c, l, pixel); // obtem um pixel
				cont++;
				if (msg.length() > 3) {
					// Quando encontrar *** significa que a mensagem acabou
					tam = msg.length();
					char1 = msg.charAt(tam - 1);
					char2 = msg.charAt(tam - 2);
					char3 = msg.charAt(tam - 3);
					if ((char1 == '*') && (char2 == '*') && (char3 == '*')) {
						for (int i = 0; i <= msg.length() - 4; i++)
							aux = aux + msg.charAt(i);

						msg = aux;
						return (msg);
					}
				}
				if (contBits < 8) {
					// Lê 8 bits, para poder formar um caracter
					if (contBits == 0) {
						if ((pixel[0] & mascara) == 1)
							buffer = (char) ((buffer) | 1); // desloca um bit pra esquerda e coloca o bit um
						else
							buffer = (char) ((buffer) & (~1)); // desloca um bit pra esquerda e coloca o bit zero
					} else {
						if ((pixel[0] & mascara) == 1)
							buffer = (char) ((buffer << 1) | 1); // desloca um bit pra esquerda e coloca o bit um
						else
							buffer = (char) ((buffer << 1) & (~1)); // desloca um bit pra esquerda e coloca o bit zero
					}
					contBits++;
				} else {
					// Depois de formar um caracter ele é concatenado com a mensagem e depois começa
					// a ler outro caracter
					contBits = 0;
					msg = msg + (char) buffer;
					buffer = 0;
				}
				
			}
		
		} catch (IOException e) {
			
				e.printStackTrace();
			} finally {
				System.out.println("A mensagem é: "+msg);
			}
		
		return msg;
		
	}
	
}