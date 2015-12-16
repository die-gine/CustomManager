$(document).ready(function() {
    
	stateValues = ['BEGIN RECORDING USER STORY','START','STOP','END RECORDING USER STORY'];
	stateHints = ['Choose a BDD-Keyword, enter a step definiton ein and click *Start Recording Step*','Follow the step definiton by clicking on on page, after that click *Stop Recording Step*','Your choice: End of User Story Recording or Start Recording a new Step Definition','Begin now the recording of a new USER STORY']
	/*aktueller Status in dem sich das Recording befindet, globale Variable*/
	state = stateValues[4];
	urlCheck = null; 
    restoreContents();
	restoreState();

	

	//macht auf fremden seiten probleme
	//$("[rel='tooltip']").tooltip();
	
	/*liest den Local Storage für den Status aus und stellt den zugehörigen Zustand im Rekorder her*/
    function restoreState() {
        var myState = localStorage['State'];
			setState(myState);	
			
			switch(myState) {
			case stateValues[0]:
				$('#recording_userstory_btn').attr('record', 'true');	
				$('#recording_userstory_btn').val('End Recording User Story');
				$('#step_definition').slideDown();
				$('#recording_step_btn').show();
				$('#recording_us_title').hide();
				break;
			case stateValues[1]:
				$('#recording_userstory_btn').attr('record', 'true');	
				$('#recording_userstory_btn').val('End Recording User Story');
				$('#step_definition').slideDown();
				$('#recording_step_btn').show();
				$('#recording_us_title').hide();
				$('#recording_step_btn').attr('record', 'true');
				$('#recording_step_btn').val('Stop Recording Step');
				$('#recording_userstory_btn').hide();
				$('#recording_us_title').hide();
				break;
			case stateValues[2]:
				$('#recording_userstory_btn').attr('record', 'true');	
				$('#recording_userstory_btn').val('End Recording User Story');
				$('#step_definition').slideDown();
				$('#recording_step_btn').show();
				$('#recording_step_btn').attr('record', 'false');
				$('#recording_step_btn').val('Start Recording Step');
				$('#recording_userstory_btn').show();
				$('#recording_us_title').hide();
				break;		
			case stateValues[3] || undefined:
				$('#recording_userstory_btn').attr('record', 'false');
				$('#recording_userstory_btn').val('Begin Recording User Story');
				$('#step_definition').slideUp();
				$('#recording_step_btn').hide();
			default:
				break;
			}
		
    }
	
	function saveState(){		
		var recording = getState();
        localStorage['State'] = recording;
    }
	
	/*setzt den aktuellen Recording-Status*/
	function setState(state){
		this.state = state;	
	}
	
	/*gibt den Recording-Status zurück*/
	function getState(){
		return this.state;		
	}
	
	/*speichert den Bereich '#recorded_information' im Local Storage */
	function save(){
		var recording = $('#recorded_information').html();
        localStorage['recordingInformation'] = recording;
    }
    
	/*liest den Local Storage aus*/
    function restoreContents() {
        var myRecordList = localStorage['recordingInformation'];
        if (myRecordList != undefined) {
            $('#recorded_information').html(myRecordList);
        }
    }

	/*bindet Button an Umschaltefunktion*/
	$('#recording_edit_ls').bind('click', toggleEditContent);
    $('#recording_step_btn').bind('click', toggleStep);
    $('#recording_reset_ls').bind('click', resetContent);		
	$('#recording_userstory_btn').bind('click', toggleUserStory);
	
  
	
	/*Umschalter Start/Stop für US, beginnt/beendet das Aufzeichnen einer User Story, ändert Status*/
	function toggleUserStory() {
	
		 /*Startet das Aufzeichnen der User Story, slided Stepdefinition ein, zeigt Record-Step-Button,  stateValues[0] ==BEGIN RECORDING USER STORY; stateValues[2]==STOP STEP DEFINITION*/
		if (getState()==undefined || getState()== stateValues[3] ) {
			
			$('#recording_userstory_btn').attr('record', 'true');	
			
			if(getState() != stateValues[0]){
				setState(stateValues[0]);
				var titleUS = $('#recording_us_title').val();
				$("#recorded_information").append('<div class="recorder_userStory" id="title"><i class="recorder_icon material-icons">play_circle_outline</i>  '+ getState() +': <a id="title_user_story"> '+ titleUS+ '</a></div>');
			}
			$('#recording_userstory_btn').val('End Recording User Story');
			$('#recording_us_title').hide();
			$('#step_definition').slideDown();
			$('#recording_step_btn').show();
			$('#hints').html(stateHints[0]); 
			save();
			saveState();		
		} else if (getState()== stateValues[2] || getState()== stateValues[0]){
			
			 /*Beendet das Aufzeichnen der User Story*/
			$('#recording_userstory_btn').attr('record', 'false');
			if(getState() != stateValues[1] ){
				setState(stateValues[3]);
				$("#recorded_information").append('<div class="recorder_userStory" id="title"><i class="recorder_icon material-icons">stop</i>'+ getState() + '</div>');
			}
			$('#recording_us_title').val('');
			$('#recording_us_title').show();
			$('#recording_userstory_btn').val('Begin Recording User Story');
			$('#step_definition').slideUp();
			$('#recording_step_btn').hide();
			$('#hints').html(stateHints[3]); 
			save();
			saveState();
		}
	}
	
	/*Umschalter Start/Stop für Step, beginnt/beendet das Aufzeichnen eines given/when/then-Steps, ändert Status*/
	function toggleStep() {

		 /*Startet das Aufzeichnen des Steps*/
		if (getState()== stateValues[0] || getState()== stateValues[2]) {
			
			$('#recording_step_btn').attr('record', 'true');
			if(getState() != stateValues[1]){
				var radioBtn = $('input[name="bdd_keyword"]:checked').val();
				var recording_step_def = $('#recording_step_def').val();
				urlCheck = document.URL;
				setState(stateValues[1]);
				$("#recorded_information").append('<div class="recorder_stepDef" id="title"><i class="recorder_icon material-icons">play_arrow</i>'+ getState()+'</div>');	
				$("#recorded_information").append('<div id="rec_json" class="rec_output_json recorder_element"><div class="recorder_bold" id='+radioBtn +'>'+radioBtn+'</div><span id="rec_step_def_json">'+ recording_step_def +'</span><div id="rec_url_json">'+ document.URL +'</div></div>');	
					
			}
			
			$('#recording_step_btn').val('Stop Recording Step');
			$('#recording_userstory_btn').hide();
			$('#hints').html(stateHints[1]); 
			save();
			saveState();
		} else if(getState()== stateValues[1]) {
			
			 /*Beendet das Aufzeichnen des Steps*/
			$('#recording_step_btn').attr('record', 'false');
			var recording_step_def = $('#recording_step_def').val();
			if(getState() != stateValues[2]){
				setState(stateValues[2]);
				if(document.URL != urlCheck){
					$("#recorded_information").append('<div class="recorder_target_url">'+ document.URL +'</div>');
				}
				
				$("#recorded_information").append('<div class="recorder_stepDef" id="title"><i class="recorder_icon material-icons">pause</i>'+ getState() + '</div>');	
				$('#recording_step_def').val('');
			}
			$('#hints').html(stateHints[2]); 
			$('#recording_step_btn').val('Start Recording Step');
			$('#recording_userstory_btn').show();
			save();
			saveState();
		}
	}
	
	/*Lauscht ob ein Input der Seite angeklickt wird und ob aufgezeichnet wird - record=true
    $(document).on('click', 'td', function () {
    	if (checkIfRecording()&& checkIfPartOfRecordsetup(this)) {
	        record(this);   	      
    	 }
    });
	
		/*Lauscht ob ein Input der Seite angeklickt wird und ob aufgezeichnet wird - record=true
    $(document).on('click', 'input', function () {
    	if (checkIfRecording()&& checkIfPartOfRecordsetup(this)) {
	        record(this);   	      
    	 }

    });*/
	
	/*Lauscht ob ein Input der Seite angeklickt wird und ob aufgezeichnet wird - record=true*/
    $(document).on('click', function (evt) {
    	if (checkIfRecording()&& checkIfPartOfRecordsetup(evt)) {
			evt.stopPropagation();
			alert(evt.target.tagName);  
	        record(evt);   	      
    	 }
    });

	
	function record(evt){
    	var element = getElementInfo(evt);
		var xPath = getXPath(evt);https://api.jquery.com/category/internals/
    	$("#recorded_information").append('<div class="recorder_element"> Element: '+ element +' xPath: '+ xPath +'</div>');
    	var recording = $('#recorded_information').html();
        localStorage['recordingInformation'] = recording;
    }
	
	/*Prüft ob gerade Tests aufgezeichnet werden*/
	function checkIfRecording() {
    	return $('#recording_userstory_btn').attr('record') == 'true' && $('#recording_step_btn').attr('record') == 'true';
    }
	
	/*Prüft Element Teil des RecoderSetups ist*/
	function checkIfPartOfRecordsetup(element) {
    	var element_name = $(element.target).attr('name');
    	return element_name!= 'recording_setup' && element_name!= 'bdd_keyword';
    }
	
	function getElementInfo(evt) {
		var element = '-';
		element += evt.target.nodeName.toLowerCase();
		if($(evt.target).attr('id')!= undefined){
			element += ' id= ';
			element += $(evt.target).attr('id');
		}
		
		if($(evt.target).attr('name')!= undefined){
			element += ' name = ';
			element += $(evt.target).attr('name');
		}
		
		if($(evt.target).attr('class')!= undefined){
			element += ' class=';
			element += $(evt.target).attr('class');
		}
		
		if($(evt.target).text()!= ''){
			element += ' text= ';
			element += $(evt.target).text();
		}
		
		if($(evt.target).attr('value')!= undefined){
			element += ' value= ';
			element += $(evt.target).attr('value');
		}
		element += '-';
		return element;
	  }
	
	/*gibt den xpath eines Elements zurück*/
	function getXPath(evt){
		var evt = evt.target;
		var val=evt.value;
		var xpath = '';
		for ( ; evt && evt.nodeType == 1; evt = evt.parentNode ){
			var id = $(evt.parentNode).children(evt.tagName).index(evt) + 1;
			id > 1 ? (id = '[' + id + ']') : (id = '');
			xpath = '/' + evt.tagName.toLowerCase() + id + xpath;
		}
		return xpath;
	}
	
	/*Umschalter Edit/Speichern*/
	function toggleEditContent(e) {
        if ($('#recorded_information').attr('contenteditable') == 'false') {
            $('#recorded_information').attr('contenteditable', 'true');
            $('#recording_edit_ls').val('Save');
            $('#recorded_information').focus();
        } else {
            $('#recorded_information').attr('contenteditable', 'false');
            $('#recording_edit_ls').val('Edit');
			save();
        }
    }


	/*cleared den Local Storage*/
    function resetContent(e) {
        localStorage.clear();
        window.location.reload();
    }
    

});