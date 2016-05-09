package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@Theme("mytheme")
// @Widgetset("com.example.myapplication.MyAppWidgetset")
public class MyUI extends UI {
	static int colorNumber = 0;

	class ColoredLabel extends Label {
		public ColoredLabel() {
			super();
			addStyleName("color-label");
			addStyleName("color-" + (colorNumber++) % 10);
		}
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		int rowCount = 58;
		GridLayout gridLayout = new GridLayout(6, rowCount);
		for (int i = 0; i < rowCount; i++) {
			gridLayout.setRowExpandRatio(i, 1);
		}
		gridLayout.setSizeFull();
		for (int i = 0; i < 20; i++) {
			int upperLeftRow = i * 2;
			int upperLeftCol = 0;
			int lowerRightCol = 5;
			int lowerRightRow = upperLeftRow + 1;
			ColoredLabel coloredLabel = new ColoredLabel();
			coloredLabel.setSizeFull();
			gridLayout.addComponent(coloredLabel, upperLeftCol, upperLeftRow, lowerRightCol, lowerRightRow);
		}

		gridLayout.setHeight("500%");
		Component root = new Panel(gridLayout);
		root.setSizeFull();
		setContent(root);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}

