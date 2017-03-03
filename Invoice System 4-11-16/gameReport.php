<?php
/*
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*1/19/16
*Assignment 1, Problem 1:
*Processes a CSV file containing videogame information and produces two reports: a list of publishers and a list of games
*/

//Alphabitizes publishers/game titles
function alphabatize($arr, $index){
	return strcmp($arr[$i][$index], $arr[$j][$index]);
}

//A function used to count the number of platforms per game or games per publisher
function counter($data, $input, $output){
	$finalArray = array();
	$previousGame = $data[0][$input];
	$counter = 0;
	//holds the different types of output
	$outputArray = array($data[0][$output]);
	for($i = 1; $i < count($data); $i++){
		//checks if this input is the same as previous input, if not, then it resets all variables
		if(strcmp($previousGame, $data[$i][$input]) != 0){
			$finalArray[$previousGame] = $counter;
			$previousGame = $data[$i][$input];
			$outputArray = array();
			$counter = 0;
		}
		
		//temp is used to check if it the output is already in the the output array  
		$temp = $counter;
		for($j = 0; $j < count($outputArray); $j++){
			if(strcmp($outputArray[$j], $data[$i][$output]) == 0 || strcmp($data[$i][$output], "")){
				$temp++;
				break;
			}
		}
		
		if($temp === $counter){
			$outputArray[count($outputArray)] = $data[$i][$output];
			$counter++;
		}
	}
	return $finalArray;
}

//The main code of the program:

	$gameData = array(array());
	$gameDataArray = file($argv[1]);
	
	for($i = 0; $i < count($gameDataArray); $i++){
		$singleGame = str_getcsv($gameDataArray[$i]);
		$gameData[$i]["Game"] = $singleGame[0];
		$gameData[$i]["Publisher"] = $singleGame[1];
		$gameData[$i]["Year"] = $singleGame[2];
		$gameData[$i]["Platform"] = $singleGame[3];
	}

	printf("Publisher Game Counts\n==============================\n");
	usort($gameDataArray, function($a, $b){
		return strcmp($a["Publisher"], $b["Publisher"]);
	});
	
	$publisherGameCountArray = counter($gameData, "Publisher", "Game");
	foreach ($publisherGameCountArray as $category => $value) {
		printf("%s: %s\n", $category, $value);
	}
	
	usort($gameDataArray, function($a, $b){
		return strcmp($a["Game"], $b["Game"]);
	});
	array_multisort($gameData, SORT_ASC, "Game");
	
	$printer = counter($gameData, "Game", "Platform");
	
	printf("Game Platform Counts\n==============================\n");
	$publisherGameCountArray = counter($gameData, "Game", "Platform");
	foreach ($publisherGameCountArray as $category => $value) {
		printf("%s: %s\n", $category, $value);
	}
?>