// 3. Listę unikalnych zawodów;
//MR
var zad_name = "zad_3"
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
	emit(this.job, 1)
}

var reduceFunction = function(key, values) {
   return key;
};


var finalizeFunction = function (key, reducedVal) {
	return key;
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

