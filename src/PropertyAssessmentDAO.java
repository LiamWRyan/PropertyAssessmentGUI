import javafx.collections.ObservableList;
import java.util.List;


public interface PropertyAssessmentDAO {
    PropertyAssessment getAssessmentByAcctNum(String acctNum);
    List<PropertyAssessment> getAssessmentsByNeighbourhood(String neighbourhood);
    List<PropertyAssessment> getAssessmentsByAssessmentClass(String assessmentClass);
    List<PropertyAssessment> getFirst1000PropertyAssessments();
    ObservableList<String> determineAssessmentClasses(ObservableList<String> oList);
    List<PropertyAssessment> queryResults(String acctNum, String address, String neighbourhood, Integer min, Integer max, String assessmentClass );
}
