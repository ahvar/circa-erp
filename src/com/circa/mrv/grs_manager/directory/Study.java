/**
 * 
 */
package com.circa.mrv.grs_manager.directory;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.location.ResearchSite;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;

/**
 * The Study is maintained by a ResearchClient.
 * 
 * @author ArthurVargas 
 */
public class Study {
	/** The unique ID number for this study */
	private long id;
	/** Primary physician associated with this study */
	private String name;
	/** The first day of the study */
	private Calendar start;
	/** The last day of the study */
	private Calendar last;
	/** A list of the research sites for this particular study */
	LinkedAbstractList<ResearchSite> sites;
	/** The default number of research sites */
	private static final int DEFAULT = 5;
	
	/**
	 * Constructs the Study with this ID, name of the primary physician leading the study, and a start date. 
	 * The list of research sites is set to DEFAULT. An end date for the study is not set by this constructor.
	 * 
	 * @param num the unique ID of the study
	 * @param name the name of the physician in charge of the study
	 * @param start the start date 
	 */
	public Study(long num, String name, Calendar start ) {
		setNum(num);
		setName(name);
		setStart(start);
		sites.setCapacity(DEFAULT);
	}
	
	/**
	 * Constructs the Study with this ID, name of the primary physician leading the study, a start date, an end date
	 * and a list of research sites where the study will be conducted. 
	 * 
	 * @param num the unique ID of the study
	 * @param name the name of the physician in charge of the study
	 * @param start the start date 
	 * @param last the end date
	 * @param sites a LinkedList of research sites
	 */
	public Study(long num, String name, Calendar start, Calendar last, LinkedAbstractList<ResearchSite> sites) {
	    this(num,name,start);
		setLast(last);
		setSites(sites);
	}
	
    /**
     * Return the ID for this Study.
     * @return num the ID for the Study.
     */
	public long getNum() {
		return id;
	}

	/**
	 * Sets the ID for this study to num
	 * @param num the ID
	 */
	public void setNum(long num) {
		this.id = num;
	}

	/**
	 * Returns the name of the primary physician in charge of the Study
	 * @return the name of the physician
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the primary physician to the name parameter.
	 * @param name the name of the physician
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the start date for the Study.
	 * @return start the start date.
	 */
	public Calendar getStart() {
		return start;
	}

	/**
	 * Sets the start date to the parameter start.
	 * @param start the beginning of the study
	 */
	public void setStart(Calendar start) {
		this.start = start;
	}

	/** 
	 * Returns the last date the Study is ongoing.
	 * @return last the last date of the Study.
	 */
	public Calendar getLast() {
		return last;
	}

	/**
	 * Sets the 'last' instance variable to the Calendar object called 'last'
	 * @param last the last date of the Study
	 */
	public void setLast(Calendar last) {
		this.last = last;
	}

	/**
	 * Returns a LinkedAbstractList of the ResearchSites for this particular study
	 * @return sites the list of ResearchSites
	 */
	public LinkedAbstractList<ResearchSite> getSites() {
		return sites;
	}

	/**
	 * Sets the 'sites' instance variable to the LinkedAbstractList<> object called 'sites'
	 * @param sites the list of ResearchSites for this Study
	 */
	public void setSites(LinkedAbstractList<ResearchSite> sites) {
		this.sites = sites;
	}
	
	/**
	 * Adds this ResearchSite to the list of ResearchSites named 'sites'
	 * @param site
	 */
	public void addSite(ResearchSite site) {
		sites.add(site);
	}
}
