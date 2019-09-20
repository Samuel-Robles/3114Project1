package Class;

public class Student implements ClassMember{

    private String firstName;
    private String lastName;
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public int compareTo(ClassMember compStudent) {
        int compNames = (this.lastName).compareTo(compStudent.getLastName());
        if (compNames == 0) {
            return (this.firstName).compareTo(compStudent.getFirstName());
        }
        return compNames;
    }
}
