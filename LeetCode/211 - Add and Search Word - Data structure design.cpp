class WordDictionary {

	struct Node {
		char val;
		vector<Node*> children;
		Node(char val) : val{val} {}
	};

	Node* root;

	void addWordIntoTrie(Node* cur, string& word, int index = 0) {
		if(index == word.length())
			return;
		for(Node* child : cur->children) {
			if(child->val == word[index]) {
				addWordIntoTrie(child, word, index + 1);
				return;
			}
		}
		Node* newNode {new Node{word[index]}};
		cur->children.emplace_back(newNode);
		addWordIntoTrie(newNode, word, index + 1);
	}

	bool dfs(Node* cur, string& word, int index = 0) {
		if(index == word.length())
			return true;
		if(cur == nullptr)
			return false;
		
		for(Node* child : cur->children) {
			if(word[index] == '.' && dfs(child, word, index + 1)) 
				return true;
			else if(word[index] == child->val && dfs(child, word, index + 1))
				return true;
		}
		return false;
	}

public:
    /** Initialize your data structure here. */
    WordDictionary() : root {new Node{' '}} {}
    
    /** Adds a word into the data structure. */
    void addWord(const string& word) {
    	string added = word + '$';
        addWordIntoTrie(root, added);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    bool search(const string& word) {
    	string searched = word + '$';
        return dfs(root, searched);
    }
};
