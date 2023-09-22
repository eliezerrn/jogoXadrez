package Xadrez.pecas;

import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.XadrezPeca;

public class Cavalo extends XadrezPeca {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean podeMover(Posicao posicao) {
		XadrezPeca p = (XadrezPeca) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() - 2);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getLinha() - 2 , posicao.getColuna() - 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() -2, posicao.getColuna() + 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() + 2);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() +2 , posicao.getColuna() + 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() +2 , posicao.getColuna() - 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() +1 , posicao.getColuna() - 2);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}

}
