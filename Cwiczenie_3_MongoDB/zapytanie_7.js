// 7. Usuń z bazy osoby o wzroście przekraczającym 190;
var cursor = db.people.aggregate([
    {
        $addFields:
        {
            height_num:
            {
                $convert:
                {
                    input: "$height", to: "decimal"
                }
            }
        }
    },
    {
        $match: {
            height_num: {
                $gt: 190
            }
        }
    }
])

var arr = cursor.toArray()

var ids = arr.map(function(d) {return d._id})

var res = db.people.deleteMany(
    {
        _id:
        {
            $in: ids
        }
    }
)

printjson(res)

