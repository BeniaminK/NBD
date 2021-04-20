// 4. Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości;
//A
const c = db.people.aggregate([
    {
      $project: {
          height: 1,
          weight: 1,
          _id: 0,
          nationality: 1
      }
    },
    {
        $set: {
            "height": {$toDouble: "$height"}
        }
    },
    {
        $set: {
            "weight": {$toDouble: "$weight"}
        }
    },
    {
        $set: {
            "BMI": {
                $divide: ["$weight", {$pow: [{$divide: ["$height", 100]}, 2]}]
            }
        }
    },
    {
        $project: {
            "BMI": 1,
            "nationality": 1,
        }
    },
    {
        $group: {
            _id: "$nationality",
            BMI_min: {
                $min: "$BMI"
            },
            BMI_avg: {
                $avg: "$BMI"
            },
            BMI_max: {
                $max: "$BMI"
            }
        }
    },
    {
        $set:
        {
            BMI_min: {
                $round: ["$BMI_min", 2]
            },
            BMI_avg: {
                $round: ["$BMI_avg", 2]
            },
            BMI_max: {
                $round: ["$BMI_max", 2]
            }
        }
    },
    {
        $sort: {
            "_id": 1
        }
    }
]);

printjson(c.toArray());

