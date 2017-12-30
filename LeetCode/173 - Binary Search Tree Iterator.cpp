/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class BSTIterator {
    stack<TreeNode*> treeStack;
private:
    void pushAllLeft(TreeNode* cur) {
        while(cur) {
            treeStack.push(cur);
            cur = cur->left;
        }
    }

public:
    BSTIterator(TreeNode *root) {
        pushAllLeft(root);
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return !treeStack.empty();
    }

    /** @return the next smallest number */

    int next() {
        TreeNode* curnode = treeStack.top();
        treeStack.pop();
        pushAllLeft(curnode->right);
        return curnode->val;
    }
};

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = BSTIterator(root);
 * while (i.hasNext()) cout << i.next();
 */
 
 // disorganized code below
 /*
 class BSTIterator {
    stack<TreeNode*> treeStack;
    
    TreeNode* cur;
public:
    BSTIterator(TreeNode *root) : cur {root} {}

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return cur || !treeStack.empty();
    }

    /** @return the next smallest number */
    int next() {
        
        while(cur)  {
            treeStack.push(cur);
            cur = cur->left;
        }
        TreeNode* target = treeStack.top();
        treeStack.pop();
        
        cur = target->right;
        return target->val;
    }
};
*/
