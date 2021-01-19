// Decode the chartData from home.html.
let decodedChartData = decodeHtml(chartData);
let chartJsonArray = JSON.parse(decodedChartData);

let length = chartJsonArray.length;
let numericData = [];
let labelData = [];

for (let i = 0; i < length; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
	title: {
		display: true,
		text: "Project Statuses"
	}
}
});

// Return a string.
function decodeHtml(html) {
	let txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}