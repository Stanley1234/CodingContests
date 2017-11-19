#include <iostream>
#include <vector>
using namespace std;

/*
Accpted

However, this version is almost five times slower than the second version
under the following test case:
9
6 4 5 8 3 1 2 7 9
9
2 3 6 7 1 8 9 5 4
9
2 1 7 9 5 6 4 8 3
9
7 8 3 6 4 1 9 2 5
9
9 4 8 6 2 1 5 7 3
9
9 5 1 3 6 8 7 4 2
9
2 6 1 9 5 7 4 8 3
9
2 4 3 9 6 8 5 1 7
9
8 7 4 5 1 6 9 3 2
9
1 2 7 4 9 6 8 5 3
9
8 2 9 5 4 6 7 3 1
9
7 4 3 5 8 1 2 9 6
9
3 5 9 2 4 8 6 1 7
9
5 3 9 2 8 4 1 6 7
8
1 2 3 4 5 6 7 8
7
1 2 3 4 5 6 7
8
6 4 3 2 8 1 5 7
9
3 6 4 8 7 5 1 2 9
9
9 8 7 6 5 4 3 2 1
0
*/

class BookEditor {

    int N;   //1 <= N <= 9
    vector<int> books;
    int ans;

    bool sortedBooks();
    bool isPossible(int curDepth, int maxDepth);
    bool dfs(int curDepth, int maxDepth);

public:
    BookEditor(int N);
    void read();
    void solve();
    void output();

};

BookEditor::BookEditor(int N) : N {N}, books(10) {}

void BookEditor::read() {
    for(int i = 0;i < N;i ++)
   	 cin >> books[i];
}

bool BookEditor::sortedBooks() {
    // ascending order
    for(int i = 1;i < N;i ++)
   	 if(books[i] < books[i - 1])
   		 return false;
    return true;
}

bool BookEditor::isPossible(int curDepth, int maxDepth) {
  int cnt = 0;
  for(int i = 0;i < N - 1;i ++)
    if(books[i] != books[i + 1] - 1)
      cnt ++;
  return cnt <= 3 * (maxDepth - curDepth + 1);
}


bool BookEditor::dfs(int curDepth, int maxDepth) {

    if(curDepth == maxDepth + 1) {
   	 if(sortedBooks())
   		 return true;
   	 return false;
    }
    if(!isPossible(curDepth, maxDepth))
      return false;

    for(int start = 1;start < N;start ++) {
     for(int end = start;end < N;end ++) {
       
       // never split a consecutive sequence
       while(end < N - 1 && books[end + 1] == books[end] + 1)
        end ++;

       for(int insertPos = 0;insertPos < start;insertPos ++) {
         vector<int> tmp;
         // prepare
         tmp.insert(tmp.begin(), books.begin() + start, books.begin() + end + 1);

         books.erase(books.begin() + start, books.begin() + end + 1);
         books.insert(books.begin() + insertPos, tmp.begin(), tmp.end());

         // dfs
         if(dfs(curDepth + 1, maxDepth))
           return true;

         // restore
         books.erase(books.begin() + insertPos, 
                     books.begin() + insertPos + (end - start + 1));
         books.insert(books.begin() + start, tmp.begin(), tmp.end());
       }
     }
    }
    

    return false;
}


void BookEditor::solve() {

    for(int iterMaxDepth = 0;iterMaxDepth <= N - 1;iterMaxDepth ++) {
      //printf("%d\n", iterMaxDepth);
   	  if(dfs(1, iterMaxDepth)) {
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
    while(cin >> N) {
      if(!N)
        break;
   	  BookEditor be {N};
   	  be.read();
   	  be.solve();
      cout << "Case " << kase << ": ";
   	  be.output();
      kase ++;
    }

    return 0;
}
