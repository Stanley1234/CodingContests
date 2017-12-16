/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
    int sum = 0;
    
    void sumUp(TreeNode* root, int digits) {
        if(root->left == nullptr && root->right == nullptr) {
            digits = digits * 10 + root->val;
            sum += digits;
            return;
        }
        if(root->left) sumUp(root->left, digits * 10 + root->val);
        if(root->right) sumUp(root->right, digits * 10 + root->val);
    }
    
public:
    int sumNumbers(TreeNode* root) {
        if(root == nullptr)
            return 0;
        sumUp(root, 0);
        return sum;
    }
};
