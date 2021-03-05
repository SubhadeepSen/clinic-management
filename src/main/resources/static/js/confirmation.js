(function() {
	$("#printInvoice").on('click', function() {
		let link = "/downloadInvoice/" + $("#patientId").attr('value') + "/" + $("#invoiceId").attr('value');
		window.open(link, "_blank");
	});
	$(document).ready(function() {
		let link = "/downloadInvoice/" + $("#patientId").attr('value') + "/" + $("#invoiceId").attr('value');
		window.open(link, "_blank");
	})
})();