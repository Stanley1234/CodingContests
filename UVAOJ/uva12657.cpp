/*
Edege cases:
** swap two elements that are neighbours

optimization measure:
** find the starting point => right or left of 0 is the starting point



*/


#include <iostream>
#include <utility>
#include <stdio.h>
#define MAXN 100005
#define NOTHING 0
#define DEBUG 0
using namespace std;

int leftBox[MAXN], rightBox[MAXN];

#if DEBUG == 1
	void debug(int N, bool reverse) {

		int startPoint;
		if(!reverse)
			startPoint = rightBox[NOTHING];
		else
			startPoint = leftBox[NOTHING];
		
		while(startPoint != NOTHING) { 
			// odd position
			cout << startPoint << " ";
			if(!reverse)
				startPoint = rightBox[startPoint];
			else
				startPoint = leftBox[startPoint];
		}
		cout << endl;
	}
#endif


void init(int N) {
	
	leftBox[NOTHING] = N;
	for(int i = 1;i <= N;i ++)
		leftBox[i] = i - 1;
	rightBox[N] = NOTHING;
	for(int i = 0;i < N;i ++)
		rightBox[i] = i + 1;
}

inline void addLink(int x, int y) {
	if(x == y)
		return;
	rightBox[x] = y;
	leftBox[y] = x;
}

inline void removeBox(int x) {
	addLink(leftBox[x], rightBox[x]);
}

void swapBox(int x, int y) {
	if(x == y)
		return;

	if(leftBox[y] == x)  {
		int rightY = rightBox[y], leftX = leftBox[x];

		addLink(leftX, y);
		addLink(y, x);
		addLink(x, rightY);


	} else if(leftBox[x] == y) {
		swapBox(y, x);
	} else {
		int leftX = leftBox[x], leftY = leftBox[y];
		int rightX = rightBox[x], rightY = rightBox[y];

		addLink(leftX, y);
		addLink(y, rightX);
		addLink(leftY, x);
		addLink(x, rightY);
	}

	
}

inline void putBoxToLeft(int x, int y) {
	removeBox(x);
	addLink(leftBox[y], x);
	addLink(x, y);
}

inline void putBoxToRight(int x, int y) {
	removeBox(x);
	addLink(x, rightBox[y]);
	addLink(y, x);
}

inline long long sumUp(int N, bool reverse) {

	int startPoint = rightBox[NOTHING];
	long long ans = 0;
	for(int i = 1;i <= N;i ++) {
		if(i % 2 == 1)
			ans += startPoint;
		startPoint = rightBox[startPoint];
	}

	if(reverse && N % 2 == 0)
		ans = ((long long)N * (N + 1) / 2) - ans;
	return ans;
}



int main() {
	int N, M;
	int kase = 1;
	while(scanf("%d", &N) == 1) {
		scanf("%d", &M);

		init(N);

		bool reverse = false;
		int cmd, x, y;
		for(int i = 0;i < M;i ++) {
			scanf("%d", &cmd);

			if(cmd == 4) 
				reverse = !reverse;
			else {
				scanf("%d %d", &x, &y);

				if(reverse && (cmd == 1 || cmd == 2)) 
					cmd = 3 - cmd;
				
				// move X to leftBox of Y
				if(cmd == 1) 
					putBoxToLeft(x, y);
				else if(cmd == 2) 
					putBoxToRight(x, y);
				else if(cmd == 3) 
					swapBox(x, y);

				
			}
			#if DEBUG == 1
				debug(N, reverse);
			#endif
		}
		

		printf("Case %d: %lld\n", kase, sumUp(N, reverse));
		kase ++;
	}

	return 0;
}