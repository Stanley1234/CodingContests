/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {

    
    private UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> graph) {
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        graph.put(node.label, newNode);
        
        for(UndirectedGraphNode neighbor : node.neighbors) {
        	if(graph.containsKey(neighbor.label))
                newNode.neighbors.add(graph.get(neighbor.label));
            else 
                newNode.neighbors.add(clone(neighbor, graph));
        }
        return newNode;
    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<Integer, UndirectedGraphNode> visited = new HashMap<>();
        return clone(node, visited);
    }
}
