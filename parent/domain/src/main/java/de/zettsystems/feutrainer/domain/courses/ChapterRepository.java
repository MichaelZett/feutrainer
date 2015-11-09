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

import org.springframework.data.domain.Pageable;
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
	List<Chapter> findAllByCourseUnitAndIdLikeIgnoreCase(CourseUnit courseUnit, String id, Pageable pageable);

	@Transactional
	List<Chapter> findAllByIdLikeIgnoreCaseAndNameLikeIgnoreCaseAndCourseUnit(String id, String name,
			CourseUnit courseUnit, Pageable pageable);

	@Transactional
	List<Chapter> findAllByIdLikeIgnoreCaseAndNameLikeIgnoreCase(String id, String name, Pageable pageable);

	@Transactional
	long countByCourseUnitAndIdLikeIgnoreCase(CourseUnit courseUnit, String id);

	@Transactional
	long countByIdLikeIgnoreCaseAndNameLikeIgnoreCase(String id, String name);

	@Transactional
	long countByIdLikeIgnoreCaseAndNameLikeIgnoreCaseAndCourseUnit(String id, String name, CourseUnit courseUnitFilter);

	@Transactional
	long countByNameLikeIgnoreCase(String name);

	@Transactional
	List<Chapter> findAllByCourseUnitAndNameLikeIgnoreCase(CourseUnit value, String name);
}
