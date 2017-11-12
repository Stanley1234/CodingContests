#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <utility>
#include <cstring>
#include <cstdlib>
#include <cmath>
using namespace std;

long long encode(string& state) {
    long long cnt = 0;
    for(int i = 0;i < state.length();i ++) {
   	 cnt = cnt * 10 + (state[i] - '0');
    }
    return cnt;
}

int encode(int state[][3]) {
    int cnt = 0;
    for(int i = 0;i < 3;i ++) {
   	 for(int j = 0;j < 3;j ++) {
   		 cnt = cnt * 10 + state[i][j];
   	 }
    }
}


void decode(int state[][3], int enState) {
    int r = 2, c = 2;

    while(r >= 0) {

   	 state[r][c] = enState % 10;
   	 enState /= 10;

   	 c --;
   	 if(c == -1) {
   		 c = 2;
   		 r --;
   	 }
    }

}

bool withinBoundary(int r, int c) {
    return r >= 0 && r < 3 && c >= 0 && c < 3;
}

void debug(long long state) {

	int a[3][3];
	decode(a, state);
	for(int i = 0;i < 3;i ++) {
		for(int j = 0;j < 3;j ++) {
			cout << a[i][j];
		}
		cout << endl;
	}
	cout << "--------------------- " << endl;
}


void solve(string& start, string& end) {

    queue<int> q;
    map<long long, bool> visited;
    map<long long, long long> prevState;

    long long enStart = encode(start);
    q.push(enStart);
    visited[enStart] = true;
    prevState[enStart] = -1;

    while(!q.empty()) {
   	 long long enCurState = q.front();
   	 q.pop();

   	 // is the final stage
   	 if(encode(end) == enCurState) {

   		while(prevState[enCurState] != -1) {
   			debug(enCurState);
   			enCurState = prevState[enCurState];
   		}
   		debug(enCurState);

   		return;
   	 }

   	 // decode the state
   	 int curState[3][3];
   	 decode(curState, enCurState);
   	 
   	 // find the white grid
   	 int whiteR, whiteC;
   	 for(int i = 0;i < 3;i ++) {

   		 bool found = false;
   		 for(int j = 0;j < 3;j ++) {
   			 if(curState[i][j] == 0) {
   				 whiteR = i;
   				 whiteC = j;
   				 found = true;
   				 break;
   			 }
   		 }
   		 if(found)
   			 break;
   	 }

   	 // generate next states
   	 const int rOffset[4] = {-1, 0, 1, 0};
   	 const int cOffset[4] = {0, 1, 0, -1};

   	 for(int i = 0;i < 4;i ++) {
   		 int nextState[3][3];
   		 memcpy(nextState, curState, sizeof(curState));

   		 
   		 if(!withinBoundary(whiteR + rOffset[i], whiteC + cOffset[i]))
   			 continue;

   		 swap(nextState[whiteR + rOffset[i]][whiteC + cOffset[i]],
   			  nextState[whiteR][whiteC]);
   		 long long enNextState = encode(nextState);
   		 if(visited[enNextState])
   		 	continue;

   		 q.push(enNextState);
   		 visited[enNextState] = true;
   		 prevState[enNextState] = enCurState;
   	 }
    }



}

int main() {
    string startState, endState;
    cin >> startState >> endState;

    solve(startState, endState);


    return 0;
}
