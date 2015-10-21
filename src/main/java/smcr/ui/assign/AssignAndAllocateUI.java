package smcr.ui.assign;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="/assign/assignmentANDallocationUI")
@Title("HR Assignment AND Allocation Page")
@Theme("valo")
public class AssignAndAllocateUI extends UI {
	
	Label option = new Label("Choose an option to either Assign Responsibility or Allocate People");
	Link assignResponsibility = new Link("Assign Responsibilities to Functions",new ExternalResource("/assign/assignResponsibilitiesUI"));
	Link allocatePeople = new Link("Allocate People to Responsibilities",new ExternalResource("/assign/allocatePeopleUI"));

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
			
			choose.addComponents(option,assignResponsibility,allocatePeople);
			choose.setSizeUndefined();
			choose.setSpacing(false);
			setContent(choose);

		}

}
