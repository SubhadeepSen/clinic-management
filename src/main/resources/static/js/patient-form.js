(function() {
	$(document.body).on('click', "#add-chief-complaint-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "chiefComplaints";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-chief-complaint-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"chiefComplaints\"></label> <div class=\"col-sm-8\"> <input id=\"chiefComplaints\" name=\"chiefComplaints\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-chief-complaint-row\">+</label> <label class=\"remove-row\" id=\"remove-chief-complaint-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#chiefComplaintsParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-chief-complaint-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "chiefComplaintsParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-chief-complaint-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$(document.body).on('click', "#add-on-examination-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "onExaminations";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-on-examination-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"onExaminations\"></label> <div class=\"col-sm-8\"> <input id=\"onExaminations\" name=\"onExaminations\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-on-examination-row\">+</label> <label class=\"remove-row\" id=\"remove-on-examination-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#onExaminationsParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-on-examination-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "onExaminationsParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-on-examination-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$(document.body).on('click', "#add-medical-history-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "medicalHistories";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-medical-history-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"medicalHistories\"></label> <div class=\"col-sm-8\"> <input id=\"medicalHistories\" name=\"medicalHistories\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-medical-history-row\">+</label> <label class=\"remove-row\" id=\"remove-medical-history-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#medicalHistoriesParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-medical-history-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "medicalHistoriesParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-medical-history-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$(document.body).on('click', "#add-investigation-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "investigations";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-investigation-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"investigations\"></label> <div class=\"col-sm-8\"> <input id=\"investigations\" name=\"investigations\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-investigation-row\">+</label> <label class=\"remove-row\" id=\"remove-investigation-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#investigationsParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-investigation-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "investigationsParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-investigation-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$(document.body).on('click', "#add-advice-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "advices";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-advice-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"advices\"></label> <div class=\"col-sm-8\"> <input id=\"advices\" name=\"advices\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-advice-row\">+</label> <label class=\"remove-row\" id=\"remove-advice-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#advicesParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-advice-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "advicesParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-advice-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$(document.body).on('click', "#add-workDone-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "workDones";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-workDone-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"workDones\"></label> <div class=\"col-sm-8\"> <input id=\"workDones\" name=\"workDones\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-workDone-row\">+</label> <label class=\"remove-row\" id=\"remove-workDone-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#workDonesParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-workDone-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "workDonesParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-workDone-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$(document.body).on('click', "#add-medicine-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementName = "medicines";
		elementInfo.removeRowDomString = "<div> <label class=\"remove-row\" id=\"remove-medicine-row\">-</label> </div>";
		elementInfo.addRowDomString = "<div class=\"row\"> <div class=\"form-group\"> <label class=\"control-label col-sm-2\" for=\"medicines\"></label> <div class=\"col-sm-8\"> <input id=\"medicines\" name=\"medicines\" type=\"text\" class=\"form-control\" value=\"\"> </div> <div> <label class=\"add-row\" id=\"add-medicine-row\">+</label> <label class=\"remove-row\" id=\"remove-medicine-row\">-</label> </div> </div> </div>";
		elementInfo.elementParentDivId = "#medicinesParentDiv";	
		addRow(elementInfo);
	});
	
	$(document.body).on('click', "#remove-medicine-row" ,function(){
		let elementInfo = {};
		elementInfo.currentElement = this;
		elementInfo.elementParentDivId = "medicinesParentDiv";
		elementInfo.addRowLabelString = "<label class=\"add-row\" id=\"add-medicine-row\">+</label>";
		removeRow(elementInfo);
	});
	
	$('#nextAppointmentDate').on('click', function(){
		$(this).val("");
	});
	
	$('#populate').on('click', function(){
		let errorTooltip = "<span class=\"custom-tootip glyphicon glyphicon-info-sign error-glyphicon\" data-toggle=\"tooltip\" title=\"\" data-placement=\"left\" data-original-title=\"Phone number should be numeric and 10 digits\"></span>"
		let phoneNumber = $("#phoneNumber").val();
		if(!phoneNumber.match("^\\d{10}$")){
			$(this).parent().append(createElementFromHTML(errorTooltip));
			$("#phoneNumber").addClass('field-error-border');
			$('[data-toggle="tooltip"]').tooltip();
			return;
		}
		
		window.location.href="/polulatePatientForm?phoneNumber="+phoneNumber
	});
	
})();

$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});

function addRow(elementInfo){
	let formGroup = $(elementInfo.currentElement).parent().parent();
	$(elementInfo.currentElement).parent().remove();
	let countOfelementName = document.getElementsByName(elementInfo.elementName).length;
	if(countOfelementName > 1) {
		formGroup.append(createElementFromHTML(elementInfo.removeRowDomString));
	}
	$(elementInfo.elementParentDivId).append(createElementFromHTML(elementInfo.addRowDomString));
}

function removeRow(elementInfo){
	$(elementInfo.currentElement).parent().parent().parent().remove();
	let childrens = document.getElementById(elementInfo.elementParentDivId).children;
	let lastChild = childrens[childrens.length - 1];
	let formGroup = lastChild.firstElementChild
	let addRow = formGroup.getElementsByClassName('add-row')[0];
	let removeRow = formGroup.getElementsByClassName('remove-row')[0];
	
	if(addRow && removeRow){
		return;
	}
	
	if(removeRow){
		removeRow.parentNode.insertBefore(createElementFromHTML(elementInfo.addRowLabelString), removeRow.parentNode.firstChild);
	} else if(!addRow){
		formGroup.append(createElementFromHTML('<div>' + elementInfo.addRowLabelString + '</div>'));
	}
}

function createElementFromHTML(htmlString) {
  var div = document.createElement('div');
  div.innerHTML = htmlString.trim();
  return div.firstChild; 
}