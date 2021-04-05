db = db.getSiblingDB('nbd')
// 2. Jedna kobieta narodowości chińskiej;
printjson(db.people.findOne({sex: "Female", nationality: "China"}))

