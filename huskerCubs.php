<?php
/*The huskerCubs program from Assignment 2
Translated from C to PHP
1/14/16
*/

function primeCheck($number) {
    for ($i=2; $i<$number; $i++) {
        if ($number % $i === 0 && $i != $number) return false;
    }
    return true;
}

$n = intval($argv[1]);

/*1 is always a perfect square, so there's no need to actually test that - we can start the tests at 2*/
printf("Go Huskers!\n");

for ($i=2; $i<=$n; $i++) {
	
	$j = sqrt($i);
	$k = round($j);
	
	/*Check if the number is prime and print "Go Cubs!" if it is*/
	if(primeCheck($i)===true) {
		printf("Go Cubs!\n");
	}
	
	/*Check if the number is a perfect square and print "Go Huskers!" if it is*/
	else if(pow($k, 2)==$i) {
		printf("Go Huskers!\n");
	}
	
	/*Having failed the two previous tests, just print the number*/
	else {
		printf("%d\n", $i);
	}
}
?>