package top.ks.common.clone;

public class TestShallowClone {
    /**
     * 浅拷贝
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        // 原始对象
        Student stud = new Student("math", 150, "KS", 18);
        System.out.println("原始对象: " + stud.getName() + " - " + stud.getSubject().getName());

        // 拷贝对象
        Student clonedStud = (Student) stud.clone();
        System.out.println("拷贝对象: " + clonedStud.getName() + " - " + clonedStud.getSubject().getName());

        // 原始对象和拷贝对象是否一样：
        System.out.println("原始对象和拷贝对象是否一样: " + (stud == clonedStud));
        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("原始对象和拷贝对象的name属性是否一样: " + (stud.getName() == clonedStud.getName()));
        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("原始对象和拷贝对象的subj属性是否一样: " + (stud.getSubject() == clonedStud.getSubject()));

        stud.setName("ks-high");
        clonedStud.setName("ks-low");
        stud.getSubject().setName("high-math");
        clonedStud.getSubject().setScore(100);
        System.out.println("更新后的原始对象: " + stud.getName() + " - " + stud.getSubject().getName() + "-" + stud.getSubject().getScore());
        System.out.println("更新原始对象后的克隆对象: " + clonedStud.getName() + " - " + clonedStud.getSubject().getName() + "-" + stud.getSubject().getScore());
    }
}
