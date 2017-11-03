/*
Alternative way:
since the canvas has size of 32x32.
we can create an array and draw directly on the array twice


*/



#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Node {
	char color;
	Node* c1;
	Node* c2;
	Node* c3;
	Node* c4;

	Node(char color) : color {color}, c1 {nullptr}, c2 {nullptr}, c3 {nullptr}, c4 {nullptr} {}

	int compute(const int size = 32) const {
		if(color == 'e')
			return 0;
		else if(color == 'f')
			return size * size;
		else {
			int sum = 0;
			if(c1)
				sum += c1->compute(size / 2);
		 	if(c2)
		 		sum += c2->compute(size / 2);
		 	if(c3)
		 		sum += c3->compute(size / 2);
		 	if(c4)
		 		sum += c4->compute(size / 2);
		 	return sum;
		}
	}
};

Node* constructTree(vector<Node*>& memoryPool) {
	// f - black
	// p - grey
	// e - white
	char color;
	cin >> color;

	Node* curNode = new Node{color};
	memoryPool.push_back(curNode);
    if(color == 'p') {
		curNode->c1 = constructTree(memoryPool);
		curNode->c2 = constructTree(memoryPool);
		curNode->c3 = constructTree(memoryPool);
		curNode->c4 = constructTree(memoryPool);
	} 
	return curNode;
}


void softMerge(Node*& lhs, Node* rhs) {
	if(!lhs) {
		lhs = rhs;
		return;
	}
	if(!rhs)
		return;

	if(lhs->color == 'e' || rhs->color == 'f') {
		lhs->color = rhs->color;
		lhs->c1 = rhs->c1;
		lhs->c2 = rhs->c2;
		lhs->c3 = rhs->c3;
		lhs->c4 = rhs->c4;
	} else if(lhs->color == 'p') {
		softMerge(lhs->c1, rhs->c1);
		softMerge(lhs->c2, rhs->c2);
		softMerge(lhs->c3, rhs->c3);
		softMerge(lhs->c4, rhs->c4);
	}
}

int main() {
	int kase;
	cin >> kase;
	while(kase -- > 0) {
		vector<Node*> memoryPool;
		Node* root1 = constructTree(memoryPool);
		Node* root2 = constructTree(memoryPool);


		softMerge(root1, root2);
		cout << "There are " << root1->compute() << " black pixels." << endl;
	}

	return 0;
}