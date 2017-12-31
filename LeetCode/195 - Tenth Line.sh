num=$(wc -l file.txt | awk '{print $1}')
if [ $num -ge 10 ]; then
    head -10 file.txt | tail -1
fi
