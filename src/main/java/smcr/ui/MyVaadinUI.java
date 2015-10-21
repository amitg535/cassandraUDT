package smcr.ui;



import org.springframework.beans.factory.annotation.Autowired;

import smcr.command.Classification;
import smcr.service.ClassificationService;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SpringUI(path="myUI")
@Title("Add Classification!")
@Theme("valo")
public class MyVaadinUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* Hundreds of widgets.
	 * Vaadin's user interface components are just Java objects that encapsulate
	 * and handle cross-browser support and client-server communication. The
	 * default Vaadin components are in the com.vaadin.ui package and there
	 * are over 500 more in vaadin.com/directory.
     */
    Link firmSetup = new Link("Firm Setup",new ExternalResource("/firmSetupUI"));
	TextField filter = new TextField();
    Grid classificationList = new Grid();
    Button newClassification = new Button("New classification");
	
	ClassificationForm classificationForm = new ClassificationForm();
	
	@Autowired
	ClassificationService classificationService;

	@Override
	protected void init(VaadinRequest request) {
		
		 configureComponents();
	        buildLayout();
		
		
	}
	
	private void configureComponents() {
		 /* Synchronous event handling.
        *
        * Receive user interaction events on the server-side. This allows you
        * to synchronously handle those events. Vaadin automatically sends
        * only the needed changes to the web page without loading a new page.
        */
       newClassification.addClickListener(e -> classificationForm.edit(new Classification()));

       filter.setInputPrompt("Filter classifications...");
       filter.addTextChangeListener(e -> refreshClassifications(e.getText()));
       
       
       classificationList.setContainerDataSource(new BeanItemContainer<>(Classification.class));
       classificationList.setColumnOrder("code", "classification_name");
       classificationList.setSelectionMode(Grid.SelectionMode.SINGLE);
       classificationList.addSelectionListener(e
               -> classificationForm.edit((Classification) classificationList.getSelectedRow()));
       refreshClassifications();
	}
	
	
	 private void buildLayout() {
		 
		 HorizontalLayout actions = new HorizontalLayout(firmSetup,filter, newClassification);
	        actions.setWidth("100%");
	        filter.setWidth("100%");
	        actions.setExpandRatio(filter, 1);

	        VerticalLayout left = new VerticalLayout(actions, classificationList);
	        left.setSizeFull();
	        classificationList.setSizeFull();
	        left.setExpandRatio(classificationList, 1);
	        
	        HorizontalLayout mainLayout = new HorizontalLayout(left, classificationForm);
	        mainLayout.setSizeFull();
	        mainLayout.setExpandRatio(left, 1);

	        // Split and allow resizing
	        setContent(mainLayout);
		 
		 
			
		 
		 
	 }
	 
	 /* Choose the design patterns you like.
     *
     * It is good practice to have separate data access methods that
     * handle the back-end access and/or the user interface updates.
     * You can further split your code into classes to easier maintenance.
     * With Vaadin you can follow MVC, MVP or any other design pattern
     * you choose.
     */
    void refreshClassifications() {
        refreshClassifications(filter.getValue());
    }

    private void refreshClassifications(String stringFilter) {
        classificationList.setContainerDataSource(new BeanItemContainer<>(
                Classification.class, classificationService.findAll(stringFilter)));
        classificationForm.setVisible(false);
    }
	

}
