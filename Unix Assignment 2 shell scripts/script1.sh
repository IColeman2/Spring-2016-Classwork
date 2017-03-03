i=0
size=${#1}
while
     [ $i -lt $size ]
do
     i=$(( $i + 1 ))
     echo $1
done

if
	[ $2 -gt 0 ]
then
	echo "Positive"
fi

if
	[ $2 -lt 0 ]
then
	echo "Negative"
fi
