package Aplicacao;

import Xadrez.XadrezPartida;

public class Programa {

	public static void main(String[] args) {
		
		XadrezPartida partida = new XadrezPartida();
		UI.imprimirTabuleiro(partida.getPecas());

	}

}
