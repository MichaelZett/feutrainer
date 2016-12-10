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
package de.zettsystems.feutrainer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.security.VaadinSecurity;
import org.vaadin.spring.security.util.SecurityExceptionUtils;
import org.vaadin.spring.sidebar.components.ValoSideBar;
import org.vaadin.spring.sidebar.security.VaadinSecurityItemFilter;
import org.vaadin.viritin.label.RichText;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main application ui.
 *
 * @author michael_zoeller
 * @created 26.08.2013
 */
@Theme("valo")
@SpringUI
public class FeuTrainerApplication extends UI implements ViewDisplay {
	private Panel springViewDisplay;

	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private VaadinSecurity vaadinSecurity;
	@Autowired
	private ValoSideBar sideBar;

	/**
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {
		initLayout();

	}

	private void initLayout() {
		setErrorHandler(createErrorHandler());

		final HorizontalLayout body = new HorizontalLayout();
		body.setSizeFull();

		this.sideBar.setItemFilter(new VaadinSecurityItemFilter(this.vaadinSecurity));
		body.addComponent(this.sideBar);

		this.springViewDisplay = new Panel();
		this.springViewDisplay.setSizeFull();
		body.addComponent(this.springViewDisplay);
		body.setExpandRatio(this.springViewDisplay, 1.0f);

		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		RichText headerCaption = new RichText().withSafeHtml("<h1><strong>FeU Trainer</strong> - by ZettSystems</h1>");
		root.addComponent(headerCaption);
		root.addComponent(body);
		root.setExpandRatio(headerCaption, 1);
		root.setExpandRatio(body, 9);

		Navigator navigator = new Navigator(this, (ViewDisplay) this);
		this.viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
		navigator.addProvider(this.viewProvider);
		navigator.setErrorView(ErrorView.class);
		navigator.navigateTo(navigator.getState());
		setContent(root);
	}

	private DefaultErrorHandler createErrorHandler() {
		return new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				if (SecurityExceptionUtils.isAccessDeniedException(event.getThrowable())) {
					Notification.show("Sorry, you don't have access to do that.");
				} else {
					super.error(event);
				}
			}
		};
	}

	@Override
	public void showView(View view) {
		this.springViewDisplay.setContent((Component) view);
	}
}
