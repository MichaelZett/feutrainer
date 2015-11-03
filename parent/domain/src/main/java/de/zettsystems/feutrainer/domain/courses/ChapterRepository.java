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
package de.zettsystems.feutrainer.domain.courses;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import de.zettsystems.feutrainer.domain.base.BaseRepository;

/**
 * Repository for Chapters.
 *
 * @author michael_zoeller
 * @created 26.08.2013
 */
public interface ChapterRepository extends BaseRepository<Chapter> {
	@Transactional
	List<Chapter> findAllByCourseUnit(CourseUnit courseUnit);

}
