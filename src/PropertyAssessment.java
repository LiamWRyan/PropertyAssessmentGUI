import org.jetbrains.annotations.Nullable;

/* Public Class PropertyAssessment
 *
 * Purpose: Represents a single property assessment. Contains both getters and setters for all fields.
 *          Additionally, it has methods for creating values that are composed of multiple fields, such as an
 *          address (see buildAddress method), which is composed of a unit number, street name, and street number.
 *
 */
public class PropertyAssessment {
    // fields
    public String accountNum;
    public String suite;
    public String houseNumber;
    public String streetName;
    public String garage; // Y or N
    public String neighbourhoodID;
    public String neighbourhood;
    public String ward;
    public String assessedValue;
    public String longatude;
    public String latitude;
    //public String pointLoc;
    public String assessmentClassPer1;
    public String assessmentClassPer2;
    public String assessmentClassPer3;
    public String assessmentClass1;
    public String assessmentClass2;
    public String assessmentClass3;


    public PropertyAssessment()
    {

    }

    /* OVERRIDE: toString
     *
     * Purpose: Method to override toString and display a PropertyAssessment.
     *
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        sb.append("Account Number = ").append(accountNum).append('\n');
        if (!suite.equals("") || !streetName.equals("") || !houseNumber.equals(""))
        {
            sb.append("Address = ").append(houseNumber).append(suite).append(" ").append(streetName).append(" ").append("\n");
        }
        sb.append("Assessed value = ").append('$').append(assessedValue).append('\n');

        if (!assessmentClass1.equals(""))
        {
            sb.append("Assessment class = [").append(assessmentClass1).append(" ").append(assessmentClassPer1).append("%");
        }

        if (!assessmentClass2.equals(""))
        {
            sb.append(", ").append(assessmentClass2).append(" ").append(assessmentClassPer2).append("%");
        }

        if (!assessmentClass3.equals(""))
        {
            sb.append(", ").append(assessmentClass3).append(" ").append(assessmentClassPer3).append("%");
        }

        sb.append("]\nNeighbourhood = ").append(neighbourhood).append(" ").append('(').append(ward).append(")\n");
        sb.append("Location = ").append('(').append(latitude).append(", ").append(longatude).append(')').append("\n");

        return sb.toString();
    }

    /*
    * Purpose: set the account number.
    *
    * Parameters: String accountnum
    *       String representation of the account number.
    *
    * Return: void
     */
    public void setAccountNum(String accountnum) { accountNum = accountnum; }

    /*
     * Purpose: set the assessed value.
     *
     * Parameters: String assessvalue
     *      String representation of the assessed value.
     *
     * Return: void
     */
    public void setAssessedValue(String assessvalue) { assessedValue = assessvalue; }

    /*
     * Purpose: set the neighbourhood.
     *
     * Parameters: String neighourH
     *      The neighbourhood name.
     *
     * Return: void
     */
    public void setNeighbourhood(String neighbourH) { neighbourhood = neighbourH; }

    /*
     * Purpose: get the suite
     *
     * Parameters: void
     *
     * Return: String suite
     *      The suite as a string.
     */
    public String getSuite() {
        return suite;
    }

    /*
     * Purpose: set the suite
     *
     * Parameters: String suite
     *      The suite as a string.
     *
     * Return: void
     */
    public void setSuite(String suite) { this.suite = suite; }

    /*
     * Purpose: get the house number
     *
     * Parameters: void
     *
     * Return: String houseNumber
     *      The house number as a string.
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /*
     * Purpose: set the account number
     *
     * Parameters: String houseNumber
     *      The house number as a string.
     *
     * Return: void
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /*
     * Purpose: get the street name
     *
     * Parameters: void
     *
     * Return: String streetName
     *      String representation of the street name.
     */
    public String getStreetName() {
        return streetName;
    }

    /*
     * Purpose: set the street name.
     *
     * Parameters: String streetName
     *          A string that represents the street name.
     *
     * Return: void
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /*
     * Purpose: get the garage ('y' or 'n')
     *
     * Parameters: void
     *
     * Return: String garage
     *      'y' or 'n' for if it has a garage
     */
    public String getGarage() {
        return garage;
    }

    /*
     * Purpose: set the garage.
     *
     * Parameters: String garage
     *          "Y" or "N" for if they have a garage.
     *
     * Return: void
     */
    public void setGarage(String garage) {
        this.garage = garage;
    }

    /*
     * Purpose: get the neighbourhoodID
     *
     * Parameters: void
     *
     * Return: String neighbourhoodID
     *      The neighbourhoodID as a string.
     */
    public String getNeighbourhoodID() {
        return neighbourhoodID;
    }

    /*
     * Purpose: set the neighbourhood ID.
     *
     * Parameters: String neighbourhoodID
     *      String representation of the neighbourhoodID
     *
     * Return: void
     */
    public void setNeighbourhoodID(String neighbourhoodID) {
        this.neighbourhoodID = neighbourhoodID;
    }

    /*
     * Purpose: get the ward.
     *
     * Parameters: void
     *
     * Return: String ward
     *      String representation of the ward.
     */
    public String getWard() {
        return ward;
    }

    /*
     * Purpose: set the ward.
     *
     * Return: void
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /*
     * Purpose: get the longitude
     *
     * Parameters: void
     *
     * Return: String longatude
     *      String representation of the longitude.
     */
    public String getLongatude() {
        return longatude;
    }

    /*
     * Purpose: set the longitude
     *
     * Parameters: String longatude
     *      String representation of longatude.
     *
     * Return: void
     */
    public void setLongatude(String longatude) {
        this.longatude = longatude;
    }

    /*
     * Purpose: get the latitude
     *
     * Parameters: void
     *
     * Return: String latitude
     *      String representation of the latitude.
     */
    public String getLatitude() {
        return latitude;
    }

    /*
     * Purpose: set the latitude.
     *
     * Parameters: String latitude
     *      String representation of latitude.
     *
     * Return: void
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /*
     * Purpose: get the percent of assessment class1.
     *
     * Parameters: void
     *
     * Return: String assessmentClassPer1
     *      String representation of the percent of assessment class1.
     */
    public String getAssessmentClassPer1() {
        return assessmentClassPer1;
    }

    /*
     * Purpose: set the percentage of the first assessment class.
     *
     * Parameters: String assessmentClassPer1
     *      The percentage of properties that are in the first assessment class as a string.
     *
     * Return: void
     */
    public void setAssessmentClassPer1(String assessmentClassPer1) {
        this.assessmentClassPer1 = assessmentClassPer1;
    }

    /*
     * Purpose: get the percent of assessment class2.
     *
     * Parameters: void
     *
     * Return: String assessmentClassPer2
     *      String representation of the percent of assessment class2,
     */
    public String getAssessmentClassPer2() {
        return assessmentClassPer2;
    }

    /*
     * Purpose:set the percentage of the second assessment class.
     *
     * Parameters: String assessmentClassPer2
     *      The percentage of properties that are in the second assessment class as a string.
     *
     * Return: void
     */
    public void setAssessmentClassPer2(String assessmentClassPer2) {
        this.assessmentClassPer2 = assessmentClassPer2;
    }

    /*
     * Purpose: get the percent of assessment class3.
     *
     * Parameters: void
     *
     * Return: String assessmentClassPer3
     *      String representation of the percent of assessment class3.
     */
    public String getAssessmentClassPer3() {
        return assessmentClassPer3;
    }

    /*
     * Purpose: set the percentage of the third assessment class.
     *
     * Parameters: String assessmentClassPer3
     *      The percentage of properties that are in the third assessment class as a string.
     *
     * Return: void
     */
    public void setAssessmentClassPer3(String assessmentClassPer3) {
        this.assessmentClassPer3 = assessmentClassPer3;
    }

    /*
     * Purpose: get the first assessment class.
     *
     * Parameters: void
     *
     * Return: String assessmentClass1
     *      String representation of assessmentClass1.
     */
    public String getAssessmentClass1() {
        return assessmentClass1;
    }

    /*
     * Purpose: set the first assessment class.
     *
     * Parameters: String assessmentClass1
     *      The first assessment class as a string.
     *
     * Return: void
     */
    public void setAssessmentClass1(String assessmentClass1) {
        this.assessmentClass1 = assessmentClass1;
    }

    /*
     * Purpose: get the second assessment class.
     *
     * Parameters: void
     *
     * Return: String assessmentClass2
     *      String representation of assessmentClass2.
     */
    public String getAssessmentClass2() {
        return assessmentClass2;
    }

    /*
     * Purpose: set the second assessment class.
     *
     * Parameters: String assessmentClass2
     *      The second assessment class as a string.
     *
     * Return: void
     */
    public void setAssessmentClass2(String assessmentClass2) {
        this.assessmentClass2 = assessmentClass2;
    }

    /*
     * Purpose: get the third assessment class.
     *
     * Parameters: void
     *
     * Return: String assessmentClass3
     *      String representation of assessmentClass3.
     */
    public String getAssessmentClass3() {
        return assessmentClass3;
    }

    /*
     * Purpose: set the third assessment class.
     *
     * Parameters: String assessmentClass3
     *      The third assessment class as a string.
     *
     * Return: void
     */
    public void setAssessmentClass3(String assessmentClass3) {
        this.assessmentClass3 = assessmentClass3;
    }

    /* Method: buildAddress
     *
     * Purpose: Use a string builder to build the address. The address is a string composed of any of 3 values
     *          (suite + streetName + houseNumber) assuming they are not empty. Any empty values are not appended.
     *
     * Parameters: void
     *
     * Return: String stringBuilder.toString
     *         The address as a string.
     */
    public String buildAddress(){

        StringBuilder stringBuilder = new StringBuilder();

        if (!this.suite.equals("")){
            stringBuilder.append(this.suite);
        }
        //
        if (!this.streetName.equals("") && !this.suite.equals("")){
            stringBuilder.append(" ").append(this.streetName);
        }else{ // if suite doesn't exist we don't want to add random spaces before streetName
            stringBuilder.append(this.streetName);
        }
        if (!this.houseNumber.equals("")){
            stringBuilder.append(" ").append(this.houseNumber);
        }

        return stringBuilder.toString().toUpperCase();

    }

    /* Method: buildNeighbourhoodAndWard
     *
     * Purpose: Use a string builder to build the neighbourhood. The neighbourhood is a string composed of 2 values
     *          (neighbourhood + ward) if they are not empty. Any empty values are not appended.
     *
     * Parameters: void
     *
     * Return: String stringBuilder.toString
     *         The neighbourhood as a string.
     */
    public String buildNeighbourhoodAndWard(){
        StringBuilder stringBuilder = new StringBuilder();

        if (!this.neighbourhood.equals("")){
            stringBuilder.append(neighbourhood.toUpperCase());
        }
        if (!this.ward.equals("")){
            stringBuilder.append(" ").append("(").append(ward).append(")");
        }
        return stringBuilder.toString();
    }

    /* Method: buildLatLong
     *
     * Purpose: Use a string builder to build the point location. The point location is a string composed of 2 values
     *          (latitude + longitude) if they are not empty. Any empty values are not appended.
     *
     * Parameters: void
     *
     * Return: String stringBuilder.toString
     *         The point location as a string.
     */
    public String buildLatLong(){
        StringBuilder stringBuilder = new StringBuilder();

        if (!this.latitude.equals("")){
            stringBuilder.append("(").append(this.latitude).append(",");
        }
        if (!this.longatude.equals("")){
            stringBuilder.append(this.longatude).append(")");
        }
        return stringBuilder.toString();
    }

    /* Method: buildAssessmentClass
     *
     * Purpose: Use a string builder to build the assessment class(s). The assessment class can contain any combination
     *          of 3 assessment classes and their corresponding percentages. Any empty values are not appended.
     *
     * Parameters: void
     *
     * Return: String stringBuilder.toString
     *         The assessment class as a string.
     */
    public String buildAssessmentClass(){
        StringBuilder stringBuilder = new StringBuilder();

        if (!assessmentClass1.equals(""))
        {
            stringBuilder.append("[").append(assessmentClass1.toUpperCase()).append(" ").append(assessmentClassPer1).append("%");
        }

        if (!assessmentClass2.equals(""))
        {
            stringBuilder.append(", ").append(assessmentClass2.toUpperCase()).append(" ").append(assessmentClassPer2).append("%");
        }

        if (!assessmentClass3.equals(""))
        {
            stringBuilder.append(", ").append(assessmentClass3.toUpperCase()).append(" ").append(assessmentClassPer3).append("%");
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /*
     * Purpose: get the account number
     *
     * Parameters: void
     *
     * Return: String accountNum
     *      String representation of the account number.
     */
    public String getAccountNum() { return this.accountNum; }

    /*
     * Purpose: get the assessed value
     *
     * Parameters: void
     *
     * Return: String assessedValue
     *      String representation of the assessed value.
     */
    public String getAssessedValue() { return this.assessedValue; }

    /*
     * Purpose: get the neighbourhood.
     *
     * Parameters: void
     *
     * Return: String neighbourhood
     *      String representation of the neighbourhood.
     */
    public String getNeighbourhood() { return this.neighbourhood; }

}
