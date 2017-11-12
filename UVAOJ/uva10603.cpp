#include <iostream>
#include <queue>
#include <array>
#include <algorithm>
#define MAXN 201
#define INF 99999999
using namespace std;


class PourWater {
    int caps[3], dest;
    array<int, MAXN> ans;

    struct State {
   	 int cups[3];
   	 int dist;

   	 State(int a, int b, int c, int d = 0) {
   		 cups[0] = a;
   		 cups[1] = b;
   		 cups[2] = c;

   		 dist = d;
   	 }

   	 State(const State& rhs) {
   		 for(int i = 0;i < 3;i ++)
   			 cups[i] = rhs.cups[i];
   		 dist = rhs.dist;
   	 }

   	 bool operator<(const State& rhs) const {
   		 return dist > rhs.dist;
   	 }
    };

    void updateAns(const State& s);
    void bfs(State inital);

public:
    PourWater(int a, int b, int c, int d);
    void solve();
    void output();

};


void PourWater::updateAns(const State& s) {
    for(int i = 0;i < 3;i ++) {
   	 if(ans[s.cups[i]] > s.dist)    
   		 ans[s.cups[i]] = s.dist;    
    }
}

void PourWater::bfs(State inital) {
    priority_queue<State> q;
    array<array<bool, MAXN + 1>, MAXN + 1> visited;

    for_each(visited.begin(), visited.end(), 
      [](array<bool, MAXN + 1>& row) {row.fill(false);});

    q.push(inital);
    while(!q.empty()) {
   	 State curState = q.top();
   	 q.pop();

   	 // if this state has been reached before
   	 // it means that the previous state must be better than this state
   	 // because the dist of previous state is smaller
   	 if(visited[curState.cups[0]][curState.cups[1]])
   		 continue;
   	 visited[curState.cups[0]][curState.cups[1]] = true;
   	 updateAns(curState);
     // prune the branches
   	 if(ans[dest] < INF)
   		 return;

   	 for(int i = 0;i < 3;i ++) {
   		 for(int j = 0;j < 3;j ++) {


   			 // - cannot pour water from and to the same cup
   			 // - cannot pour water when the cup is empty
   			 // - cannot be filled with water when the cup is full
   			 if(i == j || curState.cups[i] == 0 || curState.cups[j] == caps[j]) 
   				 continue;
         
   			 int pourAmount = min(curState.cups[i], caps[j] - curState.cups[j]);

   			 State nextState {curState};
   			 nextState.cups[i] -= pourAmount;
   			 nextState.cups[j] += pourAmount;
   			 nextState.dist += pourAmount;

   			 // if this state has not been reached before
   			 if(!visited[nextState.cups[0]][nextState.cups[1]]) 
   				 q.push(nextState);
         
   		 }
   	 }
    }
}

PourWater::PourWater(int a, int b, int c, int d) {
    caps[0] = a;
    caps[1] = b;
    caps[2] = c;
    dest = d;

    ans.fill(INF);
}

void PourWater::solve() {

    State inital {0, 0, caps[2]};
    bfs(inital);
}

void PourWater::output() {
    for(int i = dest;i >= 0;i --) {
   	 if(ans[i] < INF) {
   		 cout << ans[i] << " " << i << endl;
   		 return;
   	 }
    }
}

int main() {
    ios::sync_with_stdio(false);

    int kase;
    cin >> kase;
    while(kase -- > 0) {
      int capA, capB, capC, dest;
      cin >> capA >> capB >> capC >> dest;

      PourWater pw{capA, capB, capC, dest};
      pw.solve();
      pw.output();
    }
    return 0;
}
