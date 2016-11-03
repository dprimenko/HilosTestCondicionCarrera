package com.pspro;

class Contador {
	public static volatile int cuenta = 0;
}

class Sumador extends Thread {
	public void run() {
		for (int i = 0; i < 10000; i++) {
			Contador.cuenta++;
		}
	}
}

class Restador extends Thread {
	public void run() {
		for (int i = 0; i < 500; i++) {
			Contador.cuenta--;
		}
	}
}

public class HilosTestCondicionCarrera {
	public static void main(String[] args) throws InterruptedException {
		Sumador sumador = new Sumador();
		Restador restador = new Restador();
		
		sumador.start();
		restador.start();
		sumador.join();
		restador.join();
		System.out.println("Contador vale finalmente: " + Contador.cuenta);
	}
}
