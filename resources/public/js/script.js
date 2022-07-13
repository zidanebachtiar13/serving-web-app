function openPembahasan(noSoal) {
	var i, tabcontent;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	document.getElementById(noSoal).style.display = "block";
}

document.getElementById("defaultOpen").click();
