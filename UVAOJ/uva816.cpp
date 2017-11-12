#include <iostream>
#include <queue>
#include <string>
#include <vector>
using namespace std;

class MoveState {
public:
	int r = -1;
	int c = -1;
	int dir = -1;

	MoveState(int r, int c, int dir) : r {r}, c{c}, dir{dir} {}
	MoveState() {}

	static MoveState make_state(int r, int c, int dir) {
		return MoveState{r, c, dir};
	}
};

class Maze {
public:

	static const int NORTH = 0;
	static const int EAST = 1;
	static const int SOUTH = 2;	
	static const int WEST = 3;

private:
	int map[9][9][4][4];
	MoveState prevState[9][9][4];
	int length[9][9][4];

	// the location/direction is always relative to the absolute space
	// map[row][col][faced direction][dest direction]
	// prevState[row][col][faced direction]

public:

	Maze() {
		for(int i = 0;i < 9;i ++)
			for(int j = 0;j < 9;j ++)
				for(int u = 0;u < 4;u ++)
					for(int v = 0;v < 4;v ++)
						map[i][j][u][v] = false;
		for(int i = 0;i < 9;i ++)
			for(int j = 0;j < 9;j ++)
				for(int u = 0;u < 4;u ++)
					length[i][j][u] = 0;
	}

	void add(int r, int c, int baseDir, int destDir) {
		map[r][c][baseDir][destDir] = true;
	}
	int canTravelTo(int sr, int sc, int ss, int dr, int dc) {
		// the mahaton difference between (sr, sc) and (dr, dc) must be one
		if(dr <= 0 || dc <= 0 || dr > 9 || dc > 9)
			return -1;
		// the third condition is to see if this direction has been visited
		if(sr - 1 == dr && map[sr][sc][ss][NORTH] && prevState[dr][dc][NORTH].dir == -1)
			return NORTH;
		else if(sr + 1 == dr && map[sr][sc][ss][SOUTH] && prevState[dr][dc][SOUTH].dir == -1)
			return SOUTH;
		else if(sc - 1 == dc && map[sr][sc][ss][WEST] && prevState[dr][dc][WEST].dir == -1)
			return WEST;
		else if(sc + 1 == dc && map[sr][sc][ss][EAST] && prevState[dr][dc][EAST].dir == -1)
			return EAST;
		return -1;
	}
	void travel(int sr, int sc, int ss, int dr, int dc, int ds) {
		prevState[dr][dc][ds] = MoveState::make_state(sr, sc, ss);
		length[dr][dc][ds] = length[sr][sc][ss] + 1;
	}

	void printPath(int startR, int startC, int startDir, int endR, int endC) {
		int endDir = -1;
		for(int i = 0;i < 4;i ++)
			if(prevState[endR][endC][i].dir != -1) {
				endDir = i;
				break;
			}

		// in some corner case, starting point can be double visited

		vector<pair<int, int>> ans;
		ans.push_back(make_pair(endR, endC));
		MoveState* curState = &prevState[endR][endC][endDir];
		int cnt = length[endR][endC][endDir];
		while(cnt -- > 0) {
			ans.insert(ans.begin(), make_pair(curState->r, curState->c));
			curState = &prevState[curState->r][curState->c][curState->dir];
		}

		cout << "  ";
		cnt = 0;
		for(int i = 0;i < ans.size();i ++) {
			if(cnt >= 10) {
				cout << endl << "  ";
				cnt = 0;
			}
			if(cnt >= 1)
				cout << " ";
			cout << "(" << ans[i].first << "," << ans[i].second << ")";
			cnt ++;
		}
		cout << endl;
	}
};

int getDirection(char dir) {
	if(dir == 'N')
		return Maze::NORTH;
	else if(dir == 'S')
		return Maze::SOUTH;
	else if(dir == 'E')
		return Maze::EAST;
	else if(dir == 'W')
		return Maze::WEST;
}


int computeDirection(char baseDir, char rotation) {
	int pos = getDirection(baseDir);

	if(rotation == 'L')
		pos --;
	else if(rotation == 'R')
		pos ++;
	if(pos < 0)
		pos += 4;
	else
		pos %= 4;
	return pos;
}

void constructMaze(Maze& maze) {
	
	while(true) {
		int r, c;
		cin >> r;
		if(r == 0)
			break;
		cin >> c;

		string dir;
		while(cin >> dir) {
			if(dir == "*")
				break;

			char baseDir = dir[0];
			for(int i = 1;i < dir.length();i ++) {
				int destDir = computeDirection(baseDir, dir[i]);
				maze.add(r, c, getDirection(baseDir), destDir);
			}
		}
	}

}

bool bfs(Maze& maze, int startR, int startC, int startDir, int endR, int endC) {
	queue<MoveState> q;

	// this case is hard coded
	// imo, the question itself does not indicate that the first step should be made
	// to the startDit direction
	int nr = startR, nc = startC, ns = startDir;
	if(startDir == Maze::NORTH) 
		nr --;
	else if(startDir == Maze::SOUTH)
		nr ++;
	else if(startDir == Maze::EAST)
		nc ++;
	else if(startDir == Maze::WEST)
		nc --;
	maze.travel(startR, startC, startDir, nr, nc, ns);
	if(nr == endR && nc == endC) 
		return true;
	q.push(MoveState::make_state(nr, nc, ns));

	while(!q.empty()) {
		auto state = q.front();
		q.pop();

		const int offsetR[4] = {-1, 0, 1, 0};
		const int offsetC[4] = {0, 1, 0, -1};
		for(int i = 0;i < 4;i ++) {
			int nr = state.r + offsetR[i];
			int nc = state.c + offsetC[i];

			int ns = maze.canTravelTo(state.r, state.c, state.dir, nr, nc);
			if(ns == -1)
				continue;
			if(nr == endR && nc == endC) {
				maze.travel(state.r, state.c, state.dir, nr, nc, ns);
				return true;
			}

			maze.travel(state.r, state.c, state.dir, nr, nc, ns);
			q.push(MoveState::make_state(nr, nc, ns));
		}
	}
	return false;
}
int main() {
	ios::sync_with_stdio(false);

	string kaseName;
	while(true) {
		cin >> kaseName;
		if(kaseName == "END")
			break;

		int startR, startC;
		int endR, endC;
		string startDir;

		cin >> startR >> startC >> startDir >> endR >> endC;

		Maze maze;
		constructMaze(maze);
		maze.add(startR, startC, getDirection(startDir[0]), getDirection(startDir[0]));

		cout << kaseName << endl;
		if(!bfs(maze, startR, startC, getDirection(startDir[0]), endR, endC))
			cout << "  No Solution Possible" << endl;
		else
			maze.printPath(startR, startC, getDirection(startDir[0]), endR, endC);
	}
	return 0;
}
