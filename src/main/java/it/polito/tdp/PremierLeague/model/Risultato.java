package it.polito.tdp.PremierLeague.model;

public class Risultato {

	private int goalCasa;
	private int goalTrasf;
	private int espulsiCasa;
	private int espulsiTrasf;
	public Risultato(int goalCasa, int goalTrasf, int espulsiCasa, int espulsiTrasf) {
		super();
		this.goalCasa = goalCasa;
		this.goalTrasf = goalTrasf;
		this.espulsiCasa = espulsiCasa;
		this.espulsiTrasf = espulsiTrasf;
	}
	/**
	 * @return the goalCasa
	 */
	public int getGoalCasa() {
		return goalCasa;
	}
	/**
	 * @param goalCasa the goalCasa to set
	 */
	public void setGoalCasa(int goalCasa) {
		this.goalCasa = goalCasa;
	}
	/**
	 * @return the goalTrasf
	 */
	public int getGoalTrasf() {
		return goalTrasf;
	}
	/**
	 * @param goalTrasf the goalTrasf to set
	 */
	public void setGoalTrasf(int goalTrasf) {
		this.goalTrasf = goalTrasf;
	}
	/**
	 * @return the espulsiCasa
	 */
	public int getEspulsiCasa() {
		return espulsiCasa;
	}
	/**
	 * @param espulsiCasa the espulsiCasa to set
	 */
	public void setEspulsiCasa(int espulsiCasa) {
		this.espulsiCasa = espulsiCasa;
	}
	/**
	 * @return the espulsiTrasf
	 */
	public int getEspulsiTrasf() {
		return espulsiTrasf;
	}
	/**
	 * @param espulsiTrasf the espulsiTrasf to set
	 */
	public void setEspulsiTrasf(int espulsiTrasf) {
		this.espulsiTrasf = espulsiTrasf;
	}
	
	
}
