package smcr.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
//import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="homeUI")
@Title("HR Home Page")
@Theme("valo")
public class HomeUI extends UI {
	
	Label welcome = new Label();
	Label option = new Label();
	Link one = new Link("Firm Setup",new ExternalResource("/firmSetupUI"));
	Link two = new Link("Assignment and Allocation",new ExternalResource("/assign/assignmentANDallocationUI"));
	Link three = new Link("Display Reports",new ExternalResource("/reports/displayReportsUI"));

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		
		configureComponents();
		buildLayout();
		
	}
	
	private void configureComponents() {
		
		welcome.setValue("Welcome to AllSop Demo");
		option.setValue("Please select an option");
		//one.setTargetName("_blank");
	}
	
	private void buildLayout() {
		VerticalLayout choose = new VerticalLayout();
		choose.addComponent(welcome);
		choose.addComponent(option);
		//choose.addComponent(one);
		choose.addComponents(one,two,three);
		choose.setSizeUndefined();
		choose.setSpacing(false);
		setContent(choose);
		
	}

}
