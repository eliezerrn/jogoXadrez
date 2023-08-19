package MesaDoJogo;

import Xadrez.pecas.Torre;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca [][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {	
		if(linhas < 1 || colunas < 1) {
			throw new TabuleiroExcessao("Erro criando tabuleiro: É necessário que tenha pelo menos 1 linha e uma coluna! ERN03");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		
		pecas = new Peca [linhas][colunas];
	}
	

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca (int linha, int coluna) {
		if(!isPosicaoExiste(linha,coluna)) {
			throw new TabuleiroExcessao("Posição não existe no tabuleiro!(ERN01)");
		}
		return pecas [linha][coluna];
	}

	public Peca peca (Posicao posicao) {
		if(!isPosicaoExiste(posicao)) {
			throw new TabuleiroExcessao("Posição não existe no tabuleiro!(ERN02)");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void lugarPeca(Peca peca, Posicao posicao) {
		if(isTemPeca(posicao)) {
			throw new TabuleiroExcessao("Já existe peça na posicção " + posicao + "! (ERN04)");
		}
		
		pecas [posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean isPosicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < this.linhas && coluna >= 0 && coluna < this.colunas;
	}
	
	public boolean isPosicaoExiste(Posicao posicao) {
		return isPosicaoExiste(posicao.getLinha(),posicao.getColuna());
	}
	
	public boolean isTemPeca(Posicao posicao) {
		if(!isPosicaoExiste(posicao)) {
			throw new TabuleiroExcessao("Posição não existe no tabuleiro!(ERN05)");
		}
		return peca(posicao) != null;		
	}
}
