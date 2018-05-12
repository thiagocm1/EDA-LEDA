package graph;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe representativa de um grafo.
 */
public class Graph {

	private Set<Integer> vertexes;
	private Set<Edge> edges;

	public Graph() {
	}

	public Graph(List<Integer> inputVertexes, List<Integer> outputVertexes, List<Double> values, int numVertexes) {
		this.edges = new HashSet<Edge>();
		this.vertexes = new HashSet<Integer>();
		this.buildEdges(inputVertexes, outputVertexes, values, numVertexes);
		this.buildVertexes(numVertexes);
	}

	public Graph(List<Integer> inputVertexes, List<Integer> outputVertexes, int numVertexes) {
		this.edges = new HashSet<Edge>();
		this.vertexes = new HashSet<Integer>();
		this.buildEdges(inputVertexes, outputVertexes, numVertexes);
		this.buildVertexes(numVertexes);
	}

	/**
	 * Constr�i as arestas do Grafo. Um v�rtice de entrada ter� liga��o com o
	 * v�rtice de sa�da que tiver o mesmo �ndice e seu peso ser� o valor que est�
	 * contido no mesmo �ndice dos v�rtices, na lista de pesos. Exemplo:
	 * inputVertexes[0] formar� uma aresta com o v�rtice outputVertexes[0], que ter�
	 * peso value[0].
	 * 
	 * @param inputVertexes
	 *            - Lista contendo todos os v�rtices de entrada
	 * @param outputVertexes
	 *            - Lista contendo todos os v�rtices de sa�da.
	 * @param values
	 *            - Lista contendo todos os pesos das arestas do Grafo.
	 * 
	 *            TO-DO: Refatorar m�todo.
	 */
	private void buildEdges(List<Integer> inputVertexes, List<Integer> outputVertexes, List<Double> values, int numVertexes) {
		for (int i = 0; i < inputVertexes.size(); i++) {
			vertexes.add(inputVertexes.get(i));
			vertexes.add(outputVertexes.get(i));
			Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
			pair.put(inputVertexes.get(i), outputVertexes.get(i));
			Edge e = new Edge(values.get(i), inputVertexes.get(i), outputVertexes.get(i));
			this.edges.add(e);
		}
	}

	/**
	 * Sobrescrita do m�todo buildEdges para arestas sem peso.
	 * 
	 * @param inputVertexes
	 * @param outputVertexes
	 */
	private void buildEdges(List<Integer> inputVertexes, List<Integer> outputVertexes, int numVertexes) {
		for (int i = 0; i < inputVertexes.size(); i++) {
			vertexes.add(inputVertexes.get(i));
			vertexes.add(outputVertexes.get(i));
			Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
			pair.put(inputVertexes.get(i), outputVertexes.get(i));
			Edge e = new Edge(inputVertexes.get(i), outputVertexes.get(i));
			this.edges.add(e);
		}
	}
	
	private void buildVertexes(int numVertexes) {
		if(this.vertexes.size() < numVertexes) {
			int n = numVertexes - vertexes.size();
			
			for(int i = 0; i < n; i ++) {
				vertexes.add(vertexes.size() + 1);
			}
		}
	}

	public Set<Integer> getVertexes() {
		return vertexes;
	}

	public void setVertexes(Set<Integer> vertexes) {
		this.vertexes = vertexes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((vertexes == null) ? 0 : vertexes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (vertexes == null) {
			if (other.vertexes != null)
				return false;
		} else if (!vertexes.equals(other.vertexes))
			return false;
		return true;
	}

}
