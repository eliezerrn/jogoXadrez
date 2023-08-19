package Xadrez.pecas;

import Mesa.Tabuleiro;
import Xadrez.Cor;
import Xadrez.XadrezPeca;

public class Torre extends XadrezPeca {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "T";
	}
}
