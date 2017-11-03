#include <iostream>
#include <array>
#include <set>
#include <map>
#include <queue>
#include <algorithm>
using namespace std;

/*

Idea:
- construct a graph with useful information
- check if the graph contains a euler path(a path including every edge exactly once)

Properties:
A directed graph has an euler path if and only 
1. the graph is connected
2. at most one vertex has inDegree - outDegree == 1
   at most one vertex has outDegree - inDegree == 1
   other vertex has inDegree == outDegree
*/

bool isEulerian(array<int, 26>& ins, array<int, 26>& outs) {
	int c1 = 0;
	int c2 = 0;

	for(int i = 0;i < 26;i ++) {
		if(ins[i] == outs[i])
			continue;
		if(ins[i] == outs[i] - 1)
			c1 ++;
		else if(ins[i] - 1 == outs[i])
			c2 ++;
		else
			return false;
	}
	return c1 <= 1 && c2 <= 1;
}

bool isConnected(array<array<bool, 26>, 26>& graph,
					array<int, 26>& ins, array<int, 26>& outs) {


	// if a index's indegree or outdegree > 0
	// then the vertex is included in the graph
	set<int> vertices;
	map<int, bool> visited;
	queue<int> q;

	// choose the starting point
	// before this function is called, we checked if condition (2) is satisfied
	// if all vertices has an even degree, then any vertex can be the starting point
	// if one vertex has in - out == 1, then there must be another one with out - in  == 1
	// choose the one with out - in == 1 as starting point
	int startVertex = -1;
	for(int i = 0;i < 26;i ++) {
		if(ins[i] > 0 || outs[i] > 0) {
			vertices.insert(i);

			if(outs[i] == ins[i] + 1)
				startVertex = i;
		}
	}
	if(startVertex == -1)
		startVertex = *vertices.begin();
	q.push(startVertex);
	visited[startVertex] = true;

	// a graph is connected if there exists a spanning tree
	while(!q.empty()) {
		int curIndex = q.front();
		q.pop();

		for(int nextIndex = 0;nextIndex < 26;nextIndex ++) {
			if(visited[nextIndex] || !graph[curIndex][nextIndex])
				continue;
			q.push(nextIndex);
			visited[nextIndex] = true;
		}
	}

	for(auto vertex : vertices) {
		if(!visited[vertex])
			return false;
	}
	return true;
}

int main() {
	ios::sync_with_stdio(false);

	int T;
	cin >> T;
	while(T -- > 0) {
		array<array<bool, 26>, 26> graph;
		for_each(graph.begin(), graph.end(), [](array<bool,26>& row) {row.fill(0);});

		array<int, 26> inDegree;
		array<int, 26> outDegree;

		inDegree.fill(0);
		outDegree.fill(0);

		// read in
		int N;
		cin >> N;

		string word;
		while(N -- > 0) {
			cin >> word;
			inDegree[word[word.length() - 1] - 'a'] ++;
			outDegree[word[0] - 'a'] ++;
			graph[word[0] - 'a'][word[word.length() - 1] - 'a'] = true;
		}

		// if condition (2) and condition (1) are satisfied
		if(!isEulerian(inDegree, outDegree) || !isConnected(graph, inDegree, outDegree))
			cout << "The door cannot be opened." << endl;
		else
			cout << "Ordering is possible." << endl;
	}


	return 0;
}
