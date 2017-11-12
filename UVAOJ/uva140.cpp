#include <iostream>
#include <string>
#include <map>
#include <set>
#include <vector>
#define INF 999999999
using namespace std;

void parseGraph(map<char, set<char>>& graph, string& line) {
	size_t startPos = 0;
	

	while(startPos < line.length()) {
		size_t semicolon = line.find(';', startPos);

		if(semicolon == string::npos)
			semicolon = line.length();
		size_t colon = line.find(':', startPos);
		
		// pick out the desired section of string
		char left = line.substr(startPos, colon - startPos)[0];
		string right = line.substr(colon + 1, semicolon - colon - 1);

		// construct a subgraph centered at vertex 'left'
		for(int i = 0;i < right.length();i ++) 
			graph[left].insert(right[i]);
		
		// construct a subgraph in reverse
		for(int i = 0;i < right.length();i ++)
			graph[right[i]].insert(left);
		
		startPos = semicolon + 1;
	}
}

int compareAbsMax(int a, int b) {
	a = (a > 0 ? a : -a);
	b = (b > 0 ? b : -b);
	return a > b ? a : b;
}

// alternatively, can use next_permutation 
// since there are only 8! possibilities
void dfs(const int curDepth,
			int& minBandwidth,
			map<char, set<char>>& graph, 
			map<char, bool>& used, 
			map<char, int>& seq,
			vector<char>& ans) {

	int numOfVertices = graph.size();

	if(curDepth == numOfVertices) {
		// compute the current bandwidth
		int curMaxBandWidth = 0;
		
		for(auto& entry : seq) {
			char u = entry.first;
			set<char>& adjs = graph[u];
			for(auto& v : adjs)
				curMaxBandWidth = compareAbsMax(seq[v] - seq[u], curMaxBandWidth);
		}
		// update the global minBandwidth
		if(curMaxBandWidth < minBandwidth) {
			minBandwidth = curMaxBandWidth;

			for(auto& entry : seq) 
				ans[entry.second] = entry.first;
		}
		return;
	}

	for(auto& entry : graph) {
		char u = entry.first;

		// has been used or not adjacent to the previous character
		if(used[u])
			continue;
		seq[u] = curDepth;
		used[u] = true;
		dfs(curDepth + 1, minBandwidth, graph, used, seq, ans);
		used[u] = false;
	}

}

void output(vector<char>& ans, int minBandwidth) {
	for(auto u : ans)
		cout << u << " ";
	cout << "-> ";
	cout << minBandwidth << endl;
}


int main() {
	ios::sync_with_stdio(false);

	string line;
	while(getline(cin, line)) {
		if(line[0] == '#')
			break;

		// dos to unix
		if(line[line.length() - 1] == '\r') 
			line = line.substr(0, line.length() - 1);

		map<char, set<char>> graph;
		parseGraph(graph, line);

		map<char, bool> used;
		map<char, int> seq;
		vector<char> ans(graph.size());

		int minBandwidth = INF;
		dfs(0, minBandwidth, graph, used, seq, ans);
		output(ans, minBandwidth);
	}


	return 0;
}