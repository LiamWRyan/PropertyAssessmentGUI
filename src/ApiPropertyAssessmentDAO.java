import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/* class ApiPropertyAssessmentDAO
 *
 * Purpose: a data access object for accessing and storing property assessments through Edmonton's open data portal.
 *          This class implements the PropertyAssessmentDAO.
 *
 */
public class ApiPropertyAssessmentDAO implements PropertyAssessmentDAO, Runnable{

    String endpoint = "https://data.edmonton.ca/resource/q7d6-ambg.csv?";
    String query = "$limit=1000&$offset=0&$order=account_number";
    String query2 = "$limit=9999999&$offset=0&$order=account_number";
    String[] splitDataTemp;
    String[] splitData;
    String responseCleaned;
    String temp;
    String cachedData; // raw string

    public PropertyAssessments cachedPropertyAssessments = new PropertyAssessments(); // cleaned

    @Override
    public void run(){
        String propertyAssessmentsString;
        boolean hasFirst1000 = (cachedData != null && cachedPropertyAssessments.PropertyAssessmentList.size() < 10000);
        // if the first 1000 has been queried, begin the the next query
        if (hasFirst1000){
            propertyAssessmentsString = beginQuery(endpoint, query2);
        }else{
            propertyAssessmentsString = beginQuery(endpoint, query);
        }

        if (propertyAssessmentsString!=null){
            this.cachedData = propertyAssessmentsString;
            parseIntoPropertyAssessments(cleanString(cachedData));
        }

        // no call back for when thread was completed and putting a throw here caused other issues, so we divide
        // by zero, and then catch with the uncaught exception handler callback placed on the thread in the gui.

        Integer c = 1 / 0;
    }

    public ApiPropertyAssessmentDAO(){

    }

    /* Method: parseIntoPropertyAssessments
     *
     * Purpose: parse the cleaned data into property assessments and store them.
     *
     * Parameters: String[] cleanedString
     *             a string of property assessment data that has been cleaned.
     *
     * Return: void
     */
    public void parseIntoPropertyAssessments(String[] cleanedString) {
        for (int i = 0; i < cleanedString.length; i++){

            this.temp = cleanedString[i];
            this.splitData = temp.split(",");
            if (!this.splitData[8].equals("Assessed Value")) {
                PropertyAssessment assessment = new PropertyAssessment();
                assessment.setAccountNum(this.splitData[Index.ACCOUNTNUMBER.getI()]);
                assessment.setSuite(this.splitData[Index.SUITE.getI()]);
                assessment.setHouseNumber(this.splitData[Index.HOUSENUMBER.getI()]);
                assessment.setStreetName(this.splitData[Index.STREETNAME.getI()]);
                assessment.setGarage(this.splitData[Index.GARAGE.getI()]);
                assessment.setNeighbourhoodID(this.splitData[Index.NEIGHBOURHOODID.getI()]);
                assessment.setNeighbourhood(this.splitData[Index.NEIGHBOURHOOD.getI()]);
                assessment.setWard(this.splitData[Index.WARD.getI()]);
                assessment.setAssessedValue(this.splitData[Index.ASSESSEDVALUE.getI()]);
                assessment.setLatitude(this.splitData[Index.LATITUDE.getI()]);
                assessment.setLongatude(this.splitData[Index.LONGITUDE.getI()]);

                if (this.splitData.length > Index.ASSESSMENTCLASS1.getI()) {
                    assessment.setAssessmentClassPer1(this.splitData[Index.ASSESSMENTCLASSPER1.getI()]);
                    assessment.setAssessmentClass1(this.splitData[Index.ASSESSMENTCLASS1.getI()]);
                } else {
                    assessment.setAssessmentClass1("");
                }

                if (this.splitData.length > Index.ASSESSMENTCLASS2.getI()) {
                    assessment.setAssessmentClassPer2(this.splitData[Index.ASSESSMENTCLASSPER2.getI()]);
                    assessment.setAssessmentClass2(this.splitData[Index.ASSESSMENTCLASS2.getI()]);
                } else {
                    assessment.setAssessmentClass2("");
                }

                if (this.splitData.length > Index.ASSESSMENTCLASS3.getI()) {
                    assessment.setAssessmentClassPer3(this.splitData[Index.ASSESSMENTCLASSPER3.getI()]);
                    assessment.setAssessmentClass3(this.splitData[Index.ASSESSMENTCLASS3.getI()]);
                } else {
                    assessment.setAssessmentClass3("");
                }

                this.cachedPropertyAssessments.addAssessment(assessment);
            }
            else{
                continue;
            }
        }
    }

    /* Method: cleanString
     *
     * Purpose: takes a raw response and cleans it so that it can be processed easier.
     *
     * Parameters: String propertyAssessmentString
     *              a string that represents the property assessments.
     *
     * Return: String[] splitDataTemp
     */
    public String[] cleanString(String propertyAssessmentString){
        this.responseCleaned = propertyAssessmentString.replaceAll("\"", "");
        splitDataTemp = this.responseCleaned.split("\n");
        //System.out.println(splitDataTemp[3]); DEVTEST
        return splitDataTemp;
    }

    /* Method beginQuery(String endpoint, String query)
     *
     * Purpose: create a http client and request for some data.
     *
     * Parameters: String endpoint; this is the part of query that remains constant (the limit and offset may change
     *                              in the future when caching is fully implemented)
     *             String query; the part of the query that is unique and varies depending on what data we want in the
     *                           the response.
     *
     * Return: String response.body()
     *         the body of the response from our query as a string.
     */
    public String beginQuery(String endpoint, String query){
        String builtRequest = endpoint;
        builtRequest += query;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(builtRequest))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    /* OVERRIDE PropertyAssessments.getAssessmentsByNeighbourHood(String neighbourhood)
     *
     * Purpose: override the method for getting all property assessments with neighbourhood = neighbourhood.
     *
     * Return: List<PropertyAssessment>; the list of property assessments for the provided neighbourhood.
     */
    @Override
    public List<PropertyAssessment> getAssessmentsByNeighbourhood(String neighbourhood) {
        return cachedPropertyAssessments.getAssessmentsByNeighbourhood(neighbourhood);
    }

    /* OVERRIDE PropertyAssessments.getAssessmentsByAssessmentClass(String assessmentClass)
     *
     * Purpose: override the method for getting all property assessments where there assessment class = assessment class.
     *
     * Return: List<PropertyAssessment>; the list of property assessments for the provided assessment class.
     */
    @Override
    public List<PropertyAssessment> getAssessmentsByAssessmentClass(String assessmentClass) {
        return cachedPropertyAssessments.getAssessmentsByAssessmentClass(assessmentClass);
    }

    /* OVERRIDE PropertyAssessments.getAssessmentByAcctNum(String acctNum)
     *
     * Purpose: override the method for getting a property assessment where there account number = acctNum.
     *
     * Return: PropertyAssessment; a property assessment with the provided account number.
     */
    @Override
    public PropertyAssessment getAssessmentByAcctNum(String acctNum) {
        return cachedPropertyAssessments.getAssessmentByAcctNum(acctNum);
    }

    /* OVERRIDE PropertyAssessments.getFirst1000PropertyAssessments()
     *
     * Purpose: override the method for getting the first thousand property assessments.
     *
     * Return: List<PropertyAssessment>; the list of the first 1000 property assessments.
     */
    @Override
    public List<PropertyAssessment> getFirst1000PropertyAssessments() {
        return cachedPropertyAssessments.getFirst1000PropertyAssessments();
    }

    /* OVERRIDE PropertyAssessments.determineAssessmentClasses(ObservableList<String> oList)
     *
     * Purpose: override the method for determining assessment classes.
     *
     * Return: ObservableList<String>; a list of unique assessment classes.
     */
    @Override
    public ObservableList<String> determineAssessmentClasses(ObservableList<String> oList) {
        return cachedPropertyAssessments.determineAssessmentClasses(oList);

    }

    /* OVERRIDE PropertyAssessments.queryResults(String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass) {
     *
     * Purpose: override the method for querying property assessments based one the provided parameters.
     *
     * Return: the results of the query.
     */
    @Override
    public List<PropertyAssessment> queryResults(String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass) {
        return cachedPropertyAssessments.queryResults(acctNum, address, neighbourhood, min, max, assessmentClass);
    }
}
