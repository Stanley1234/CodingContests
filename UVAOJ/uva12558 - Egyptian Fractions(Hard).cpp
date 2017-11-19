#include <iostream>
#include <cstring>
#define MAXN 10001
using namespace std;

typedef long long LL;

class Solver {
	LL a, b;
	bool cannotUsed[1001];

	int ansLen = 0;
	LL ans[MAXN];
	
	bool dfs(int curDepth, int maxDepth, LL* seq,
				 const LL& fa, const LL& fb);
	bool better(LL* v1, int v1Len) ;
public:
	Solver();
	void read();
	void solve();
	void output();
};

Solver::Solver() {
	memset(cannotUsed, false, sizeof(cannotUsed));
	memset(ans, -1, sizeof(ans));
}

void Solver::read() {
	int k, tmp;
	cin >> a >> b;
	cin >> k;
	while(k -- > 0) {
		cin >> tmp;
		cannotUsed[tmp] = true;
	}
}

static LL gcd(LL a, LL b) {
	return b == 0 ? a : gcd(b, a % b);
}

static LL getFirst(const LL& a, const LL& b) {
	// get the smallest c such that 1 / c <= a / b
	LL c;
	for(c = 1;b > c * a;c ++)
		;
	return c;
}

bool Solver::better(LL* v1, int v1Len) {
	//  no answer has been found 
	if(ans[0] == -1)
		return true;
	// the last should be minimized.
	// if the lasts are the same, the penultimate is minized
	// and so on
	int t = v1Len;
	while(t >= 1) {
		if(v1[t] == ans[t]) t --;
		else return v1[t] < ans[t];
	}
	return false;
}

bool Solver::dfs(int curDepth, int maxDepth, LL* seq,
				 const LL& fa, const LL& fb) {
	if(curDepth == maxDepth) {
		if(fb % fa || fb / fa <= seq[curDepth - 1] || (fb / fa <= 1000 && cannotUsed[fb / fa])) 
			return false;
		seq[curDepth] = fb / fa;
		if(better(seq, curDepth)){
			ansLen = curDepth;
			memcpy(ans, seq, sizeof(LL) * (curDepth + 1));
		}
		return true;
	}
	LL from = max(getFirst(fa, fb), seq[curDepth - 1] + 1);
	
	// produce next states
	bool find = false;
	for(LL i = from;;i ++) {
		// prune the branches
		if(fb * (maxDepth - curDepth + 1) < i * fa) break;
		if(i <= 1000 && cannotUsed[i]) continue;
		// temporary fraction
		LL tfa = fa * i - fb;
		LL tfb = fb * i;
		LL div = gcd(tfa, tfb);
		seq[curDepth] = i;
		
		find = find | dfs(curDepth + 1, maxDepth, seq, tfa / div, tfb / div);
	}
	return find;
}

void Solver::solve() {
	LL* seq = new LL[MAXN];
	seq[0] = 0;
	for(int iterMaxDepth = 1;;iterMaxDepth ++) {
		if(dfs(1, iterMaxDepth, seq, a, b)) {
			ansLen = iterMaxDepth;
			break;
		}
	}
	delete seq;	
}

void Solver::output() {
	cout << a << "/" << b << "=";
	for(int i = 1;i <= ansLen;i ++) {
		if(i >= 2) cout << "+";
		cout << "1/" << ans[i];
	}
	cout << endl;
}

int main() {
	ios::sync_with_stdio(false);
	int T;
	int kase = 1;
	cin >> T;
	while(T -- > 0) {
		Solver solver;
		solver.read();
		solver.solve();
		cout << "Case " << kase++ << ":" << " ";
		solver.output();
	}

	return 0;
}