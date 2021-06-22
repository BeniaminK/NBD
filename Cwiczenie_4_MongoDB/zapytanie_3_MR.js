// 3. Listę unikalnych zawodów;
//MR
const zad_name = "zad_3";

const mapFunction = function() {
    emit(this.job, 1);
};

const reduceFunction = function(key, values) {
   return 1;
};


const finalizeFunction = function (key, reducedVal) {
        return key;
};

db.people.mapReduce(
        mapFunction,
        reduceFunction,
    {
        query: { },
        out: zad_name,
        finalize: finalizeFunction
    }
);

const c = db.getCollection(zad_name).find().sort( { _id: 1 } )

while (c.hasNext() ) {
    print(c.next().value)
}

