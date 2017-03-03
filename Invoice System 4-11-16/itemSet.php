<?php 
/*
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@unl.edu, nisha.bookworm@gmail.com
*1/19/2016
*Assignment 1, problem 3:
*Takes a list of baskets and their contents and outputs every combination of items that were in the baskets
*/

$basketList = fopen(argv[1]);
$basketNum = fgets($basketList);
//Make an array with all possible items from the baskets and save an array with the baskets
$allItems = array();
$itemPresent = false;
$arrayOfBaskets = array();
while(!feof($basketList){
	$basket = fgets($basketList);
	$tempArray = explode(',', $basket);
	//basket will be saved in the next open index
	$arrayOfBaskets[] =  $tempArray;
	for($i = 0; $i < count($tempArray); $i++){
		for($j = 0; $i < count($allItems); $i++){
			if($tempArray[i] == $allItems[j]){
				$itemPresent = true;
			}
		}
		if($itemPresent ==  false){
			$allItems[] = $tempArray[i];
		}
		$itemPresent = false;
	}
}
//TODO: find all possible combos and how often they occur





echo "Items: [";
foreach($allItems as $value){
	echo $value . ", ";
}
echo "]\n";
echo "Number of baskets: " . $basketNum . "\n";

fclose(argv[1]);
?>





