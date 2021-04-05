db = db.getSiblingDB('nbd')
// 8. Zastąp nazwę miasta „Moscow” przez „Moskwa” u wszystkich osób w bazie;
var result = db.people.updateMany(
    {
        "location.city": "Moscow"
    },
    {
        $set: {
            "location.city": "Moskwa"
        }
    }
)

printjson(result)

// printjson(db.people.find({ "location.city": "Moskwa" }).toArray())

