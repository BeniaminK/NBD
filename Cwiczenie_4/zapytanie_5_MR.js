// 5. Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty.
//MR
const zad_name = "zad_5";

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
        const value = {
            balance_sum: currConvert(this.credit[i].balance),
            counter: 1
        };

		emit(this.credit[i].currency, value);
	}
};

const reduceFunction = function(key, values) {
    const value = {
        balance_sum: 0,
        counter: 0
    };
	for (let i = 0; i < values.length; i++) {
        value.balance_sum += values[i].balance_sum;
        value.counter += values[i].counter;
	}
    return value;
};


const finalizeFunction = function (key, reducedVal) {
    const total_balance = convBack(reducedVal.balance_sum);
    const average_balance = reducedVal.balance_sum / reducedVal.counter / 100;
 
    const value = {
        money_sum: total_balance,
        money_avg: average_balance
    };
   return value;
};

db.people.mapReduce(
	mapFunction,
	reduceFunction,
    {
        query:
        {
            nationality: "Poland",
            sex: "Female"
        },
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

