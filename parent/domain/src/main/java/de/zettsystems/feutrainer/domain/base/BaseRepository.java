/*
 * This file is part of a Werum Software & Systems AG project.
 *
 * Copyright (c)
 *    Werum Software & Systems AG
 *    All rights reserved.
 *
 * This source file may be managed in different Java package structures,
 * depending on actual usage of the source file by the Copyright holders:
 *
 * for Werum:  com.werum.* or any other Werum owned Internet domain
 *
 * Any use of this file as part of a software system by none Copyright holders
 * is subject to license terms.
 *
 * Last Change: $Id$
 *
 * $HeadURL$
 */
package de.zettsystems.feutrainer.domain.base;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base Repository.
 *
 * @author michael_zoeller
 * @param <T>
 *            the generic type
 * @created 21.10.2015
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

	/**
	 * Find all by.
	 *
	 * @param pageable
	 *            the pageable
	 * @return the list
	 */
	@Transactional(readOnly = true)
	List<T> findAllBy(Pageable pageable);

	@Transactional(readOnly = true)
	List<T> findAllByNameLikeIgnoreCase(String id);

}
