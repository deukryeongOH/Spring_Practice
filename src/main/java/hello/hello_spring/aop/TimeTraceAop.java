package hello.hello_spring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP 사용시 필요한 Annotation
@Component // 스프링 빈에 등록해야하는데 Component 사용도 좋지만 SpringConfig에 직접 등록하는걸 선호
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..))")
    //@Around("execution(* hello.hello_spring.service..*(..))") //service만 확인하고 싶을 때 경로 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start = " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
