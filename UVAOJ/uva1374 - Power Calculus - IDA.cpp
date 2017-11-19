#include <iostream>
#include <queue>
#include <cstring>
#include <map>
#include <cmath>
using namespace std;

/*
Optimization:
- prune the branch
- always use the most recent calculated number
- try addition before subtraction

* always use the most recent calculated number
This method works but left unproven

*/

class Calculator {

	struct State {
		int expn;
		int step;

		State(int e, int s) : expn {e}, step {s} {}
	};

	int N;
	int ans = 0;

	bool dfs(int curLevel, int maxLevel, int* seq,
					 int largestNum, bool* visited);
public:
	bool read();
	void solve();
	void output();

};

bool Calculator::read() {
	cin >> N;
	return N;
}

bool canExceed(int base, int expn, int bound) {
	if(base > bound)
		return true;
	return base * pow(2, expn) >= bound;
}

bool Calculator::dfs(int curLevel, int maxLevel, int* seq,
					 int largestNum, bool* visited) {
	if(curLevel == maxLevel) {
		if(visited[N]) {
			ans = curLevel;
			return true;
		}
		return false;
	}
	// prune the branches
	if(!canExceed(largestNum, maxLevel - curLevel, N))
		return false;
	
	// produce next states
	int newExpn;
	for(int i = 0;i <= curLevel;i ++) {
		newExpn = seq[curLevel] + seq[i];
		if(newExpn <= 1000 && !visited[newExpn]) {
			visited[newExpn] = true;
			seq[curLevel + 1] = newExpn;
			if(dfs(curLevel + 1, maxLevel, seq, max(largestNum, newExpn), visited))
				return true;
			visited[newExpn] = false;
			seq[curLevel + 1] = 0;
		}

		newExpn = seq[curLevel] - seq[i];
		if(newExpn >= 1 && !visited[newExpn]) {
			visited[newExpn] = true;
			seq[curLevel + 1] = newExpn;
			if(dfs(curLevel + 1, maxLevel, seq, max(largestNum, newExpn), visited))
				return true;
			visited[newExpn] = false;
			seq[curLevel + 1] = 0;
		}
	}
	return false;
}

void Calculator::solve() {
	int seq[50];
	bool visited[1001];
	memset(seq, 0, sizeof(seq));
	memset(visited, false, sizeof(visited));

	seq[0] = 1;
	visited[1] = true;
	for(int iterMaxDepth = 0;iterMaxDepth <= 13;iterMaxDepth ++) {
		//cout << iterMaxDepth << endl;
		if(dfs(0, iterMaxDepth, seq, 1, visited))
			return;
	}
}

void Calculator::output() {
	cout << ans << endl;
}

int main() {
	
	while(true) {
		Calculator calc;
		if(!calc.read())
			break;
		calc.solve();
		calc.output();
	}
	return 0;
}