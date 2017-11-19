#include <iostream>
#include <cstring>
using namespace std;

/*
Method:
- BFS:
  One way to solve this problem is by bfs. Since each permutation of positions
  of books is a state, there are a finite number of states. Thus theoretically,
  we can visit all states and find the optimal solutions

  For each state, there are many states that can be derived from. Without
  any strategies, bfs will certainly take a large amount of time.
  Now the question is that will this problem be solve within the specified
  period with any strategy?

- IPA:
  Optimization rules:
  * the maximum depth for IDA is N - 1
  * let h be the number of books whose following index is not correct(i.e. prev + 1 == post)
    At each iteration, h can decrease up to 3
    Thus prune the branch if h > 3 * (maxDepth - curDepth + 1)
  * never split a consecutive sequence 
  * use array instead of vector class
*/

class BookEditor {

    int N;   //1 <= N <= 9
    int books[10];
    int ans;

   
    bool dfs(int *book, int curDepth, int maxDepth);

public:
    BookEditor(int N);
    void read();
    void solve();
    void output();

};

BookEditor::BookEditor(int N) : N {N}, ans{0} {}

void BookEditor::read() {
    for(int i = 0;i < N;i ++)
   	 cin >> books[i];
}

bool sortedBooks(int *books, int N) {
    // ascending order
    for(int i = 1;i < N;i ++)
   	 if(books[i] < books[i - 1])
   		 return false;
    return true;
}

bool isPossible(int *books, int N, int curDepth, int maxDepth) {
  int cnt = 0;
  for(int i = 0;i < N - 1;i ++)
    if(books[i] != books[i + 1] - 1)
      cnt ++;
  return cnt <= 3 * (maxDepth - curDepth + 1);
}

void copyAhead(int* from, int* to, int len, int start, int end, int insertPos) {
  // insertPos < start

  int beginPos = 0;
  memcpy(to, from, sizeof(int) * insertPos);
  memcpy(to + insertPos, from + start, sizeof(int) * (end - start + 1));
  memcpy(to + insertPos + end - start + 1, from + insertPos, sizeof(int) * (start - insertPos));
  memcpy(to + end + 1, from + end + 1, sizeof(int) * (len - end - 1));
}

bool BookEditor::dfs(int *books, int curDepth, int maxDepth) {

    if(curDepth == maxDepth + 1) {
   	 if(sortedBooks(books, N))
   		 return true;
   	 return false;
    }
    if(!isPossible(books, N, curDepth, maxDepth))
      return false;

    for(int start = 1;start < N;start ++) {
     for(int end = start;end < N;end ++) {
       // never split a consecutive sequence
       while(end < N - 1 && books[end + 1] == books[end] + 1)
         end ++;

       for(int insertPos = 0;insertPos < start;insertPos ++) {
         int newBooks[10];
         // prepare
         copyAhead(books, newBooks, N, start, end, insertPos);
         // dfs
         if(dfs(newBooks, curDepth + 1, maxDepth))
           return true;
       }
     }
    }
    return false;
}

void BookEditor::solve() {

    for(int iterMaxDepth = 0;iterMaxDepth <= N - 1;iterMaxDepth ++) {
   	  if(dfs(books, 1, iterMaxDepth)) {
        ans = iterMaxDepth;
        return;
      }
    }
}
void BookEditor::output() {
    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(false);

    int N;
    int kase = 1;
    while(cin >> N && N) {
   	  BookEditor be {N};
   	  be.read();
   	  be.solve();
      cout << "Case " << kase << ": ";
   	  be.output();
      kase ++;
    }

    return 0;
}
