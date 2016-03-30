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

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User.
 *
 * @author michael_zoeller
 * @created 28.03.2016
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUsername(String userName);

}
