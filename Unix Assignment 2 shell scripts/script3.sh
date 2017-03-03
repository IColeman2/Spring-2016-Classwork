#!/bin/bash
echo "Enter a number:"
read minimum
echo "Enter in a bigger number:"
read maximum
echo

if 
	[ $maximum -lt $minimum ]
then
	echo "The first number must be smaller than the second!"
else
	echo The files in $1 that are between $minimum and $maximum bytes are the following:
	echo
	cd $1
	for file in *
	do
		if
			[ -f $file ]
		then
			fileSize=$(wc -c <"$file")
			if
				[ $fileSize -le $maximum ]
			then
				if
					[ $fileSize -ge $minimum ]
				then
					echo $file is $fileSize bytes
				fi
			fi
		fi
	done
fi
