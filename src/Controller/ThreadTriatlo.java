package Controller;

import java.util.concurrent.Semaphore;

public class ThreadTriatlo extends Thread{

    private static int posicao = 0;
    private static int[][] colocacao = new int[25][2];
	private Semaphore semaColocacao;
	private Semaphore semaPistola;
	private int pontosTiros = 0;
	private int idAtleta;
	
	public ThreadTriatlo(int idAtleta, Semaphore semaColocacao, Semaphore semaPistola) {
		super();
		this.idAtleta = idAtleta;
		this.semaPistola = semaPistola;
		this.semaColocacao = semaColocacao;
	}
	
	@Override
	public void run() {

        correr();
        try {
			semaPistola.acquire();
			atirar();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			semaPistola.release();
		}
      
        pedalar();
        
        try {
        	semaColocacao.acquire();
        	pontuacao();
        }catch (Exception e) {
			System.err.println("Deu erro ao colocar colocacao: " + e);
		}finally {
			semaColocacao.release();
		}
        
		
		super.run();
	}

	private void correr() {
		
		System.out.println("O atleta " + idAtleta + " comecou a correr");
		
		int distanciaPercorrida = 0;
		
		while(3000 > distanciaPercorrida) {
			
			distanciaPercorrida += (int)((Math.random()*6)+20);
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("O atleta " + idAtleta + " terminou de correr");
		
	}
	
	private void atirar() {
		
		int tiros = 0;
		
		System.out.println("O atleta " + idAtleta + " Comecou a dar tiro");
		
		while(3 > tiros) {
			
			try {
				
				pontosTiros += (int)(Math.random()*11);
				sleep((int)((Math.random()*2501)+500));
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			tiros++;
		}
		
		System.out.println("O atleta " + idAtleta + " Pontuou " + pontosTiros + " Nos tiros");
		
	}
	
	private void pedalar() {
		
		System.out.println("O atleta " + idAtleta + " Comecou a pedalar");
		
		int distanciaPercorrida = 0;
		
		while(5000 > distanciaPercorrida) {
			
			distanciaPercorrida += (int)((Math.random()*11)+30);
			try {
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("O atleta " + idAtleta + " Terminou de pedalar");
		
	}
	
	private void pontuacao() {
		
		int pont = 250;
		
		for (int i = 0; i < 25; i++) {
			
			if(i == posicao) {
				colocacao[i][0] = idAtleta;
				colocacao[i][1] = pont + pontosTiros;
				System.out.println("O atleta " + idAtleta + " Chegou em " + (posicao+1)+ "° e ficou com a pontuação de chegada de: " + pont + ". mas vamos ver como vai ser o placar final!!!" );
			}
			pont -= 10;
		}
		
		posicao = posicao + 1;
		
		if(posicao == 25) {
			
			organizar();
			
		}
		
	}

	private void organizar() {
		
		for (int i = 0; i < colocacao.length; i++) {
			for (int j = i+1; j < colocacao.length; j++) {
				if(colocacao[i][1] < colocacao[j][1]) {
					int[][] aux = colocacao;
					colocacao[i][0] = colocacao[j][0];
					colocacao[i][1] = colocacao[j][1];
					colocacao[j][0] = aux[i][0];
					colocacao[j][1] = aux[i][1];
				}
			}
		}
		
		imprimir();
		
	}

	private void imprimir() {
		
		for (int i = 0; i < colocacao.length; i++) {
			
			System.out.println("O atleta Nº: " + colocacao[i][0] + " sua pontuação foi: " + colocacao[i][1]);
			
		}
		
	}
	
}
