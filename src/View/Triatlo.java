package View;

import java.util.concurrent.Semaphore;

import Controller.ThreadTriatlo;

public class Triatlo {
    
	public static void main(String[] args) {
		
		Semaphore semaPistola = new Semaphore(5);
		Semaphore semaColocacao = new Semaphore(1);

		
		for (int i = 1; i <= 25; i++) {
			Thread atleta = new ThreadTriatlo(i, semaColocacao, semaPistola);
			atleta.start();
		}
		
	}

}
