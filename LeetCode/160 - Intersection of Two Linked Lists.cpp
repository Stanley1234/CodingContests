/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
    
    int getLen(ListNode* cur) {
        int cnt = 0;
        while(cur != nullptr) {
            cnt ++;
            cur = cur->next;
        }
        return cnt;
    }
    
public:
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        
        if(lenA > lenB) 
            while(lenA -- > lenB) headA = headA->next;
        else if(lenB > lenA) 
            while(lenB -- > lenA) headB = headB->next;
        while(headA != nullptr) {
            if(headA == headB) {
                return headA;
            }
            
            headA = headA->next;
            headB = headB->next;
        }
        return nullptr;
    }
};
