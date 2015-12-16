$(document).ready(function() {
	
	$('#recording_save_as_json').click(function() {
	
		var jsonString ="{";
		
		jsonString += '"title_us" : ' + $('#title_user_story').html() +',' + '"description" : ' + 'testBeschreibung todo tag einfügen';
		var stepInfo= $('.rec_json');
		if(stepInfo.length>0){			
			jsonString += ',';
			for (var i = 0; i < stepInfo.length; i++) { 
				children = elementChildren (stepInfo[i]);
				for (var i = 0; i < children.length; i++) { 
					
					var child = children[i];
					alert (child.id);


					
				}
			}
		}
		
		jsonString += '}';
		
		

		
		
		
		
	
	
		$('#output').text(JSON.stringify({
	
			title_user_story:$('#title_user_story').html(),
			given : {
						"definition" : $('#rec_step_def_json').html(),
						"url" : $('#rec_step_def_json').html(),
						"targeturl" : $('#rec_step_def_json').html(),
						"value" : $('#rec_url_json').html()

				},
			"when" : {	
						"name" : "I set my login name 'admin'",
						"value" : "<input id='name'></input>"					
				},
			"then" : {
						"name" : "I can read 'Welcome admin' on the site",
						"value" : "http://localhost:8180/CustomManagerWeb/login.xhtml"
				}
	
		}));
	});
	
	function elementChildren (element) {
	    var childNodes = element.childNodes,
	        children = [],
	        i = childNodes.length;
	    
	    while (i--) {
	        if (childNodes[i].nodeType == 1) {
	            children.unshift(childNodes[i]);
	        }
	    }

	    return children;
	}
	
	function createJSON() {
    jsonObj = [];
    $('.rec_output_json').each(function() {

        var url = $(this).children('#rec_url_json').html();
        var definition = $(this).children('#rec_step_def_json').html();
		//alert(definition);

        item = {}
        item ["url"] = url;
        item ["definition"] = definition;

        jsonObj.push(item);
    });

    console.log(JSON.stringify(jsonObj));
}
	
});