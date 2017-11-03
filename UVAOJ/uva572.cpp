#include <iostream>
#include <queue>
#include <utility>
#include <array>
#define MAX_SIZE 105
using namespace std;

void readMap(array<array<char, MAX_SIZE>, MAX_SIZE>& map, int M, int N) {
	for(int i = 0;i < M;i ++) 
		for(int j = 0;j < N;j ++) 
			cin >> map[i][j];
		
	
}

bool isBounary(int m, int n, int M, int N) {
	return m >= 0 && n >= 0 && m < M && n < N;
}

int BFS(array<array<char, MAX_SIZE>, MAX_SIZE>& map, int M, int N) {

	queue<pair<int,int>> q;
	int cnt = 1;

	for(int i = 0;i < M;i ++) {
		for(int j = 0;j < N;j ++) {
			if(map[i][j] != '@')
				continue;
			q.push(make_pair(i, j));
			map[i][j] = '*';

			while(!q.empty()) {
				pair<int,int> curPos = q.front();
				q.pop();

				// process neighbour hood
				for(int u = -1;u <= 1;u ++) {
					for(int v = -1;v <= 1;v ++) {
						int newM = curPos.first + u;
						int newN = curPos.second + v;
						if(!isBounary(newM, newN, M, N) || map[newM][newN] != '@')
							continue;
						map[newM][newN] = '*';
						q.push(make_pair(newM, newN));
					}
				}
			}
			cnt ++;
		}
	}
	return cnt - 1;
}


int main() {
	int M, N;
	while(true) {
		array<array<char, MAX_SIZE>, MAX_SIZE> map;
		cin >> M >> N;
		if(M == 0) 
			break;	
		readMap(map, M, N);
		cout << BFS(map, M, N) << endl;
	}
	return 0;
}