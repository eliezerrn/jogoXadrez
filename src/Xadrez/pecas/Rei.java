package Xadrez.pecas;

import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.XadrezPeca;

public class Rei extends XadrezPeca {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean podeMover(Posicao posicao) {
		XadrezPeca p = (XadrezPeca) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//Acima
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna());
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Abaixo
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna());
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Noroeste
		p.setValores(posicao.getLinha() -1 , posicao.getColuna() - 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Noroeste
		p.setValores(posicao.getLinha() -1 , posicao.getColuna() + 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Suldoeste
		p.setValores(posicao.getLinha() +1 , posicao.getColuna() - 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Suldeste
		p.setValores(posicao.getLinha() +1 , posicao.getColuna() + 1);
		if(getTabuleiro().isPosicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		
		
		return matriz;
	}

}
