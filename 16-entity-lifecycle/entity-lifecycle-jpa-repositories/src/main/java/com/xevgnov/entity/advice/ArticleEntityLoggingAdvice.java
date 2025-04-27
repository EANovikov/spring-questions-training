package com.xevgnov.entity.advice;

import com.xevgnov.entity.entity.Article;
import com.xevgnov.entity.repository.ArticlePersistenceContextInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class ArticleEntityLoggingAdvice {

    private final ArticlePersistenceContextInfoRepository<Article, UUID> persistenceContextInfoRepository;

    @Around("execution(* com.xevgnov.entity.repository.ArticleRepository.*(..))")
    public Object logEntityPresence(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("[Before {} call] Article ID {} present in JPA's Persistence Context: {}",
                proceedingJoinPoint.getSignature().getName(),
                getArticleUUID(proceedingJoinPoint),
                isInPersistenceContext(proceedingJoinPoint));
        Object result = proceedingJoinPoint.proceed();

        boolean isInPersistenceContext;
        if (result instanceof Article) {
            isInPersistenceContext = isInPersistenceContext((Article) result);
        } else {
            isInPersistenceContext = isInPersistenceContext(proceedingJoinPoint);
        }

        log.info("[After {} call] Article ID {} present in JPA's Persistence Context: {}",
                proceedingJoinPoint.getSignature().getName(),
                getArticleUUID(proceedingJoinPoint),
                isInPersistenceContext);
        return result;
    }

    private UUID getArticleUUID(ProceedingJoinPoint proceedingJoinPoint) {
        UUID articleId = getArticleUUID(proceedingJoinPoint.getArgs());
        if (articleId == null) {
            Article article = getArticle(proceedingJoinPoint.getArgs());
            articleId = article.getId();
        }
        return articleId;
    }

    private boolean isInPersistenceContext(Article article) {
              return persistenceContextInfoRepository.isEntityInPersistenceContext(article);
    }

    private boolean isInPersistenceContext(ProceedingJoinPoint proceedingJoinPoint) {
        UUID articleId = getArticleUUID(proceedingJoinPoint.getArgs());
        if (articleId == null) {
            Article article = getArticle(proceedingJoinPoint.getArgs());
            return persistenceContextInfoRepository.isEntityInPersistenceContext(article);
        } else {
            return persistenceContextInfoRepository.isEntityInPersistenceContextById(articleId);
        }
    }

    private Article getArticle(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg instanceof Article)
                .map(arg -> (Article) arg).findFirst()
                .orElse(new Article());

    }

    private UUID getArticleUUID(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg instanceof UUID)
                .map(arg -> (UUID) arg).findFirst()
                .orElse(null);

    }

}
