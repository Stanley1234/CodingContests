#include <iostream>
using namespace std;

/*
By induction, suppose S does not contain any repeated adjacent sequence
Now if we append a to the end of S resulting in Sa, 
to check if Sa contains any repeated adjacent sequence, 
we only check if Sa contains any sub-sequence involving a

*/

bool isValid(string& str) {
	// by checking suffix
	int len = 1;
	int strLen = str.length();

	while(len * 2 <= strLen) {
		bool repeated = true;
		const int s1 = strLen - len * 2;
		const int s2 = strLen - len;
		for(int i = 0;i < len;i ++)
			if(str[s1 + i] != str[s2 + i]) {
				repeated = false;
				break;
			}
		if(repeated)
			return false;
		len ++;
	}
	return true;
}

bool solve(string& str, const int N, const int L, int& cnt) {

	if(cnt == N) {
		// answer
		int strLen = str.length();
		int group = 1;
		for(int i = 0;i < strLen;i ++) {
			if(0 < i && i % 4 == 0) {
				group ++;
				
				if(group % 16 == 1)
					cout << endl;
				else
					cout << " ";
			}
			cout << str[i];
		}
		cout << endl;
		cout << strLen << endl;
		//cout << str << endl;
		return true;
	}

	for(int i = 0;i < L;i ++) {
		char curChar = 'A' + i;

		str += curChar;
		if(!isValid(str)) 
			str.erase(str.length() - 1);
		else {
			cnt ++;
			if(!solve(str, N, L, cnt))
				str.erase(str.length() - 1);
			else
				return true;
		}
	}
	return false;
}

int main() {
	int N, L;
	while(cin >> N >> L) {
		if(N == 0 && L == 0)
			break;
		string seq;
		int cnt = 0;
		solve(seq, N, L, cnt);
	}


	return 0;
}