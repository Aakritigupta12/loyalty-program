
$(document).ready(function() {
	$.ajax({
		url: "/customer"
	}).then(function(data) {

		$.each(data, function(i, d) {
			var row = '<tr>';
			$.each(d, function(j, e) {
				if (Array.isArray(e)) {
					row += '<td><button id="' + d.customerId + '" class="transactions"> Get Transactions</button></td>';
				}
				else {
					row += '<td>' + e + '</td>';
				}
			});
			row += '</tr>';
			$('#customer-table').append(row);
		});

		var elements = document.getElementsByClassName("transactions");
		Array.from(elements).forEach(function(element) {
			element.addEventListener('click', function(e) {
				let cId = e.path[0].id;
				$('#transaction-list').empty();
				let transactionHistory = [];
				$.each(data, function(i, d) {

					if (d.customerId == cId) {

						$.each(d, function(j, e) {
							if (Array.isArray(e) && e.length != 0) {
								transactionHistory = e;

								$.each(transactionHistory, function(i, d) {
									var row = '<tr>';
									$.each(d, function(j, e) {

										row += '<td>' + e + '</td>';

									});
									row += '</tr>';
									$('#transaction-table').append(row);
								});

							}
						});
					}
				});


			});
		});
	});


});

document.getElementById("transact").onclick = function() {

	const customerId = document.getElementById("customer-id").value;
	const transactionValue = document.getElementById("transaction-value").value;
	const transactionDate = document.getElementById("transaction-date").value;
	$.ajax({
		type: "POST",
		data: { customerId: customerId, transactionValue: transactionValue, transactionDate: transactionDate },
		url: "/transaction",
		success: function(response) {
			window.location.reload(true);
		}
	});

};


