#!/bin/bash 

# Start script for bankrupt-officer-search-api  

PORT=8080
exec java -jar -Dserver.port="${PORT}" "bankrupt-officer-search-api.jar"
