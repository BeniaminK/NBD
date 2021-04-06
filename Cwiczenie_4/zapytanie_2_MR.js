//2. Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty;
//MR
var zad_name = "zad_2"
var currConvert = function(curr) {

	idx = curr.indexOf('.')

	if (idx < 0) {
		idx = curr.length
	}

	main_curr = parseInt(curr.substring(0, idx)) * 100
	pct_curr = parseInt((curr.substring(idx + 1, curr.length) + "00").substring(0, 2))
	return main_curr + pct_curr
}

var convBack = function(cents) {
	cents = cents.toString()
	var l = cents.length;
	return cents.substring(0, l - 2) + "." + cents.substring(l - 2, l)
}

var mapFunction = function() {

	var currConvert = function(curr) {

		idx = curr.indexOf('.')

		if (idx < 0) {
			idx = curr.length
		}

		main_curr = parseInt(curr.substring(0, idx)) * 100
		pct_curr = parseInt((curr.substring(idx + 1, curr.length) + "00").substring(0, 2))
		return main_curr + pct_curr
	}

	var i;
	for (i = 0; i < this.credit.length; i++) {
		emit(this.credit[i].currency, currConvert(this.credit[i].balance))
	}
}

var reduceFunction = function(key, values) {
   return Array.sum(values);
};


var finalizeFunction = function (key, reducedVal) {

	var convBack = function(cents) {
		cents = cents.toString()
		var l = cents.length;
		return cents.substring(0, l - 2) + "." + cents.substring(l - 2, l)
	}

  return convBack(reducedVal);
};

var c = db.people.mapReduce(
	mapFunction,
	reduceFunction,
    {
        query: { },
        out: zad_name,
		finalize: finalizeFunction
    }
)

printjson(c)

printjson(db.getCollection(zad_name).find().sort( { _id: 1 } ).toArray())

