package smcr.ui;



import smcr.command.Classification;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class ClassificationForm extends FormLayout {
	
	TextField code = new TextField("Classification Code");
	TextField classification_name = new TextField("Classification Name");
	Button save = new Button("Add",this::save);
	
	
	Classification classification;
	
	 // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<Classification> formFieldBindings;
    
    
    public ClassificationForm() {
        configureComponents();
        buildLayout();
    }
    
    private void configureComponents() {
        /* Highlight primary actions.
         *
         * With Vaadin built-in styles you can highlight the primary save button
         * and give it a keyboard shortcut for a better UX.
         */
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }
    
    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save);
        actions.setSpacing(true);

		addComponents(actions, code, classification_name);
    }
 

	
	 public void save(Button.ClickEvent event) {
	        try {
	        	code.setValidationVisible(true);
	        	classification_name.setValidationVisible(true);
	            // Commit the fields from UI to DAO
	            formFieldBindings.commit();

	            // Save DAO to backend with direct synchronous service API
	          
	            getUI().classificationService.addClassification( classification.getCode(),
	                    classification.getClassification_name());

	            String msg = String.format("Saved '%s %s'.",
	                   classification.getCode(),
	                    classification.getClassification_name());
	            Notification.show(msg,Type.TRAY_NOTIFICATION);
	            getUI().refreshClassifications();
	        } catch (FieldGroup.CommitException e) {
	            // Validation exceptions could be shown here
	        } catch (Exception e) {
	        	
	        }
	    }
	 
	 void edit(Classification classification) {
	        this.classification= classification;
	       
	        if(classification.getCode() == null) {
	        	 code.setValidationVisible(false);
	        	 classification_name.setValidationVisible(false);
	        	 classification.setCode("");
	        	 classification.setClassification_name("");
	        }
	        if(classification != null) {
	            // Bind the properties of the contact POJO to fiels in this form
	            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(classification, this);
	            code.focus();
	        }
	        setVisible(classification!= null);
	    }
	 
	 @Override
	    public MyVaadinUI getUI() {
	        return (MyVaadinUI) super.getUI();
	    }

}
