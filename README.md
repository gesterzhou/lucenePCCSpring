
This is my original version

How to run:
Download gmfire 9.8.3
cd lucenePCCSpring
./gradlew build
~/pivotal-gemfire-9.8.3/bin/gfsh
run --file=script.gfsh

In intelliJ, run the ExampleTest

Then in gfsh, run following
query --query="select * from /Person"
search lucene --name=personIndex --region=/Person --queryString="Tom*" --defaultField=name

In intelliJ, run the Example
start web pages:
http://localhost:8080/showData
http://localhost:8080/showDataById/23

