#include <iostream>
#include <array>

#define MAXN 1001
using namespace std;

/*
Method: Iterative Deepening Algorithm(IDA)
*/

long long gcd(long long a, long long b) {
	return b == 0 ? a : gcd(b, a % b);
}

long long getFirst(long long num, long long den) {
	// the smallest c such that 1 / c <= num / den
	// that is (den <= c * num)
	long long c;
	for(c = 1;den > c * num;c ++)
		;
	return c;
}

bool better(long long* tmp, long long* ans, int size) {
	// assume the size of both must be equal
	// check if the smallest fraction is larger

	return ans[0] == -1 || tmp[size] >= ans[size];
}

bool dfs(long long* ans, long long* tmp,
		 int curDepth, int maxDepth, long long from, 
		 long long numerator, long long denominator) {
	if(curDepth == maxDepth) {
		if(denominator % numerator || (denominator / numerator) <= from) 
			return false;

		tmp[curDepth] = (denominator / numerator);

		if(better(tmp, ans, curDepth)) {
			// when length is the same, the smallest fraction should be as huge as possible 
			for(int i = 1;i <= curDepth;i ++)
				ans[i] = tmp[i];
		}
		return true;
	}

	from = max(from + 1, getFirst(numerator, denominator));

	bool find = false;
	for(long long i = from;;i ++) {
		// prune
		// maxDepth - depth + 1   	   aa
		// -----------------  < 　------------
		//        i              　　  bb
		if((maxDepth - curDepth + 1) * denominator < numerator * i)
			break;

		tmp[curDepth] = i;
		// num / den - 1 / i = (num * i - den) / (den * i)
		long long newDenominator = denominator * i;
		long long newNumerator = numerator * i - denominator;	
		long long divisor = gcd(newNumerator, newDenominator);
	
		find = find | dfs(ans, tmp, 
				   		  curDepth + 1, maxDepth, i, 
				   		  newNumerator / divisor, newDenominator / divisor);
	}
	return find;
}

int main() {
	long long a, b;
	while(cin >> a >> b) {
		long long ans[MAXN], tmp[MAXN];
		for(int i = 0;i < MAXN;i ++) {
			ans[i] = tmp[i] = -1;
		}

		for(int iterMaxDepth = 1;;iterMaxDepth ++) {
			//cout << iterMaxDepth << endl;
			if(dfs(ans, tmp, 1, iterMaxDepth, 0, a, b))
				break;
		}
		
		for(int i = 1;i < MAXN && ans[i] != -1;i ++)
			cout << "1 / " << ans[i] << " ";
		cout << endl;
	}

	return 0;
}
