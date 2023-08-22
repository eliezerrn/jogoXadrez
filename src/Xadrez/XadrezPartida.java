package Xadrez;

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
	
	private void informaNovaPeca(char coluna, int linha, XadrezPeca xadrezPeca) {
		tabuleiro.informaPeca(xadrezPeca,new XadrezPosicao(coluna, linha).toPosicao());
	}
	
	private void iniciarPartida() {	
				
		informaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		informaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        informaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        informaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        informaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        informaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        informaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        informaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        informaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        informaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        informaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        informaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
	
}
