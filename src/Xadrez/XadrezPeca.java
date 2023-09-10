package Xadrez;

import MesaDoJogo.Peca;
import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;

public abstract class XadrezPeca extends Peca {
	
	private Cor cor;
	private int contadorMovimento;

	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContadorMovimento() {
		return contadorMovimento;
	}
	
	public void somarMovimento() {
		contadorMovimento++;
	}
	public void diminuirMovimento() {
		contadorMovimento--;
	}
	
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.paraPosicao(posicao);
	}
	
	protected boolean isPecaAdversaria(Posicao posicao) {
		XadrezPeca pecaXadrez = (XadrezPeca) getTabuleiro().peca(posicao);
		return pecaXadrez != null && pecaXadrez.getCor() != cor;
	}
}
