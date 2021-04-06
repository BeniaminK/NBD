// 5. Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty.
//A
var c = db.people.aggregate([
    {
        $unwind: "$credit"
    },
    {
      $set: {
            "credit.balance": {$toDecimal: "$credit.balance"}
      }
    },
    {
      $match: {
          nationality: "Poland",
          sex: "Female"
      }
    },
    {
        $group: {
            _id: "$credit.currency",
            money_sum: {
                $sum: "$credit.balance"
            },
            money_avg: {
                $avg: "$credit.balance"
            }
        }
    },
    {
        $sort: {
            "_id": 1
        }
    }

])

printjson(c.toArray())

