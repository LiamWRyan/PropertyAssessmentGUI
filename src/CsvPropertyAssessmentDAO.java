import javafx.collections.ObservableList;
import java.io.FileNotFoundException;
import java.util.List;


/* class CsvPropertyAssessmentDAO
 *
 * Purpose: This class implements the PropertyAssessmentDAO interface and overrides several functions.
 *          It constructs a new CsvParser Object and then offers functionality defined in PropertyAssessments, which
 *          is loosely coupled through the PropertyAssessmentDAO.
 *
 */
public class CsvPropertyAssessmentDAO implements PropertyAssessmentDAO {

    CsvParser csvParser;
    // constructor. instantiates a new CsvParser object given a filename
    public CsvPropertyAssessmentDAO(String filename) throws FileNotFoundException {
        this.csvParser = new CsvParser(filename);
    }

    /* OVERRIDE PropertyAssessments.getAssessmentsByNeighbourHood(String neighbourhood)
     *
     * Purpose: override the method for getting all property assessments with neighbourhood = neighbourhood.
     *
     * Return: List<PropertyAssessment>; the list of property assessments for the provided neighbourhood.
     */
    @Override
    public List<PropertyAssessment> getAssessmentsByNeighbourhood(String neighbourhood) {
        return csvParser.assessments.getAssessmentsByNeighbourhood(neighbourhood);
    }


    /* OVERRIDE PropertyAssessments.getAssessmentsByAssessmentClass(String assessmentClass)
     *
     * Purpose: override the method for getting all property assessments where there assessment class = assessment class.
     *
     * Return: List<PropertyAssessment>; the list of property assessments for the provided assessment class.
     */
    @Override
    public List<PropertyAssessment> getAssessmentsByAssessmentClass(String assessmentClass) {
        return csvParser.assessments.getAssessmentsByAssessmentClass(assessmentClass);
    }


    /* OVERRIDE PropertyAssessments.getAssessmentByAcctNum(String acctNum)
     *
     * Purpose: override the method for getting a property assessment where there account number = acctNum.
     *
     * Return: PropertyAssessment; a property assessment with the provided account number.
     */
    @Override
    public PropertyAssessment getAssessmentByAcctNum(String acctNum) {
        return csvParser.assessments.getAssessmentByAcctNum(acctNum);
    }

    /* OVERRIDE PropertyAssessments.getFirst1000PropertyAssessments()
     *
     * Purpose: override the method for getting the first thousand property assessments.
     *
     * Return: List<PropertyAssessment>; the list of the first 1000 property assessments.
     */
    @Override
    public List<PropertyAssessment> getFirst1000PropertyAssessments() {
        return csvParser.assessments.getFirst1000PropertyAssessments();
    }


    /* OVERRIDE PropertyAssessments.determineAssessmentClasses(ObservableList<String> oList)
     *
     * Purpose: override the method for determining assessment classes.
     *
     * Return: ObservableList<String>; a list of unique assessment classes.
     */
    @Override
    public ObservableList<String> determineAssessmentClasses(ObservableList<String> oList) {
        return csvParser.assessments.determineAssessmentClasses(oList);
    }

    /* OVERRIDE PropertyAssessments.queryResults(String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass) {
     *
     * Purpose: override the method for querying property assessments based one the provided parameters.
     *
     * Return: the results of the query.
     */
    @Override
    public List<PropertyAssessment> queryResults(String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass) {
        return csvParser.assessments.queryResults(acctNum, address, neighbourhood, min, max, assessmentClass);
    }
}
