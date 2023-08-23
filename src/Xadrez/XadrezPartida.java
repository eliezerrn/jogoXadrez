package Xadrez;

import MesaDoJogo.Peca;
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
	
	public boolean [][] movimentosPossiveis(XadrezPosicao posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicao(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public XadrezPeca executarJogada(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		
		validarPosicao(origem);
		validarPosicaoDestino(origem, destino);
		
		Peca pecaCapturada = moverPeca(origem,destino);
		return (XadrezPeca) pecaCapturada;
	}
	
	private Peca moverPeca(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca capturada = tabuleiro.removerPeca(destino);
		
		tabuleiro.informaPeca(p, destino);
		
		return capturada;
	}
	
	private void validarPosicao(Posicao posicao) {
		if(!tabuleiro.isPosicaoExiste(posicao)) {
			throw new XadrezExcessao("Não foi possível capturar a peça, ela não existe. (ERN XP2).");
		}
		if(!tabuleiro.peca(posicao).isTemMovimentoPossivel()) {
			throw new XadrezExcessao("Não existe movimentos possíveis para esta peça (ERN XP3");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new XadrezExcessao("Esta peça não pode se mover para este destino (ERN XP4)");
		}
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
