/*
Corner case:
- The input pair (u, v) can appear multiple times
  So we have to check if (u, v) pair has already existed or not
  Otherwise the incremented pair will not be correct

*/


#include <iostream>
#include <array>
#include <vector>
#include <algorithm>
using namespace std;

array<array<bool, 105>, 105> map;
array<int, 105> inDegree;
vector<int> ans;

int main() {
	ios::sync_with_stdio(false);
	int N, M;
	while(cin >> N >> M) {
		if(!N && !M)	
			break;
		// init
		ans.clear();
		inDegree.fill(0);
		for_each(map.begin(), map.end(), [](array<bool, 105>& row) {row.fill(false);});

		// read in
		for(int i = 0;i < M;i ++) {
			int u, v;
			cin >> u >> v;
			if(!map[u][v])
				inDegree[v] ++;

			map[u][v] = true;
		}	
		// topo sort
		while(ans.size() < N) {
			for(int i = 1;i <= N;i ++)  {
				if(inDegree[i] == 0) {
					inDegree[i] = -1;  // avoid being added into vector
					for(int j = 1;j <= N;j ++)
						if(map[i][j] && inDegree[j] > 0) 
							inDegree[j] --;						
					ans.push_back(i);			
				}
			}
		}
		// output
		for(int i = 0;i < N;i ++) {
			cout << ans[i];
			if(i < N - 1)
				cout << " ";
		}
		cout << endl;
	}
	return 0;
}