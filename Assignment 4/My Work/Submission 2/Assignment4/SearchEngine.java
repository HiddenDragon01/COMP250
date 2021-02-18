package Sorting;

import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	
	
	public void crawlAndIndex(String url) throws Exception {
		
		if (this.internet.vertexList.size() == 0) {
			this.internet.addVertex(url);
		}
		
		
		ArrayList<String> content = this.parser.getContent(url);
		this.internet.setVisited(url, true);	
		
		for (int i = 0; i < content.size(); i++) {
			
						
			
			
			if ((this.wordIndex.get(content.get(i))) == null) {
				ArrayList<String> newurl = new ArrayList<String>();
				newurl.add(url);
				this.wordIndex.put(content.get(i), newurl);
				
				
			} else {
				
				boolean didBreak = false;
				ArrayList<String> urls = this.wordIndex.get(content.get(i));
							
				
				for (int j = 0; j < urls.size(); j++) {
					
					if (urls.get(j).equals(url)) {
						
						didBreak = true;
						break;
					}
					
				}
				if (!didBreak) {
					urls.add(url);
					this.wordIndex.put(content.get(i),urls);
				}
				
				
			}
			
		}
		
		
		ArrayList<String> neighbors = parser.getLinks(url);
		
		for (int i = 0; i < neighbors.size(); i++) {
									
			if (!this.internet.getVisited(neighbors.get(i))) {
				
				
				this.internet.addVertex(neighbors.get(i));
				
				this.internet.addEdge(url, neighbors.get(i));

				crawlAndIndex(neighbors.get(i));
				
				
			} else {
				
				this.internet.addEdge(url, neighbors.get(i));
				
				
			}
			
		}
		
		
		
	}
	
	
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		
		ArrayList<String> allVertices = this.internet.getVertices();
		
		ArrayList<Double> rankLastIteration = new ArrayList<Double>();
		
		ArrayList<Double> rankThisIteration = new ArrayList<Double>();
		
		double check = 0;
		
		double keepTrack = 0;
		
		
		for (int i = 0; i < allVertices.size(); i++) {
			
			this.internet.setPageRank(allVertices.get(i), 1);
			rankLastIteration.add(1.0);
		}
		
		rankThisIteration = computeRanks(allVertices);
		
		
		
		for (int i = 0; i < allVertices.size(); i++) {
			
			
			check = Math.abs(rankLastIteration.get(i) - rankThisIteration.get(i)); 
			
			if (check < epsilon) {
				keepTrack++;
			}
			
			
		}
		
		if (keepTrack == allVertices.size()) {
			return;
			
		}
		
		for(int j = 0; j < rankLastIteration.size(); j++) {
			
			internet.setPageRank(allVertices.get(j), rankThisIteration.get(j));
			
			
		}
	
		keepTrack = 0;
		check = 0;
		
		while (keepTrack != allVertices.size()) {
			
			keepTrack = 0;
			check = 0;
			
			rankLastIteration = rankThisIteration;
			rankThisIteration = computeRanks(allVertices);
			
			
			for(int j = 0; j < rankThisIteration.size(); j++) {
				
				internet.setPageRank(allVertices.get(j), rankThisIteration.get(j));
					
					
			}
			
			for (int i = 0; i < allVertices.size(); i++) {
			
				
				
				check = Math.abs(rankLastIteration.get(i) - rankThisIteration.get(i)); 
				
				if (check < epsilon) {
					keepTrack++;
				}
				
			}
				
				
			
				
			
			
		}
		
		
		
		
	}

	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
				
		
		
		ArrayList<String> edgesInto = new ArrayList<String>();
		
		ArrayList<Double> rankList = new ArrayList<Double>();
		
		double rank;
		
		double sumrankdivout = 0;
		
		for (int i = 0; i < vertices.size(); i++) {
			
			edgesInto = this.internet.getEdgesInto(vertices.get(i));
			
			
			
			for (int j = 0; j < edgesInto.size(); j++) {
				double d = this.internet.getPageRank(edgesInto.get(j));
				double s = this.internet.getOutDegree(edgesInto.get(j));
					
				sumrankdivout += (this.internet.getPageRank(edgesInto.get(j)))/(this.internet.getOutDegree(edgesInto.get(j)));
				int b = 0;
			}
			
			rank = (sumrankdivout * .5) + .5;
			rankList.add(rank);
			
			
			sumrankdivout = 0;	
				
			
			
		}
		
		
		return rankList;
		
	}

	
	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {
		
		
		ArrayList<String> queryUrls = new ArrayList<String>();
		
		queryUrls = this.wordIndex.get(query);
		
		HashMap<String, Double> hashRanks = new HashMap<String, Double>();
		
		for (int i = 0; i < queryUrls.size(); i++) {
			
			hashRanks.put(queryUrls.get(i), this.internet.getPageRank(queryUrls.get(i)));
						
		}
		
		ArrayList<String> sortedQueryUrls = Sorting.fastSort(hashRanks);
		
		return sortedQueryUrls;
		
	}
}
