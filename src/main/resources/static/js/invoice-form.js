(function() {
	$(document.body).on('click', "#add-invoice-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementClassName = "invoiceRow";
		elementInfo.removeInvoiceRowDomString = "<div class=\"col-sm-1 col-md-1 col-lg-1\"> <label class=\"invoice-remove-row\" id=\"remove-invoice-row\">-</label> </div>";
		elementInfo.addInvoiceRowDomString = "<div class=\"row invoiceRow margin-top-10px\"> <div class=\"col-sm-6 col-md-6 col-lg-6\"> <input id=\"workDone\" name=\"workDoneAmounts[pos].workDone\" type=\"text\" class=\"form-control\" required=\"true\"> <span class=\"custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden\" data-toggle=\"tooltip\" title=\"\" data-placement=\"left\" data-original-title=\"Work done information must be entered\"></span> </div> <div class=\"col-sm-5 col-md-5 col-lg-5\"> <input id=\"amount\" name=\"workDoneAmounts[pos].amount\" type=\"text\" class=\"form-control text-center\" value=\"0.0\" required=\"true\"> <span class=\"custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden\" data-toggle=\"tooltip\" title=\"\" data-placement=\"left\" data-original-title=\"Charged amount for this work must be entered\"></span> </div> <div class=\"col-sm-1 col-md-1 col-lg-1\"> <label class=\"invoice-add-row\" id=\"add-invoice-row\">+</label> <label class=\"invoice-remove-row\" id=\"remove-invoice-row\">-</label> </div> </div>";
		elementInfo.elementParentDivId = "#invoiceParentDiv";	
		addInvoiceRow(elementInfo);
		$('[data-toggle="tooltip"]').tooltip();
		updateTotalAmount();
	});
	
	$(document.body).on('click', "#remove-invoice-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "invoiceParentDiv";
		elementInfo.totalAmountInputFieldId = "totalAmount";
		elementInfo.addInvoiceRowLabelString = "<label class=\"invoice-add-row\" id=\"add-invoice-row\">+</label>";
		removeInvoiceRow(elementInfo);
		updateTotalAmount();
	});
	
	$(document.body).on('blur', "input" ,function(){
		let $inputField = $(this);
		let inputFieldName = $inputField.attr('name');
		let inputFieldValue = $inputField.val();
		
		if(inputFieldValue === '' || (inputFieldName.includes('amount') && isNaN(parseFloat(inputFieldValue)))) {
			$inputField.css("border-color", "red");
			$inputField.parent().find('span').removeClass('hidden');
		} else {
			$inputField.removeAttr("style");
			$inputField.parent().find('span').addClass('hidden');
		}
		updateTotalAmount();
	});
	
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();   
	});
	
	$("#next").on('click', function(e){
		e.preventDefault();
		let totalAmount = 0.0;
		let totalAmountDisplayValue = $('#totalAmount').val();
		let isEmptyInputField = false;
		$('input').each(function(){
			let $inputField = $(this);
			let inputFieldName = $inputField.attr('name');
			let inputFieldValue = $inputField.val();
			if (inputFieldName.includes('amount')){
				totalAmount = totalAmount + parseFloat(inputFieldValue);
			}
			if(inputFieldValue === ''){
				isEmptyInputField = true;
				$inputField.css("border-color", "red");
				$inputField.parent().find('span').removeClass('hidden');
			}
		});
		
		if(totalAmountDisplayValue == totalAmount && !isEmptyInputField){
			$('#invoiceform').submit();
		}
	});
	
})();

function addInvoiceRow(elementInfo){
	let formGroup = $(elementInfo.currentElement).parent().parent();
	$(elementInfo.currentElement).parent().remove();
	let countOfelements= document.getElementsByClassName(elementInfo.elementClassName).length;
	if(countOfelements > 1) {
		formGroup.append(createElementFromHTML(elementInfo.removeInvoiceRowDomString));
	}
	let addInvoiceRowDomString = elementInfo.addInvoiceRowDomString.replaceAll('pos', countOfelements);
	$(elementInfo.elementParentDivId).append(createElementFromHTML(addInvoiceRowDomString));
}

function removeInvoiceRow(elementInfo){
	let amountInput = $(elementInfo.currentElement).parent().parent().find('#workDoneAmounts')[0];
	$(elementInfo.currentElement).parent().parent().remove();
	let childrens = document.getElementById(elementInfo.elementParentDivId).children;
	let lastInvoiceRow = childrens[childrens.length - 1];
	let lastDiv = lastInvoiceRow.lastElementChild
	let addInvoiceRow = lastDiv.getElementsByClassName('invoice-add-row')[0];
	let removeInvoiceRow = lastDiv.getElementsByClassName('invoice-remove-row')[0];
	
	if(addInvoiceRow && removeInvoiceRow){
		return;
	}
	
	if(removeInvoiceRow){
		removeInvoiceRow.parentNode.insertBefore(createElementFromHTML(elementInfo.addInvoiceRowLabelString), removeInvoiceRow.parentNode.firstChild);
	} else if(!addInvoiceRow){
		lastInvoiceRow.append(createElementFromHTML('<div class=\"col-sm-1 col-md-1 col-lg-1\">' + elementInfo.addInvoiceRowLabelString + '</div>'));
	}
}

function updateTotalAmount(){
	let totalAmount = 0.0;
	let prevTotalAmount = $('#totalAmount').val();
	$('input').each(function(){
		let $inputField = $(this);
		let inputFieldName = $inputField.attr('name');
		if (inputFieldName.includes('amount')){
			totalAmount = totalAmount + parseFloat($inputField.val());
		}
	});
	totalAmount = isNaN(totalAmount)?prevTotalAmount:totalAmount;
	$('#totalAmount').val(totalAmount);
}

function createElementFromHTML(htmlString) {
  var div = document.createElement('div');
  div.innerHTML = htmlString.trim();
  return div.firstChild; 
}