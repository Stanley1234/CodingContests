#include <iostream>
#include <array>
using namespace std;

int main() {
	ios::sync_with_stdio(false);

	int N;
	int kase = 1;
	while(cin >> N) {
		array<int, 20> seq;
		for(int i = 0;i < N;i ++) 
			cin >> seq[i];
		
		long long maxProd = 0;
		for(int start = 0;start < N;start ++) {

			long long tmpMax = 0;
			long long curProd = 1;
			for(int end = start;end < N;end ++) {
				curProd *= seq[end];

				if(curProd > tmpMax)
					tmpMax = curProd;
			}

			if(tmpMax > maxProd)
				maxProd = tmpMax;
		}
		cout << "Case #" << kase << ": The maximum product is " << maxProd << "." << endl << endl;
		kase ++;
	}

	return 0;
}