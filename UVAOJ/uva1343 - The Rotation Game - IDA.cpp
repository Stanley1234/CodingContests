#include <iostream>
#include <cstring>
using namespace std;

class GameSolver {

//       A     B
//       00    01
//       02    03
// 04 05 06 07 08 09 10  C
//       11    12
// 13 14 15 16 17 18 19  D
//       20    21  
//       22    23
//       F     E

	static int center[8];
	static int A[7], B[7], C[7], D[7];

	int initialBoard[24];

	int ansMask = 0;
	string ansSteps;

	bool ida(int *curBoard, int curDepth, int maxDepth, string&);
	void shift(int* board, char type);
	void shift(int* board, int* rule, int dir);
	bool isEnd(int *curBoard);
public:
	bool read();
	void solve();
	void output();
};
int GameSolver::center[8] = {6, 7, 8, 11, 12, 15, 16, 17};
int GameSolver::A[7] = {0, 2, 6, 11, 15, 20, 22};
int GameSolver::B[7] = {1, 3, 8, 12, 17, 21, 23};
int GameSolver::C[7] = {4, 5, 6, 7, 8, 9, 10};
int GameSolver::D[7] = {13, 14, 15, 16, 17, 18, 19};


bool GameSolver::read() {
	int cnt = 0;
	while(cnt < 24) {
		cin >> initialBoard[cnt];
		if(cnt == 0 && initialBoard[cnt] == 0)
			return false;
		cnt ++;
	}
	return true;
}

bool GameSolver::isEnd(int *curBoard) {
	for(int i = 0;i < 8;i ++)
		if(curBoard[center[i]] == 0)
			return false;
	return true;
}


void GameSolver::shift(int* board, int* rule, int dir) {
	if(dir == 1) {
		int tmp = board[rule[0]];  
		for(int i = 0;i < 6;i ++)
			board[rule[i]] = board[rule[i + 1]];
		board[rule[6]] = tmp;
	} else {
		int tmp = board[rule[6]];  
		for(int i = 6;i >= 1;i --)
			board[rule[i]] = board[rule[i - 1]];
		board[rule[0]] = tmp;
	}
}

void GameSolver::shift(int* board, char type) {
	switch(type) {
		case 'A': shift(board, A, 1); return;
		case 'B': shift(board, B, 1); return;
		case 'C': shift(board, C, -1); return;
		case 'D': shift(board, D, -1); return;
		case 'E': shift(board, B, -1); return;
		case 'F': shift(board, A, -1); return;
		case 'G': shift(board, D, 1); return;
		case 'H': shift(board, C, 1); return;
	}
}


bool GameSolver::ida(int *curBoard, int curDepth, int maxDepth, 
					 string& curSteps) {
	if(curDepth == maxDepth) {

		// the first one must be the ans because
		// 1. it needs the least step
		// 2. it has the smallest alphabetic order
		if(isEnd(curBoard)) 
			return true;
		
		return false;
	}
	// prune the branches
	int d = 0;
	for(int i = 0;i < 8;i ++)
		if(curBoard[center[i]] != 0)
			d ++;

	if(maxDepth - curDepth + d < 8)
		return false;

	// produce next states
	for(int i = 0;i < 8;i ++) {
		int tmpBoard[24];
		memcpy(tmpBoard, curBoard, sizeof(int) * 24);
		
		shift(tmpBoard, 'A' + i);
		curSteps.insert(curSteps.end(), 'A' + i);
		if(ida(tmpBoard, curDepth + 1, maxDepth, curSteps))
			return true;
		curSteps.erase(curSteps.length() - 1, 'A' + i);
	}
	return false;
}

void GameSolver::solve() {
	for(int iterMaxDepth = 0;;iterMaxDepth ++) {
		
		int tmpBoard[24];
		
		// mask
		bool ok = false;
		for(int i = 1;i <= 3;i ++) {
			memcpy(tmpBoard, initialBoard, sizeof(initialBoard));
			for(int k = 0;k < 24;k ++)
				if(tmpBoard[k] != i) 
					tmpBoard[k] = 0;

			string tmpSteps;

			if(ida(tmpBoard, 0, iterMaxDepth, tmpSteps)) {
				if(ansSteps == "" || tmpSteps.compare(ansSteps) < 0) {
					ansSteps = tmpSteps;
					ansMask = i;
				}
				ok = true;
			}
		}
		if(ok)
			return;
	}
}

void GameSolver::output() {
	if(ansSteps == "") 
		cout << "No moves needed" << endl;
	else
		cout << ansSteps << endl;
	cout << ansMask << endl;
}

int main() {
	while(true) {
		GameSolver gs;
		if(!gs.read())
			break;
		gs.solve();
		gs.output();
	}


	return 0;
}