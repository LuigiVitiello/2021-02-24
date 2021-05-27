package it.polito.tdp.PremierLeague.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{

	public enum EventType {
		GOAL,
		ESPULSIONE,
		INFORTUNIO
	}
	
	private EventType tipo;
	private Team squadra;
	private int ordine;
	
	@Override
	public int compareTo(Evento e) {

		return this.ordine-e.ordine;
	}

	/**
	 * @return the tipo
	 */
	public EventType getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the squadra
	 */
	public Team getSquadra() {
		return squadra;
	}

	/**
	 * @param squadra the squadra to set
	 */
	public void setSquadra(Team squadra) {
		this.squadra = squadra;
	}

	/**
	 * @return the time
	 */
	public int getOrdine() {
		return ordine;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int ordine) {
		this.ordine = ordine;
	}

	public Evento(EventType tipo,Team squadra, int ordine) {
		super();
		this.tipo = tipo;
		this.squadra = squadra;
		this.ordine = ordine;
	}

	@Override
	public String toString() {
		return "Evento [tipo=" + tipo + ", squadra=" + squadra + ", ordine=" + ordine + "]";
	}
	
	
}
