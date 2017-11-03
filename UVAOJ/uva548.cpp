#include <iostream>
#include <sstream>
#define MAXN 10010
#define INF 999999999
using namespace std;


struct Node {
	int value;
	Node* left;
	Node* right;

	Node(int value, Node* left = nullptr, Node* right = nullptr) 
		: value {value}, left {left}, right {right} {}
	~Node() {
		delete left;
		delete right;
	}
};

int inOrder[MAXN], postOrder[MAXN];
int inOrderLen = 0, postOrderLen = 0;

int findPos(int* arr, int element, 
			int start = 0, int end = inOrderLen - 1) {
	
	for(int i = start;i <= end;i ++)
		if(element == arr[i])
			return i;
	return -1;
}

// leftIndex, rightIndex for inorder array
Node* createTrees(int inOrderLeft, int inOrderRight,
					int postOrderLeft, int postOrderRight) {
	if(inOrderLeft > inOrderRight)
		return nullptr;

	int rootPosByInorder = findPos(inOrder, postOrder[postOrderRight], inOrderLeft, inOrderRight);
	int leftCount = rootPosByInorder - inOrderLeft;


	Node* root = new Node {inOrder[rootPosByInorder]};
	root->left = createTrees(inOrderLeft, rootPosByInorder - 1, 
								postOrderLeft, postOrderLeft + leftCount - 1);
	root->right = createTrees(rootPosByInorder + 1, inOrderRight,
								postOrderLeft + leftCount, postOrderRight - 1);

	return root;
}

void findLeastPath(Node* root, int& leastLeafNode, int &leastPathValue, int curValue) {


	// leave node
	if(!root->left && !root->right) {

		if(leastPathValue > curValue + root->value
			|| (leastPathValue == curValue + root->value && root->value < leastLeafNode)) {

			leastPathValue = curValue + root->value;
			leastLeafNode = root->value;
		}
		return;
	}
	if(root->left)
		findLeastPath(root->left, leastLeafNode, leastPathValue, curValue + root->value);
	if(root->right)
		findLeastPath(root->right, leastLeafNode, leastPathValue, curValue + root->value);

}


int main() {

	string postOrderLine;
	string inOrderLine;

	while(getline(cin, inOrderLine)) {
		getline(cin, postOrderLine);

		// convert to array of integers
		istringstream iss{postOrderLine};

		int tmp;
		while(iss >> tmp) 
			postOrder[postOrderLen ++] = tmp;

		iss.clear();
		iss.str(inOrderLine);
		while(iss >> tmp) 
			inOrder[inOrderLen ++] = tmp;

		// solve the problem
		// first create the tree
		Node* root = createTrees(0, inOrderLen - 1, 0, postOrderLen - 1);

		// then ...
		int leastLeafNode = INF, leastPathValue = INF;
		findLeastPath(root, leastLeafNode, leastPathValue, root->value);
		cout << leastLeafNode << endl;

		// clear 
		delete root;
		inOrderLen = postOrderLen = 0;
	}



	return 0;
}