package top.ks.common.spring.aoplearn;

import org.springframework.stereotype.Component;

@Component
public class SubjectImpl implements Subject {


    @Override
    public void add() {
        System.out.println("增加逻辑");
    }

    @Override
    public void delete() {
        System.out.println("删除逻辑");
    }
}
