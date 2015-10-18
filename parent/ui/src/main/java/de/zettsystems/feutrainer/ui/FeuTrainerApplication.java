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

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Main application ui.
 * 
 * @author michael_zoeller
 * @created 26.08.2013
 */
@Theme("valo")
@SpringUI
public class FeuTrainerApplication extends UI {
    public static final String NAME = "main";

    /**
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(VaadinRequest request) {
        HorizontalSplitPanel mainSplitPanel = new HorizontalSplitPanel();
        mainSplitPanel.setFirstComponent(new Label("Hello"));
        mainSplitPanel.setSecondComponent(new Label("User"));
        mainSplitPanel.setSizeFull();
        setContent(mainSplitPanel);

    }

}
