<?php
/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / skenny@huskers.unl.edu / nisha.bookworm@gmail.com
*1/19/16
*Assignment 1, Problem 2:
*A program which takes a partial word and produces of possible valid English words by matching them against a standard dictionary 
*/

//A function which compares the partial word to a complete word
function comparator($dictionaryWord, $partialWord, $size){
	$dictionaryWordArray = str_split($dictionaryWord);
	$partialWordArray = str_split($partialWord);
    for($i=0; $i < $size; $i++){
        if($partialWordArray[$i] != '-' && $dictionaryWordArray[$i] != $partialWordArray[$i]){
             return false;
        }
    }
	return true;
}

$word = $argv[1];
$sizeOfWord = strlen($word);

printf("Searching for words matching %s:\n", $word);
		
$dictionary = fopen("/usr/share/dict/american", "r");

if($dictionary) {
	
	while(!feof($dictionary)){ 
		$currentLine = fgets($dictionary);
		
		//The -1 accounts for the newline character added by fgets
		$sizeOfLine = strlen($currentLine)-1;

		if ($sizeOfLine === $sizeOfWord) {
			if(comparator($currentLine, $word, $sizeOfWord)){
				printf("%s", $currentLine);
			}
		}
	}
	fclose($dictionary);
}

?>
