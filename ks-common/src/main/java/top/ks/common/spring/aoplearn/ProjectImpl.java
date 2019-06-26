package top.ks.common.spring.aoplearn;

public class ProjectImpl implements Subject {
    @Override
    public void add() {
        System.out.println("Project add");
    }

    @Override
    public void delete() {
        System.out.println("Project delete");
    }
}
