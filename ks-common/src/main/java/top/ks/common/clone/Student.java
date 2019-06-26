package top.ks.common.clone;

public class Student implements Cloneable{

    private Subject subject;

    private String name;

    private int age;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String subjectName, int subjectScore, String name, int age) {
        Subject subject = new Subject(subjectName, subjectScore);
        this.subject = subject;
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Student() {
    }
}
