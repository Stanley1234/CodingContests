class Solution {
    
    
    const int offsetR[4] = {-1, 0, 1, 0};
    const int offsetC[4] = {0, 1, 0, -1};
    
    bool withinBoundary(int r, int c, int h, int w) {
        return r >= 0 && c >= 0 && r < h && c < w;
    }
    
    void BFS(vector<vector<char>>& board, int r, int c) {
        
        int height = board.size();
        int width = board[0].size();
        
        queue<pair<int,int>> q;
        
        if(board[r][c] != 'O') 
            return;
        board[r][c] = 'N';
        q.push(make_pair(r, c));
        
        while(!q.empty()) {
            auto curPos = q.front(); q.pop();
            
            for(int i = 0;i < 4;i ++) {
                int nr = curPos.first + offsetR[i];
                int nc = curPos.second + offsetC[i];
                
                if(!withinBoundary(nr, nc, height, width))
                    continue;
                if(board[nr][nc] != 'O') 
                    continue;
                
                board[nr][nc] = 'N';
                q.push(make_pair(nr, nc));
            }
        }
    }
    
    void markOConnectedToBoundary(vector<vector<char>>& board) {
        int h = board.size();
        int w = board[0].size();
        
        for(int i = 0;i < h;i ++) {
            BFS(board, i, 0);
            BFS(board, i, w - 1);
        }
        for(int j = 0;j < w;j ++) {
            BFS(board, 0, j);
            BFS(board, h - 1, j);
        }
    }
    
    void remark(vector<vector<char>>& board) {
        int h = board.size();
        int w = board[0].size();
        
        for(int i = 0;i < h;i ++) {
            for(int j = 0;j < w;j ++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                else if(board[i][j] == 'N')
                    board[i][j] = 'O';
            }
        }
    }
    
public:
    void solve(vector<vector<char>>& board) {
        if(board.size() == 0)
            return;
        markOConnectedToBoundary(board);
        remark(board);
    }
};
