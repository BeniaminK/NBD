//2. Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty;
//MR
const zad_name = "zad_2";

const currConvert = function(curr) {
    // Convert currency to cents to prevent precision loss on currency values e.g.
    // 12.3 -> 1230
    // 12.09 -> 1209
    // 80 -> 8000
    // This way we don't use the floating point rounding

    const idx = curr.includes(".") ? curr.indexOf(".") : curr.length;
    const main_curr = parseInt(curr.substring(0, idx)) * 100;
    const pct_curr = parseInt((curr.substring(idx + 1, curr.length) + "00").substring(0, 2));
    return main_curr + pct_curr;
};

const convBack = function(cents) {
    // Converts back integer cents to string value e.g.
    // 0 -> ".0"
    // 5 -> ".05"
    // 156 -> "1.56"

    const centsStr = cents.toString();
    const l = centsStr.length;
    if (l == 1)
        return centsStr.substring(0, l - 2) + "." + "0" + centsStr.substring(l - 2, l);
    return centsStr.substring(0, l - 2) + "." + centsStr.substring(l - 2, l);
};


const mapFunction = function() {

	for (let i = 0; i < this.credit.length; i++) {
		emit(this.credit[i].currency, currConvert(this.credit[i].balance));
	}
};

const reduceFunction = function(key, values) {
    return [ Array.sum(values) ];
};


const finalizeFunction = function (key, reducedVal) {

    reducedVal = reducedVal[0];

  return convBack(reducedVal);
};

db.people.mapReduce(
	mapFunction,
	reduceFunction,
    {
        query: { },
        out: zad_name,
        finalize: finalizeFunction,
        scope:
        {
            currConvert: currConvert,
            convBack: convBack
        }
    }
);

printjson(db.getCollection(zad_name).find().sort( { _id: 1 } ).toArray());

