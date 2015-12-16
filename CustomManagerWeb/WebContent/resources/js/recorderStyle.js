$(document).ready(function () {

	function addGlobalStyle(css) {
		var head, style;
		head = document.getElementsByTagName('head')[0];
		if (!head) { return; }
		style = document.createElement('style');
		style.type = 'text/css';
		style.innerHTML = css;
		head.appendChild(style);
	}
	
	addGlobalStyle('.recorder { background-color:lightgrey; border: 10px solid lightgrey;margin-top:10px; }');
	addGlobalStyle('.recorder_width_100{width:100px;}');
	addGlobalStyle('.recorder_float_left{float:left;}');
	addGlobalStyle('.recorder_float_right{float:right;}');
	addGlobalStyle('.recorder_margin_10{margin: 5px;}');
	addGlobalStyle('.recorder_border_0{border: 0px;}');
	addGlobalStyle('.recorder_nostyle{list-style-type: none;}');
	addGlobalStyle('.recorder_output{padding: 2em 20em;}');
	addGlobalStyle('.recorder_hints{font-size: 10px;padding: 10px 10px;}');
	addGlobalStyle('.recorder_userStory{font-size: 18px;padding: 10px 10px;}');
	addGlobalStyle('.recorder_stepDef{font-size: 15px;padding: 10px 10px;}');
	addGlobalStyle('.recorder_element{font-size: 15px;padding: 10px 10px; background-color: #E0F2F7;}');
	addGlobalStyle('.recorder_bold{font-weight: bold;}');
	addGlobalStyle('.recorder_icon{vertical-align:text-bottom;}');

});