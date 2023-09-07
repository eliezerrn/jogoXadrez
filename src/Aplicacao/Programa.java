package Aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.XadrezExcessao;
import Xadrez.XadrezPartida;
import Xadrez.XadrezPeca;
import Xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		XadrezPartida partida = new XadrezPartida();
		List<XadrezPeca> pecasCapturadas = new ArrayList<>(); 
		
		while(true) {
			try {
				
				UI.limparTela();
				//UI.imprimirTabuleiro(partida.getPecas());
				UI.imprimirPartida(partida, pecasCapturadas);
				
				System.out.println();
				System.out.println("Posição origem:");
				XadrezPosicao origem = UI.lerPosicaoXadrez(sc);
				
				boolean [][] possiveisMovimentos = partida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.imprimirTabuleiro(partida.getPecas(), possiveisMovimentos);
				
				System.out.println();
				System.out.println("Posicao destino:");
				XadrezPosicao destino = UI.lerPosicaoXadrez(sc);
				
				XadrezPeca pecaCapturada = partida.executarJogada(origem, destino);
				
				if(pecaCapturada != null) {
					pecasCapturadas.add(pecaCapturada);
				}
				
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
