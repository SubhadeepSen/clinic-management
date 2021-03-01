(function() {
	$("#letsGo").on('click', function(e) {
		e.preventDefault();
		if (isValidInputValue()) {
			$("#loginForm").submit();
		}
	});

	$("input").on('blur', function() {
		let $input = $(this);
		if ($input.val() !== "") {
			$input.css('border-color', '');
			$input.parent().find('span').addClass('hidden');
		}
	});

	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
})();

function isValidInputValue() {
	let isValid = true;
	$("input").each(function() {
		let $input = $(this);
		if ($input.val() === "") {
			$input.css('border-color', '#e40000');
			$input.parent().find('span').removeClass('hidden');
			isValid = false;

		}
	});
	return isValid;
}