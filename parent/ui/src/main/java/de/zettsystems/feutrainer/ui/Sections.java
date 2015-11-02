/*
 * Copyright 2015 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.zettsystems.feutrainer.ui;

import org.springframework.stereotype.Component;
import org.vaadin.spring.sidebar.annotation.SideBarSection;
import org.vaadin.spring.sidebar.annotation.SideBarSections;

/**
 * @author zoellerm
 */
@Component
@SideBarSections({ @SideBarSection(id = Sections.GENERAL, caption = "General"),
		@SideBarSection(id = Sections.ADMIN, caption = "Admin"),
		@SideBarSection(id = Sections.MASTER_DATA, caption = "Master Data"),
		@SideBarSection(id = Sections.TESTS, caption = "Tests") })
public class Sections {

	public static final String GENERAL = "general";
	public static final String ADMIN = "admin";
	public static final String MASTER_DATA = "masterdata";
	public static final String TESTS = "tests";
}
