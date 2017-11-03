#include <iostream>
#include <sstream>
#include <string>
#include <queue>
#include <set>
#include <map>
#include <algorithm>
#define MAX_SIZE 205
using namespace std;




class Picture {
    array<string, MAX_SIZE> picture;

    int width;
    int height;

    int curRow;

    string decodeHex(char ch) {
   	 switch(toupper(ch))  {
    	case '0': return "wwww";
    	case '1': return "wwwf";
    	case '2': return "wwfw";
    	case '3': return "wwff";
    	case '4': return "wfww";
    	case '5': return "wfwf";
    	case '6': return "wffw";
    	case '7': return "wfff";
    	case '8': return "fwww";
    	case '9': return "fwwf";
    	case 'A': return "fwfw";
    	case 'B': return "fwff";
    	case 'C': return "ffww";
    	case 'D': return "ffwf";
    	case 'E': return "fffw";
    	case 'F': return "ffff";
   	 }

    }

    char decodeCharacter(int num) {
   	 switch(num) {
   		 case 0: return 'W';
   		 case 1: return 'A';
   		 case 2: return 'K';
   		 case 3: return 'J';
   		 case 4: return 'S';
   		 case 5: return 'D';
   	 }
    }

    bool inBoundary(int r, int c) {
   	 return r >= 0 && c >= 0 && r < height && c < width;
    }
    void bfs(int startX, int startY, char replace) {
   	 queue<pair<int,int>> q;

   	 q.push(make_pair(startX, startY));
   	 picture[startX][startY] = replace;

   	 while(!q.empty()) {
   		 auto entry = q.front();
   		 q.pop();

   		 int r = entry.first;
   		 int c = entry.second;


   		 const int offsetR[4] = {-1, 0, 0, 1};
   		 const int offsetC[4] = {0, -1, 1, 0};

   		 for(int k = 0;k < 4;k ++) {
   			 int nr = entry.first + offsetR[k];
   			 int nc = entry.second + offsetC[k];

   			 if(inBoundary(nr, nc) && picture[nr][nc] == 'w') {
   				 picture[nr][nc] = replace;
   				 q.push(make_pair(nr, nc));
   			 }

   		 }
   	 }
    }

    int markAndCount(int startX, int startY) {	
   	 queue<pair<int,int>> q;

   	 q.push(make_pair(startX, startY));

   	 picture[startX][startY] = 'b';
   	 int cnt = 0;
   	 while(!q.empty()) {
   		 auto entry = q.front();
   		 q.pop();

   		 int r = entry.first;
   		 int c = entry.second;

   		 const int offsetR[4] = {-1, 0, 0, 1};
   		 const int offsetC[4] = {0, -1, 1, 0};
   		 // count new enclosed area
   		 for(int k = 0;k < 4;k ++) {
   			 int nr = entry.first + offsetR[k];
   			 int nc = entry.second + offsetC[k];
   			 if(!inBoundary(nr, nc))
   			 	continue;

   			 char curChar = picture[nr][nc];

   			 if(curChar == 'b' || curChar == 'f' || curChar == 'r')
   				 continue;
   			 cnt ++;
   			 bfs(nr, nc, 'r');
   		 }
   		 // find the next black
   		 for(int k = 0;k < 4;k ++) {
   			 int nr = entry.first + offsetR[k];
   			 int nc = entry.second + offsetC[k];

   			 if(inBoundary(nr, nc) && picture[nr][nc] == 'f') {
   				 picture[nr][nc] = 'b';
   				 q.push(make_pair(nr, nc));
   			 }
   		 }
   	 }
   	 return cnt;
    }

    void markMargin() {
   	 // start from the boundary
   	 // mark the margin as 'r'
   	 queue<pair<int, int>> q;


   	 // remove margin
   	 for(int c = 0;c < width;c ++) {    
   		 if(picture[0][c] == 'w')
   			 bfs(0, c, 'r');

   		 if(picture[height - 1][c] == 'w')
   			 bfs(height - 1, c, 'r');
   	 }
   	 for(int r = 0;r < height;r ++) {
   		 if(picture[r][0] == 'w')
   			 bfs(r, 0, 'r');
   		 if(picture[r][width - 1] == 'w')
   			 bfs(r, width - 1, 'r');
   	 }
    }

    void output(string& ans) {
   	 // print in alphabetic order

   	 for(int r = 0;r < height;r ++) {
   		 for(int c = 0;c < width;c ++) {
   			 if(picture[r][c] != 'f')
   				 continue;
   			 int eigen = markAndCount(r, c);
   			 ans += decodeCharacter(eigen);
   		 }
   	 }
   	 //cout << *this;
   	 sort(ans.begin(), ans.end());
   	 cout << ans << endl;
    }

public:
    Picture(int H, int W) : height {H}, width {W * 4}, curRow {0} {}

    void addLine(string& hex) {
   	 for(unsigned int i = 0;i < hex.length();i ++) 
   		picture[curRow] += decodeHex(hex[i]);
   	 curRow ++;
    }
    void translate() {
   	 string ans;
   	 markMargin();
   	 //cout << *this;
   	 output(ans);
    }

    friend ostream& operator<<(ostream& out, Picture & picture);
};

ostream& operator<<(ostream& out, Picture & p) {
	out << p.curRow << endl;
	for(int i = 0;i < p.curRow;i ++) {
		out << p.picture[i] << endl;
	}
	return out;
}

int main() {
    int H, W;
    int kase = 1;
    while(true) {
   	 cin >> H >> W;
   	 if(H == 0)
   		 break;

   	 Picture picture {H, W};
   	 string hexLine;
   	 for(int i = 0;i < H;i ++) {
   		 cin >> hexLine;
   		 picture.addLine(hexLine);
   	 }
   	 //cout << picture;
   	 cout << "Case " << kase << ": ";
   	 picture.translate();
   	 kase ++;
    }
    return 0;
}
