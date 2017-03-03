<?php
/*
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*1/19/16
*Assignment 1, Problem 1:
*Processes a CSV file containing videogame information and produces two reports: a list of publishers and a list of games
*/

//A function used to count the number of platforms per game or games per publisher
function counter($gameDataArray, $forCategory, $dataToCount){
	$finalOutputMap = array();
	$currentCategoryName = $gameDataArray[0][$forCategory]; //Gets the first category name
	$i = 0;
	while($i<count($gameDataArray)) {
		$counter = 0;
		$hasBeenCounted = array(); 
		//Go through array until you run out of the category we're looking at
		while($i<count($gameDataArray) && $gameDataArray[$i][$forCategory]==$currentCategoryName) {
			//Makes sure the game actually exists
			if($gameDataArray[$i][$dataToCount] && !array_key_exists($gameDataArray[$i][$dataToCount], $hasBeenCounted)) {
				$counter++;
				$hasBeenCounted[$gameDataArray[$i][$dataToCount]]=true;
			}
			$i++;
		}
		$finalOutputMap[$currentCategoryName] = $counter;
		if($i<count($gameDataArray)) {
			$currentCategoryName = $gameDataArray[$i][$forCategory];
		}
	}
	return $finalOutputMap;
}

//Read in files

$gameDataArray = array();
$fileRead = file($argv[1]);

foreach($fileRead as $line){
	$CSVData = str_getcsv($line);
	$game = [
		"Game Name"=>trim($CSVData[0]),
		"Publisher"=>trim($CSVData[1]),
		"Year"=>trim($CSVData[2]),
		"Platform"=>trim($CSVData[3]),
		];
	array_push ($gameDataArray, $game);
}

//Sort and output
printf("\nPublisher Game Counts\n=======================================\n");
usort($gameDataArray, function($a, $b){
	return strcmp($a["Publisher"], $b["Publisher"]);
});

$publisherGameCountArray = counter($gameDataArray, "Publisher", "Game Name");
foreach ($publisherGameCountArray as $category => $value) {
	printf("%-30s\t%5s\n", $category, $value);
}

usort($gameDataArray, function($a, $b){
	return strcmp($a["Game Name"], $b["Game Name"]);
});

printf("\nGame Platform Counts\n=======================================\n");
$gamePlatformCountArray = counter($gameDataArray, "Game Name", "Platform");
foreach ($gamePlatformCountArray as $category => $value) {
	if($category) {
		printf("%-30s\t%5s\n", $category, $value);
	}
}
?>