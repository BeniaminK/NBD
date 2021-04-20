// 1. Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet);
//A
const c = db.people.aggregate([
    {
        $set: {
            "height": {$toDecimal: "$height"}
        }
    },
    {
        $set: {
            "weight": {$toDecimal: "$weight"}
        }
    },
    {
        $group: {
            _id: "$sex",
            avg_weight: {
                $avg: "$weight"
            },
            avg_height: {
                $avg: "$height"
            }
        }
    },
    {
        $set:
        {
            avg_weight: {
                $round: ["$avg_weight", 2]
            },
            avg_height: {
                $round: ["$avg_height", 2]
            }
        }
    }
]);

printjson(c.toArray());

