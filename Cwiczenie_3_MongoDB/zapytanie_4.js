// 4. Lista wszystkich osób znajdujących się w bazie o wadze z przedziału <68, 71.5);
printjson(db.people.aggregate([
    {
        $addFields: {
            weight_num: { $convert: { input: "$weight", to: "decimal" } }
        }
    },
    {
        $match: {
            weight_num: {
                $gte: 68,
                $lt: 71.5
            }
        }
    }
]).toArray())

