package Class;

public interface ClassMember extends Comparable<ClassMember>{
    public int compareTo(ClassMember compMember);
    public String getFirstName();
    public String getLastName();
}
