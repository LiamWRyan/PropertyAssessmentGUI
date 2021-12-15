import javafx.collections.ObservableList;
import java.util.*;

/* Public Class PropertyAssessments
*
* Purpose: manages a collection of property assessment objects and their statistics. This class contains
* methods for calculating and then displaying descriptive statistics of PropertyAssessment collections.
*
 */
public class PropertyAssessments extends PropertyAssessment{ //implements PropertyAssessmentInterface{

    public List<PropertyAssessment> PropertyAssessmentList = new ArrayList<PropertyAssessment>();

    public PropertyAssessments()
    {


    }

    /* Method: addAssessment
     *
     * Purpose: Method to add a propertyAssessment to the PropertyAssessments List
     *
     * Parameters: PropertyAssessment
     *         an object of type PropertyAssessment
     *
     * Return: null
     */
    public void addAssessment(PropertyAssessment propertyAssessment)
    {
        this.PropertyAssessmentList.add(propertyAssessment);
    }

    /* Method: queryResults
     *
     * Purpose: given the values from all textFields determine whether or not the parameter was selected to be queried
     *          by, and if it was, check that it meets the constraints, or contains the parameter, or is equal
     *          to the parameter. Which one depends on the parameter type. If all conditionals are passed then we add the
     *          property assessment to the return list.
     *
     * Parameters: String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass
     *
     *
     * Return: List<PropertyAssessment> searchPropertyAssessments
     *         A list of property assessments that met all criteria.
     */
    public List<PropertyAssessment> queryResults(String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass){

        List<PropertyAssessment> searchPropertyAssessments = new ArrayList<>();
        neighbourhood = neighbourhood.toUpperCase();
        address = address.toUpperCase();

        for (PropertyAssessment propertyAssessment : PropertyAssessmentList) {
            if (!propertyAssessment.assessedValue.equals("assessed_value")) {
                if (!acctNum.equals("")) {
                    if (!propertyAssessment.accountNum.equals(acctNum)) {
                        continue;
                    }
                } else {
                    if (!address.equals("")) {
                        if (!propertyAssessment.buildAddress().contains(address)) {
                            continue;
                        }
                    }
                    if (!neighbourhood.equals("")) {
                        if (!propertyAssessment.buildNeighbourhoodAndWard().contains(neighbourhood)) {
                            continue;
                        }
                    }
                    if (min != null) {
                        if (Integer.parseInt(propertyAssessment.assessedValue) < min) {
                            continue;
                        }
                    }
                    if (max != null) {
                        if (Integer.parseInt(propertyAssessment.assessedValue) > max) {
                            continue;
                        }
                    }
                    if (!assessmentClass.equals("")) {
                        if (!propertyAssessment.buildAssessmentClass().contains(assessmentClass)) {
                            continue;
                        }
                    }
                }
                searchPropertyAssessments.add(propertyAssessment);
            }
        }
        // no results found. Return null
        if (searchPropertyAssessments.size() == 0){
            return null;
        }else {
            return searchPropertyAssessments;
        }
    }


    /* Method: getFirst1000PropertyAssessments
     *
     * Purpose: Return the first 1000 property assessments.
     *
     * Parameters: void
     *
     * Return: List<PropertyAssessment> first1000List
     *        A list of PropertyAssessment(s) that is composed of the first 1000 property assessment lists.
     */
    public List<PropertyAssessment> getFirst1000PropertyAssessments(){
        List<PropertyAssessment> first1000List = new ArrayList<>();
        List<PropertyAssessment> PropertyAssessmentListCopy = PropertyAssessmentList;

        int counter = 0;
        for (PropertyAssessment propertyAssessment : PropertyAssessmentListCopy){
            counter ++;
            if (counter < 1000) {
                first1000List.add(propertyAssessment);
            }
        }
        // first1000List.get(0).accountNum.equals("Account Number") ||
        if(first1000List.size() > 0) {
            if (first1000List.get(0).accountNum.equals("account_number")) {
                first1000List.remove(0);
            }
        }
        return first1000List;
    }

    /* Method: getAssessmentByAddress
     *
     * Purpose: return a property assessment list for property assessment(s) with an address that equals that of
     *          the provided address or contains a portion of it.
     *
     * Parameters: String address
     *          The string we want to compare against each property assessments address.
     *
     * Return: List<PropertyAssessment> addressList
     *          a list of property assessment(s) that contains property assessments with the provided address.
     *
     */
    public List<PropertyAssessment> getAssessmentsByAddress(String address){
        List<PropertyAssessment> addressList = new ArrayList<>();
        address = address.toUpperCase();
        // Iterate through each property assessment
        for (PropertyAssessment propertyAssessment : PropertyAssessmentList){
            // call the function to build and return the address for a property assessment, then check that it
            // contains the address string. If it does, add it to the addressList
            if (propertyAssessment.buildAddress().contains(address)){
                addressList.add(propertyAssessment);
            }
        }

        if (addressList.isEmpty()){
            return null;
        }else {
            return addressList;
        }
    }

    /* Method: getAssessmentsByAssessedValue
     *
     * Purpose: return property assessments that exist with-in the provided maximum and minimum range.
     *
     * Parameters: String max, String min
     *          max; a maximum assessed value for the property assessments we want to return
     *          min; a minimum assessed value for the property assessments we want to return
     *
     * Return: List<PropertyAssessment> minMaxAssessmentList
     *          a list of property assessment(s) with assessed values with-in [min, max]
     *         null; null if no property assessments are found.
     */
    public List<PropertyAssessment> getAssessmentsByAssessedValue(String max, String min){
        List<PropertyAssessment> minMaxAssessmentList = new ArrayList<>();
        // if the user does not provide a max or min value we set them to an arbitrarily large number or
        // 0 respectively. It is possible that value(s) could be excluded in the return results if its price is
        // over the automatic max and a max is not provided, but is unlikely as most properties should not exceed
        // over 2 billion. 2147483646 is one under the maximum allowed value for an integer in java.
        if (max.equals("")){
            max = "2147483646";
        }
        if (min.equals("")){
            min = "0";
        }
        // Iterate through each property assessment.
        for (PropertyAssessment propertyAssessment : PropertyAssessmentList){
            // Check that the assessed value is not the column name.
            if (!propertyAssessment.assessedValue.equals("assessed_value")) {
                // grab each assessed value and parse it to an int, then use inclusive comparisons to make sure it is
                // with-in the assessed value max and min.
                try {
                    if (Integer.parseInt(propertyAssessment.assessedValue) <= Integer.parseInt(max) && Integer.parseInt(propertyAssessment.assessedValue) >= Integer.parseInt(min)) {
                        minMaxAssessmentList.add(propertyAssessment);
                    }
                }catch(Exception e){ // if the user provides a value that cant be parsed into int we return null.
                    return null;
                }
            }
        }
       if (minMaxAssessmentList.isEmpty()){
           return null;
       }else {
           return minMaxAssessmentList;
       }
    }

    /* Method: determineAssessmentClasses
     *
     * Purpose: based on the list of property assessments, determine which property assessment classes are present.
     *
     * Parameters: ObservableList<String> oList
     *              an observable list that we want to fill with property assessment classes.
     *
     * Return: ObservableList<String> oList
     *          an observable list of unique property assessment class strings.
     */
    public ObservableList<String> determineAssessmentClasses(ObservableList<String> oList){
        List<String> assessmentClassList = new ArrayList<>();

        for (PropertyAssessment propertyAssessment : PropertyAssessmentList) {
            if (!propertyAssessment.assessmentClass1.equals("") && !assessmentClassList.contains(propertyAssessment.assessmentClass1)) {
                assessmentClassList.add(propertyAssessment.assessmentClass1);
            }
            if (!propertyAssessment.assessmentClass2.equals("") && !assessmentClassList.contains(propertyAssessment.assessmentClass2)) {
                assessmentClassList.add(propertyAssessment.assessmentClass2);
            }
            if (!propertyAssessment.assessmentClass3.equals("") && !assessmentClassList.contains(propertyAssessment.assessmentClass3)) {
                assessmentClassList.add(propertyAssessment.assessmentClass3);
            }
        }

        for (String string : assessmentClassList) {
            if (!string.equals("mill_class_1") && !string.equals("mill_class_2") && !string.equals("mill_class_3")) {
                oList.add(string);
            }
        }
        return oList;
    }


    /* Method: getAssessmentByAcctNum
     *
     * Purpose: Method to return a propertyAssessment object based on a provided account number.
     *
     * Parameters: String
     *      The account number we want to search for.
     *
     * Return: PropertyAssessment propertyAssessment; the property assessment with the account number provided
     *         null; if not property assessment with acctNum is found
     */
    public PropertyAssessment getAssessmentByAcctNum(String acctNum)
    {
        for (int i = 1; i < (PropertyAssessmentList.size() - 1); i++)
        {
            PropertyAssessment propertyAssessment = PropertyAssessmentList.get(i);
            if (propertyAssessment.accountNum.equals(acctNum))
            {
                return propertyAssessment;
            }
        }
        return null;
    }

    /* Method: getAssessmentsByNeighbourhood
     *
     * Purpose: Method to return a list of property assessments objects with the same neighbourhood.
     *
     * Parameters: String neighbourhood
     *      The name of the neighbourhood for which we want to collect property assessments on.
     * Return: List<PropertyAssessment> propertyAssessment;  A list of propertyAssessment(s) for the specified neighbourhood.
     *         null; null if no property assessments are found with the provided neighbourhood.
     */
    public List<PropertyAssessment> getAssessmentsByNeighbourhood(String neighbourhood){
        List<PropertyAssessment> neighbourhoodAssessments = new ArrayList<PropertyAssessment>();
        neighbourhood = neighbourhood.toUpperCase();

        for (PropertyAssessment propertyAssessment : PropertyAssessmentList) {
            if (propertyAssessment.neighbourhood.toUpperCase().contains(neighbourhood)) {

                neighbourhoodAssessments.add(propertyAssessment);
            }
        }
        if (neighbourhoodAssessments.isEmpty()){
            return null;
        }else {
            return neighbourhoodAssessments;
        }
    }

    /* Method: getAssessmentsByAssessmentClass
     *
     * Purpose: Method to return a list of property assessments objects in the same assessment class.
     *
     * Parameters: String
     *      The name of the assessment class for which we want to collect property assessments on.
     * Return: List<PropertyAssessment> propertyAssessment; A list of propertyAssessment(s) for the specified assessment class.
     *         null; null if no property assessments are found for the specified assessmentClass.
     *
     */
    public List<PropertyAssessment> getAssessmentsByAssessmentClass(String assessmentClass){

        assessmentClass = assessmentClass.toUpperCase();
        List<PropertyAssessment> assessmentClassAssessments = new ArrayList<PropertyAssessment>();

        for (PropertyAssessment propertyAssessment : PropertyAssessmentList) {
            if (propertyAssessment.assessmentClass1.toUpperCase().equals(assessmentClass) || propertyAssessment.assessmentClass2.toUpperCase().equals(assessmentClass) || propertyAssessment.assessmentClass3.toUpperCase().equals(assessmentClass)) {
                assessmentClassAssessments.add(propertyAssessment);
            }
        }

        if (assessmentClassAssessments.isEmpty()){
            return null;
        }else {
            return assessmentClassAssessments;
        }
    }


    /* Method: displayDescriptiveNeighbourhoodStats
     *
     * Purpose: Method to return descriptive statistics of all properties in the same neighbourhood.
     *
     * Parameters: List<PropertyAssessment>
     *        A list of propertyAssessment(s) for a specified neighbourhood.
     * Return: String
     *      Descriptive stats for the neighbourhood as a formatted string.
     */
    public String displayDescriptiveNeighbourhoodStats(List<PropertyAssessment> neighbourhoodAssessments){
        return displayDescriptiveStats(neighbourhoodAssessments);
    }

    /* Method: displayDescriptiveAssessmentClassStats
     *
     * Purpose: Method to return descriptive statistics of all properties with a specified assessment class.
     *
     * Parameters: List<PropertyAssessment>
     *        A list of all propertyAssessment(s) with the specified assessment classes.
     * Return: String
     *      Descriptive stats for the properties with the specified assessment class as a formatted string.
     */
    public String displayDescriptiveAssessmentClassStats(List<PropertyAssessment> classAssessmentAssessments){
        return displayDescriptiveStats(classAssessmentAssessments);
    }

    /* Method: displayDescriptiveStats
     *
     * Purpose: Method to return the descriptive statistics of all property assessments.
     *
     * Parameters: null
     *
     * Return: String
     *      The descriptive statistics in a formatted string.
     */
    public String displayDescriptiveStats(List<PropertyAssessment> pas) {
        StringBuffer sb  = new StringBuffer("Descriptive statistics of all property assessments");
        sb.append("\nn = ").append(this.numAssessments(pas)).append("\n");
        sb.append("min = ").append('$').append(this.minAssessment(pas)).append("\n");
        sb.append("max = ").append('$').append(this.maxAssessment(pas)).append("\n");
        sb.append("range = ").append('$').append(this.assessmentRange(pas)).append("\n");
        sb.append("mean = ").append('$').append(this.assessmentMean(pas)).append("\n");
        sb.append("median = ").append('$').append(this.assessmentMedian(pas)).append("\n");
        return sb.toString();
    }

    /* Method: numAssessments
     *
     * Purpose: Method to return the number of records.
     *
     * Parameters: List<PropertyAssessment>
     *      a list of property assessment objects.
     *
     * Return: int
     *      Number of propertyAssessments
     */
    public int numAssessments(List<PropertyAssessment> propertyAssessments)
    {
        return (propertyAssessments.size());
    }

    /* Method: minAssessment
     *
     * Purpose: Method to return the min assessed value.
     *
     * Parameters: List<PropertyAssessment>
     *      a list of property assessment objects.
     *
     * Return: int
     *      The minimum assessed value.
     */
    public int minAssessment(List<PropertyAssessment> propertyAssessments) {
        int min = 0;

        min = Integer.parseInt(propertyAssessments.get(0).getAssessedValue());
        int temp = 0;

        for (int i = 0; i < (propertyAssessments.size()); i++)
        {
            temp = Integer.parseInt(propertyAssessments.get(i).getAssessedValue());
            if ( temp < min) { min = temp; }
        }
        return min;
    }

    /* Method: maxAssessment
     *
     * Purpose: Method to return the max assessed value.
     *
     * Parameters: List<PropertyAssessment>
     *      a list of property assessment objects.
     *
     * Return: int
     *      The maximum assessed value.
     */
    public int maxAssessment(List<PropertyAssessment> propertyAssessments){
        int max = 0;
        max = Integer.parseInt(propertyAssessments.get(0).getAssessedValue());

        int temp = 0;
        for (int i = 0; i < (propertyAssessments.size()); i++)
        {
            temp = Integer.parseInt(propertyAssessments.get(i).getAssessedValue());
            if ( temp > max) { max = temp; }
        }
        return max;
    }

    /* Method: assessmentRange
     *
     * Purpose: Method to return the range of assessed values.
     *
     * Parameters: List<PropertyAssessment>
     *      a list of property assessment objects.
     *
     * Return: int
     *      The range of assessed values (max - min)
     */
    public int assessmentRange(List<PropertyAssessment> propertyAssessments){
        int range = 0;
        range = this.maxAssessment(propertyAssessments)-this.minAssessment(propertyAssessments);
        return range;
    }

    /* Method: assessmentMean
     *
     * Purpose: Method to return the mean assessed value.
     *
     * Parameters: List<PropertyAssessment>
     *      a list of property assessment objects.
     *
     * Return: int
     *      The mean; sum of all assessed values / the total number of entries.
     */
    public long assessmentMean(List<PropertyAssessment> propertyAssessments){
        long runningTotal = 0;
        int n = 0;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            runningTotal += Integer.parseInt(propertyAssessment.getAssessedValue());
            n += 1.0;
        }
        return (runningTotal/n);
    }

    /* Method: assessmentMedian
     *
     * Purpose: Method to return the median assessed value.
     *
     * Parameters: List<PropertyAssessment>
     *      a list of property assessment objects.
     *
     * Return: int
     *      The median; the assessed value with 50% of assessed values > than it and 50% of assessed vales < than it.
     */
    public int assessmentMedian(List<PropertyAssessment> propertyAssessments){
        int median = 0;
        List<Integer> orderedAssessmentValues = new ArrayList<Integer>();
        int middle = propertyAssessments.size() / 2;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            orderedAssessmentValues.add(Integer.parseInt(propertyAssessment.getAssessedValue()));
        }
        Collections.sort(orderedAssessmentValues);
        median = orderedAssessmentValues.get(middle);
        return median;
    }


}
