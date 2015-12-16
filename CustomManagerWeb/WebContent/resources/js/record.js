/**
 * 
 */


$(document).ready(function () {
  
  /*Stellt das Recording-Buttonset bereit*/
  setRecordSetup();
  
  /*holt Inhalt des aktuellen LocalStorage 'testRecording'*/
  restoreContents();
  
  /*Bindet Buttons an Funktionen*/
  $('#recordBtn').bind('click', toggleStart);
  $('#stopBtn').bind('click', sendToServer);
  $('#resetBtn').bind('click', resetContent);
  
  var localStor = localStorage['testRecording'];
   


    function toggleStart(e) {
         /*Startet das Recording*/
        if ($('#recordBtn').attr('record') == 'false') {
            $('#recordBtn').attr('record', 'true');
            $("#record_overview").append("Start Recording </br>");
            $('#recordBtn').val('Stop');

        } else {
             /*Stoppt Aufzeichnungen und iniziiert Speicherung im LS*/
            $('#recordBtn').attr('record', 'false');
            $("#record_overview").append("Stop Recording </br>");
            $('#recordBtn').val('Start');
            /*saveRecordings();*/
        }
    }
  
  
    /*Lauscht ob ein Input der Seite angeklickt wird und ob aufgezeichnet wird - record=true*/
    $(document).on('click', 'input', function () {
    	if (checkIfRecording()&& checkIfRecordsetup(this)) {
	        record(this);   	      
    	 }
      });
    
    /*Lauscht ob ein Input der Seite angeklickt wird und ob aufgezeichnet wird - record=true*/
    $(document).on('click', 'span', function () {
    	if (checkIfRecording()&& checkIfRecordsetup(this)) {
	        record(this);   	      
    	 }
      });
    
    function record(element){
    	//element und url werden gefeched und in ls geschrieben, danach in html ausgabe gegeben...
    	//wichtig das muss noch vor einem klick passieren weil die seiten wechseln können 
    	// fetchen in der reihenfolge: url, eingabe in input, click, (neue) url
    	
    	var step = $('#step_definiton').val();
    	step +=' URL: '+ document.URL;
    	localStor = step;
    	$('#step_definiton').val('');

        var recording = $("#record_overview").append(step);
        $('#step_definiton').val('');
        recording.append('</br>');
        var tagInfo = setElementInfo(element);
        recording.append(tagInfo +'<br>');
    }
    
    function checkIfRecording() {
    	return $('#recordBtn').attr('record') == 'true';
    }
    
    function checkIfRecordsetup(element) {
    	var element_name = $(element).attr('name');
    	return element_name!= 'recordingSetup';
    }
    
      function saveRecordings() {
        var testRecording = $('#testRecording').html();
        localStorage['testRecording'] = testRecording;
    }
  
        function sendToServer() {
          /*to do: send LS to Server */
    }
  
  
    function resetContent(e) {
        localStorage.clear();
        window.location.reload();
    }
  
      function restoreContents() {
      	if (localStor != undefined) {
            $('#record_overview').html(localStor);
        }
    }
  
  
  function setElementInfo(param) {
    var element = 'ID: ';
    element += $(param).attr('id');
    element += ', Name: ';
    element += $(param).attr('name');
    element += ', Class: ';
    element += $(param).attr('class');
    element += ', Text: ';
    element += $(param).text();
    element += ', Type: ';
    element += $(param).attr('type');
    element += ', Value: ';
    element += $(param).attr('value');
    return element;
  }
  
  
function setRecordSetup() {
    var recordBtn = document.createElement("input");
    var resetBtn = document.createElement("input");
    var stopBtn = document.createElement("input");
    var inputStatement = document.createElement("input");
    var record_overview = document.createElement("div");
  
    recordBtn.setAttribute('id', 'recordBtn');
    recordBtn.setAttribute('type', 'button');
    recordBtn.setAttribute('value', 'Start');
    recordBtn.setAttribute('record', 'false');
    recordBtn.setAttribute('name', 'recordingSetup');
  
    stopBtn.setAttribute('id', 'stopBtn');
    stopBtn.setAttribute('type', 'button');
    stopBtn.setAttribute('value', 'Send Tests to Server');
    stopBtn.setAttribute('name', 'recordingSetup');
  
    resetBtn.setAttribute('id', 'resetBtn');
    resetBtn.setAttribute('type', 'button');
    resetBtn.setAttribute('value', 'Reset LS');
  
    inputStatement.setAttribute('type','text');
    inputStatement.setAttribute('id','step_definiton');
    inputStatement.setAttribute('name','recordingSetup');
  
    record_overview.setAttribute('id', 'record_overview');
    record_overview.setAttribute('name', 'recordingSetup');

    $('body').append(inputStatement);
    $('body').append(recordBtn);
    $('body').append(stopBtn);
    $('body').append(resetBtn); 
    $('body').append(record_overview);
}
  
  
});
