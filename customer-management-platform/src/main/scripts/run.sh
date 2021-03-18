#!/bin/sh
echo "***************************************************************************"
echo "Waiting for the Oracle database server to start on $ORACLE_HOST:$ORACLE_PORT"
echo "***************************************************************************"
#while ! tnsping xe; do sleep 3; done
sleep 60
echo ">>>>>>>>>>>> The Oracle databse server has started"
echo "********************************************************"
echo "Starting Configuration Server"
echo "********************************************************"
java -jar /app.jar