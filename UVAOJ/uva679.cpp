#include <iostream>
#include <stdio.h>
using namespace std;

/*
						      o
         		 (2k+1)/            \(2k+2)
         		      o              o
              (4k+1)/   \(4k+3) /(4k+2)  \(4k+4)
				   o    o      o          o

Observe:
- for each level, all left subtrees will be visited first 
- 2k + 1 contains only two cases: 4k+1, 4k+3. Thus 4k+1 must represent the left subtree
  Similarly, 4k + 1 contains only two cases: 8k + 1, 8k + 5

Conclusion:
With the above pattern, we can describe the entire tree


*/

int main() {
	int N;
	cin >> N;

	int D, I;
	while(N -- > 0) {
		cin >> D >> I;

		int factor = 1;
		int pos = 1;
		while(--D > 0) {
			factor *= 2;


			if(0 < I % factor && I % factor <= factor / 2) // turn left
				pos *= 2;
			else
				pos = pos * 2 + 1;
			//printf("I:%d factor:%d pos:%d\n", I, factor, pos);
		}
		cout << pos << endl;
	}


	return 0;
}