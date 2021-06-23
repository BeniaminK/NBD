BUCKET := s23765
SERVER := http://localhost:8098
SERVER_ADDR := ${SERVER}/buckets/${BUCKET}/keys
LAST := 10

list_buckets:
	curl -i -XGET ${SERVER}/buckets?buckets=true

NUMBERS := $(shell seq -w 1 ${LAST})
JOBS := $(addprefix zad_,${NUMBERS})
.PHONY: all ${JOBS}
all: ${JOBS} ; echo "$@ success"

zad_01:
	curl -i -XPOST ${SERVER_ADDR}/1 -H "Content-Type: application/json" -d '{"imie": "Agnieszka", "nazwisko": "Spencer", "wiek": 21, "wzrost": 163}'
	curl -i -XPOST ${SERVER_ADDR}/2 -H "Content-Type: application/json" -d '{"imie": "Bartosz", "nazwisko": "Tracz", "wiek": 22, "wzrost": 168}'
	curl -i -XPOST ${SERVER_ADDR}/3 -H "Content-Type: application/json" -d '{"imie": "Celina", "nazwisko": "Ujanowska", "wiek": 23, "wzrost": 173}'
	curl -i -XPOST ${SERVER_ADDR}/4 -H "Content-Type: application/json" -d '{"imie": "Damian", "nazwisko": "Wojcieszek", "wiek": 24, "wzrost": 178}'
	curl -i -XPOST ${SERVER_ADDR}/5 -H "Content-Type: application/json" -d '{"imie": "Ewelina", "nazwisko": "Zielak", "wiek": 25, "wzrost": 183}'

	curl ${SERVER}/buckets/${BUCKET}/keys?keys=true

zad_02:
	curl -i -XGET ${SERVER_ADDR}/1

zad_03:
	curl -i -XPOST ${SERVER_ADDR}/1 -H "Content-Type: application/json" -d '{"imie": "Agnieszka", "nazwisko": "Spencer", "wiek": 21}'

zad_04:
	curl -i -XPOST ${SERVER_ADDR}/1 -H "Content-Type: application/json" -d '{"imie": "Agnieszka", "nazwisko": "Spencer", "wiek": 21, "wzrost": 163, "miasto": "Warszawa"}'

zad_05:
	curl -i -XPOST ${SERVER_ADDR}/1 -H "Content-Type: application/json" -d '{"imie": "Agnieszka", "nazwisko": "Spencer", "wiek": 21, "wzrost": 163, "miasto": "Lublin"}'

zad_06:
	curl -i -XDELETE ${SERVER_ADDR}/1

zad_07:
	curl -i -XDELETE ${SERVER_ADDR}/6

zad_08:
	curl -i -XPOST ${SERVER_ADDR} -H "Content-Type: application/json" -d '{"imie": "Filip"}'

zad_09:
	curl -i -XGET ${SERVER_ADDR}/KfW2bASOd3ZYVJuMUqCKBGX5TJo

zad_10:
	curl -i -XDELETE ${SERVER_ADDR}/KfW2bASOd3ZYVJuMUqCKBGX5TJo
