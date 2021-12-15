import java.io.FileNotFoundException;

public class Lab5Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Testing with CsvPropertyAssessmentDAO...");
        try {
            CsvPropertyAssessmentDAO csvPaDao = new CsvPropertyAssessmentDAO("Property_Assessment_Data_2021.csv");
            PropertyAssessment propertyAssessment;

            System.out.println("T1:");
            // get an assessment by an account number
            System.out.println(csvPaDao.getAssessmentByAcctNum("10000001"));
            System.out.println("T2:");
            System.out.println(csvPaDao.getAssessmentByAcctNum("10000007"));
            System.out.println("T3:");
            propertyAssessment = csvPaDao.getAssessmentByAcctNum("kzyxz");
            if (propertyAssessment == null){
                System.out.println("No property assessment with account number: " + "kzyxz");
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }
}
