// 4. Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości;
//MR
var zad_name = "zad_4"
var mapFunction = function() {
	w = parseFloat(this.weight)
	h = parseFloat(this.height) / 100
	bmi = w / (h * h)
	emit(this.nationality, bmi)
}

var reduceFunction = function(key, values) {
	bmi_max = Math.max(...values)
	bmi_min = Math.min(...values)
	bmi_avg = Array.sum(values) / values.length

	value = {
		"bmi_max": bmi_max,
		"bmi_min": bmi_min,
		"bmi_avg": bmi_avg,
	}
	return value;
};


var finalizeFunction = function (key, reducedVal) {
	return reducedVal;
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

