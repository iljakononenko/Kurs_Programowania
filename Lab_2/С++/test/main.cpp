#include <iostream>
#include "help.h"

using namespace std;

int main(int argc, char const *argv[])
{
	help n(35);
	cout << n.getnum() << endl;
	return 0;
}