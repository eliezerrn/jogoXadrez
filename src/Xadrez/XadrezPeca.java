package Xadrez;

import MesaDoJogo.Peca;
import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;

public abstract class XadrezPeca extends Peca {
	
	private Cor cor;

	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean isPecaAdversaria(Posicao posicao) {
		XadrezPeca pecaXadrez = (XadrezPeca) getTabuleiro().peca(posicao);
		return pecaXadrez != null && pecaXadrez.getCor() != cor;
	}
}
