package de.zettsystems.feutrainer.domain.organisation;

import javax.persistence.Entity;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

/**
 * Entity for university and such.
 *
 * @author michael_zoeller
 * @created 26.08.2013
 */
@Entity
public class Institute extends AbstractBaseEntity {

	/**
	 * Instantiates a new institute.
	 */
	public Institute() {
		super();
	}

	/**
	 * Instantiates a new institute.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public Institute(String id, String name) {
		super(id, name);
	}

}
