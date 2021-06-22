MONGODB_SERVER=mongodb://192.168.1.39:49154/nbd

LAST := 10

init:
	mongosh ${MONGODB_SERVER} --eval "db.people.drop()"
	mongoimport ${MONGODB_SERVER} --file cwiczenia2.json --db nbd --jsonArray -c people

NUMBERS := $(shell seq 1 ${LAST})
JOBS := $(addprefix zad,${NUMBERS})
.PHONY: all ${JOBS}
all: ${JOBS} ; echo "$@ success"
${JOBS}: zad%: ; echo Zadanie: $* ; mongo ${MONGODB_SERVER} zapytanie_$*.js > wyniki_$*.json

