package com.mobidevday;

import java.util.*;

/**
 * The class Presentations maintains a ArrayList of the Presentations in the Conference.
 * It gets an Iterator, PresentationIterator to iterate over the Presentations.
 * 
 * 
 * @author Don Ward
 *
 */
public class Presentations {
private ArrayList<Presentation> presentationList = new ArrayList<Presentation>();


/**
 * Adds a Presentation to the list of presentations maintained by the Presentations Class.
 * 
 * @param presenter
 * @param presentationTitle
 * @param presentationDesc
 */
public void addPresentation(String presenter, String presentationTitle, String presentationDesc){
		Presentation tempPres = new Presentation();
		tempPres.setPresenter(presenter);
		tempPres.setDescription(presentationDesc);
		tempPres.setTitle(presentationTitle);
		presentationList.add(tempPres);
}

/**
 * Returns the Presentation Title for a given Presenter Name. 
 * 
 * @param presenterName
 * @return
 */
public String getPresentationTitle(String presenterName){
	Presentation tempPres  = null;
	PresentationIterator it = getIterator();
	while(it.hasNext()){
		tempPres = it.next();
		if(tempPres.getPresenter().equals(presenterName))
		{
			return tempPres.getTitle();			
		}
		}
	return "";
	}

/**
 * Returns the Presentation Description for a given Presenation Title
 * 
 * @param presenterName
 * @return
 */
public String getPresentationDescription(String presentationTitle){
	Presentation tempPres  = null;
	PresentationIterator it = getIterator();
	while(it.hasNext()){
		tempPres = it.next();
		if(tempPres.getTitle().equals(presentationTitle))
		{
			return tempPres.getDescription();			
		}
		}
	return "";
	
	
}

private PresentationIterator getIterator()
{
	return new PresentationIterator(presentationList);
}

}

