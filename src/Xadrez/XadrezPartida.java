package Xadrez;

import java.util.ArrayList;
import java.util.List;

import MesaDoJogo.Peca;
import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class XadrezPartida {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List <Peca> pecasDoTabuleiro = new ArrayList<>();
	private List <Peca> pecasCapturadas = new ArrayList<>();
	
	public XadrezPartida() {
		tabuleiro = new Tabuleiro(8, 8);
		
		turno = 1;
		jogadorAtual = Cor.BRANCO;		
		iniciarPartida();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
		
		proximoTurno();
		
		return (XadrezPeca) pecaCapturada;
	}
	
	private Peca moverPeca(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca capturada = tabuleiro.removerPeca(destino);
		
		tabuleiro.informaPeca(p, destino);
		
		if(capturada != null)
		{
			pecasDoTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}
		
		return capturada;
	}
	
	private void validarPosicao(Posicao posicao) {
		if(!tabuleiro.isPosicaoExiste(posicao)) {
			throw new XadrezExcessao("Não foi possível capturar a peça, ela não existe. (ERN XP2).");
		}
		
		if(jogadorAtual != ((XadrezPeca)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcessao("A peça escolhida não é sua. (ERN XP6");
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
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private void informaNovaPeca(char coluna, int linha, XadrezPeca xadrezPeca) {
		tabuleiro.informaPeca(xadrezPeca,new XadrezPosicao(coluna, linha).toPosicao());
		pecasDoTabuleiro.add(xadrezPeca);
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
