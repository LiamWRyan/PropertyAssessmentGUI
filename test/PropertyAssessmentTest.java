import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/* TEST CASES FOR CLASS: PropertyAssessment
 *
 * Each test case is for a getter.
 * #1: The first test checks that upon instantiation of the object it is
 *     null.
 * #2: The second test makes a new object and sets the value and then checks that the asserted value is
 *     what the getter returns.
 *
 * By doing both tests in one test method, we can assure that the object is constructed correctly.
 * and we can assure that the object is set correctly by setting a value and getting it again.
 *
 * In essence, each test method tests not only instantiation, but also the getter and setter method
 * for a field.
 *
 */
class PropertyAssessmentTest {

    @Test
    void getSuite() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getSuite());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setSuite("I am the suite");
        assertEquals("I am the suite", paOther.getSuite());
    }

    @Test
    void getHouseNumber() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getHouseNumber());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setHouseNumber("I am the house number");
        assertEquals("I am the house number", paOther.getHouseNumber());

    }

    @Test
    void getStreetName() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getStreetName());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setStreetName("I am the street name");
        assertEquals("I am the street name", paOther.getStreetName());
    }

    @Test
    void getGarage() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getGarage());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setGarage("Y");
        assertEquals("Y", paOther.getGarage());
    }

    @Test
    void getNeighbourhoodID() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getNeighbourhoodID());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setNeighbourhoodID("I am a neighbourhoodID");
        assertEquals("I am a neighbourhoodID", paOther.getNeighbourhoodID());
    }

    @Test
    void getWard() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getWard());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setWard("ward8");
        assertEquals("ward8", paOther.getWard());
    }

    @Test
    void getLongatude() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getLongatude());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setLongatude("-113.1237674257483553");
        assertEquals("-113.1237674257483553", paOther.getLongatude());
    }

    @Test
    void getLatitude() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getLatitude());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setLatitude("12.14124124124");
        assertEquals("12.14124124124", paOther.getLatitude());
    }

    @Test
    void getAssessmentClassPer1() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessmentClassPer1());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessmentClassPer1("34");
        assertEquals("34", paOther.getAssessmentClassPer1());
    }

    @Test
    void getAssessmentClassPer2() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessmentClassPer2());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessmentClassPer2("56");
        assertEquals("56", paOther.getAssessmentClassPer2());
    }

    @Test
    void getAssessmentClassPer3() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessmentClassPer3());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessmentClassPer3("100");
        assertEquals("100", paOther.getAssessmentClassPer3());
    }

    @Test
    void getAssessmentClass1() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessmentClass1());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessmentClass1("residential");
        assertEquals("residential", paOther.getAssessmentClass1());
    }

    @Test
    void getAssessmentClass2() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessmentClass2());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessmentClass2("commercial");
        assertEquals("commercial", paOther.getAssessmentClass2());
    }

    @Test
    void getAssessmentClass3() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessmentClass3());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessmentClass3("rural");
        assertEquals("rural", paOther.getAssessmentClass3());
    }

    @Test
    void getAccountNum() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAccountNum());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAccountNum("123345");
        assertEquals("123345", paOther.getAccountNum());
    }

    @Test
    void getAssessedValue() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getAssessedValue());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setAssessedValue("123456789");
        assertEquals("123456789", paOther.getAssessedValue());
    }

    @Test
    void getNeighbourhood() {
        //#1
        PropertyAssessment pa = new PropertyAssessment();
        assertNull(pa.getNeighbourhood());
        //#2
        PropertyAssessment paOther = new PropertyAssessment();
        paOther.setNeighbourhood("le neighbourhood");
        assertEquals("le neighbourhood", paOther.getNeighbourhood());
    }

}