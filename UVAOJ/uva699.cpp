#include <iostream>
#include <map>
using namespace std;

struct Node {

	int value;
	Node* left;
	Node* right;

	Node(int value, Node* left = nullptr, Node* right = nullptr) 
		: value {value}, left{left}, right {right} {}
	~Node() {
		delete left;
		delete right;
	}
};



Node* addNodes() {
	int value;
	cin >> value;

	if(value == -1)
		return nullptr;

	Node* curNode = new Node{value};

	curNode->value = value;
	curNode->left = addNodes();
	curNode->right = addNodes();

	return curNode;
}

void compute(map<int, int>& ans, Node* node, int dis) {
	if(!node)
		return;
	ans[dis] += node->value;
	compute(ans, node->left, dis - 1);
	compute(ans, node->right, dis + 1);
}


void outputAns(map<int, int>& ans) {

	bool firstOne = true;
	for(auto& entry : ans) {
		if(!firstOne) {
			cout << " ";
		}
		firstOne = false;
		cout << entry.second;
	}

}

int main() {

	int kase = 1;
	while(true) {
		Node* root = addNodes();
		if(root == nullptr)
			break;
		

		map<int, int> ans;
		compute(ans, root, 0);
		cout << "Case " << kase << ":" << endl;
		outputAns(ans);
		cout << endl << endl;

		delete root;
		kase ++;
	}

	return 0;
}