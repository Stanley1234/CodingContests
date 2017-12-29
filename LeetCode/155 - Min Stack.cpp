class MinStack {

    struct Node {
        int val;
        int min;
        Node* next;
        
        Node(int val, int min) : val{val}, min{min}, next{nullptr} {}
    };
    
    int minElem;
    int size;
    Node* pTop;
    
public:
    /** initialize your data structure here. */
    MinStack() : size{0}, pTop{nullptr}  {}

    void push(int x) {
        if(size == 0)
            minElem = x;
        else
            minElem = (minElem > x ? x : minElem);
        
        Node* frame {new Node {x, minElem}};
        frame->next = pTop;
        pTop = frame;
        size ++;
    }
    
    void pop() {
        if(size == 0)
            return;
        
        Node* nextFrame = pTop->next;
        delete pTop;
        pTop = nextFrame;
        
        if(pTop != nullptr)
            minElem = pTop->min;
        size --;
    }
    
    int top() {
        return pTop->val;
    }
    
    int getMin() {
        return minElem;
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
