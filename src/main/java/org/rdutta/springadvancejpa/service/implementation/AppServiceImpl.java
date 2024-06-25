package org.rdutta.springadvancejpa.service.implementation;

import jakarta.transaction.Transactional;
import org.rdutta.springadvancejpa.dao.AppDAO;
import org.rdutta.springadvancejpa.entity.Course;
import org.rdutta.springadvancejpa.entity.Instructor;
import org.rdutta.springadvancejpa.entity.InstructorDetails;
import org.rdutta.springadvancejpa.entity.Student;
import org.rdutta.springadvancejpa.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {
    private final AppDAO appDAO;

    @Autowired
    public AppServiceImpl(AppDAO appDAO) {
        this.appDAO = appDAO;
    }


    @Transactional
    @Override
    public void createInstructor(Instructor instructor) {
        appDAO.save(instructor);
    }

    @Override
    public Instructor getInstructorByInstructorId(UUID instructorId) {
        return appDAO.findByInstructorId(instructorId);
    }

    @Transactional
    @Override
    public void removeInstructorById(UUID instructorId) {
        appDAO.delete(instructorId);
    }

    @Override
    public InstructorDetails getInstructorDetailsById(UUID instructorDetailId) {
        return appDAO.findInstructorDetailsByInstructorId(instructorDetailId);
    }


    // Delete All relation
    @Transactional
    @Override
    public void deleteInstructorDetailsByInstructorId(UUID instructorId) {
        appDAO.deleteInstructorDetailsByInstructorId(instructorId);
    }

    @Override
    public List<Course> getCoursesByInstructorId(UUID instructorId) {
        return appDAO.findCoursesByInstructorId(instructorId);
    }

    @Override
    public Instructor getInstructorByInstructorIdJoinFetch(UUID instructorId) {
        return appDAO.findInstructorByInstructorIdJoinFetch(instructorId);
    }

    @Transactional
    @Override
    public void deleteInstructorById(UUID instructorId) {
        appDAO.deleteInstructorByInstructorId(instructorId);
    }

    @Transactional
    @Override
    public void saveCourseWithReviews(Course course) {
        appDAO.saveCourseWithReviews(course);
    }

    @Override
    public Course getCourseReview(UUID courseId) {
        return appDAO.filerCourseAndReviews(courseId);
    }

    @Transactional
    @Override
    public void deleteCourseReview(UUID courseId) {
        appDAO.deleteCourseWithReviews(courseId);
    }

    @Transactional
    @Override
    public void saveCourseStudent(Course course) {
        appDAO.saveCourseWithStudents(course);
    }

    @Override
    public Course getCourseStudentByCourseId(UUID courseId) {
        return appDAO.filerCourseAndStudentsByCourseId(courseId);
    }


    //Delete only the instructorDetails
}
