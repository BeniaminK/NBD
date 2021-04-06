// 1. Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet);
//MR
var zad_name = "zad_1"
var finalizeFunction = function (key, reducedVal) {
  reducedVal.avg = reducedVal.qty/reducedVal.count;
  return reducedVal.avg;
};

var reduceFunction = function(keySKU, countObjVals) {
   reducedVal = { count: 0, qty: 0 };
   for (var idx = 0; idx < countObjVals.length; idx++) {
       reducedVal.count += countObjVals[idx].count;
       reducedVal.qty += countObjVals[idx].qty;
   }
   return reducedVal;
};

var c = db.people.mapReduce(
    function () {
		var value_h = { count: 1, qty: parseFloat(this.height) };
		var value_w = { count: 1, qty: parseFloat(this.weight) };

        emit(this.sex + "_Height", value_h )
		emit(this.sex + "_Weight", value_w )
    },
	reduceFunction,
    {
        query: { },
        out: zad_name,
		finalize: finalizeFunction
    }
)

printjson(c)

printjson(db.getCollection(zad_name).find().sort( { _id: 1 } ).toArray())

