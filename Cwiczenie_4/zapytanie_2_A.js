// 2. Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty;
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
        $group: {
            _id: "$credit.currency",
            money_sum: {
                $sum: "$credit.balance"
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

