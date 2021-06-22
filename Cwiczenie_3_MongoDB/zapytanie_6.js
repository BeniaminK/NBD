// 6. Dodaj siebie do bazy, zgodnie z formatem danych użytych dla innych osób (dane dotyczące karty kredytowej, adresu zamieszkania i wagi mogą być fikcyjne);
printjson(db.people.insertOne({
    birth_date: new Date(1986, 5, 13, 19, 30, 0),
    credit: {
        "type": "visa",
        "number": "1234567890",
        "currency": "PLN",
        "balance": "1234.56",
    },
    description: "My account",
    email: "beniamin.kalinowski@gmail.com",
    height: 183,
    job: "Software Engineer",
    first_name: "Beniamin",
    last_name: "Kalinowski",
    location: {
        "city":  "Warszawa",
        "address": {
            streetname: "Sportowa",
            streetnumber: "02658"
        }
    },
    nationality: "Poland",
    sex: "Male",
    weight: 83
}))

