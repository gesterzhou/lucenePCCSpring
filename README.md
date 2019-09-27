
This is my original version

How to run:
~/pcc/lucenePCCSpring
~/pivotal-gemfire-9.8.3/bin/gfsh
run --file=script.gfsh

In intelliJ, run the ExampleTest

Then in gfsh, run following
query --query="select * from /Person"
search lucene --name=personIndex --region=/Person --queryString="Tom*" --defaultField=name

