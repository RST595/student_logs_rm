package com.aston.repo;

import com.aston.exception.ServiceError;
import com.aston.model.BaseModel;
import com.aston.model.Student;
import com.aston.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.aston.exception_handler.ErrorMessage.SERVER_ERROR;
import static com.aston.exception_handler.ErrorMessage.STUDENT_NOT_FOUND;
import static org.springframework.http.HttpStatus.*;

@Repository
@RequiredArgsConstructor
public class StudentRepo {

    public Student getStudentById(int id){
        Transaction transaction = null;
        Student student;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ServiceError(INTERNAL_SERVER_ERROR, SERVER_ERROR);
        }
        if(student == null) { throw new ServiceError(NOT_FOUND, STUDENT_NOT_FOUND); }
        return student;
    }

    public List<Student> getAllStudents(){
        Transaction transaction = null;
        List<Student> students;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
            throw new ServiceError(INTERNAL_SERVER_ERROR, SERVER_ERROR);
        }
        if(students == null) { throw new ServiceError(NOT_FOUND, STUDENT_NOT_FOUND); }
        return students;
    }
    public ResponseEntity<String> addNewStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ServiceError(INTERNAL_SERVER_ERROR, SERVER_ERROR);
        }
        return new ResponseEntity<>( "Student added", OK);
    }

    public ResponseEntity<String> deleteStudentById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.load(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ServiceError(INTERNAL_SERVER_ERROR, SERVER_ERROR);
        }
        return new ResponseEntity<>("Student with id = " + id + ", deleted successfully", OK);
    }

    public ResponseEntity<String> updateStudentOrLog(BaseModel studentOrLog){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(studentOrLog);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ServiceError(INTERNAL_SERVER_ERROR, SERVER_ERROR);
        }
        return new ResponseEntity<>("Element with id = " + studentOrLog.getId() + ", was added successfully", OK);
    }
}
