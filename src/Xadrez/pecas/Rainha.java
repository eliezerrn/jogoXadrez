package Xadrez.pecas;

import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.XadrezPeca;

public class Rainha extends XadrezPeca {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() -1);
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() -1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		p.setValores(posicao.getLinha(), posicao.getColuna() +1);
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() -1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// nordeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() +1);
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() +1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// suldeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() +1);
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1, p.getColuna() +1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// suldoeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() -1);
		while(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1, p.getColuna() -1);
		}
		if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}
}
