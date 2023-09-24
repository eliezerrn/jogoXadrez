package Xadrez.pecas;

import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.XadrezPartida;
import Xadrez.XadrezPeca;

public class Peao extends XadrezPeca {

	 private XadrezPartida partida;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, XadrezPartida partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		if (getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)&& getTabuleiro().isPosicaoExiste(p2) && !getTabuleiro().isTemPeca(p2) && getContadorMovimento() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() -1);
			if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}	
			
			// Movimento especial en passant
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					
				if(getTabuleiro().isPosicaoExiste(esquerda) && isPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partida.getEnPassantVulnerable()) {
					matriz[esquerda.getLinha() -1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				
				if(getTabuleiro().isPosicaoExiste(direita) && isPecaAdversaria(direita) && getTabuleiro().peca(direita) == partida.getEnPassantVulnerable()) {
					matriz[direita.getLinha() -1][direita.getColuna()] = true;
				}								
			}
			
		} else {
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().isPosicaoExiste(p) && !getTabuleiro().isTemPeca(p)&& getTabuleiro().isPosicaoExiste(p2) && !getTabuleiro().isTemPeca(p2) && getContadorMovimento() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() -1);
			if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().isPosicaoExiste(p) && isPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Movimento especial en passant
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					
				if(getTabuleiro().isPosicaoExiste(esquerda) && isPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partida.getEnPassantVulnerable()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				
				if(getTabuleiro().isPosicaoExiste(direita) && isPecaAdversaria(direita) && getTabuleiro().peca(direita) == partida.getEnPassantVulnerable()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}								
			}
		}								
		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
