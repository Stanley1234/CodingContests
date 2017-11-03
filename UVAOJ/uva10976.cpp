#include <stdio.h>
#include <vector>
using namespace std;

int main() {
	//ios::sync_with_stdio(false);

	int k;
	while(scanf("%d", &k) == 1) {

		vector<pair<int, int>> ans;
		for(int y = k + 1;y <= 2 * k;y ++) {
			if((k * y) % (y - k) == 0) 
				ans.push_back(make_pair((k * y) / (y - k), y));
		}

		int lenAns = ans.size();
		printf("%d\n", lenAns);
		for(int i = 0;i < lenAns;i ++) 
			printf("1/%d = 1/%d + 1/%d\n", k, ans[i].first, ans[i].second);
	}
	return 0;
}