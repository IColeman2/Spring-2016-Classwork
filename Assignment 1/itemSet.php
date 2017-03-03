<?php 
/*
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@unl.edu, nisha.bookworm@gmail.com
*1/19/2016
*Assignment 1, problem 3:
*Takes a list of baskets and their contents and outputs every combination of items that were in the baskets
*/

$basketList = fopen($argv[1], "r");
$basketNum = fgets($basketList);
//Make an array with all possible items from the baskets and save an array with the baskets
$allItems = array();
$items = array();
while(!feof($basketList)){
	$basket = fgets($basketList);
	$tempArray = explode(',', $basket);
	foreach($tempArray as $key => $value){
		$tempArray[$key] = trim($value);
	}
	for($i = 0; $i < count($tempArray); $i++){
		$tempIndex = checkIfExists(array( 0 => $tempArray[$i]), $allItems, 1);
		if($tempIndex != null){
			$allItems[$tempIndex]++;
		}
		else{
			$allItems[$tempArray[$i]] = 1;
			$items[]= $tempArray[$i];
		}
		for($j = $i + 1; $j < count($tempArray); $j++){
			$tempIndex = checkIfExists(array( 0 => $tempArray[$i], 1 => $tempArray[$j]), $allItems, 2);
			if($tempIndex != null){
				$allItems[$tempIndex]++;
			}
			else{
				$allItems[$tempArray[$i].", ".$tempArray[$j]] = 1;
			}
			for($k = $j + 1; $k < count($tempArray); $k++){
				//echo "i = $i, j = $j, k = $k";
				$tempIndex = checkIfExists(array(0 => $tempArray[$i], 1 => $tempArray[$j], 2 => $tempArray[$k]), $allItems, 3);
				if($tempIndex != null){
					$allItems[$tempIndex]++;
				}
				else{
					$allItems[$tempArray[$i].", ".$tempArray[$j].", ".$tempArray[$k]] = 1;
				}
			}
		}
	}
}

function checkIfExists($arrayOfThingsToCheck, $arrayToCheckAgainst, $amountInCombination){
	if($amountInCombination === 1){
		if(array_key_exists($arrayOfThingsToCheck[0], $arrayToCheckAgainst)){
			$array[]=$arrayOfThingsToCheck[0];
			return $arrayOfThingsToCheck[0];
		}
		return null;
	}
	else if($amountInCombination === 2){
		if(array_key_exists($arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[1],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[1];
		}
		else if(array_key_exists($arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[0],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[0];
		}
		return null;
	}
	else if($amountInCombination === 3){
		if(array_key_exists($arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[2], $arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[2];
		}
		else if(array_key_exists($arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[1],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[1];
		}
		else if(array_key_exists($arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[0],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[0];
		}
		else if(array_key_exists($arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[2],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[2];
		}
		else if(array_key_exists($arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[1],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[0].", ".$arrayOfThingsToCheck[1];
		}
		else if(array_key_exists($arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[0],$arrayToCheckAgainst)){
			return $arrayOfThingsToCheck[2].", ".$arrayOfThingsToCheck[1].", ".$arrayOfThingsToCheck[0];
		}
	}
}

printf("Items: [");
printf(implode(", ", $items));
printf("]\n");
printf("Number of baskets: %d\n", $basketNum);
foreach($allItems as $key => $value){
	printf("%d => [%s]\n", $value, $key);
}

fclose($basketList);
?>