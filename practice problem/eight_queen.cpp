#include <iostream>
#include <map>
#include <array>
#include <algorithm>
using namespace std;

// g++ -std=c++14 -DSIZE=8 eight_queen.cpp
/*
NOTE:
- cannot deduce the position of chess with row, ddown, and dup
- must init array and map

*/


void solve(int c, map<int, bool>& row,
				map<int, bool>& ddown, map<int, bool> dup,
				array<array<bool, SIZE>, SIZE>& board) {

	if(c == SIZE) {
		// print solution
		for(int i = 0;i < SIZE;i ++) {
			for(int j = 0;j < SIZE;j ++) {
				if(board[i][j])
					cout << "●";
				else
					cout << "○";
			}
			cout << endl;
		}
		cout << "----------------------------------" << endl;
		return;
	}

	for(int r = 0;r < SIZE;r ++) {
		if(row[r] || ddown[c + (SIZE - 1 - r)] || dup[r + c])
			continue;
		row[r] = ddown[c + (SIZE - 1 - r)] = dup[r + c] = true;
		board[r][c] = true;
		solve(c + 1, row, ddown, dup, board);
		row[r] = ddown[c + (SIZE - 1 - r)] = dup[r + c] = false;
		board[r][c] = false;
	}
}

int main() {
	map<int, bool> row, ddown, dup;
	array<array<bool, SIZE>, SIZE> board;

	for_each(board.begin(), board.end(), [](array<bool, SIZE>& row) {row.fill(false);});

	for(int i = 0;i < SIZE;i ++)
		row[i] = false;
	for(int i = 0;i < SIZE * 2;i ++) {
		ddown[i] = dup[i] = false;
	}

	solve(0, row, ddown, dup, board);


	return 0;
}