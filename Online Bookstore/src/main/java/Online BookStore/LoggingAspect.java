package bookstore;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* RegistrationServlet.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature().getName());
    }
}