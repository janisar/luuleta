package ee.ardel.learningsession.models;

public enum Role {

    EMPLOYEE, COMPANY;

    public static Role from(int role) {
        switch (role) {
            case 0:
                return EMPLOYEE;
            case 1:
                return COMPANY;
            default:
                throw new UnsupportedOperationException("No such role!");
        }
    }
}
