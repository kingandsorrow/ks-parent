package top.ks.common.spring.aoplearn;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AopAdvisor {

    @Pointcut("execution(* top.ks.common.spring.aoplearn.Subject.add*(..))")
    public void pointCut() {

    }

    @Before(value = "pointCut()")
    private void before() {
        System.out.println("执行之前的校验");

    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("执行之后的处理");
    }

//    @Around(value = "pointCut()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) {
//        System.out.println("环绕之前");
//
//    }


}


