#!/bin/sh

term_handler(){
  echo 'Stopping...'
  exit 0
}

trap 'term_handler' TERM

# Run the application
java -jar ${JAR_FILE} &

# Wait indefinitely
while true
do
  tail -f /dev/null & wait ${!}
done