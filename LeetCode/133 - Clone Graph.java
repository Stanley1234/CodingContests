/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    private Map<Integer, UndirectedGraphNode> mapper = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        mapper.put(node.label, cloned);
        for(UndirectedGraphNode neigh : node.neighbors) {
            if(mapper.containsKey(neigh.label)) {
                cloned.neighbors.add(mapper.get(neigh.label));
            } else {
                cloned.neighbors.add(cloneGraph(neigh));
            }
        }
        return cloned;
    }
}
