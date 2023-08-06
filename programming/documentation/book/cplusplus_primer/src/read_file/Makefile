source = main.cc
object = $(subst .cc,.o,$(source))

main : $(object)
	g++ -o main $(object) -std=c++11

$(object) : $(source)
	g++ -c $(source) -std=c++11

clean :
	rm main $(object)
