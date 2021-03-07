(function() {
	$("#download").on('click', function() {
		download();
	});
	$(document).ready(function() {
		download();
		
	})
	
	function download() {
		let patientId = $("#patientId").attr('value');
		let invoiceId = $("#invoiceId").attr('value');
		let dateOfVisit = $("#dateOfVisit").attr('value');
		let link = "/downloadInvoice?patientId=" + patientId + "&invoiceId=" + invoiceId + "&dateOfVisit=" + dateOfVisit;
		window.open(link, "_blank");
	}
})();