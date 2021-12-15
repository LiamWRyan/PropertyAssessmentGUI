import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.FileNotFoundException;
import java.util.List;
import java.text.NumberFormat;

/* class PropertyAssessmentGUIMain
 *
 * Purpose: create a GUI that displays property assessments and allows the user to do various searchers
 *          through the data, as well as choose the data source to populate the table view with.
 *
 * Caveat: this is labs 6-8
 */
public class PropertyAssessmentGUIMain extends Application {

    private final int WIDTH = 1500;
    private final int HEIGHT = 600;

    private TableView<PropertyAssessment> table;
    private ObservableList<PropertyAssessment> propertyAssessmentObservableList;

    private ComboBox<String> dataSource;
    private ComboBox<String> assessmentClass;
    private TextField accountNumber;
    private TextField address;
    private TextField neighbourhood;
    private TextField minValue;
    private TextField maxValue;

    private final String[] dataSourceList = {"CSV File", "Edmonton's Open Data Portal"};
    private String[] assessmentClassList = {};
    public ApiPropertyAssessmentDAO apiPaDao;
    public CsvPropertyAssessmentDAO csvPaDao;



    public PropertyAssessmentGUIMain() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Edmonton Property Assessments");

        primaryStage.setScene(configureScene());

        primaryStage.show();

    }

    /* Method: configureScene
     *
     * Purpose: uses helpers to configure a scene.
     *
     * Parameters: void
     *
     * Return: new Scene
     *      the primary scene for the GUI which consists of the table and controls.
     */
    private Scene configureScene(){

        HBox hBoxOfTableAndGridPane = new HBox(5);

        GridPane buttonsAndTextFields = configureGridPane();
        configureTable();

        hBoxOfTableAndGridPane.getChildren().addAll(buttonsAndTextFields, table);

        return new Scene(hBoxOfTableAndGridPane, WIDTH,HEIGHT);

    }

    /* Method: configureGridPane
     *
     * Purpose: Create a GridPane to hold all the controls and set on action events for each button that is in the
     *          gridPane.
     *
     * Parameters: void
     *
     * Return: GridPane gridPane
     *      a GridPane that contains all the controls.
     */
    private GridPane configureGridPane(){

        // create buttons.
        Button readData = new Button("Read Data");
        readData.setPrefWidth(270);
        Button search = new Button("Search");
        search.setPrefWidth(135);
        Button reset = new Button("Reset");
        reset.setPrefWidth(135);

        instantiateTextFields();

        // new GridPane and its options
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setVgap(6);
        gridPane.setPadding((new Insets(10,10,10,10)));
        gridPane.setPrefWidth(270);

        // Second GridPane for buttons and textFields at the bottom that span across more than
        // one column.
        GridPane gridPane1 = new GridPane();
        gridPane1.setVgap(6);
        gridPane1.setHgap(15);

        // Create labels that require bolding.
        final Label boldLabel1 = new Label("Find Property Assessment");
        boldLabel1.setFont(Font.font("Arial",  FontWeight.BOLD,16));
        final Label boldLabel2 = new Label("Select Data Source");
        boldLabel2.setFont(Font.font("Arial",  FontWeight.BOLD,16));

        gridPane.add(boldLabel2, 0,0);
        gridPane.add(dataSource, 0,1);
        gridPane.add(readData,0,2);

        gridPane.add(boldLabel1,0,3);

        gridPane.add(new Label("Account Number:"),0,4);
        gridPane.add(accountNumber,0,5);

        gridPane.add(new Label("Address (#suite #house street):"),0,6);
        gridPane.add(address,0,7);

        gridPane.add(new Label("Neighbourhood:"),0,8);
        gridPane.add(neighbourhood, 0,9);

        gridPane.add(new Label("Assessment Class:"), 0,10);
        gridPane.add(assessmentClass,0,11);

        gridPane.add(new Label("Assessed Value Range:"),0,12);
        gridPane1.add(minValue,0,0);
        gridPane1.add(maxValue,1,0);

        gridPane1.add(search,0,1);
        gridPane1.add(reset,1,1);

        gridPane.add(gridPane1,0,13);

        // ----------------------------------------------------------------
        // Set an on action event for when the readData button is pushed.
        readData.setOnAction(event -> {
            String chosenDataSource = dataSource.getValue();
            // The user must select a data source. If they do not we create an alert.
            if (chosenDataSource.equals("")){
                createAlert("Please Select A Data Source");
            }
            // If the chosen data source is the CSV we grab the first 1000 elements from the CSV
            if (chosenDataSource.equals("CSV File")) {
                // if the csvPaDao is null, instantiate it and then perform the logic.
                if (csvPaDao == null) {
                    try {
                        csvPaDao = new CsvPropertyAssessmentDAO("Property_Assessment_Data_2021.csv");
                        propertyAssessmentObservableList.clear();
                        propertyAssessmentObservableList.addAll(csvPaDao.getFirst1000PropertyAssessments());
                        // update the assessment classes dynamically based on what data is loaded in.
                        assessmentClass.setItems((ObservableList<String>) csvPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else{ // the user has already read the csv file data.
                    propertyAssessmentObservableList.clear();
                    propertyAssessmentObservableList.addAll(csvPaDao.getFirst1000PropertyAssessments());
                    // update the assessment classes dynamically based on what data is loaded in.
                    assessmentClass.setItems((ObservableList<String>) csvPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                }
            }
            // If the chosen data source is Edmonton's open data portal.
            if (chosenDataSource.equals("Edmonton's Open Data Portal")){
                // If the apiPaDao is null, instantiate it, start a thread to cache data and then perform logic.
                if (apiPaDao == null) {
                    apiPaDao = new ApiPropertyAssessmentDAO();
                    Thread initialThread = new Thread(apiPaDao);
                    // the thread will throw when its completed, then we know the data is done loading. This will create
                    // a non-blocking UI.
                    initialThread.setUncaughtExceptionHandler((t, e) -> {
                        propertyAssessmentObservableList.clear();
                        propertyAssessmentObservableList.addAll(apiPaDao.getFirst1000PropertyAssessments());
                        // update the assessment classes dynamically based on what data is loaded in. On the initial
                        // run the assessment class combo box will only have loaded in the assessment classes that
                        // belong to the first 1000 property assessments in the data.
                        assessmentClass.setItems((ObservableList<String>) apiPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                        Thread secondThread = new Thread(apiPaDao);
                        // the thread will throw when its completed, then we know the data is done loading. This will create
                        // a non-blocking UI.
                        secondThread.setUncaughtExceptionHandler((t1, e1) -> {
                            // If the user decides to switch to the csv file while the second thread is still running we
                            // could update the assessment class combo box with the wrong data. For example, if the online
                            // data source gets a new assessment class that is not in the csv file it could appear in the
                            // combo box and then a user could try and search by it and cause a crash. So, it is important to
                            // check that they are still on the same data source before re-updating the assessment classes.
                            if (dataSource.getValue().equals("Edmonton's Open Data Portal")) {
                                assessmentClass.setItems((ObservableList<String>) apiPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                            }
                        });
                        // start the second thread.
                        secondThread.start();

                    });
                    // start the initial thread.
                    initialThread.start();

                }else{
                    propertyAssessmentObservableList.clear();
                    propertyAssessmentObservableList.addAll(apiPaDao.getFirst1000PropertyAssessments());
                    // update the assessment classes dynamically based on what data is loaded in.
                    assessmentClass.setItems((ObservableList<String>) apiPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                }
            }
        });

        // Set on action event for when search button is pressed.
        search.setOnAction(event -> {

            String providedAccountNumber = accountNumber.getText();
            String providedAddress = address.getText();
            String providedNeighbourhood = neighbourhood.getText();
            String providedMinValue = minValue.getText();
            String providedMaxValue = maxValue.getText();
            String chosenDataSource = dataSource.getValue();
            String chosenAssessmentClass = assessmentClass.getValue();
            List<PropertyAssessment> propertyAssessments;
            int parsedMax;
            int parsedMin;

            // assess the values of max and min, if a max is not provided, set it to the largest possible int -1
            // else set it to the provided value.
            if (providedMaxValue.equals("")){
                parsedMax = 2147483646;
            }else{
                // its possible that user may try to enter a non integer like value which will cause a number format
                // exception.
                try {
                    parsedMax = Integer.parseInt(providedMaxValue);
                }catch(NumberFormatException e){
                    createAlert("Inappropriate Integer Provided");
                    parsedMax = 2147483646;
                }
            }
            // if a min is not provided set it to 0, else set it to the provided min.
            if (providedMinValue.equals("")){
                parsedMin = 0;
            }else{
                // its possible that user may try to enter a non integer like value which will cause a number format
                // exception.
                try {
                    parsedMin = Integer.parseInt(providedMinValue);
                }catch(NumberFormatException e){
                    createAlert("Inappropriate Integer Provided");
                    parsedMin = 0;
                }
            }

            // if the data source is not chosen, alert the user.
            if (chosenDataSource.equals("")){
                createAlert("Please Select a Data source");
            }
            // if the data source is Edmonton's Open Data Portal
            if (chosenDataSource.equals("Edmonton's Open Data Portal")) {
                // The user tries to search without reading the data first, create alert.
                if (apiPaDao == null){
                    createAlert("Must Read From Data Source First");
                }else {
                    propertyAssessments = apiPaDao.queryResults(providedAccountNumber, providedAddress, providedNeighbourhood, parsedMin, parsedMax, chosenAssessmentClass);
                    // no results are found create alert
                    if (propertyAssessments == null) {
                        createAlert("No Property Assessments Found");
                    } else {
                        propertyAssessmentObservableList.clear();
                        propertyAssessmentObservableList.addAll(propertyAssessments);
                    }
                }

                // if the chosen data source is the CSV file.
            } else if (chosenDataSource.equals("CSV File")){
                // The user tries to search without reading the data first, create alert.
                if (csvPaDao == null){
                    createAlert("Must Read From Data Source First");
                }else{
                    propertyAssessments = csvPaDao.queryResults(providedAccountNumber, providedAddress, providedNeighbourhood, parsedMin, parsedMax, chosenAssessmentClass);
                    // no results are found create alert
                    if (propertyAssessments == null) {
                        createAlert("No Property Assessments Found");
                    } else {
                        propertyAssessmentObservableList.clear();
                        propertyAssessmentObservableList.addAll(propertyAssessments);
                    }
                }
            }
        });


        // Set on action event for when the reset button is pressed.
        reset.setOnAction(event -> {
            // clear all the textField values.
            accountNumber.setText("");
            address.setText("");
            neighbourhood.setText("");
            minValue.setText("");
            maxValue.setText("");
            assessmentClass.setValue("");
            // determine which data source was selected.
            String chosenDataSource = dataSource.getValue();
            // there needs to be a data source.
            if (chosenDataSource.equals("")){
                createAlert("Please Select A Data Source");
            }
            // If the chosen data source is the csv file, re fill the table with that data with no constraints.
            if (chosenDataSource.equals("CSV File")) {
                if (csvPaDao == null) {
                    createAlert("Please Read From The Data Source Before Resetting");
                } else{
                    propertyAssessmentObservableList.clear();
                    propertyAssessmentObservableList.addAll(csvPaDao.getFirst1000PropertyAssessments());
                    // update the assessment classes dynamically based on what data is loaded in.
                    assessmentClass.setItems((ObservableList<String>) csvPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                }
            }

            // If the chosen data source is Edmonton's open data portal. re fill the table with that data with no
            // constraints.
            if (chosenDataSource.equals("Edmonton's Open Data Portal")){
               if (apiPaDao == null) {
                   createAlert("Please Read From The Data Source Before Resetting");
            }else{
                    propertyAssessmentObservableList.clear();
                    propertyAssessmentObservableList.addAll(apiPaDao.getFirst1000PropertyAssessments());
                    // update the assessment classes dynamically based on what data is loaded in.
                    assessmentClass.setItems((ObservableList<String>) apiPaDao.determineAssessmentClasses(FXCollections.observableArrayList(assessmentClassList)));
                }
            }
        });

        return gridPane;
    }

    /* Method: instantiateTextFields
     *
     * Purpose: create the textFields and comboBox's and set their sizes.
     *
     * Parameters: void
     *
     * Return: void
     */
    private void instantiateTextFields(){

        dataSource = new ComboBox<>(FXCollections.observableArrayList(dataSourceList));
        dataSource.setPrefWidth(270);
        dataSource.setValue("");
        //------------------------------
        assessmentClass = new ComboBox<>(FXCollections.observableArrayList(assessmentClassList));
        assessmentClass.setPrefWidth(270);
        assessmentClass.setValue("");
        //------------------------------
        accountNumber = new TextField();
        accountNumber.setPrefWidth(270);
        //------------------------------
        address = new TextField();
        address.setPrefWidth(270);
        //------------------------------
        neighbourhood = new TextField();
        neighbourhood.setPrefWidth(270);
        //------------------------------
        minValue = new TextField();
        minValue.setPrefWidth(135);
        maxValue = new TextField();
        maxValue.setPrefWidth(135);

    }

    /* Method: configureTable
     *
     * Purpose: configure the table columns
     *
     * Parameters: void
     *
     * Return: void
     */
    private void configureTable(){

        table = new TableView<>();
        table.setPadding((new Insets(10,10,10,10)));
        table.setPrefWidth(1220);
        propertyAssessmentObservableList = FXCollections.observableArrayList();
        table.setItems(propertyAssessmentObservableList);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        TableColumn<PropertyAssessment, String> accountNumberCol = new TableColumn<>("Account");
        accountNumberCol.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
        accountNumberCol.prefWidthProperty().bind(table.widthProperty().multiply(0.07));
        table.getColumns().add(accountNumberCol);


        // Set the address column (consists of suite number + street name + house number)
        TableColumn<PropertyAssessment, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PropertyAssessment, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PropertyAssessment, String> propertyAssessment) {
                return new ReadOnlyObjectWrapper(propertyAssessment.getValue().buildAddress());
            }
        });
        addressCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        table.getColumns().add(addressCol);

        // Set the assessed value column
        TableColumn<PropertyAssessment, String> assessedValueCol = new TableColumn<>("Assessed Value");
        assessedValueCol.setCellValueFactory(new PropertyValueFactory<>("assessedValue"));
        assessedValueCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        assessedValueCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty ? "" : currencyFormat.format(Integer.parseInt(value)));
            }
        });

        table.getColumns().add(assessedValueCol);

        // Set the assessment class column (consists of all assessment classes and their respective %)
        TableColumn<PropertyAssessment, String> assessmentClassCol = new TableColumn<>("Assessment Class");
        assessmentClassCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PropertyAssessment, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PropertyAssessment, String> propertyAssessment) {
                return new ReadOnlyObjectWrapper(propertyAssessment.getValue().buildAssessmentClass());
            }
        });
        assessmentClassCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        table.getColumns().add(assessmentClassCol);


        // Set the neighbourhood column (consists of neighbour hood and ward)
        TableColumn<PropertyAssessment, String> neighbourhoodCol = new TableColumn<>("Neighbourhood");
        neighbourhoodCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PropertyAssessment, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PropertyAssessment, String> propertyAssessment) {
                return new ReadOnlyObjectWrapper(propertyAssessment.getValue().buildNeighbourhoodAndWard());
            }
        });
        neighbourhoodCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        table.getColumns().add(neighbourhoodCol);


        // Set the latitude and longitude column (consists of latitude followed by longitude)
        TableColumn<PropertyAssessment, String> latlongCol = new TableColumn<>("(Latitude, Longitude)");
        latlongCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PropertyAssessment, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PropertyAssessment, String> propertyAssessment) {
                return new ReadOnlyObjectWrapper(propertyAssessment.getValue().buildLatLong());
            }
        });
        latlongCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        table.getColumns().add(latlongCol);

    }

    /* Method: createAlert
     *
     * Purpose: create an alert with some supplied context.
     *
     * Parameters: String alertInformation
     *           information about the alert that we want to display to the user.
     *
     * Return: void
     */
    private void createAlert(String alertInformation){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        // get rid of header.
        alert.setHeaderText(null);
        alert.setContentText(alertInformation);
        alert.showAndWait();
    }
}
