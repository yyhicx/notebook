main : main.o sales_item.o
	g++ -o main main.o sales_item.o -std=c++11

main.o : main.cc sales_item.h
	g++ -c main.cc -std=c++11

sales_item.o : sales_item.cc sales_item.h
	g++ -c sales_item.cc -std=c++11

clean :
	rm main main.o sales_item.o
