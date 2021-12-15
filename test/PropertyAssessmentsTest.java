import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* TEST CASES FOR CLASS: PropertyAssessments
 *
 * Each test case is for one of the statistics calculations, which are called by other methods.
 *
 */
class PropertyAssessmentsTest {

    /* numAssessments() TEST
     *
     * Create an empty PropertyAssessment(s) collection then assert that its n = 0.
     * Then add 10 PropertyAssessment(s) and assert that n = 10.
     */
    @Test
    void numAssessments() {
        //create empty PropertyAssessments collection
        PropertyAssessments pas = new PropertyAssessments();
        assertEquals(0, pas.numAssessments(pas.PropertyAssessmentList));
        // add 10 assessments
        for(int i = 0; i < 10; i++){
            PropertyAssessment pa = new PropertyAssessment();
            pas.PropertyAssessmentList.add(pa);
        }
        assertEquals(10, pas.numAssessments(pas.PropertyAssessmentList));
    }

    /* minAssessment() TEST
     *
     * Create an empty PropertyAssessment(s) collection and an array of values.
     *
     * Then for each value in arr, create a new PropertyAssessment with the assessment value = a value
     * from arr.
     *
     * Then check assert that the minAssessment method returns a value that is = the smallest value in arr.
     *
     */
    @Test
    void minAssessment() {
        //create empty PropertyAssessments collection
        PropertyAssessments pas = new PropertyAssessments();
        String[] arr = {"3233333", "45675", "10", "12124"};

        // add 3 assessments
        for(String i : arr){
            PropertyAssessment pa = new PropertyAssessment();
            pa.setAssessedValue(i);
            pas.PropertyAssessmentList.add(pa);
        }
        assertEquals(10, pas.minAssessment(pas.PropertyAssessmentList));

    }

    /* maxAssessment() TEST
     *
     * Create an empty PropertyAssessment(s) collection and an array of values.
     *
     * Then for each value in arr, create a new PropertyAssessment with the assessment value = a value
     * from arr.
     *
     * Then assert that the maxAssessment method returns a value that is = the largest value in arr.
     *
     */
    @Test
    void maxAssessment() {
        //create empty PropertyAssessments collection
        PropertyAssessments pas = new PropertyAssessments();
        String[] arr = {"3233333", "45675", "1034", "1123"};

        // add 3 assessments
        for(String i : arr){
            PropertyAssessment pa = new PropertyAssessment();
            pa.setAssessedValue(i);
            pas.PropertyAssessmentList.add(pa);
        }
        assertEquals(3233333, pas.maxAssessment(pas.PropertyAssessmentList));

    }

    /* assessmentRange() TEST
     *
     * Create an empty PropertyAssessment(s) collection and an array of values.
     *
     * Then for each value in arr, create a new PropertyAssessment with the assessment value = a value
     * from arr.
     *
     * Then assert that the assessmentRange method returns a value that is = to the range
     * (difference between max-min).
     *
     */
    @Test
    void assessmentRange() {
        //create empty PropertyAssessments collection
        PropertyAssessments pas = new PropertyAssessments();
        String[] arr = {"0", "10", "12", "14", "20"};

        // add 3 assessments
        for(String i : arr){
            PropertyAssessment pa = new PropertyAssessment();
            pa.setAssessedValue(i);
            pas.PropertyAssessmentList.add(pa);
        }
        assertEquals(20, pas.assessmentRange(pas.PropertyAssessmentList));

    }

    /* assessmentMean() TEST
     *
     * Create an empty PropertyAssessment(s) collection and an array of values.
     *
     * Then for each value in arr, create a new PropertyAssessment with the assessment value = a value
     * from arr.
     *
     * Then assert that the assessmentMean method returns a value that is = sum of items in arr / num of items in arr.
     *
     */
    @Test
    void assessmentMean() {
        //create empty PropertyAssessments collection
        PropertyAssessments pas = new PropertyAssessments();
        String[] arr = {"5", "5", "5", "5", "5"};

        // add 3 assessments
        for(String i : arr){
            PropertyAssessment pa = new PropertyAssessment();
            pa.setAssessedValue(i);
            pas.PropertyAssessmentList.add(pa);
        }
        assertEquals(5, pas.assessmentMean(pas.PropertyAssessmentList));
    }

    /* assessmentMedian() TEST
     *
     * Create an empty PropertyAssessment(s) collection and an array of values.
     *
     * Then for each value in arr, create a new PropertyAssessment with the assessment value = a value
     * from arr.
     *
     * Then assert that the assessmentMedian method returns a value that is = the value in the middle of the
     * list. (already sorted so that the correct result is more obvious).
     *
     */
    @Test
    void assessmentMedian() {
        //create empty PropertyAssessments collection
        PropertyAssessments pas = new PropertyAssessments();
        String[] arr = {"5", "5", "10", "11", "12", "13", "45"};

        // add 3 assessments
        for(String i : arr){
            PropertyAssessment pa = new PropertyAssessment();
            pa.setAssessedValue(i);
            pas.PropertyAssessmentList.add(pa);
        }
        assertEquals(11, pas.assessmentMedian(pas.PropertyAssessmentList));
    }
}