function toggleLogin()	{
	return true;
	//skip this for now
	
	try	{
		$('loginDiv').style.display = ($('loginDiv').style.display=='none') ? '' : 'none';
		if($('loginDiv').style.display!='none')	{
			$('loginForm').username.focus();
			$('loginForm').username.select();
			try	{
				Fat.fade_element("loginMsg",60,3000);
			}
			catch(err){}
		}
		
		return false;
	}
	catch(err)	{
		//alert(err);
		return true;
	}
	return false;
}

function checkRadio(rad)	{
	//alert(rad.nodeName + " : " + rad.nodeValue);
	if(rad.nodeName == "INPUT" && !rad.checked)	{
		rad.checked = true;
	}
}

function changeStudyText(combo)
{

	var i = combo.value;

    var myDescription = "description_"+i;

		
		myString = $(myDescription).value;
	

	$('descriptionTxt').innerHTML = myString;
		
}

function changeStudyVersion(el)	{
	//console.log("show " + el.value);
	/*
	$$("#optionsBox select").each(
		function(e)	{
			//console.log(e.id);
			e.hide();
		}
	)
	*/
	$("studyId").options.length = 0;
	for(var i=0; i<$(el.value+"_options").options.length; i++)	{
	
		$("studyId").options[$("studyId").options.length] = new Option($(el.value+"_options").options[i].text,$(el.value+"_options").options[i].value); 
	
	}
	changeStudyText($("studyId"));
	//$(el.value+"_options").show();
}

function changeText(combo)	{
	var index = combo.selectedIndex - 1;
	var name = combo.text;

	if (index < 0)	{
		myString = "There are multiple analyses of the data in this study. Please review the context-sensitive abstract when you use the dropdown list above to select a specific analysis.";
	}
	else	{
		myString = document.associationsForm.methods[index].value;
	}

	//document.associationsForm.abstract.value = myString;
	$('abstractTxt').innerHTML = myString;
}

function changeDescriptionText(combo, type)	{
	var index = combo.selectedIndex - 1;
	var name = combo.text;

	if (index < 0)	{
		myString = "This is a context-sensitive description for the analysis group selected in the above drop down.";
	}
	else	{
		if (type == 'Subject')
			myString = document.subjectForm.description[index].value;
		else
			myString = document.genotypeForm.description[index].value;
	}

	$('abstractTxt').innerHTML = myString;
}

function openDescription()	{
	//index = document.associationsForm.analysis.selectedIndex - 1;
	//if (index >= 0)
	//{
	//	url = document.associationsForm.description[index].value;
	//	window.open(url, "Description", "menubar=no,toolbar=no");
	//}
	//else
	//{
		//url = "caGWAS_Analysis_Descriptions.pdf";
		url = document.associationsForm.description[0].value;
		window.open(url, "Description", "menubar=no,toolbar=no");
	//}
}

function clearLoginForm()
{
	document.loginForm.action="reset.do?formName=Login";
	document.loginForm.submit();
}

function clearAssocForm()
{
	document.associationsForm.action="reset.do?formName=Association";
	document.associationsForm.submit();

	//try
	//{
	//	if($('searchAssocForm'))
	//	{
	//		$('searchAssocForm').reset();
	//	}
	//}
	//catch(err)
	//{
	//}
	
	//document.associationsForm.analysis.value = "";
	//document.associationsForm.association[0].checked = false;
	//document.associationsForm.association[1].checked = false;
	//document.associationsForm.pvalue.value = "";
	//document.associationsForm.rank.value = "";
	//clearAnnotations(document.associationsForm);
}

function clearPopForm()
{
	document.populationForm.action="reset.do?formName=Population";
	document.populationForm.submit();

	//try
	//{
	//	if($('searchPopForm'))
	//	{
	//		$('searchPopForm').reset();
	//	}
	//}
	//catch(err)
	//{
	//}

	//document.populationForm.population.value = "All";
	//document.populationForm.weinbergValue.value = "";
	//document.populationForm.alleleValue.value = "";
	//document.populationForm.completionValue.value = "";
	//clearAnnotations(document.populationForm);
}	

function clearGenotypeForm()
{
	document.genotypeForm.action="reset.do?formName=Genotypes";
	document.genotypeForm.submit();

	//try
	//{
	//	if($('searchGenoForm'))
	//	{
	//		$('searchGenoForm').reset();
	//	}
	//}
	//catch(err)
	//{
	//}
	
	//document.genotypeForm.queryType[0].checked = false;
	//document.genotypeForm.queryType[1].checked = false;
	//document.genotypeForm.population.value = "";
	//document.genotypeForm.analysisGroup.value = "";
	//document.genotypeForm.qcStatus.value = "QC+";
	
	//clearSubject(document.genotypeForm);
	//clearAnnotations(document.genotypeForm);
}

function clearSubjectForm()
{
	document.subjectForm.action="reset.do?formName=Subjects";
	document.subjectForm.submit();

	//try
	//{
	//	if($('searchSubjForm'))
	//	{
	//		$('searchSubjForm').reset();
	//	}
	//}
	//catch(err)
	//{
	//}
	
	//document.subjectForm.queryType[0].checked = false;
	//document.subjectForm.queryType[1].checked = false;
	//document.subjectForm.population.value = "";
	//document.subjectForm.analysisGroup.value = "";
	//clearSubject(document.subjectForm);
}
  
function clearAnnotations(form)
{
  	form.chromosome.value = "";
  	form.chromosomeFrom.value = "";
  	form.chromosomeTo.value = "";
	form.fromUnit.value="bp";
	form.toUnit.value="bp";
	form.hugoList.value = "";
	form.idList.value = "";
}

function clearSubject(form)
{
  	form.gender.value = "";
  	form.lowerAge.value = "";
  	form.upperAge.value = "";
	form.caseControl.value = "";
	form.familyHistory.value = "";
}

function showWait()
{
	document.all.wait.style.display='block';
	document.all.searchForm.style.display='none';
	setTimeout('document.images["progress"].src = "images/cagwas_loader_anim.gif"', 200);
}

function changeAnalysisAbstract(combo, i, offset)
{
	var index = combo.selectedIndex;
	var name = combo.text;

	myString = document.associationsForm.methodDescription[index + offset].value;

	//if (index < 0)
	//{
	//	myString = "Please review this context-sensitive abstract when you use the dropdown list above.";
	//}
	//else
	//{
	//	myString = document.associationsForm.methodDescription[index + offset].value;
	//}

	$('methodAbstractTxt' + i).innerHTML = myString;
}
