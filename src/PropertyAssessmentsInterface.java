import java.util.List;
// may not be needed yet.
public interface PropertyAssessmentsInterface {

    void addAssessment(PropertyAssessment a);
    void getAssessmentByAcctNum(String a);

    List<PropertyAssessment> getAssessmentsByNeighbourhood(String a);
    List<PropertyAssessment> getAssessmentsByAssessmentClass(String a);
    int numAssessments(List<PropertyAssessment> a);
    int minAssessment(List<PropertyAssessment> a);
    int maxAssessment(List<PropertyAssessment> a);
    int assessmentRange(List<PropertyAssessment> a);
    long assessmentMean(List<PropertyAssessment> a);
    int assessmentMedian(List<PropertyAssessment> a);
}
