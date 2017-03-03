<?php

  /* PHP code starts with a <?php and ends with a ?> so that it can
     be interleaved with static HTML content.
   */

/* functions can be declared which will have global scope */

function getMin($array) {
  if($array == null || count($array) == 0) {
    return null;
  }

  $min = $array[1];
  for($i=2; $i<count($array); $i++) {
    if($min > $array[$i]) {
      $min = $array[$i];
    }
  }
  return $min;
}

function getMax($array) {
  if($array == null || count($array) == 0) {
    return null;
  }

  $max = $array[1];
  for($i=2; $i<count($array); $i++) {
    if($max < $array[$i]) {
      $max = $array[$i];
    }
  }
  return $max;
}

function getAverage($array) {
  if($array == null) {
    return null;
  }

  $sum = getSum($array);
  return ($sum / (count($array)-1));
}

function getSum($array) {
  if($array == null || count($array) == 0) {
    return null;
  }
  $sum = $array[1];
  for($i=2; $i<count($array); $i++) {
	  $sum+=$array[$i];
  }
  return $sum;
}
  /*
  echo "Please input the number of integers being entered (>=2): ";
  
  $numberOfEntries = intval(trim(fgets(STDIN)));

  if($numberOfEntries < 2) {
    print "Invalid input: number of entries must be at least 2\n";
    exit(1);
  }

  $numbers = array();
  echo "Enter your integers one at at time, followed by the enter key.\n";
  $count = 0;
  while($count < $numberOfEntries) {

    $numbers[] = intval(trim(fgets(STDIN)));
    $count++;
  } */

  $min = getMin($argv);
  $max = getMax($argv);
  $average = getAverage($argv);
  $sum = getSum($argv);

  /*
    Output can be done with echo, print, or printf, string concatenation is done
    with the period operator.
   */
  echo "The sum is ".$sum."\n";
  echo "The average is ".$average."\n";
  echo "The highest is ".$max."\n";
  echo "The lowest is ".$min."\n";
?>
