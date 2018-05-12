
#!/bin/sh
ID=`ps -ef | grep loanPlatform | grep -v "grep" | awk '{print $2}'`
echo $ID
echo "----start kill process--------"
for id in $ID
do
kill -9 $id
echo "killed $id"
done
echo "------kill end---------"


echo "start new proccess , commond is 'nohup java -Dspring.profiles.active=test -jar loanPlatform-1.0-SNAPSHOT.jar > loanPlatform.out 3>&1 &' "
nohup java -Dspring.profiles.active=test -jar loanPlatform-1.0-SNAPSHOT.jar > loanPlatform.out 3>&1 &
