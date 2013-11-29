/*
 * DressingSuggestion.java
 */
package org.ups.dressingengine.impl;

import java.util.ArrayList;
import java.util.List;

import org.ups.dressingengine.IDressingSuggestion;
import org.ups.dressingengine.IDressingSuggestionListener;



/**
 *
 */
public class DressingSuggestionImpl implements IDressingSuggestion {
	
	
	private List<IDressingSuggestionListener> listeners = new ArrayList<IDressingSuggestionListener>();


	/* (non-javadoc)
	 * @see org.ups.dressingengine.IDressingSuggestion#addListener(org.ups.dressingengine.IDressingSuggestionListener)
	 */
	public void addListener(IDressingSuggestionListener listener) {
		this.listeners.add(listener);
	}


	/* (non-javadoc)
	 * @see org.ups.dressingengine.IDressingSuggestion#removeListener(org.ups.dressingengine.IDressingSuggestionListener)
	 */
	public void removeListener(IDressingSuggestionListener listener) {
		this.listeners.remove(listener);
	}


	/* (non-javadoc)
	 * @see org.ups.dressingengine.IDressingSuggestion#sunGlassesNeeded()
	 */
	public boolean sunGlassesNeeded() {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-javadoc)
	 * @see org.ups.dressingengine.IDressingSuggestion#umbrellaNeeded()
	 */
	public boolean umbrellaNeeded() {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-javadoc)
	 * @see org.ups.dressingengine.IDressingSuggestion#coatNeeded()
	 */
	public boolean coatNeeded() {
		// TODO Auto-generated method stub
		return false;
	}

}
