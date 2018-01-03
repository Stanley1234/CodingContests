class Trie {
    
    struct Node {
        char val;
        vector<Node*> children;
        Node(char val) : val{val} {}
    };
    
    const char WORD_END = '#';
    Node* root;

    Node* addChildIfNotExist(Node* cur, char ch) {
        Node* find = nullptr;
        for(Node* child : cur->children) {
            if(child->val == ch) {
                find = child;
                break;
            }
        }
        if(!find) {
            find = new Node{ch};
            cur->children.emplace_back(find);
        }
        return find;
    }
    
    void insertNode(Node* cur, const string& str, int index = 0) {
        if(index == str.length())
            return;
        Node* nextNode = addChildIfNotExist(cur, str[index]);
        insertNode(nextNode, str, index + 1);
    }
    bool searchNodes(Node* cur, const string& word, int index = -1) {
        if(index == word.length() - 1)
            return true;
        if(cur == nullptr)
            return false;
        for(Node* child : cur->children) {
            if(word[index + 1] == child->val)
                return searchNodes(child, word, index + 1);
        }
        return false;
    }
    
public:
    /** Initialize your data structure here. */
    Trie() : root {new Node{' '}} {}
    
    /** Inserts a word into the trie. */
    void insert(string word) {
        word += WORD_END;
        insertNode(root, word);
    }
    
    /** Returns if the word is in the trie. */
    bool search(string word) {
        word += WORD_END;
        return searchNodes(root, word);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string prefix) {
        return searchNodes(root, prefix);
    }
};
