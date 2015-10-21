package smcr.ui.setup;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="firmSetupUI")
@Title("HR Firm Setup Page")
@Theme("valo")
public class FirmSetupUI extends UI {
	
	Label option = new Label("Choose an option to setup your firm");
	Link role = new Link("Role Details",new ExternalResource("/setup/addRoleUI"));
	Link classification = new Link("Classification Details",new ExternalResource("/myUI"));
	Link functions = new Link("Add Prescribed Function",new ExternalResource("/setup/addFunctionUI"));
	Link responsibilities = new Link("Add Responsibilities",new ExternalResource("/setup/addResponsibilityUI"));
	Link home = new Link("Home", new ExternalResource("/homeUI"));
	

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		
		configureComponents();
		buildLayout();

	}
	
private void configureComponents() {
		
}
	
	private void buildLayout() {
		VerticalLayout choose = new VerticalLayout();
		
		choose.addComponents(option,role,classification,functions,responsibilities,home);
		choose.setSizeUndefined();
		choose.setSpacing(false);
		setContent(choose);

	}
}
