#include <vector>
#include <stack>
#include <iostream>
using namespace std;

int main() {

	
	int N;

	while(true) {
		cin >> N;
		if(N == 0)
			break;
		
		do {
			vector<int> expected;
			stack<int> transistor;

			int tmp;
			cin >> tmp;
			if(tmp == 0) {
				cout << endl;
				break;
			}
			expected.push_back(tmp);
			for(int i = 1;i < N;i ++) {
				cin >> tmp;
				expected.push_back(tmp);
			}	
				
			

			bool ok = true;
			int AId = 0, curId = 0;
			while(curId != N) {
				if(AId + 1 == expected[curId]) {
					curId ++;
					AId ++;
				} else if(!transistor.empty() && transistor.top() == expected[curId]) {
					transistor.pop();
					curId ++;
				} else if(AId < N){
					transistor.push(AId + 1);
					AId ++;
				} else {
					ok = false;
					break;
				}

			}
			if(ok)
				cout << "Yes" << endl;
			else
				cout << "No" << endl;

		} while(true);

	}



	return 0;
}