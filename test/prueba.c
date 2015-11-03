# include <stdio.h> 

int sum(int x, int y){


	x=x/2;

	
	return x+y;
}

int inc(int x){
	return x+1;
}

void main() 
{ 
	int n1,n2;
	int res; 
	n1=10;
	n2=inc(n1);
	res=sum(n1,n2);
    //printf ("La suma es: %d",res); 
}
