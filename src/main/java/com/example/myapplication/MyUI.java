package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@Theme("mytheme")
//@Widgetset("com.example.myapplication.MyAppWidgetset")
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

		int rowCount = 3;
		GridLayout gridLayout = new GridLayout(6, rowCount);
		for (int i = 0; i < rowCount; i++) {
			gridLayout.setRowExpandRatio(i, 1);
		}
		gridLayout.setSizeFull();
		for (int i = 0; i < 10; i++) {
			gridLayout.addComponent(new ColoredLabel());
		}

		Label label = new Label("Shrinking header");
		label.addStyleName("header");
		VerticalLayout responsive = new VerticalLayout(label, gridLayout);
		responsive.setExpandRatio(gridLayout, 1);
		responsive.setSizeFull();
		responsive.addStyleName("responsive");
		Responsive.makeResponsive(responsive);
		Component root = new VerticalSplitPanel(responsive, new Label(getDesc()));
		root.setSizeFull();
		setContent(root);
	}

	private String getDesc() {
		return "Resize the split panel upwards until the \"Shrinking header shrinks\". After that the GridLayout row heights do not respect the set expand ratios anymore.";
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
