db = db.getSiblingDB('nbd')
// 1. Jedna osoba znajdująca się w bazie
printjson(db.people.findOne())
