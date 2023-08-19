package Aplicacao;

import Mesa.Posicao;
import Mesa.Tabuleiro;
import Xadrez.XadrezPartida;

public class Programa {

	public static void main(String[] args) {
		
		XadrezPartida partida = new XadrezPartida();
		UI.imprimirTabuleiro(partida.getPecas());

	}

}
