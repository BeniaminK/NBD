// 4. Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości;
//MR
const zad_name = "zad_4";

const mapFunction = function() {
    const w = parseFloat(this.weight);
    const h = parseFloat(this.height) / 100;
	const bmi = w / (h * h);

	const value = {
		BMI_max: bmi,
		BMI_min: bmi,
		BMI_sum: bmi,
        counter: 1
	};

	emit(this.nationality, value);
};

const reduceFunction = function(key, values) {

	const retValue = {
		BMI_max: values[0].BMI_max,
		BMI_min: values[0].BMI_min,
		BMI_sum: 0,
        counter: 0
	};

    for (let i = 0; i < values.length; i++) {
        retValue.BMI_max = Math.max(values[i].BMI_max, retValue.BMI_max);
        retValue.BMI_min = Math.min(values[i].BMI_min, retValue.BMI_min);
        retValue.BMI_sum += values[i].BMI_sum;
        retValue.counter += values[i].counter;
    }

	return retValue;
};


const finalizeFunction = function (key, reducedVal) {
	const retValue = {
		BMI_min: reducedVal.BMI_min,
		BMI_avg: reducedVal.BMI_sum / reducedVal.counter,
		BMI_max: reducedVal.BMI_max,
	};
	return retValue;
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

printjson(db.getCollection(zad_name).find().sort( { _id: 1 } ).toArray());

