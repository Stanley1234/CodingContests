#include <iostream>
using namespace std;

class Backpack {
	long long N, S1, V1, S2, V2;
	long long ans = 0;

public:
	void read();
	void solve();
	void output();
};

void Backpack::read() {
	cin >> N >> S1 >> V1 >> S2 >> V2;
}

void Backpack::solve() {
	
	// if both s1 and s2 is small
	// the TC is O(max{s1,s2})
	if(S1 * S2 < N) {

		// let the rhs be the one with smaller value
		if(S2 * V1 < S1 * V2) {
			swap(S1, S2);
			swap(V1, V2);
		}

		// the number of rhs cannot exceed S1 - 1
		// because assume that the number of rhs is S1(value is S1 * V2)
		// since S2 * V1 > S1 * V2, we can replace these S1 rhs with S2 lhs 
		for(int i = 0;i <= S1 - 1;i ++) 
			ans = max(ans, i * V2 + ((N - i * S2) / S1) * V1);

	} else {
		// otherwise, either s1 and s2 is relatively large wrt N
		if(S1 < S2) {
			swap(S1, S2);
			swap(V1, V2);
		}

		// tc is O(N / S1) or O(N / S2)(S1, S2 is the one before swapped)
		for(int i = 0;i <= N / S1;i ++) {
			ans = max(ans, i * V1 + ((N - i * S1) / S2) * V2);
		}
	}

	/*
	Correct but TLE:
	if(N / S2 >= S1) {
		// 1. the last S1 * S2 capacity must be kept for later calculation
		// 2. the penultimate S1 * S2 capacity need not be kept for later calculation
		// because N % (S1 * S2) may yield some space

		ans += (N / (S1 * S2) - 1) * max(S2 * V1, S1 * V2);
		N = N % (S1 * S2) + (S1 * S2);
	}
	long long tmpMax = 0;
	for(int i = 0;i * S1 <= N;i ++) {
		int j = (N - (i * S1)) / S2;
		if(tmpMax < i * V1 + j * V2)
			tmpMax = i * V1 + j * V2;
	}
	ans += tmpMax;
	*/
}

void Backpack::output() {
	cout << ans << endl;
}


int main() {
	ios::sync_with_stdio(false);

	int T;
	int kase = 1;
	cin >> T;
	while(T -- > 0) {
		Backpack bp;
		bp.read();
		bp.solve();
		cout << "Case #" << kase << ": ";
		bp.output();
		kase ++;
	}

	return 0;
}