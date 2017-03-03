<?php

  $months = array(
   1 => "January",
   2 => "February",
   3 => "March",
   4 => "April",
   5 => "May",
   6 => "June",
   7 => "July",
   8 => "August",
   9 => "September",
   10 => "October",
   11 => "November",
   12 => "December");
?>

<html>
  <head>
    <title>Birthday Program</title>
  </head>

  <body>
    
    <h1>Birthday Calculator Program</h1>

    <p>Greetings.  Please enter your name and your birtday below, then hit
      "submit".</p>
      <form method="post" action="birthday_result.php">
      <table>
        <tr>
          <td>Name:</td>
          <td><input type='text' name='name'/></td>
        </tr>
        <tr>
          <td>Birthday:</td>
          <td>
           <select name="month">
	     <?php 
		foreach($months as $key => $month) {
		  print "<option value='" . $key . "'>" . $month . "</option>\n";
		}
              ?>
            </select>
	    <select name="date">
	      <?php
            for($i=1; $i<=31; $i++) {
                print "<option value='" . $i . "'>" . $i . "</option>\n";
            
            ?>
	    </select>
            <select name="year">
             <?php
                for($i=1950; $i<=2000; $i++) {
                  print "<option value='" . $i . "'>" . $i . "</option>\n";
                }
              ?>
            </select>
          </td>
        </tr>
        <tr> 
          <td><input type='submit' name='submit' value='Submit'/></td>
        </tr>
      </table>
      </form>
  </body>
</html>
