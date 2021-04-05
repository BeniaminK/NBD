db = db.getSiblingDB('nbd')
// 10. Usuń u wszystkich osób o zawodzie „Editor” własność „email”.
var c = db.people.updateMany(
    {
        job: "Editor"
    },
    {
        $unset: {
            "email": ""
        }
    }
)

printjson(c)

// printjson(db.people.findOne({job: "Editor"}))

