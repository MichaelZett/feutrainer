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
package de.zettsystems.feutrainer.domain.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for User.
 *
 * @author michael_zoeller
 * @created 28.03.2016
 */
public interface UserRepository extends JpaRepository<User, Long> {
	@Transactional(readOnly = true)
	User findUserByUsername(String userName);

	@Transactional(readOnly = true)
	List<User> findAllBy(Pageable pageable);

}
