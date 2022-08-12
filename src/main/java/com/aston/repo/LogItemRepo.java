package com.aston.repo;

import com.aston.exception.ServiceError;
import com.aston.exception_handler.ErrorMessage;
import com.aston.model.LogItem;
import com.aston.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import static com.aston.exception_handler.ErrorMessage.SERVER_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Repository
@RequiredArgsConstructor
public class LogItemRepo {
    public LogItem getElementById(int logId) {
        Transaction transaction = null;
        LogItem logItem;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            logItem = session.get(LogItem.class, logId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ServiceError(INTERNAL_SERVER_ERROR, SERVER_ERROR);
        }
        if(logItem == null) {throw new ServiceError(HttpStatus.NOT_FOUND, ErrorMessage.LOG_ITEM_NOT_FOUND);}
        return logItem;
    }
}
