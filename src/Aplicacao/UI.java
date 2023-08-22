package Aplicacao;

import Xadrez.XadrezPeca;

public class UI {

	public static void imprimirTabuleiro(XadrezPeca[][] pecas) {
		for (int linha =0; linha<pecas.length; linha++) {
			System.out.print((8 - linha) + " ");
			for (int coluna = 0; coluna<pecas.length; coluna ++)
			{
				imprimirPeca(pecas[linha][coluna]);
			}
			System.out.println("");
		}
		System.out.println("  A B C D E F G H");
	}
	
	private static void imprimirPeca(XadrezPeca peca) {
		if (peca == null) {
			System.out.print("-");
		} else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
