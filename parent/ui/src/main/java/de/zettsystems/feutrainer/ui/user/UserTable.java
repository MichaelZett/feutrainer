package de.zettsystems.feutrainer.ui.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.viritin.SortableLazyList;
import org.vaadin.viritin.fields.MTable;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.user.User;
import de.zettsystems.feutrainer.domain.user.UserRepository;

@SpringComponent
@ViewScope
public class UserTable extends MTable<User> {
	/** The Constant PAGESIZE. */
	private static final int PAGESIZE = 45;
	@Autowired
	private UserRepository userRepository;

	public UserTable() {
		super(User.class);
		withProperties(initializeProperties()).withColumnHeaders(initializeHeaderCaptions())
				.setSortableProperties(initializeSortableProperties()).withFullWidth();
	}

	/**
	 * List entities.
	 */
	@SuppressWarnings("unchecked")
	public void listEntities() {
		setBeans(new SortableLazyList<User>(
				(firstRow, asc, sortProperty) -> retrieveFilteredBeans(firstRow, asc, sortProperty),
				() -> retrieveCount(), PAGESIZE));
		setValue(null);
	}

	private int retrieveCount() {
		return (int) userRepository.count();
	}

	private List<User> retrieveFilteredBeans(int firstRow, boolean asc, String sortProperty) {
		return userRepository.findAllBy(createPageRequest(firstRow, asc, sortProperty));
	}

	private PageRequest createPageRequest(int firstRow, boolean asc, String sortProperty) {
		return new PageRequest(firstRow / PAGESIZE, PAGESIZE, asc ? Sort.Direction.ASC : Sort.Direction.DESC,
				sortProperty == null ? getDefaultSortProperty() : sortProperty);
	}

	/**
	 * Gets the default sort property.
	 *
	 * @return the default sort property
	 */
	private String getDefaultSortProperty() {
		return "username";
	}

	private String[] initializeProperties() {
		return new String[] { "username", "password", "role", "enabled" };
	}

	private String[] initializeSortableProperties() {
		return new String[] { "username", "password", "role", "enabled" };
	}

	private String[] initializeHeaderCaptions() {
		return new String[] { "Username", "Password", "Role", "Enabled" };
	}

}
