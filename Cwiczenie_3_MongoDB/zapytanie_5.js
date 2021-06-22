// 5. Lista imion i nazwisk wszystkich osób znajdujących się w bazie oraz miast, w których mieszkają, ale tylko dla osób urodzonych w XXI wieku;
printjson(db.people.aggregate(
    {
        $match: {
            birth_date: {$gte: "20010101"}
        }
    },
    {
        $set:
        {
            "city": "$location.city"
        }
    },
    {
        $project:
        {
            first_name: 1,
            last_name: 1,
            _id: 0,
            city: 1
        }    
    }
).toArray())

