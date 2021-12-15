import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Lab3Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner myS = new Scanner(System.in);
        System.out.println("CSV filename: ");
        String filename = myS.next();
        try{
            CsvParser CSVparser = new CsvParser(filename);

            // Get descriptive stats about a assessment class. for TESTING: Residential
            Scanner myScanner = new Scanner(System.in);
            System.out.println("Assessment class: ");
            String assessmentClass = myScanner.next();
            System.out.println("Statistics (assessment class = " + assessmentClass + ')');

            List<PropertyAssessment> myList = CSVparser.assessments.getAssessmentsByAssessmentClass(assessmentClass);

            if (myList == null){
                System.out.println("No Property Assessment with assessment class: " + assessmentClass);
            }else {

                System.out.println(CSVparser.assessments.displayDescriptiveAssessmentClassStats(myList));
            }

        } catch(Exception ignored){
        }

    }

}
