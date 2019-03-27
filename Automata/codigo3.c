#include<stdio.h>
int main(){
	char vec[3];
	int a, b = 3;
	

	a = b + 4;
	if(a >= 0 ){
		a = a * ( b / 2)+ 1;
	}
	else{
		a = 0;
	}

	int c = a - b;

	while(c >= 0){
		c = c - 1;
	}
	
	return 0;
}
