#include <iostream>
#include <array>
#include <map>
using namespace std;

bool isPrime(int n) {
	if(n <= 1)
		return false;
	for(int i = 2;i * i <= n;i ++)
		if(n % i == 0)
			return false;
	return true;
}

void solve(int cur, const int N, array<int, 16>& ring, map<int, bool>& used) {
	if(cur == 0) {

		// the last checking
		if(!isPrime(ring[cur] + ring[(cur + 1) % N]))
			return;

		cout << ring[0] << " ";
		for(int i = 1;i < N; i ++) {
			cout << ring[N - i];
			if(i < N - 1)
				cout << " ";
		}
		cout << endl;
		return;
	}

	for(int i = 1;i <= N;i ++) {
		if(used[i] || !isPrime(i + ring[(cur + 1) % N]))
			continue;
		ring[cur] = i;
		used[i] = true;
		solve(cur - 1, N, ring, used);
		used[i] = false;
	}


}

int main() {
	ios::sync_with_stdio(false);

	int N;
	int kase = 1;
	while(cin >> N) {
		if(kase >= 2)
			cout << endl;

		array<int, 16> ring;
		map<int, bool> used;
		
		ring[0] = 1;
		used[1] = true;
		cout << "Case " << kase << ":" << endl;
		solve(N - 1, N, ring, used);
		kase ++;
	}
	

	
	
	return 0;
}