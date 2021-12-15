import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Lab2Main {

    public static void main(String[] args) throws FileNotFoundException {

        try {
            Scanner mySca = new Scanner(System.in);
            System.out.println("CSV filename: ");
            String filename = mySca.next();
            CsvParser CSVparser = new CsvParser(filename);

            // Display descriptive stats about all property assessments.
            System.out.println(CSVparser.assessments.displayDescriptiveStats(CSVparser.assessments.PropertyAssessmentList));

            // Get a property assessment provided by an account number. for TESTING: 1103530
            Scanner myS = new Scanner(System.in);
            System.out.println("Find property assessment by account number: ");
            String acctNum = myS.next();

            PropertyAssessment pa =  CSVparser.assessments.getAssessmentByAcctNum(acctNum);

            if (pa == null){
                System.out.println("No Property Assessment with Account Number: " + acctNum);
            }else {
                System.out.println(pa);
            }

            // Get descriptive stats about a neighbourhood. for TESTING: Granville
            Scanner mySc = new Scanner(System.in);
            System.out.println("Find a property assessment by neighbourhood: ");
            String neighbourhood = myS.next();
            System.out.println("Statistics (neighbourhood = " + neighbourhood + ')');

            List<PropertyAssessment> myList = CSVparser.assessments.getAssessmentsByNeighbourhood(neighbourhood);

            if (myList == null){
                System.out.println("No Property Assessments With Neighbourhood: " + neighbourhood);
            }else {
                System.out.println(CSVparser.assessments.displayDescriptiveNeighbourhoodStats(myList));
            }


        }catch(Exception ignored){
        }
    }

}
