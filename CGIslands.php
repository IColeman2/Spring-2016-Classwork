<?php
/*The CGIslands program from Assignment 2
Translated from Java to PHP
1/14/16
*/

if($argc != ) {
	printf("Usage: %s n DNASEQUENCE\n", $argv[0]);
	exit(1);
}

$n = intval($argv[1]);
$dna = $argv[2];

printf("DNA CGIsland program, awesome");
printf("n = %d\n", n);

$highestCount = 0;
for($i =; $i<=strlen($dna)-$n; $i++) {
	$count = 0;
	$subsequence = substr($dna, $i, $n);
	for($j = 0; $j<strlen($subsequence); $j++) {
		if($subsequence[$j] === "C" || $subsequence === "G") {
			$count++;
		}
	}
	if($highestCount<$count) {
		$highestCount = $count;
	}
}

printf("Highest frequency: %d / %d = %f%%\n", $highestCount, $n, ($highestCount/$n));

?>