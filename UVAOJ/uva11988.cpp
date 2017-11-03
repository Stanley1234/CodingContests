#include <list>
#include <iostream>
#include <iterator>
#include <string>
using namespace std;



int main() {
	string line;
	while(getline(cin, line)) {

		list<char> text;
		auto iter = text.begin();

		for(char ch : line) {
			if(ch == '[') {
				iter = text.begin();

			} else if(ch == ']') {
				iter = text.end();

			} else {
				if(iter == text.end())
					text.insert(iter, ch);
				else {
					iter = text.insert(iter, ch);
					iter = next(iter);
				}
			}
		}

		for(char ch : text) 
			cout << ch;
		cout << endl;
	}

	return 0;
}