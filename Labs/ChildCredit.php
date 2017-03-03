<?php

include_once("Child.php");

$tom = new Child("Tommy", 14); 
$dick = new Child("Richard", 12);
$harry = new Child("Harold", 21);

$kids = array($tom, $dick, $harry);

//TODO: compute the credits and output a table
echo("Child           Amount\n");
$firstChild = true;
$totalCredit = 0;
foreach($kids as $value) {
	$amount = 0;
	if($firstChild) {
		$amount = 1000;
		$totalCredit+=1000;
		$firstChild = false;
	}
	else {
		if($value->getAge() < 18) {
			$amount = 500;
			$totalCredit+=500;
		}
	}
	echo($value->getName() . " (" .$value->getAge() . ") - $" . $amount . ".00\n");
}
echo("Total Credit - $" . $totalCredit . ".00\n")

?>