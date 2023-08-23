package Aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.XadrezExcessao;
import Xadrez.XadrezPartida;
import Xadrez.XadrezPeca;
import Xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		XadrezPartida partida = new XadrezPartida();
		
		while(true) {
			try {
				
				UI.limparTela();
				UI.imprimirTabuleiro(partida.getPecas());
				System.out.println();
				System.out.println("Posição origem:");
				XadrezPosicao origem = UI.lerPosicaoXadrez(sc);
				
				System.out.println();
				System.out.println("Posicao destino:");
				XadrezPosicao destino = UI.lerPosicaoXadrez(sc);
				
				XadrezPeca pecaCapturada = partida.executarJogada(origem, destino);
				
			} catch(XadrezExcessao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

	}

}
