package Xadrez;

import Mesa.Tabuleiro;

public class XadrezPartida {

	private Tabuleiro tabuleiro;
	
	public XadrezPartida() {
		tabuleiro = new Tabuleiro(8, 8);
	}
	
	public XadrezPeca [][] getPecas(){
		XadrezPeca [][] matriz = new XadrezPeca [tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for(int l=0; l<tabuleiro.getLinhas(); l++) {
			for(int c=0; c<tabuleiro.getColunas(); c++) {
				matriz[l][c] = (XadrezPeca) tabuleiro.peca(l,c);
			}
		}
		
		return matriz;
	}
	
}
