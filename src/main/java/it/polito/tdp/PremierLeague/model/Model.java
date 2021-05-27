package it.polito.tdp.PremierLeague.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	PremierLeagueDAO dao;
	private Graph<Player,  DefaultWeightedEdge> grafo;
	private Map<Integer,Player> idMap;
	private Map<Integer, Team> mapSquadre;
	
	public Model() {
		this.dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer,Player>();
		this.dao.listAllPlayers(idMap);
		this.mapSquadre = new HashMap<Integer,Team>();
	}
	
	public void creaGrafo(Match m) {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

		this.dao.listAllTeams(mapSquadre);
		//aggiungo i vertici		
		Graphs.addAllVertices(this.grafo,this.dao.getVertici(m, idMap));
		
		//aggiungo archi
		for(Adiacenza a: this.dao.getAdiacenze(m, idMap)) {
			if(a.peso>=0) {
			    if(this.grafo.containsVertex(a.p1) && this.grafo.containsVertex(a.p2))
				    Graphs.addEdge(this.grafo, a.p1, a.p2, a.peso);
			}else {
				if(this.grafo.containsVertex(a.p1) && this.grafo.containsVertex(a.p2))
			        Graphs.addEdge(this.grafo, a.p2, a.p1, (-1)*a.peso);
			}
		}
		
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Match> getAllMatches(){
		List<Match> matches = this.dao.listAllMatches();
		Collections.sort(matches , new Comparator<Match>() {
			public int compare(Match o1,Match o2) {
				return o1.getMatchID().compareTo(o2.getMatchID());
			}
		});
		return matches;
	}
	
	public GiocatoreMigliore getMigliore() {
		if(grafo == null) {
			return null;
		}
		
		Player best = null;
		Double maxDelta = (double)Integer.MIN_VALUE;
		
		for(Player p: this.grafo.vertexSet()) {
			//calcolo somma pesi archi uscenti
			double pesoUscente = 0.0;
			for(DefaultWeightedEdge edge : this.grafo.outgoingEdgesOf(p)) {
				pesoUscente += this.grafo.getEdgeWeight(edge);
			}
			
			//calcolo somma pesi archi entranti
			double pesoEntrante =0.0;
			for(DefaultWeightedEdge edge : this.grafo.incomingEdgesOf(p)) {
				pesoEntrante += this.grafo.getEdgeWeight(edge);
			}
			
			double delta = pesoUscente -pesoEntrante;
			if(delta>maxDelta) {
				best = p;
				maxDelta = delta;
			}
		}
		return new GiocatoreMigliore(best,maxDelta);
	}

	public Graph<Player,DefaultWeightedEdge> getGrafo() {
		return this.grafo;
	}
	
	public Risultato simulaPartita(Match m, int N) {
		int idCasa = m.getTeamHomeID();
		int idTrasf = m.getTeamAwayID();
		int idMigliore = getMigliore().p.idSquad;
		Simulatore s = new Simulatore(N,idMigliore,idCasa,idTrasf);
		s.init();
		s.simula();
		return s.getRisultato();
	}
	
	public Team getTeam(int id) {
		return this.mapSquadre.get(id);
	}
}
