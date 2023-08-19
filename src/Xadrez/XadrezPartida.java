package Xadrez;

import java.awt.MultipleGradientPaint.ColorSpaceType;

import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class XadrezPartida {

	private Tabuleiro tabuleiro;
	
	public XadrezPartida() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
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
	
	private void iniciarPartida() {
		tabuleiro.lugarPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 1));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		
	}
	
}
