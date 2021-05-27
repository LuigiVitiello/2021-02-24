package it.polito.tdp.PremierLeague.model;

import java.util.PriorityQueue;

import it.polito.tdp.PremierLeague.model.Evento.EventType;

public class Simulatore {

	PriorityQueue<Evento> codaEventi;
	
	int N;
	int idSquadraMigliore;
	int idSquadraCasa;
    int idSquadraTrasferta;
	int giocatoriCasa;
	int giocatoriTrasferta;
	int ordine;
	
	int goalCasa;
	int goalTrasf;
	int espulsiCasa;
	int espulsiTrasf;
	
	public Simulatore(int n, int idSquadraMigliore, int idSquadraCasa, int idSquadraTrasferta) {
		super();
		this.N = n;
		this.idSquadraMigliore = idSquadraMigliore;
		this.idSquadraCasa = idSquadraCasa;
		this.idSquadraTrasferta = idSquadraTrasferta;
	}
	
	public void init() {
		
		/*if(this.squadraCasa.teamID == this.idSquadraMigliore) {
			
		} else {
			
		}*/
		
		this.giocatoriCasa=11;
		this.giocatoriTrasferta=11;
		this.goalCasa=0;
		this.goalTrasf=0;
		this.codaEventi = new PriorityQueue<Evento>();
		this.ordine = 1;
		
		generaEventi(N);
		
	}
	
	public void generaEventi(int num) {
		//carico eventi
		double probabilita=0.0;
		while(ordine< (num+1)) {
			probabilita = Math.random();
			if(probabilita<=0.5) {
				Evento e = new Evento(EventType.GOAL,null,ordine);
				this.codaEventi.add(e);
				ordine++;
			} else if(probabilita>0.5 && probabilita<=0.8) {
				Evento e = new Evento(EventType.ESPULSIONE,null,ordine);
				this.codaEventi.add(e);
				ordine++;
			} else {
				Evento e = new Evento(EventType.INFORTUNIO,null,ordine);
				this.codaEventi.add(e);
				ordine++;
			}
		   }
		System.out.println("eventi aggiunti: "+(ordine-1));
	}
	public void simula() {
		while(!this.codaEventi.isEmpty()) {
			Evento e = this.codaEventi.poll();
			processEvent(e);
		}
	}
	
	public void processEvent(Evento e) {
		System.out.println("Stiamo simulando");
		switch(e.getTipo()) {
		 
		case GOAL:
			System.out.println("è goallll");
			if(this.giocatoriCasa>this.giocatoriTrasferta) {
				System.out.println("goal casa");
				this.goalCasa++;
			} else if(this.giocatoriCasa<this.giocatoriTrasferta) {
				System.out.println("goal trasf");
				this.goalTrasf++;
			} else {
				if(this.idSquadraCasa==this.idSquadraMigliore) {
					this.goalCasa++;
				} else {
					this.goalTrasf++;
				}
			}
			break;
			
		case ESPULSIONE:
			System.out.println("uhh espulso");
			double prob = Math.random();
			if(prob<=0.6) {
				if(this.idSquadraCasa==this.idSquadraMigliore) {
					System.out.println("casa");
					this.espulsiCasa++;
					this.giocatoriCasa--;
				} else {
					this.espulsiTrasf++;
					this.giocatoriTrasferta--;
				}
			} else {
				if(this.idSquadraCasa!=this.idSquadraMigliore) {
					this.espulsiCasa++;
					this.giocatoriCasa--;
				} else {
					System.out.println("trasferta");
					this.espulsiTrasf++;
					this.giocatoriTrasferta--;
				}
			}
			break;
			
		case INFORTUNIO: 
			System.out.println("qualcuno si è fatto male");
			double prob2 = Math.random();
			if(prob2<=0.5) {
				N=N+2;
				generaEventi(N);
			} else {
				N=N+3;
				generaEventi(N);
			}
			break;
		}
		
	}
	
	public Risultato getRisultato() {
		Risultato r= new Risultato(this.goalCasa,this.goalTrasf,this.espulsiCasa,this.espulsiTrasf);
		return r;
	}
}
