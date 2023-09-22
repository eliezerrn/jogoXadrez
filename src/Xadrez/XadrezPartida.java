package Xadrez;

import java.util.ArrayList;
import java.util.List;

import MesaDoJogo.Peca;
import MesaDoJogo.Posicao;
import MesaDoJogo.Tabuleiro;
import Xadrez.pecas.Bispo;
import Xadrez.pecas.Cavalo;
import Xadrez.pecas.Peao;
import Xadrez.pecas.Rainha;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class XadrezPartida {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if(testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezExcessao("VocÊ não pode se colocar em check");
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {		
			proximoTurno();
		}
		
		return (XadrezPeca) pecaCapturada;
	}
	
	private Peca moverPeca(Posicao origem, Posicao destino) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removerPeca(origem);
		p.somarMovimento();
		Peca capturada = tabuleiro.removerPeca(destino);
		
		tabuleiro.informaPeca(p, destino);
		
		if(capturada != null)
		{
			pecasDoTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}
		
		return capturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removerPeca(destino);
		p.diminuirMovimento();
		tabuleiro.informaPeca(p, origem);
		
		if(pecaCapturada != null) {
			tabuleiro.informaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasDoTabuleiro.add(pecaCapturada);
		}
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
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private XadrezPeca rei(Cor cor) {
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).toList();
		for(Peca p : list) {
			if(p instanceof Rei) {
				return (XadrezPeca)p;
			}
		}
		throw new IllegalStateException("Não existe o rei na cor " + cor);
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao posicaoRei = rei(cor).getXadrezPosicao().toPosicao();
		List<Peca> pecasOponentes = pecasDoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == oponente(cor)).toList();
		
		for(Peca p : pecasOponentes) {
			boolean [][] mat = p.movimentosPossiveis();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	} 
	
	private boolean testeCheckMate(Cor cor) {
		if(!testeCheck(cor)) {
			return false;
		}
		
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).toList();
		
		for(Peca p: list) {
			boolean[][] mat = p.movimentosPossiveis();
			
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao origem = ((XadrezPeca)p).getXadrezPosicao().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = moverPeca(origem,destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if(!testeCheck) {
							return false;
						}
						
					}
				}
			}
		}
		return true;
	}
	
	private void informaNovaPeca(char coluna, int linha, XadrezPeca xadrezPeca) {
		tabuleiro.informaPeca(xadrezPeca,new XadrezPosicao(coluna, linha).toPosicao());
		pecasDoTabuleiro.add(xadrezPeca);
	}
	
	private void iniciarPartida() {	
				
		informaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		informaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		informaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		informaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		informaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		informaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		informaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		informaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		informaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		informaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));
	
		informaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		informaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		informaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		informaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		informaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		informaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		informaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		informaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		informaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		informaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
	
	}
	
}