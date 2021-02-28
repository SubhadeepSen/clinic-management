(function() {
	$(document.body).on('click', "#add-invoice-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementClassName = "invoiceRow";
		elementInfo.removeInvoiceRowDomString = "<div class=\"col-sm-1 col-md-1 col-lg-1\"> <label class=\"invoice-remove-row\" id=\"remove-invoice-row\">-</label> </div>";
		elementInfo.addInvoiceRowDomString = "<div class=\"row invoiceRow\"> <div class=\"col-sm-6 col-md-6 col-lg-6\"> <input id=\"workDoneAmounts\" name=\"workDoneAmounts['pos'].workDone\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div class=\"col-sm-5 col-md-5 col-lg-5\"> <input id=\"workDoneAmounts\" name=\"workDoneAmounts['pos'].amount\" type=\"text\" class=\"form-control text-center\" value=\"0.0\"> </div> <div class=\"col-sm-1 col-md-1 col-lg-1\"> <label class=\"invoice-add-row\" id=\"add-invoice-row\">+</label> <label class=\"invoice-remove-row\" id=\"remove-invoice-row\">-</label> </div> </div>";
		elementInfo.elementParentDivId = "#invoiceParentDiv";	
		addInvoiceRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-invoice-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "invoiceParentDiv";
		elementInfo.totalAmountInputFieldId = "totalAmount";
		elementInfo.addInvoiceRowLabelString = "<label class=\"invoice-add-row\" id=\"add-invoice-row\">+</label>";
		removeInvoiceRow(elementInfo);
	});
	
	$(document.body).on('blur', "#workDoneAmounts" ,function(){
		let $currAmountInputField = $(this);
		let $workDoneInputField = $currAmountInputField.parent().parent().find('#workDoneAmounts');
		
		if($workDoneInputField.val() === '') {
			$workDoneInputField.css("border-color", "red");
		}
	});
	
	$(document.body).on('blur', "#workDoneAmounts" ,function(){
		let $currWorkDoneInputField = $(this);
		
		if($currWorkDoneInputField.val() != '') {
			$currWorkDoneInputField.removeAttr("style");
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
	let inputValue = amountInput.value;
	console.log(inputValue);
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
	
	//$(elementInfo.totalAmountInputFieldId).attr()
}

function createElementFromHTML(htmlString) {
  var div = document.createElement('div');
  div.innerHTML = htmlString.trim();
  return div.firstChild; 
}