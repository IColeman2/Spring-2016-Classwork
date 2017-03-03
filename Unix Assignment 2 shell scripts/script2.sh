#!/bin/bash
if
	[ $1 -eq 0 ]
then
	echo
else
	numbersToDisplay=$(($1-1))
	nMinusOne=0
	n=1
	echo "0"
	for (( i=0;i<numbersToDisplay;i++ ))
	do
		echo $n
		sum=$((n+nMinusOne))
		nMinusOne=$n
		n=$sum
	done
fi
