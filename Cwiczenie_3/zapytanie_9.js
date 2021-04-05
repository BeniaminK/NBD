db = db.getSiblingDB('nbd')
// 9. Dodaj do wszystkich osób o imieniu Antonio własność „hobby” o wartości „pingpong”;
var res = db.people.updateMany(
    {
        first_name: "Antonio"
    },
    {
      $set: {
          hobby: "pingpong"
      }
    }
)

printjson(res)

// printjson(db.people.find( { first_name: "Antonio" }).toArray())

