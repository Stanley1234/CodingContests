#include <iostream>
#include <sstream>
#include <string>
#include <array>
using namespace std;


bool onlyUsedOnce(int n1, int n2) {
	array<bool, 10> used;
	used.fill(false);

	int cnt = 5;
	while(cnt -- > 0) {
		used[n1 % 10] = 0;
		n1 /= 10;
	}

	cnt = 5;
	while(cnt -- > 0) {
		used[n1 % 10] = 0;
		n1 /= 10;
	}

	for(int i = 0;i < 10;i ++)
		if(!used[i])
			return false;
	return true;

}

string formatedOutput(int n1, int n2) {

	ostringstream oss;
	oss << n1;
	string str1 = oss.str();

	for(int i = str1.length();i <= 5;i ++) 
		str1.insert(str1.begin(), 0);
	return str1;
}

int main() {
	ios::rsync_with_sdtio(false);
	int N;
	cin >> N;

	// abcde / fghij
	// enumerating fghij will be faster than enumerating abcde
	const int lowerBound = 1000;
	const int upperBound = 99999 / N;
	for(int i = lowerBound;i <= upperBound;i ++) {
		if(!onlyUsedOnce(i * N, i)) 
			continue;
		string str1 = formate(i * N);
		string str2 = formate(i);
		cout << str1 << " / " << str2 << endl;
	}

	return 0;
}