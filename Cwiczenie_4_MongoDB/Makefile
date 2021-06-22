MONGODB_SERVER=mongodb://192.168.1.39:49154/nbd
DATA_FILE=../Cwiczenie_3_MongoDB/cwiczenia2.json 

LAST := 5

init:
	mongosh ${MONGODB_SERVER} --eval "db.people.drop()"
	mongoimport ${MONGODB_SERVER} --file ${DATA_FILE} --db nbd --jsonArray -c people

NUMBERS := $(shell seq 1 ${LAST})
JOBS := $(addprefix zad,${NUMBERS})
.PHONY: all ${JOBS}
all: ${JOBS} ; echo "$@ success"
${JOBS}: zad%: ; echo Zadanie: $* ; mongo --quiet ${MONGODB_SERVER} zapytanie_$*_A.js > wyniki_$*_A.json ; mongo --quiet ${MONGODB_SERVER} zapytanie_$*_MR.js > wyniki_$*_MR.json


