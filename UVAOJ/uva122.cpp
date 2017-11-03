#include <iostream>
#include <string>
#include <sstream>
#include <queue>
#define NOTHING -1
using namespace std;

struct Node {
	int value;
	Node* left;
	Node* right;

	Node(int value = NOTHING, Node* left = nullptr, Node* right = nullptr)
		: value {value}, left {left}, right {right} {}

	~Node() {
		delete left;
		delete right;
	}
};


bool addNodes(Node*& root, int value, string& paths, int pathCnt = 0) {
	
	if(!root) 
		root = new Node();

	if(pathCnt == paths.length()) {

		if(root->value != NOTHING)
			return false;
		root->value = value;
		return true;
	} else {
		
		if(paths[pathCnt] == 'L') {
			return addNodes(root->left, value, paths, pathCnt + 1);
		}
		else {
			return addNodes(root->right, value, paths, pathCnt + 1);
		}
	}
}

bool traverse(Node* root, string& res) {
	queue<Node*> q;
	q.push(root);
	while(!q.empty()) {
		Node* cur = q.front();
		q.pop();

		if(cur->value == NOTHING)
			return false;
		
		// add into res
		if(res != "")
			res += " ";
		res += to_string(cur->value);

		// add its children
		if(cur->left)
			q.push(cur->left);
		if(cur->right)
			q.push(cur->right);
	}

	return true;
}

int main() {

	string keyValue;
	Node* root = nullptr;
	bool complete = true;

	while(cin >> keyValue) {
		if(keyValue == "()") {

			// check the result
			string res = "";
			if(!complete || !traverse(root, res)) 
				cout << "not complete" << endl;
			else
				cout << res << endl;

			// clear
			delete root;
			root = nullptr;
			complete = true;

		} else {
			const int commaPos = keyValue.find(",");

			istringstream iss {keyValue.substr(1, commaPos)};
			int value;
			iss >> value;

			string paths = keyValue.substr(commaPos + 1, keyValue.length() - commaPos - 2);

			complete &= addNodes(root, value, paths);
		}
	}



	return 0;
}