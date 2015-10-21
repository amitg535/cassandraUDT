package smcr.ui.assign;


import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



import smcr.command.AvailableFunctions;
import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.People_Responsibility;
import smcr.service.AllAvailableService;
import smcr.service.FunctionResponsibilityService;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.AbstractSelect.AbstractSelectTargetDetails;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.Table.TableTransferable;
import com.vaadin.ui.UI;


@SpringUI(path="/assign/assignResponsibilitiesUI")
@Title("Assigned Responsibilities to Functions")
@Theme("valo")
public class ResponsibilityAssignmentUI extends UI {
	
	public static final Logger LOG = LoggerFactory.getLogger(ResponsibilityAssignmentUI.class);
	
	@Autowired
	FunctionResponsibilityService functionResponsibilityService;
	
	@Autowired
	AllAvailableService allAvailableService;
	
	Table functionsResp = new Table("Functions Responsibility");
	Table allAvailable = new Table("Available Functions,Responsibilities and People");

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub

		configureComponents();
		buildLayout();

	}
	
	private void configureComponents() {
		
		functionsResp.addContainerProperty("Functions", Label.class, null);
		functionsResp.addContainerProperty("Functions Responsibility", Table.class, null);
		functionsResp.addContainerProperty("Peoples Responsibility", Table.class, null);
		
		List<Applicable_FunctionsDom> functionsResponsibility = functionResponsibilityService.showFunctionsResponsibilities();
		
		
		int i = 1;
		for(Applicable_FunctionsDom function : functionsResponsibility ) {
			
			Integer itemId = new Integer(i);
			Label function_code = new Label (function.getFunction_code());
			Table funcResp = new Table();
			funcResp.addContainerProperty("Function Responsibility", String.class, null);
		    funcResp.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		    Set<String> eachFuncResp = function.getFunctions_responsibility();
			int j=1;
			for(String eachResp : eachFuncResp) {
				Integer funcId = new Integer(j);
				
				funcResp.addItem(new String[] {eachResp},funcId);
				j++;
			}
		 // Allow the tree to receive drag drops and handle them
		    funcResp.setDropHandler(new DropHandler() {

				@Override
				public void drop(DragAndDropEvent event) {
					// Wrapper for the object that is dragged
					TableTransferable t = (TableTransferable) event.getTransferable();
					AbstractSelectTargetDetails target = (AbstractSelectTargetDetails) event.getTargetDetails();
					
					
					// Get ids of the dragged item and the target item
					Object sourceItemId = t.getItemId(); //t.getData("availableFunctionId");
					Object sourcePropertyId = t.getPropertyId();
					System.out.println("Property Id:"+sourcePropertyId);
					Table fromEvent = t.getSourceComponent();
					
					Item item = fromEvent.getItem(sourceItemId);
					Property<String> property = item.getItemProperty(sourcePropertyId);
					
					String responsibility = (String) property.getValue();
					System.out.println(responsibility);
					
					Object targetItemId = target.getItemIdOver();
					
					
					// On which side of the target the item was dropped
					VerticalDropLocation location = target.getDropLocation();
					// Drop right on an item -> make it a child
					if (location == VerticalDropLocation.MIDDLE) {
		             
		               Item parentItem = functionsResp.getItem(itemId);
		               Property<Label> parentProperty = parentItem.getItemProperty("Functions");;
							             
						Label parentValue = (Label) parentProperty.getValue();
						System.out.println(parentValue.getValue());
						String functionCode = parentValue.getValue();
						functionResponsibilityService.updateFunctionsResponsibility(functionCode, responsibility);
						
						IndexedContainer container = (IndexedContainer) funcResp.getContainerDataSource();
						
						Object addedItemId =  container.addItemAfter(targetItemId);
						funcResp.addItem(addedItemId);
						Item addedItem = container.getItem(addedItemId);
						Property<String> existingProperty = addedItem.getItemProperty("Function Responsibility");
						existingProperty.setValue(responsibility);
						
						IndexedContainer container1 = (IndexedContainer) fromEvent.getContainerDataSource();
						container1.removeItem(sourceItemId);
						fromEvent.setContainerDataSource(container1);
					
						
						System.out.println(funcResp.getContainerDataSource());
						System.out.println(function.getFunctions_responsibility());
						funcResp.setContainerDataSource(container);
						funcResp.setSortAscending(true);
                       // funcResp.setSizeFull();
						 String msg = String.format(" '%s'from Available Responsibilities assigned to '%s' Function.",
								 responsibility,functionCode);
				            Notification.show(msg,Type.TRAY_NOTIFICATION);
						
						functionsResp.setVisible(true);
					  
					    
						
					}
				}

				@Override
				public AcceptCriterion getAcceptCriterion() {
					// TODO Auto-generated method stub
					return AcceptAll.get();
				}
		    	
		    });
		    
		    
			
			// Show exactly the currently contained rows (items)
			funcResp.setPageLength(funcResp.size());
		    Table peopleResp = new Table();
			peopleResp.addContainerProperty("name", String.class, null);
			peopleResp.addContainerProperty("People Responsible", Table.class, null);
			peopleResp.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
			Set<People_Responsibility> pepResp = function.getAssigned_responsibilities();
			int k=1;
			for(People_Responsibility each :pepResp ) {
				Integer peopleId = new Integer(k);
				peopleResp.addItem(new Object[] {each.getName()},peopleId);
							
				Table eachPeopleResp = new Table();
				eachPeopleResp.addContainerProperty("Each responsibility", String.class, null);
				eachPeopleResp.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
				Set<String> eachResp= each.getResponsibility();
				int l=1;
				for(String eachResp1 : eachResp) {
					Integer eachPeopleId = new Integer(l);
					eachPeopleResp.addItem(new Object[] {eachResp1},eachPeopleId);
					l++;
				}
				eachPeopleResp.setPageLength(eachPeopleResp.size());
				peopleResp.addItem(new Object[] {each.getName(),eachPeopleResp},peopleId);
				k++;
			}
			// Show exactly the currently contained rows (items)
						peopleResp.setPageLength(peopleResp.size());
			
			System.out.println(function.getFunction_code());
			//functionsResp.addItem(new Object[] {function_code,funcResp}, itemId);
			functionsResp.addItem(new Object[] {function_code,funcResp,peopleResp}, itemId);
			//functionsResp.addItem(function_code);
			i++;
		}
		
		//allAvailable.addContainerProperty("Availabile Details", Table.class, null);
		allAvailable.addContainerProperty("Available_Functions", Table.class, null);
		allAvailable.addContainerProperty("Available_Responsibilities", Table.class, null);
		allAvailable.addContainerProperty("Available People", Table.class, null);
		
		List<AvailableFunctions> availableFunctions=  allAvailableService.allAvailableFunctions();
		
		int c = 1;
		for( AvailableFunctions af : availableFunctions) {
			Integer allId = new Integer(c);
			
			
			Table availability_function = new Table();
			availability_function.addContainerProperty("Available Functions", String.class, null);
			availability_function.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
			List<String> availFunc = af.getAvailabeFunction();
						
			LOG.info(availFunc.toString());
			int a = 1;
			for(String code : availFunc)   {
				 Integer availableFunctionId = new Integer(a);	
				 availability_function.addItem(new Object[] {code},availableFunctionId);
		        a++;
			}
						
			
			
			Table availResponsibilities = new Table();
			availResponsibilities.addContainerProperty("Available_Responsibilities", String.class, null);
			availResponsibilities.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
			availResponsibilities.setDragMode(TableDragMode.ROW);
			List<String> availResp = af.getAvailableResponsibility();
			int b=1;
			for(String resCode : availResp) {
				Integer availableFunctionId = new Integer(b);	
				availResponsibilities.addItem(new Object[] {resCode},availableFunctionId);
		        b++;
			}
			
			Table availPeople = new Table();
			availPeople.addContainerProperty("People_Available", String.class, null);
			availPeople.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
			List<String> peopleAvail = af.getAvailablePeople();
			int d = 1;
			for(String name : peopleAvail) {
				Integer availablePeopleId = new Integer(d);
				availPeople.addItem(new Object[] {name}, availablePeopleId);
				d++;
			}
			
			
			allAvailable.addItem(new Object[] {availability_function,availResponsibilities,availPeople},allId);
			c++;
		}

	}

	private void buildLayout() {
		
			 HorizontalLayout mainLayout = new HorizontalLayout(functionsResp,allAvailable);
	        mainLayout.setSizeFull();
	      
	        setContent(mainLayout);
		 

	}

}
