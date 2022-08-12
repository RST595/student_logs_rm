package com.aston.repo;

import com.aston.exception.SQLTransactionException;
import com.aston.model.LogItem;
import com.aston.util.HibernateUtil;
import com.aston.dto.response.ResponseMessageDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LogItemRepo {

    public ResponseMessageDTO updateLogItem(LogItem logItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(logItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLTransactionException("Log item didn't updated");
        }
        return new ResponseMessageDTO(true, "Log item updated");
    }

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
            throw new SQLTransactionException("Student didnt founded");
        }
        if(logItem == null)throw new SQLTransactionException("Student didnt founded");
        return logItem;
    }
}
