package Aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.Cor;
import Xadrez.XadrezPartida;
import Xadrez.XadrezPeca;
import Xadrez.XadrezPosicao;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static XadrezPosicao lerPosicaoXadrez(Scanner sc) {
		
		try {
			String texto = sc.nextLine();
			
			char coluna = texto.charAt(0);
			int linha = Integer.parseInt(texto.substring(1));
			
			return new XadrezPosicao(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posição do xadrez, as posições vão de a até h e 0 a 8. (ERN UI01)");
		}
	}
	
	public static void imprimirPartida(XadrezPartida partida) {
		imprimirTabuleiro(partida.getPecas());
		System.out.println();
		System.out.println("Turno :" + partida.getTurno());
		System.out.println("Aguardando jogador: " + partida.getJogadorAtual());
		
	}
	
	public static void imprimirTabuleiro(XadrezPeca[][] pecas) {
		for (int linha = 0; linha < pecas.length; linha++) {
			System.out.print((8 - linha) + " ");
			for (int coluna = 0; coluna < pecas.length; coluna++) {
				imprimirPeca(pecas[linha][coluna], false);
			}
			System.out.println("");
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void imprimirTabuleiro(XadrezPeca[][] pecas, boolean [][] movimentosPossiveis) {
		for (int linha = 0; linha < pecas.length; linha++) {
			System.out.print((8 - linha) + " ");
			for (int coluna = 0; coluna < pecas.length; coluna++) {
				imprimirPeca(pecas[linha][coluna], movimentosPossiveis[linha][coluna]);
			}
			System.out.println("");
		}
		System.out.println("  A B C D E F G H");
	}
	
	private static void imprimirPeca(XadrezPeca peca, boolean corDeFundo) {
		if(corDeFundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
    	if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
}
