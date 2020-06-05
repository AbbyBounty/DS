#include <iostream>
using namespace std;

// 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
#define MAX 100

int cntr = 0;

int fibo_rec(int n) {
	cntr++;
	if (n == 1 || n == 2)
		return 1;
	return fibo_rec(n - 1) + fibo_rec(n - 2);
}

int fibo_recmem(int n, int terms[]) {
	cntr++;
	if (terms[n] != 0)
		return terms[n];
	terms[n] = fibo_recmem(n - 1, terms) + fibo_recmem(n - 2, terms);
	return terms[n];
}

int fibo_recmem(int n) {
	int terms[MAX] = { 0 };
	terms[1] = terms[2] = 1;
	return fibo_recmem(n, terms);
}

int fibo_dp(int n) {
	int i, terms[MAX] = { 0 };
	cntr++;
	terms[1] = terms[2] = 1;
	for (i = 3; i <= n; i++)
		terms[i] = terms[i - 1] + terms[i - 2];
	return terms[n];
}

int main() {
	int result;
	int n = 20;

	cntr = 0;
	result = fibo_rec(n);
	cout << "recursive result : " << result << " with recursived calls : " << cntr << endl;

	cntr = 0;
	result = fibo_recmem(n);
	cout << "recursive memoized result : " << result << " with recursived calls : " << cntr << endl;
	
	cntr = 0;
	result = fibo_dp(n);
	cout << "dynamic prog result : " << result << " with recursived calls : " << cntr << endl;

	return 0;
}
