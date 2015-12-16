$(document).ready(function () {
	
	setSetup();
	
	/*Stellt das Recording-Buttonset bereit - keywords class mit 'recorder_....'  id mit 'recording_' & 'recorded_'| class für styles und id für js functionen  */
	function setSetup() {
		$iconLink = $('<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">');
		$form = $('<div class="container recorder" name="recording_setup"><div id="hints" class="recorder_hints" name="recording_setup"></div><form id="set"></form></div>');	
		$fieldset = $('<fieldset id="step_definition" hidden="true"></fieldset>');
		$div1 = $('<div class="recorder_width_100 recorder_float_left"></div>');
		$input1 = $('<input type="radio" id="given" name="bdd_keyword" value="Given" checked ><label title="Choose a BDD statement" for="given"> Given</label><br></input>');
		$input2 = $('<input type="radio" id="when" name="bdd_keyword" value="When"><label title="Choose a BDD statement" for="when"> When</label><br></input>');
		$input3 = $('<input type="radio" id="then" name="bdd_keyword" value="Then"><label title="Choose a BDD statement" for="then"> Then</label><br></input>');		
		$div2 = $('<div class="recorder_float_left"></br><input id="recording_step_def" name="recording_setup" size="80" title="Set the behaviour according to the BDD conventions" placeholder="Set the behavior of the step according to the BDD conventions..."></div>');
		
		$fieldset2 = $('<fieldset id="btn_definition" style="border:0;"></fieldset>');
		$div3 = $('<div class="recorder_float_left"><input type="button" class="recorder_margin_10" id="recording_userstory_btn" name="recording_setup" record="false" value="Begin Recording User Story"> <input id="recording_us_title" name="recording_setup" size="70" title="Set the title of the User Story" placeholder="Title of the User Story..."> <input type="button" class="recorder_margin_10" id="recording_step_btn" name="recording_setup" record="false" value="Start Recording Step" style="display:none;"></div>');
		$div4 = $('<div class="recorder_float_right"><input type="button" class="recorder_margin_10" id="recording_edit_ls" name="recording_setup" value="Edit" contenteditable="false"><input type="button" class="recorder_margin_10" id="recording_save_as_json" name="recording_setup" record="false" value="Save as JSON"><input type="button" class="recorder_margin_10" id="recording_reset_ls" name="recording_setup" record="false" value="Reset all Tests"></div>');
		
		$fieldset3 = $('<fieldset class="recorder_border_0"></fieldset>');
		$div5 = $('<div class="recorder_output" id="recorded_information" contenteditable="false"></div> ');
		
		$('head').append($iconLink);
		$('body').append($form);
		$('body').append($fieldset3);
		$form.append($fieldset);
		$form.append($fieldset2);
		$fieldset.append($div1);
		$div1.append($input1);
		$div1.append($input2);
		$div1.append($input3);
		$fieldset.append($div2);	
		$fieldset2.append($div3);
		$fieldset2.append($div4);
		$fieldset3.append($div5);	

		
	}

});