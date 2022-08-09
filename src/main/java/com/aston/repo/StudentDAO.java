package com.aston.repo;

import com.aston.exception.SQLTransactionException;
import com.aston.model.LogItem;
import com.aston.model.Student;
import com.aston.util.HibernateUtil;
import com.aston.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentDAO {

    private final HibernateUtil hibernateUtil;
    private static final String EXCEPTION_MESSAGE = "Student didnt founded";


    public Student getStudentById(int id){
        Transaction transaction = null;
        Student student;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLTransactionException(EXCEPTION_MESSAGE);
        }
        if(student == null)throw new SQLTransactionException(EXCEPTION_MESSAGE);
        return student;
    }

    public List<Student> getAllStudents(){
        Transaction transaction = null;
        List<Student> students;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> cq = cb.createQuery(Student.class);
            Root<Student> rootEntry = cq.from(Student.class);
            CriteriaQuery<Student> all = cq.select(rootEntry);
            TypedQuery<Student> allQuery = session.createQuery(all);
            students = allQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLTransactionException(EXCEPTION_MESSAGE);
        }
        if(students == null)throw new SQLTransactionException(EXCEPTION_MESSAGE);
        return students;
    }
    public ResponseMessage addNewStudent(Student student) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLTransactionException("Student didnt added");
        }
        return new ResponseMessage(true, "Student added");
    }

    public ResponseMessage deleteStudentById(int id) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.load(Student.class, id);
            if (student != null) {
                for(LogItem item : student.getLogItem()){
                    session.delete(item);
                }
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLTransactionException("Student didnt deleted");
        }
        return new ResponseMessage(true, "Student with id = " + id + ", deleted successfully");
    }

    public ResponseMessage updateStudent(Student student){
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLTransactionException("Fail while add new Log item to student");
        }
        return new ResponseMessage(true, "Student log with id = " + student.getId() + ", was added successfully");
    }
}
