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
package de.zettsystems.futrainer.domain.organisation;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Institute.
 * 
 * @author michael_zoeller
 * @created 26.08.2013
 */
public interface InstituteRepository extends JpaRepository<Institute, Long> {

}
