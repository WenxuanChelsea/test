package project;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.text.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class View1 {
	int canvasWidth = 1000;
	int canvasHeight = 600;

	String light = "Sun";
	String water = "Medium";
	Season bloomtime = Season.SUMMER;
	String color = "bronze";
	String soil;

	int gardenWidth;
	int gardenLength;

	private Controller control;
	static final int imgWidthOrig = 100;
	static final int imgHeightOrig = 100;
	int imgWidth;
	int imgHeight;
	GraphicsContext gc;
	Image img;
	ArrayList<Plant> plantarr;
	ImageView iv = new ImageView();
	Season season;
	Pane pane = new Pane();
	Canvas canvas = new Canvas(canvasWidth, canvasHeight);

	/**
	 * the constructor of View1
	 * 
	 * @param theStage
	 */
	public View1(Stage theStage) {
		
		
//**********************menu page***************************
		BorderPane menu_bp = new BorderPane();
		Scene menu_scene = new Scene(menu_bp, 1000, 600);
		theStage.setScene(menu_scene);
		
		
		//set background
		BackgroundImage backgroundimage = new BackgroundImage(createImage("database/menu_background.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background menu_background = new Background(backgroundimage);
		menu_bp.setBackground(menu_background);
		 
		
		//set title
		ImageView title = new ImageView(createImage("database/title.png"));
		title.setFitWidth(700);
		title.setFitHeight(100);
		menu_bp.setTop(title);
		menu_bp.setMargin(title, new Insets(30, 0, 0, 150));
		
		
		//set buttons
		Button menu_start = new Button("start new garden");
		menu_start.setMinSize(300, 80);
		
		
		Button menu_load = new Button("load existing garden");
		menu_load.setMinSize(300, 80);
		final FileChooser fileChooser = new FileChooser();
		menu_load.setOnAction(
	
		new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(theStage);
				if (file != null) {
					openFile(file);
				}
			}
		}
		);

		FlowPane center = new FlowPane(0,50);
		center.setOrientation(Orientation.VERTICAL);
		center.getChildren().add(menu_start);
		center.getChildren().add(menu_load);
		menu_bp.setCenter(center);
		menu_bp.setMargin(center, new Insets(190, 0, 0, 330));
		

		
		//**********************input page for length and width***************************
		
		// add pane to the scene and add buttons to the pane
		// get the input in the buttons and store them

		// set button , label and textfield for the first frame
				Button start = new Button("Start");
				Button Menu = new Button("Menu");
				Label length = new Label("Length: ");
				length.setStyle("-fx-font-weight: bold");
				length.setTextFill(Color.ORANGE);
				length.setFont(new Font("Lora",18));
				TextField lengthtf = new TextField();
				Label label = new Label();

				Label width = new Label("Width: ");
				width.setStyle("-fx-font-weight: bold");
				width.setTextFill(Color.ORANGE);
				width.setFont(new Font("Lora",18));
				TextField widthtf = new TextField();
			
				// put all the info into grid pane
				GridPane grid = new GridPane();
				BorderPane lw = new BorderPane();

				//set background
				BackgroundImage backgroundlw = new BackgroundImage(createImage("database/backgroundlw.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				Background inputbackground = new Background(backgroundlw);
				lw.setBackground(inputbackground);
				//set Title
				ImageView input_title = new ImageView(createImage("database/inputTitle.png"));
				input_title.setFitWidth(700);
				input_title.setFitHeight(100);
				lw.setTop(input_title);
				lw.setMargin(input_title, new Insets(70, 0, 0, 150));
				
							
				grid.setVgap(15);
				grid.setHgap(40);
				grid.setPadding(new Insets(12, 6, 6, 12));
				grid.add(length, 1, 0);
				grid.add(lengthtf, 1, 1);
				grid.add(width, 5, 0);
				grid.add(widthtf, 5, 1);
				grid.add(start, 6, 12);
				grid.add(Menu, 0, 12);
				
				start.setMinHeight(30);
				start.setMinWidth(60);
				Menu.setMinHeight(30);
				Menu.setMinWidth(60);

				lw.setCenter(grid);
				lw.setMargin(grid, new Insets(95, 0, 0, 140));
				lengthtf.setPromptText("Enter your length in ft");
				lengthtf.setMinWidth(100);
				lengthtf.setMinHeight(30);
				widthtf.setPromptText("Enter your width in ft");
				widthtf.setMinWidth(100);
				widthtf.setMinHeight(30);
				Scene scene = new Scene(lw, 1000, 600);
			
				
				
				menu_start.setOnAction(e -> {
					theStage.setScene(scene);
				});

				
				  Menu.setOnAction(e -> { theStage.setScene(menu_scene);
				  
				  });
				 
				
		//***********************page for drag shape*****************************
				// make a tile pane with the shapes of the garden and a house picture on the
				// and a flow pane on the right

				TilePane shapes = new TilePane();
				AnchorPane gdDesign = new AnchorPane();
				BorderPane thirdPg = new BorderPane();
				//set title
				ImageView shape_title = new ImageView(createImage("database/shapeTitle.png"));
				shape_title.setFitWidth(500);
				shape_title.setFitHeight(50);
				shapes.setPrefWidth(150);
				thirdPg.setTop(shape_title);
				thirdPg.setMargin(shape_title, new Insets(30, 0, 0, 250));

				thirdPg.setLeft(shapes);
				
				thirdPg.setCenter(gdDesign);
				Scene scene1 = new Scene(thirdPg, 1000, 600);
				
				Button next = new Button("Next Page");
				Button gardensize = new Button("Garden Size");
				Label text = new Label("Choose your shape");
				text.setStyle("-fx-font-weight: bold");
				text.setTextFill(Color.ORANGE);
				text.setFont(new Font("Lora",15));
				
				shapes.getChildren().add(text);
				shapes.setMargin(text, new Insets(-200, 0, 0, 20));

				gdDesign.getChildren().add(next);
				gdDesign.getChildren().add(gardensize);
				gdDesign.setLeftAnchor(gardensize, 10d);
				gdDesign.setBottomAnchor(gardensize,10d);
				gdDesign.setRightAnchor(next, 10d);
				gdDesign.setBottomAnchor(next,10d);
				//setMargin(next, new Insets(450, 0, 0, 700));
				//gdDesign.setMargin(gardensize, new Insets(450, 0, 0, -700));
				
				GridPane gridShape = new GridPane();
				gridShape.setPadding(new Insets(1,1,1,3));
				shapes.getChildren().add(gridShape);
				shapes.setMargin(gridShape, new Insets(-210,0,0,20));
				ImageView grassCirc = new ImageView(createImage("database/grassCirc.jpg"));
				grassCirc.setFitWidth(150);
				grassCirc.setFitHeight(150);
				gridShape.add(grassCirc, 0, 0);
				
				
				
				grassCirc.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Dragboard grassCircdb = grassCirc.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putImage(grassCirc.getImage());
				grassCircdb.setContent(content);
				WritableImage wi = new WritableImage((int)(grassCirc.getFitHeight()/30),(int)(grassCirc.getFitWidth()/30));
				grassCirc.snapshot(new SnapshotParameters(), wi);
				grassCircdb.setDragView(wi);
			}	
		});
				
				gdDesign.setOnDragOver(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						event.acceptTransferModes(TransferMode.COPY);
					}	
				});
				
				gdDesign.setOnDragDropped(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						ImageView grassCircdbcopy = new ImageView(event.getDragboard().getImage());
						grassCircdbcopy.setFitWidth(150);
						grassCircdbcopy.setFitHeight(150);
						
						gdDesign.getChildren().add(grassCircdbcopy);
					}
				});
				
				
			//	shapes.setMargin(grassCirc, new Insets(-150, 0, 0, 20));
			//	shapes.getChildren().add(grassCirc);
				ImageView grassRec = new ImageView(createImage("database/grassRec.jpg"));
				grassRec.setFitWidth(150);
				grassRec.setFitHeight(150);
				gridShape.add(grassRec, 0, 1);
			//	shapes.getChildren().add(grassRec);
			//	shapes.setMargin(grassRec, new Insets(-150, 0, 0, 20));

				ImageView houseImg = new ImageView(createImage("database/house.png"));
				houseImg.setFitWidth(150);
				houseImg.setFitHeight(150);
				gridShape.add(houseImg, 0, 2);
			//	shapes.getChildren().add(houseImg);
			//	shapes.setMargin(houseImg, new Insets(-20, 0, 0, 20));
				
				
		
				
				

				gardensize.setOnAction(e -> {
					theStage.setScene(scene);

				});
				
				
				
		//**************************page for plant characteristic *******************************
				// set dropbox for light, color, soil, water, bloomtime, bloomfood, and a
				// confirm box to start the next frame
				
				Button confirm = new Button("Confirm");
				Button back = new Button("Back");
				confirm.setMinWidth(100);
				confirm.setMinHeight(30);
				back.setMinWidth(100);
				back.setMinHeight(30);
				
				Label label2 = new Label();
				Label Light = new Label("Light: ");
				Light.setStyle("-fx-font-weight: bold");
				Light.setTextFill(Color.ORANGE);
				Light.setFont(new Font("Lora",18));
				
				Label Water = new Label("Water Use: ");
				Water.setStyle("-fx-font-weight: bold");
				Water.setFont(new Font("Lora",18));
				Water.setTextFill(Color.ORANGE);
			
				Label BloomTime = new Label("Bloom Time: ");
				BloomTime.setStyle("-fx-font-weight: bold");
				BloomTime.setFont(new Font("Verdana",18));
				BloomTime.setTextFill(Color.ORANGE);
				
		
				
				Label plantColor = new Label("Color: ");
				plantColor.setStyle("-fx-font-weight: bold");
				plantColor.setFont(new Font("Lora",18));
				plantColor.setTextFill(Color.ORANGE);
		
				
				Label Soil = new Label("Soil: ");
				Soil.setStyle("-fx-font-weight: bold");
				Soil.setFont(new Font("Lora",18));
				Soil.setTextFill(Color.ORANGE);
			
				
				
				ComboBox Lightcb = new ComboBox();
				Lightcb.setMinWidth(100);
				Lightcb.setMinHeight(30);
				Lightcb.getItems().addAll(
						"Sun", 
						"Sun-shade ",
						"Sun-partial shade",
						"Partial sun",
						"Shade", 
						"Partial shade");

				final ComboBox Watercb = new ComboBox();
				Watercb.setMinWidth(100);
				Watercb.setMinHeight(30);
				Watercb.getItems().addAll("Wet",
						"Moist",
						"Moist-Dry",
						"Wet-Medium",
						"Medium",
						"Medium-Wet",
						"Medium-moist",
						"Dry-wet",
						"Dry-moist",
						"Dry");
				
				
				final ComboBox BloomTimecb = new ComboBox();
				BloomTimecb.setMinWidth(100);
				BloomTimecb.setMinHeight(30);
				BloomTimecb.getItems().addAll(Season.SPRING,
						Season.SUMMER,
						Season.FALL, 
						Season.WINTER);
				
				
				final ComboBox Colorcb = new ComboBox();
				Colorcb.setMinWidth(100);
				Colorcb.setMinHeight(30);
				Colorcb.getItems().addAll(
						"bronze",
						"golden", 
						"red",
						"red twig",
						"yellow", 
						"white",
						"pink",
						"orange", 
						"magenta",
						"mauve",
						"purple",
						"green");
				
				
				final ComboBox Soilcb = new ComboBox();
				Soilcb.setMinWidth(100);
				Soilcb.setMinHeight(30);
				Soilcb.getItems().addAll("Sand", "Silt", "Clay");

				// make grid pane to add all the label , combobox and button in grid pane
				GridPane grid2 = new GridPane();
				BorderPane plantOption = new BorderPane();
				
				//set Title
				ImageView charac_title = new ImageView(createImage("database/choosePlant.png"));
				charac_title.setFitWidth(700);
				charac_title.setFitHeight(100);
				plantOption.setTop(charac_title);
				plantOption.setMargin(charac_title, new Insets(70, 0, 0, 150));
				
				//set background
				BackgroundImage charac = new BackgroundImage(createImage("database/charactertistic.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				Background characBackground = new Background(charac);
				plantOption.setBackground(characBackground);
				
				
				plantOption.setCenter(grid2);
				plantOption.setMargin(grid2, new Insets(60, 0, 0, 100));
				grid2.setVgap(10);
				grid2.setHgap(20);
				grid2.setPadding(new Insets(12, 5, 5, 12));
				grid2.add(Light, 4, 0);
				grid2.add(Lightcb, 4, 1);
				grid2.add(Water, 4, 2);
				grid2.add(Watercb, 4, 3);
				grid2.add(BloomTime, 6, 0);
				grid2.add(BloomTimecb, 6, 1);
				grid2.add(plantColor, 5, 0);
				grid2.add(Colorcb, 5, 1);
				grid2.add(Soil, 5, 2);
				grid2.add(Soilcb, 5, 3);
				grid2.add(confirm, 10, 12);
				grid2.add(back, 0, 12);
				Scene scene2 = new Scene(plantOption, 1000, 600);
			

				next.setOnAction(e -> {
					theStage.setScene(scene2);

				});

				back.setOnAction(e -> {
					theStage.setScene(scene1);

				});

				// when hit on start buttom, if the length or width textfield are empty, add a
				// label and
				// ask user to get enter input. Otherwise move to next scene

				start.setOnAction(new EventHandler<ActionEvent>() {
					@Override

					public void handle(ActionEvent e) {

						if (lengthtf.getText().toString().equals("") || widthtf.getText().toString().equals("")) {
							label2.setText("PLEASE ENTER YOUR GARDEN LENGTH AND WIDTH");
							label2.setFont(new Font("Cambria", 30));
							label2.setTextFill(Color.RED);
							GridPane gridLabel2 = new GridPane();
							gridLabel2.setPadding(new Insets(3,3,3,3));
							gridLabel2.add(label2, 0,2);
							lw.setBottom(gridLabel2);
							lw.setMargin(gridLabel2, new Insets(10, 0, 0, 150));
							

						} else {
							String sl = lengthtf.getText().toString();
							gardenLength = Integer.parseInt(sl);

							String sw = widthtf.getText().toString();
							gardenWidth = Integer.parseInt(sw);

							theStage.setScene(scene1);

						}

					}
				});
						
		//**************************************************************************************

		control = new Controller();
//		Group root3 = new Group();
//		Scene scene3 = new Scene(root3, 1000, 600);
//		Group root4 = new Group();
//		Scene scene4 = new Scene(root4, 1000, 600);
//
//		confirm.setOnAction(e -> {
//			theStage.setScene(drag_scene);
//		});
//
//		theStage.setTitle("second page");
//		ScrollPane leftpane = updateleftpane(control.buildRecommand());
//		leftpane.setPrefWidth(124);
//		Button Spring = new Button("Spring");
//		Button Summer = new Button("Summer");
//		Button Fall = new Button("Fall");
//		Button Winter = new Button("Winter");
//
//		EventHandler<ActionEvent> Springevent = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				season = Season.SPRING;
//				updateSeason(plantarr, season, scene3);
//			}
//		};
//		EventHandler<ActionEvent> Summerevent = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				season = Season.SUMMER;
//				updateSeason(plantarr, season, scene3);
//			}
//		};
//		EventHandler<ActionEvent> Fallevent = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				season = Season.FALL;
//				updateSeason(plantarr, season, scene3);
//			}
//		};
//		EventHandler<ActionEvent> Winterevent = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				season = Season.WINTER;
//				updateSeason(plantarr, season, scene3);
//			}
//		};
//
//		Spring.setOnAction(Springevent);
//		Summer.setOnAction(Summerevent);
//		Fall.setOnAction(Fallevent);
//		Winter.setOnAction(Winterevent);
//
//		VBox vbox = new VBox(Spring, Summer, Fall, Winter);
//		vbox.setAlignment(Pos.TOP_LEFT);
//
//		Button next2 = new Button("next");
//		next2.setOnAction(e -> {
//			theStage.setScene(scene4);
//		});
//
//		BorderPane bp = new BorderPane();
//		
//		ImageView background = new ImageView(createImage("database/background.jpg") );
//		bp.setLeft(leftpane);
//		bp.setBottom(next2);
//		bp.setRight(background);
//		root3.getChildren().add(bp);
//		BorderPane bp2 = new BorderPane();
//		bp2.setCenter(pane);
//		bp2.setLeft(vbox);
//		root4.getChildren().add(bp2);
		
		
//*******************drag and drop page*********************
		theStage.setTitle("Garden Planner");
		BorderPane drag_bp = new BorderPane();
		Scene drag_scene = new Scene(drag_bp, 1000, 600);
		
		confirm.setOnAction(e -> {
			theStage.setScene(drag_scene);
		});
		
		
		//set left
		ScrollPane leftpane = updateleftpane(control.buildRecommand());
		leftpane.setPrefWidth(164);
		drag_bp.setLeft(leftpane);
		
		
		//set right
		Button Spring = new Button("Spring");
		Button Summer = new Button("Summer");
		Button Fall = new Button("Fall");
		Button Winter = new Button("Winter");

		EventHandler<ActionEvent> Springevent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				season = Season.SPRING;
				updateSeason(plantarr, season, drag_scene);
			}
		};
		EventHandler<ActionEvent> Summerevent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				season = Season.SUMMER;
				updateSeason(plantarr, season, drag_scene);
			}
		};
		EventHandler<ActionEvent> Fallevent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				season = Season.FALL;
				updateSeason(plantarr, season, drag_scene);
			}
		};
		EventHandler<ActionEvent> Winterevent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				season = Season.WINTER;
				updateSeason(plantarr, season, drag_scene);
			}
		};
		
		Spring.setOnAction(Springevent);
		Summer.setOnAction(Summerevent);
		Fall.setOnAction(Fallevent);
		Winter.setOnAction(Winterevent);

		VBox vbox = new VBox(Spring, Summer, Fall, Winter);
		drag_bp.setRight(vbox);
		
		
		//set center 
		ImageView background = new ImageView(createImage("database/background.jpg"));
		drag_bp.setCenter(background);
		
		
		//set bottom
		Button drag_next = new Button("next");
		drag_next.setMinSize(100, 30);
		
		Button drag_back = new Button("back");
		drag_back.setMinSize(100, 30);
		drag_back.setOnAction(e -> {
			theStage.setScene(scene2);
		});
		FlowPane drag_bottom = new FlowPane(550,0);
		drag_bottom.getChildren().add(drag_back);
		drag_bottom.getChildren().add(drag_next);
		drag_bp.setBottom(drag_bottom);
		drag_bp.setMargin(drag_bottom, new Insets(20, 20, 20, 130));
		
		
		
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent e) {

				if (Lightcb.getValue() == null || Watercb.getValue() == null || Colorcb.getValue() == null
						|| Soilcb.getValue() == null || BloomTimecb.getValue() == null) {
					label.setText("PLEASE CHOOSE THE LIGHT, WATER USE, BLOOM TIME, COLOR AND SOIL");
					label.setFont(new Font("Cambria", 12));
					label.setTextFill(Color.RED);

					plantOption.setBottom(label);
					plantOption.setMargin(label, new Insets(50, 0, 0, 350));
				//	root2.getChildren().add(root2);

				} else {
					light = (String) Lightcb.getValue();
					water = (String) Watercb.getValue();
					bloomtime = (Season) BloomTimecb.getValue();
					color = (String) Colorcb.getValue();
					soil = (String) Soilcb.getValue();

					theStage.setScene(drag_scene);
				}

			}
		});
		

//***************************rating page*********************************
		BorderPane rate_bp = new BorderPane();
		rate_bp.setPrefWidth(1000);
		rate_bp.setPrefHeight(600);
		rate_bp.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #e6e6e6, #661a33)");
		

		//set background color
		final ColorPicker picker = new ColorPicker();
		picker.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Color value = picker.getValue();
				String colorString = value.toString();
				String substring = colorString.substring(2, colorString.length() - 2);
				rate_bp.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #" + substring
						+ ", #661a33)");
			}
		});
		VBox vBox = new VBox();
		vBox.getChildren().add(rate_bp);
		vBox.getChildren().add(picker);
		Scene rate_scene = new Scene(vBox);
		

		// set title
		ImageView rate_title = new ImageView(createImage("database/rate_title.png"));
		rate_title.setFitWidth(700);
		rate_title.setFitHeight(100);
		rate_bp.setTop(rate_title);
		rate_bp.setMargin(rate_title, new Insets(30, 0, 0, 150));

		
		// set buttons
		Button back_button = new Button("Back");
		Button menu_button = new Button("Menu");
		Button save_button = new Button("Save");

		back_button.setMinSize(100, 50);
		menu_button.setMinSize(100, 50);
		save_button.setMinSize(100, 50);

		back_button.setOnAction(e -> {
			theStage.setScene(drag_scene);
		});
		menu_button.setOnAction(e -> {
			theStage.setScene(menu_scene);
		});
		String saved = "test file";
		Text sample = new Text(saved);
		save_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				// Show save file dialog
				File file = fileChooser.showSaveDialog(theStage);

				if (file != null) {
					saveTextToFile(saved, file);
				}
			}
		});
		
		FlowPane rate_bottom = new FlowPane(100, 0);
		rate_bottom.setOrientation(Orientation.HORIZONTAL);
		rate_bottom.getChildren().add(back_button);
		rate_bottom.getChildren().add(menu_button);
		rate_bottom.getChildren().add(save_button);
		rate_bp.setBottom(rate_bottom);
		rate_bp.setMargin(rate_bottom, new Insets(0, 20, 20, 250));
		
		drag_next.setOnAction(e -> {
			theStage.setScene(rate_scene);
		});
		
		
		//set center
		String[] list1 = { "condition1", "condition2", "condition3", "Total" };
		FlowPane rate_center = new FlowPane(0, 50);
		rate_center.setOrientation(Orientation.VERTICAL);
		rate_bp.setCenter(rate_center);
		rate_bp.setMargin(rate_center, new Insets(80, 0, 0, 300));
		for (int i = 0; i < list1.length; i++) {
			FlowPane fp = new FlowPane(20, 0);
			Label label1 = new Label(list1[i]);
			CheckBox checkBox1 = new CheckBox("Perfect");
			CheckBox checkBox2 = new CheckBox("Good");
			CheckBox checkBox3 = new CheckBox("Fair");
			CheckBox checkBox4 = new CheckBox("Poor");
			fp.getChildren().add(label1);
			fp.getChildren().add(checkBox1);
			fp.getChildren().add(checkBox2);
			fp.getChildren().add(checkBox3);
			fp.getChildren().add(checkBox4);
			rate_center.getChildren().add(fp);
		}
		
	}

	/**
	 * get the data for light from the constructor
	 * 
	 * @return light
	 */
	public String getLight() {
		return light;
	}

	/**
	 * get the data for water from the constructor
	 * 
	 * @return water
	 */
	public String getWater() {
		return water;

	}

	/**
	 * get the data for bloomtime from the constructor
	 * 
	 * @return bloomtime;
	 */
	public Season getBloomtime() {
		return bloomtime;
	}

	/**
	 * get the data for color from the constructor
	 * 
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * get the data for soil from the constructor
	 * 
	 * @return soil
	 */
	public String getSoil() {
		return soil;
	}

	/**
	 * get the data for gardenWidth from the constructor
	 * 
	 * @return gardenWidth
	 */
	public int getGardenWidth() {
		return gardenWidth;
	}

	/**
	 * get the data for gardenLength from the constructor
	 * 
	 * @return
	 */

	public int getGardenLength() {
		return gardenLength;
	}

	public Season getSeason() {
		return season;
	}

	/**
	 * import image based on createImage() method
	 */
	private ArrayList<Image> importImages(ArrayList<Plant> recommand) {
		ArrayList<Image> answer = new ArrayList<Image>();
		for (Plant plant : recommand) {
			Image img = createImage("database/" + plant.getName() + ".jpg");
			answer.add(img);
		}
		return answer;
	}

	private ArrayList<Image> updateImage(ArrayList<Plant> recommand, Season season) {
		ArrayList<Image> answer = new ArrayList<Image>();
		for (Plant plant : recommand) {
			Image img = createImage("database/" + plant.getName() + "-" + season.getSeason() + ".jpg");
			answer.add(img);
		}
		return answer;
	}
	
	private void set_att(int gardenWidth,int gardenLength,String light,String water, String bloomtime, String color){
		this.gardenWidth = gardenWidth;
		this.gardenLength = gardenLength;
		this.light = light;
		this.water = water;
		this.bloomtime = Season.valueOf(bloomtime);
		this.color = color;
	}

	/**
	 * get the image from file based on name
	 * 
	 * @param image_file the name of the image in the same style for all images
	 * @return image
	 */
	private Image createImage(String image_file) {
		Image img = new Image(image_file);
		return img;
	}

	public ScrollPane updateleftpane(ArrayList<Plant> selected_plants) {
		ArrayList<Image> imgs = importImages(selected_plants);
		ScrollPane leftpane = new ScrollPane();
		leftpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		FlowPane content = new FlowPane(20,20);
		content.setOrientation(Orientation.VERTICAL);
		content.setMinHeight(imgs.size()*190);
		
		for (int i = 0; i < imgs.size(); i++) {
			ImageView eachimg = new ImageView();
			eachimg.setImage(imgs.get(i));
			eachimg.setFitHeight(100);
			eachimg.setFitWidth(100);
			content.getChildren().add(eachimg); // add image to flow pane
			content.setMargin(eachimg, new Insets(10, 0, 0, 20));

			Label label = new Label(selected_plants.get(i).name);// add plant name to flow pane
			content.getChildren().add(label);
			content.setMargin(label, new Insets(0, 0, 20, 20));
			
			eachimg.setOnDragDetected(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Dragboard iv1db = eachimg.startDragAndDrop(TransferMode.COPY);
					ClipboardContent content = new ClipboardContent();
					content.putImage(eachimg.getImage());
					iv1db.setContent(content);
					// WritableImage wi = new
					// WritableImage((int)(iv1.getFitHeight()/30),(int)(iv1.getFitWidth()/30));
					WritableImage wi = new WritableImage(imgWidthOrig, imgHeightOrig);
					eachimg.snapshot(new SnapshotParameters(), wi);
					iv1db.setDragView(wi);
				}
			});
			pane.setOnDragOver(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					event.acceptTransferModes(TransferMode.COPY);
				}
			});
			pane.setOnDragDropped(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					ImageView iv1copy = new ImageView(event.getDragboard().getImage());
					iv1copy.setPreserveRatio(true);
					iv1copy.setFitHeight(imgHeightOrig);
					iv1copy.setFitWidth(imgWidthOrig);
					pane.getChildren().add(iv1copy);
				}
			});

		}

		leftpane.setContent(content);
		return leftpane;
	}

	private void updateSeason(ArrayList<Plant> recommand, Season season, Scene scene) {

		pane.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		/* Image[] imgs = (Image[]) updateImage(recommand, season).toArray(); */
		for (Plant plant : recommand) {
			Image img = createImage("database/" + plant.getName() + "_" + season.getSeason() + ".jpg");
			gc.clearRect(0, 0, canvasWidth, canvasHeight);
			Node movenode = scene.lookup(plant.getName());
			gc.drawImage(img, movenode.getScaleX(), movenode.getScaleY(), canvasWidth, canvasHeight);
		}
	}

	private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(View1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	private void openFile(File file) {
//		try {
//			desktop.open(file);
//		} catch (IOException ex) {
//			Logger.getLogger(View1.class.getName()).log(Level.SEVERE, null, ex);
//		}
	}
		
}
//test merge
