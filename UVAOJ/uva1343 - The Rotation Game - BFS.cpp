/*
TLE
*/


#include <iostream>
#include <cstring>
#include <map>
#include <queue>
using namespace std;

class GameSolver {

	class Board {
		void shiftCol(int index, int dir);
		void shiftRow(int index, int dir);
		void decodeBoard(bool board[][7]);
	public:

		static int validPosRow[24];
		static int validPosCol[24];

		int hashboard = 0;
		string action;
		int step = 0;
		
		bool centerSame();
		void encodeBoard(bool board[][7]);
		void rotate(char index);
	};


	int initialBoard[7][7];
	int ansMask;
	string ansMoves;
	
	void mask(bool board[][7], int sourceBoard[][7], int ignoreTarget);
	bool solveHelper(Board& startBoard);
	//void debug(int hashBoard) ;
public:
	bool read();
	void solve();
	void output();
};

int GameSolver::Board::validPosRow[24] = {0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6};
int GameSolver::Board::validPosCol[24] = {2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4}; 

void GameSolver::Board::rotate(char index) {
	// eight rotations
	switch(index) {
		case 'A': shiftCol(2, -1);return;
		case 'B': shiftCol(4, -1);return;
		case 'C': shiftRow(2, 1);return;
		case 'D': shiftRow(4, 1);return;
		case 'E': shiftCol(4, 1);return;
		case 'F': shiftCol(2, 1);return;
		case 'G': shiftRow(4, -1);return;
		case 'H': shiftRow(2, -1);return;
	}
}
void GameSolver::Board::decodeBoard(bool board[][7]) {
	int cnt = 0;
	while(hashboard != 0) {
		if(hashboard & 1)
			board[Board::validPosRow[cnt]][Board::validPosCol[cnt]] = true;
		hashboard >>= 1;
		cnt ++;
	}
}

void GameSolver::Board::encodeBoard(bool board[][7]) {
	int mask = 1;
	hashboard = 0;
	for(int i = 0;i < 24;i ++, mask <<= 1) {
		if(board[Board::validPosRow[i]][Board::validPosCol[i]]) {
			hashboard |= mask;
		}
	}
}

void GameSolver::Board::shiftCol(int index, int dir) {
	// upward: -1, downward: 1
	// index could only be {2, 4}
	bool board[7][7];
	memset(board, false, sizeof(board));
	decodeBoard(board);
	if(dir == -1) {
		int tmp = board[0][index];
		for(int i = 0;i <= 5;i ++)
			board[i][index] = board[i + 1][index];
		board[6][index] = tmp;
	} else {
		int tmp = board[6][index];
		for(int i = 6;i >= 1;i --)
			board[i][index] = board[i - 1][index];
		board[0][index] = tmp;
	}
	encodeBoard(board);
	
}

void GameSolver::Board::shiftRow(int index, int dir) {
	// leftward: -1, rightward: 1
	// index could only be {2, 4}
	bool board[7][7];
	memset(board, false, sizeof(board));
	decodeBoard(board);
	if(dir == -1) {
		int tmp = board[index][0];
		for(int i = 0;i <= 5;i ++)
			board[index][i] = board[index][i + 1];
		board[index][6] = tmp;
	} else {
		int tmp = board[index][6];
		for(int i = 6;i >= 1;i --)
			board[index][i] = board[index][i - 1];
		board[index][0] = tmp;
	}
	encodeBoard(board);
}

bool GameSolver::Board::centerSame() {
	return (hashboard & (1 << 6)) && (hashboard & (1 << 7))
		&& (hashboard & (1 << 8)) && (hashboard & (1 << 11)
		&& (hashboard & (1 << 12)) && (hashboard & (1 << 15))
		&& (hashboard & (1 << 16)) && (hashboard & (1 << 17)));
}

void GameSolver::mask(bool board[][7], int sourceBoard[][7], int ignoreTarget) {
	for(int i = 0;i < 7;i ++) 
		for(int j = 0;j < 7;j ++)
			if(sourceBoard[i][j] != ignoreTarget) 
				board[i][j] = false;
			else
				board[i][j] = true;
}

bool GameSolver::read() {
	memset(initialBoard, 0, sizeof(initialBoard));

	// read in
	int cnt = 0;
	int type;

	while(cnt < 24) {
		cin >> type;
		if(cnt == 0 && type == 0)
			return false;

		initialBoard[Board::validPosRow[cnt]][Board::validPosCol[cnt]] = type;
		cnt ++;
	}
	return true;
}
/*
void GameSolver:: debug(int hashboard) {
	int cnt = 0;
	for(int i = 0;i < 7;i ++) {
		for(int j = 0;j < 7;j ++) {
			if(i == Board::validPosRow[cnt]
				&& j == Board::validPosCol[cnt]) {
				if(hashboard & 1) {
					cout << "1";
				}
				else
					cout << "0";
				hashboard >>= 1;
				cnt ++;
			} else {
				cout << "0";
			}
		}
		cout << endl;
	}
	cout << " ------------------ " << endl;
}
*/
bool GameSolver::solveHelper(Board& startBoard) {
	map<int, bool> visited;
	queue<Board> q;

	q.push(startBoard);
	visited[startBoard.hashboard] = true;

	while(!q.empty()) {
		Board curBoard = q.front();
		q.pop();

		if(curBoard.centerSame()) {
			if(ansMoves == "" || curBoard.action.compare(ansMoves) < 0)
				ansMoves = move(curBoard.action); 
			return true;
		}

		for(int i = 0;i < 8;i ++) {
			Board nextBoard = curBoard;
			nextBoard.rotate('A' + i);
			//printf("next:\n");
			//debug(nextBoard.hashboard);

			if(visited[nextBoard.hashboard])
				continue;
			nextBoard.step = curBoard.step + 1;
			nextBoard.action += ('A' + i);

			visited[nextBoard.hashboard] = true;
			q.push(nextBoard);
		}
	}
	return false;
}


void GameSolver::solve() {

	for(int i = 2;i <= 2;i ++) {
		bool tmpBoard[7][7];
		mask(tmpBoard, initialBoard, i);


		Board startBoard;
		startBoard.encodeBoard(tmpBoard);
		if(solveHelper(startBoard)) {
			ansMask = i;
			return;
		}
	}
}

void GameSolver::output() {
	if(ansMoves != "")
		cout << ansMoves << endl;
	else
		cout << "No moves needed" << endl;
	cout << ansMask << endl;
}

int main() {
	ios::sync_with_stdio(false);
	while(true) {
		GameSolver gs;
		if(!gs.read())
			break;
		gs.solve();
		gs.output();
	}

	return 0;
}