class MyLinkedList {
    
private:
    
    struct Node {
        int value;
        Node* prev;
        Node* next;
    };
    
    const int DUMMY = -1;
    
    int size = 0;
    Node* head;
    Node* tail;
    
public:
    /** Initialize your data structure here. */
    MyLinkedList() {
        head = new Node{DUMMY, nullptr, nullptr};
        tail = new Node{DUMMY, nullptr, nullptr};
        head->next = tail;
        tail->prev = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    int get(int index) {
        if (index >= size) {
            return DUMMY;
        }
        Node* cur = head->next;
        while (index -- > 0) {
            cur = cur->next;
        }
        return cur->value;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    void addAtHead(int val) {
        Node* newNode = new Node{val, head, head->next};
        head->next->prev = newNode;
        head->next = newNode;
        size ++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    void addAtTail(int val) {
        Node* newNode = new Node{val, tail->prev, tail};
        tail->prev->next = newNode;
        tail->prev = newNode;
        size ++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
        } else if (index == 0) {
            addAtHead(val);
        } else {
            Node* cur = head->next;
            while (index -- > 0) {
                cur = cur->next;
            }
            Node* newNode = new Node {val, cur->prev, cur};
            cur->prev->next = newNode;
            cur->prev = newNode;
            size ++;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        Node* cur = head->next;
        while (index -- > 0) {
            cur = cur->next;
        }
        Node* prevOfCur = cur->prev;
        Node* nextOfCur = cur->next;
        prevOfCur->next = nextOfCur;
        nextOfCur->prev = prevOfCur;
        delete cur;
        
        size --;
    }
};

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
