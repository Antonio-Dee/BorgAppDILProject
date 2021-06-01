(function () {
	
	const MAX_NUM_OF_SUGGESTES = 3;

	//Load listener
	window.addEventListener("load", () => updateSuggested(), false);

	
	//update suggested function
	function updateSuggested(){

		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
			var myObj = JSON.parse(this.responseText);
		
			for(var i=1; i<=MAX_NUM_OF_SUGGESTES; i++){
				document.getElementById("suggested"+i).style = "background-image: url('../images/"+myObj[i-1].image+"');";
				document.getElementById("suggested"+i+"_name").innerHTML = myObj[i-1].name;
			}
		
		  }
		};
		xmlhttp.open("GET", '/villages/suggested', true);
		xmlhttp.send();
		}
	
}())