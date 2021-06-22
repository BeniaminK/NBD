const zad_name = "zad_1";

const mapFunction = function() {
  const value_h = { count: 1, qty: parseFloat(this.height) };
  const value_w = { count: 1, qty: parseFloat(this.weight) };

  emit( this.sex + "_Height", value_h );
  emit( this.sex + "_Weight", value_w );
};

const reduceFunction = function(key, values) {
   const reducedVal = { count: 0, qty: 0 };
   for (let idx = 0; idx < values.length; idx++) {
       reducedVal.count += values[idx].count;
       reducedVal.qty += values[idx].qty;
   }
   return reducedVal;
};

const finalizeFunction = function (key, reducedVal) {
  reducedVal.avg = reducedVal.qty / reducedVal.count;
  return reducedVal.avg;
};

const c = db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        query: { },
        out: zad_name,
        finalize: finalizeFunction
    }
);

printjson(db.getCollection(zad_name).find().sort( { _id: 1 } ).toArray());

