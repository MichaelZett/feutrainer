package de.zettsystems.feutrainer.ui.organisation;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.ui.Table;

import de.zettsystems.feutrainer.domain.organisation.Institute;

@Component
@Scope("prototype")
@Transactional
public class InstituteContainerService {
	@PersistenceContext
	EntityManager entityManager;

	private JPAContainer<Institute> entityContainer;

	public static final Object[] PROPERTIES = { "id", "name" };

	public static final String[] HEADERS = { "Institute Id", "Institute Name" };

	@PostConstruct
	public void init() {
		this.entityContainer = new JPAContainer<Institute>(Institute.class);
		CachingMutableLocalEntityProvider<Institute> entityProvider = new CachingMutableLocalEntityProvider<Institute>(
				Institute.class, this.entityManager);
		entityProvider.setTransactionsHandledByProvider(false);
		this.entityContainer.setEntityProvider(entityProvider);
	}

	public Institute getEntity(Long instituteKey) {
		return this.entityContainer.getItem(instituteKey).getEntity();
	}

	public void refresh() {
		this.entityContainer.refresh();
	}

	public void setAsContainerDataSource(Table entityTable) {
		entityTable.setContainerDataSource(this.entityContainer);
	}

	public void removeItem(Long selectedIndex) {
		this.entityContainer.removeItem(selectedIndex);
	}

	public void addEntity(Institute institute) {
		this.entityContainer.addEntity(institute);
	}

	public void editEntity(Long instituteKey, String id, String name) {
		Institute institute = this.getEntity(instituteKey);
		institute.setId(id);
		institute.setName(name);
		this.entityManager.merge(institute);
	}

}