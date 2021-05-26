package com.example.demo.business.signature;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class CertificateAspect {

    Logger logger = LoggerFactory.getLogger(CertificateAspect.class);
    
    @Resource(name = "DemoSignature")
    private CertificateService certService;

    @Before("@annotation(Cert)")
    public void checkSignature(JoinPoint joinPoint) throws Exception {
        Cert certAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Cert.class);

        if(certAnnotation.checkSignature()){
            Object[] objs = joinPoint.getArgs();
            Optional<CertificateHolder> signedRequest = Arrays.asList(objs).stream().filter(o -> o instanceof CertificateHolder).findFirst().map(o -> (CertificateHolder) o);

            boolean isCorrect = false;
            try {
                isCorrect = certService.verifySenderSignature(signedRequest.get().getSignature(), signedRequest.get().getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            if (!isCorrect) {
                throw new Exception("Invalid signature");
            }
        }

        if(certAnnotation.decryptContent()){
            Object[] objs = joinPoint.getArgs();
            Optional<CertificateHolder> signedRequest = Arrays.asList(objs).stream().filter(o -> o instanceof CertificateHolder).findFirst().map(o -> (CertificateHolder) o);
            signedRequest.get().setDencryptedContent(certService.decrypt(signedRequest.get().getEncryptedContent()));
        }

    }

}
