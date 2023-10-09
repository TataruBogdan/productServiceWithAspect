package be.intecbrussel.productapp.aspect;

import be.intecbrussel.productapp.model.Product;
import be.intecbrussel.productapp.model.dto.LoginRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import static be.intecbrussel.productapp.myLogger.myLogger.log;

@Component
@Aspect
public class LoggerAspect {

    @Before("execution(* be.intecbrussel.productapp.service.RegisterService.login(..))")
    public void beforeLoginAttempt(JoinPoint joinPoint) {
        log("ATTEMPT TO LOGIN | EMAIL: " +
                ((LoginRequest) joinPoint.getArgs()[0]).getEmail() +
                " | PASSWORD: " +
                ((LoginRequest) joinPoint.getArgs()[0]).getPassword());
    }

    @After("execution(* be.intecbrussel.productapp.service.RegisterService.*(..))")
    public void afterLoginAttempt(JoinPoint joinPoint) {
        log("SUCCESSFULLY LOGIN : " +
                ((LoginRequest) joinPoint.getArgs()[0]).getEmail() +
                " | PASSWORD: " +
                ((LoginRequest) joinPoint.getArgs()[0]).getPassword());
    }


    @AfterThrowing(
            pointcut = "execution(* be.intecbrussel.productapp.service.RegisterService.login(..))",
            throwing = "exception"
    )
    public void afterReturningPOSTProduct(JoinPoint joinPoint, Exception exception) {
        log("Exception " + ((Exception) exception));
    }

    // TODO - not working
    @AfterReturning(
            pointcut = "execution(* be.intecbrussel.productapp.service.ProductService.addProduct(..))",
            returning = "addProduct"
    )
    public void afterReturningPOSTProduct(JoinPoint joinPoint, Product addProduct) {
        log("Product added: " + ((Product)joinPoint.getArgs()[0]).getName() +
                "price: " + ((Product)joinPoint.getArgs()[0]).getPrice());
    }




}
