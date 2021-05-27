package it.polito.tdp.PremierLeague.model;

public class GiocatoreMigliore {

	Player p;
	double delta;
	public GiocatoreMigliore(Player p, double delta) {
		
		this.p = p;
		this.delta = delta;
	}
	/**
	 * @return the p
	 */
	public Player getP() {
		return p;
	}
	/**
	 * @param p the p to set
	 */
	public void setP(Player p) {
		this.p = p;
	}
	/**
	 * @return the delta
	 */
	public double getDelta() {
		return delta;
	}
	/**
	 * @param delta the delta to set
	 */
	public void setDelta(double delta) {
		this.delta = delta;
	}
	@Override
	public String toString() {
		return "GiocatoreMigliore " + p + " delta= " + delta ;
	}
	
	
	
}
