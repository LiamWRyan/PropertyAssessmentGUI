
// enumeration of constants for indexing.
public enum Index {

    ACCOUNTNUMBER (0),
    SUITE (1),
    HOUSENUMBER (2),
    STREETNAME (3),
    GARAGE (4),
    NEIGHBOURHOODID (5),
    NEIGHBOURHOOD (6),
    WARD (7),
    ASSESSEDVALUE (8),
    LATITUDE (9),
    LONGITUDE (10),
    POINTLOCATION (11),
    ASSESSMENTCLASSPER1 (12),
    ASSESSMENTCLASSPER2 (13),
    ASSESSMENTCLASSPER3 (14),
    ASSESSMENTCLASS1 (15),
    ASSESSMENTCLASS2 (16),
    ASSESSMENTCLASS3 (17);

    private final int i;

    Index(int i) {
        this.i = i;
    }

    public int getI(){
        return this.i;
    }

}
