/*
error: TIME_LIMIT_EXCEEDED
*/

#include <iostream>
#include <queue>
#include <map>
#include <vector>
#define DEBUG 0
using namespace std;

class GhostMover {
	int M, N;
	int numOfGhosts;

	int ans;

	int initalPos[3];
	int endPos[3];
	map<int, vector<int>> graph;

	bool withinBoundary(int r, int c);
	bool occupied(int* curState, int curNode, int checkedNode);

	int hash(int aId, int bId, int cId);
	void deHash(int curState, int& aId, int& bId, int& cId);

	void dfsMove(int* curState, bool* chosen,
				map<int, bool>& visited, queue<int>& q, 
				map<int, int>& parent, int parentState,
				int depth = 0);
public:
	GhostMover(int m, int n, int k);

	void readMap();
	void solve();
	void output();
};

GhostMover::GhostMover(int m, int n, int k) : M {m}, N {n}, numOfGhosts {k} {
	initalPos[0] = initalPos[1] = initalPos[2] = 0;
	endPos[0] = endPos[1] = endPos[2] = 0;
	ans = 0;
}

bool GhostMover::withinBoundary(int r, int c) {
	return r >= 0 && c >= 0 && r < M && c < N;
}

void GhostMover::readMap() {
	// construct a matrix from the character map
	// node id starts from 1

	string line;
	getline(cin, line); // skip the remaining newline character

	int matrix[M][N];
	int cnt = 1;
	for(int r = 0;r < M;r ++) {
		getline(cin, line);
	
		// debug
		if(line[line.length() - 1] == '\r')
			line = line.substr(0, line.length() - 1);

		for(int c = 0;c < N;c ++) {
			char ch = line[c];

			if(ch == '#')
				matrix[r][c] = -1;
			else {
				matrix[r][c] = cnt;

				if('a' <= ch && ch <= 'c')
					initalPos[ch - 'a'] = cnt;
				else if('A' <= ch && ch <= 'C')
					endPos[ch - 'A'] = cnt;
				cnt ++;
			}
		}
	}

#if DEBUG == 1
	for(int i = 0;i < M;i ++) {
		for(int j = 0;j < N;j ++) {
			printf("%d ", matrix[i][j]);
		}
		printf("\n");
	}
#endif

	// construct a graph from the matrix
	for(int r = 0;r < M;r ++) {
		for(int c = 0;c < N;c ++) {
			int nodeId = matrix[r][c];
			if(nodeId == -1)
				continue;

			// since a cell must be connected to another cell
			// one of the four conditions must be true
			// thus the vector, aka. graph[nodeId] has size > 0
			if(withinBoundary(r - 1, c) && matrix[r - 1][c] != -1)
				graph[nodeId].push_back(matrix[r - 1][c]);
			if(withinBoundary(r + 1, c) && matrix[r + 1][c] != -1)
				graph[nodeId].push_back(matrix[r + 1][c]);
			if(withinBoundary(r, c - 1) && matrix[r][c - 1] != -1)
				graph[nodeId].push_back(matrix[r][c - 1]);
			if(withinBoundary(r, c + 1) && matrix[r][c + 1] != -1)
				graph[nodeId].push_back(matrix[r][c + 1]);

			// a ghost can choose to stay
			graph[nodeId].push_back(nodeId);
		}
	}
#if DEBUG == 1
	for(auto& entry : graph) {
		for(auto& adjNode : entry.second) {
			printf("(%d, %d) ", entry.first, adjNode);
		}
		printf("\n");
	}
#endif
}

int GhostMover::hash(int aId, int bId, int cId) {
	return aId + bId * 1000 + cId * 1000000;
}

void GhostMover::deHash(int curState, int& aId, int& bId, int& cId) {
	cId = curState / 1000000;
	curState %= 1000000;
	bId = curState / 1000;
	curState %= 1000;
	aId = curState;
}

bool GhostMover::occupied(int* curState, int curNode, int checkedNode) {
	if(checkedNode == curState[curNode])
		return false;
	for(int i = 0;i < 3;i ++)
		if(checkedNode == curState[i])	
			return true;
	return false;
}

void GhostMover::dfsMove(int* curState, bool* chosen,
						 map<int, bool>& visited, queue<int>& q, 
						 map<int, int>& pathCnt, int parentCount,
						 int depth) {
	if(depth == numOfGhosts) {
		int newState = hash(curState[0], curState[1], curState[2]);
		if(!visited[newState]) {
			q.push(newState);
			visited[newState] = true;
			pathCnt[newState] = parentCount + 1;
		}
		return;
	}

	for(int i = 0;i < numOfGhosts;i ++) {
		if(chosen[i])
			continue;
		chosen[i] = true;
		int beforeState = curState[i];

		// choose neighbour nodes
		for(int j = 0;j < graph[beforeState].size();j ++) {
			if(occupied(curState, i, graph[beforeState][j]))
				continue;
			curState[i] = graph[beforeState][j];
			dfsMove(curState, chosen, visited, q, pathCnt, parentCount, depth + 1);
		}
		chosen[i] = false;
		curState[i] = beforeState;
	}
}

void GhostMover::solve() {

	int initalState = hash(initalPos[0], initalPos[1], initalPos[2]);
	int endState = hash(endPos[0], endPos[1], endPos[2]);

	queue<int> q;
	map<int, bool> visited;
	map<int, int> pathCnt;

	q.push(initalState);
	visited[initalState] = true;
	pathCnt[initalState] = 0;

	while(!q.empty()) {
		int curState = q.front();
		q.pop();
		
		if(endState == curState) 
			break;
		
		int curNodeIds[3] {0, 0, 0};
		deHash(curState, curNodeIds[0], curNodeIds[1], curNodeIds[2]);

		// move states
		bool chosen[3] {false, false, false};
		dfsMove(curNodeIds, chosen, visited, q, pathCnt, pathCnt[curState]);
	}
	ans = pathCnt[endState];
}

void GhostMover::output() {
	cout << ans << endl;
}


int main() {
	ios::sync_with_stdio(false);
	int m, n, k;
	while(cin >> n >> m >> k) {
		if(n == 0 && m == 0)
			break;
		GhostMover ghostMover {m, n, k};
		ghostMover.readMap();
		ghostMover.solve();
		ghostMover.output();
	}

	return 0;
}