import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* Public Class CsvParser
 *
 *
 *
 *
 */
public class CsvParser {

    String[] splitData;
    private String filename;
    public List<String> propertyAssessmentCSV = new ArrayList<>();
    public PropertyAssessments assessments = new PropertyAssessments();

    public CsvParser(String filename) throws FileNotFoundException {
        this.filename = filename;
        File csv = getFile(filename);
        Scanner CsvScanner = setScanner(csv);

        if (CsvScanner == null)  {
            throw new FileNotFoundException();
        }

        int counter = 0;
        /* Let the parsing begin... */
        while (CsvScanner.hasNextLine())
        {
            String data = CsvScanner.nextLine();

            this.splitData = splitData(data);
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

                this.assessments.addAssessment(assessment);
            }
            else{ continue; }

        }
        orderPropertyAssessments();
        CsvScanner.close();
    }

    /* Get the file. */
    private File getFile(String filename)
    {
        try
        {
            File myCsv = new File(filename);
            return myCsv;
        }
        catch(Exception e) // no worky
        {
            System.out.println("Error: can't open file " + filename);
            return null;
        }
    }

    /* set the scanner. */
    private Scanner setScanner(File csv) throws FileNotFoundException
    {
        try {
            Scanner myScanner = new Scanner(csv);
            return myScanner;
        }
        catch(Exception e){
            System.out.println("Error: can't open file " + filename);
            return null;
        }
    }

    /* Method: splitData
     *
     *  Parameters: String data
     *        Usually passed a single line from the file to be split on the comma.
     *
     *   Returns: String[] splitData
     *       a list of strings that will represent the data of a PropertyAssessment.
     */
    public String[] splitData(String data)
    {
        String[] splitData;
        splitData = data.split(",");
        return splitData;
    }

    /* Method: orderPropertyAssessments
     *
     * Purpose: Orders the property assessment list based on the account number as a string rather than int.
     *
     * Method: orderPropertyAssessments
     *
     * Parameters: void
     *
     * Return: void
     *
     */
    public void orderPropertyAssessments(){
        //Collections.sort(assessments.PropertyAssessmentList);
        assessments.PropertyAssessmentList.sort((a, b) -> {
            return a.accountNum.compareTo(b.accountNum);
        });
    }
}
