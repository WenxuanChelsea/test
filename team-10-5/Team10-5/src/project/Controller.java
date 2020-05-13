package project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller extends Application {
		//private final boolean DEBUG = true;
        private Model model;
        private View1 view1;
        
	
	  public Controller(){
		  model = new Model();
	  }
	  
	 

    
//    public void drag(MouseEvent event) {
//		Node n = (Node)event.getSource();
//		if (DEBUG) System.out.println("ic mouse drag tx: " + n.getTranslateX() + ", ex: " + event.getX() );
//		model.setX(model.getX() + event.getX()); //event.getX() is the amount of horiz drag
//		model.setY(model.getY() + event.getY());
//		view2.setX( model.getX() );
//		view2.setY( model.getY() );
//    }
//    
//    public EventHandler getHandlerForDrag() {
//		return event -> drag((MouseEvent) event);
//	}
    
    public ArrayList<Plant> buildRecommand() {
    	ArrayList<Plant> garden = new ArrayList<>();
    	model.UpdatePlant();
    	//garden = model.SelectPlant(view1.getLight(), view1.getWater(),view1.getBloomtime(),
        //		view1.getColor());
    	garden = model.getDatabase();
    	return garden;
    }
    @Override
    /**
     * get the view of different stages and gives the user recommended
     * plants to be select based on the input for the environment
     */
        public void start(Stage theStage) {  
        view1 = new View1(theStage);      
        ArrayList<Plant> garden = model.get_garden();
        model = new Model();
        ArrayList<Plant> database = new ArrayList<>();
    	model.UpdatePlant();
    	database = model.getDatabase();
        garden = model.SelectPlant(view1.getLight(), view1.getWater(),view1.getBloomtime(),
        		view1.getColor());

        theStage.show();
    }
    
    
    public static void main(String[] args) {
    	System.out.println("running");
        launch(args);
    }
    
}

