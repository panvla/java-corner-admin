package com.vladimirpandurov.javacorneradmin.utility;

import com.vladimirpandurov.javacorneradmin.dao.*;
import com.vladimirpandurov.javacorneradmin.entity.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class OperationUtility {

    public static void usersOperations(UserDao userDao){
        //createUsers(userDao);
        //updateUsers(userDao);
        //deleteUser(userDao);
        //fetchUsers(userDao);
    }

    public static void rolesOperations(RoleDao roleDao){
        createRoles(roleDao);
        updateRole(roleDao);
        deleteRole(roleDao);
        fetchRole(roleDao);
    }

    public static void instructorsOperations(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao){
        createInstructors(userDao, instructorDao, roleDao);
        updateInstructor(instructorDao);
        removeInstructor(instructorDao);
        fetchInstructors(instructorDao);
    }

    public static void studentOperations(UserDao userDao, StudentDao studentDao,RoleDao roleDao){
        createStudents(userDao, studentDao, roleDao);
        updateStudent(studentDao);
        removeStudent(studentDao);
        fetchStudents(studentDao);
    }

    private static void fetchRole(RoleDao roleDao){
        roleDao.findAll().forEach(role -> System.out.println(role.toString()));
    }


    private static void deleteRole(RoleDao roleDao) {
        roleDao.deleteById(1L);
    }


    private static void createRoles(RoleDao roleDao){
        Role role1 = new Role("Admin");
        roleDao.save(role1);
        Role role2 = new Role("Instructor");
        roleDao.save(role2);
        Role role3 = new Role("Student");
        roleDao.save(role3);
    }

    private static void updateRole(RoleDao roleDao){
        Role role = roleDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Role Not Found"));
        role.setName("NewAdmin");
        roleDao.save(role);
    }

    public static void assignRolesToUsers(UserDao userDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Admin");
        if(role == null) throw new EntityNotFoundException("Role Not Found");
        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userDao.save(user);
        });
    }

    private static void createUsers(UserDao userDao){
        User user1 = new User("user1@gmail.com", "pass1");
        userDao.save(user1);
        User user2 = new User("user2@gmail.com", "pass2");
        userDao.save(user2);
        User user3 = new User("user3@gmail.com", "pass3");
        userDao.save(user3);
        User user4 = new User("user4@gmail.com", "pass4");
        userDao.save(user4);
    }

    private static void updateUsers(UserDao userDao){
        User user = userDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        user.setEmail("newEmail@gmail.com");
        userDao.save(user);
    }

    private static void deleteUser(UserDao userDao){
        User user = userDao.findById(3L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        //userDao.delete(user);
        userDao.deleteById(3L);
    }

    private static void fetchUsers(UserDao userDao){
        userDao.findAll().forEach(user -> System.out.println(user.toString()));
    }

    private static void fetchInstructors(InstructorDao instructorDao) {
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    private static void removeInstructor(InstructorDao instructorDao) {
        instructorDao.deleteById(2L);

    }

    private static void updateInstructor(InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Instructor");
        instructorDao.save(instructor);
    }

    private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Instructor");
        if(role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("instructorUser1@gmail.com", "pass1");
        userDao.save(user1);
        user1.assignRoleToUser(role);
        Instructor instructor1 = new Instructor("instructor1FN", "instructor1LN","Experienced Instructor", user1);
        instructorDao.save(instructor1);

        User user2 = new User("instructorUser2@gmail.com", "pass2");
        userDao.save(user2);
        user2.assignRoleToUser(role);
        Instructor instructor2 = new Instructor("instructor2FN", "instructor2LN","Experienced Instructor", user2);
        instructorDao.save(instructor2);
    }

    private static void fetchStudents(StudentDao studentDao) {
        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
    }

    private static void removeStudent(StudentDao studentDao) {
        studentDao.deleteById(1L);
    }

    private static void updateStudent(StudentDao studentDao) {
        Student student = studentDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setFirstName("updatedStdFN");
        student.setLastName("updatedStdLN");
        studentDao.save(student);
    }

    private static void createStudents(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Student");
        if(role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("stdUser1@gmail.com", "pass1");
        userDao.save(user1);
        user1.assignRoleToUser(role);
        Student student1 = new Student("student1FN", "student1LN", "master", user1);
        studentDao.save(student1);

        User user2 = new User("stdUser2@gmail.com", "pass2");
        userDao.save(user2);
        user2.assignRoleToUser(role);
        Student student2 = new Student("student2FN", "student2LN", "phd", user2);
        studentDao.save(student2);
    }

    public static void coursesOperations(CourseDao courseDao, InstructorDao instructorDao, StudentDao studentDao){
        createCourses(courseDao, instructorDao);
        updateCourses(courseDao);
        deleteCourse(courseDao);
        fetchCourses(courseDao);
        assignStudentsToCourse(courseDao, studentDao);
        fetchCoursesForStudent(courseDao);
    }

    private static void fetchCoursesForStudent(CourseDao courseDao) {
        courseDao.getCoursesByStudentId(1L).forEach(course -> System.out.println(course.toString()));
    }

    private static void assignStudentsToCourse(CourseDao courseDao, StudentDao studentDao) {
        Optional<Student> student1 = studentDao.findById(1L);
        Optional<Student> student2 = studentDao.findById(2L);
        Course course = courseDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        student1.ifPresent(course::assignStudentToCourse);
        student2.ifPresent(course::assignStudentToCourse);
        courseDao.save(course);
    }

    private static void fetchCourses(CourseDao courseDao) {
        courseDao.findAll().forEach(course -> System.out.println(course.toString()));
    }

    private static void deleteCourse(CourseDao courseDao) {
        courseDao.deleteById(2L);
    }

    private static void updateCourses(CourseDao courseDao) {
        Course course = courseDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Course Not Found"));
        course.setCourseDuration("20 Hours");
        courseDao.save(course);
    }

    private static void createCourses(CourseDao courseDao, InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Instructor not Found!"));

        Course course1 = new Course("Hibernate", "5 Hourse", "Intruduction to Hibernate", instructor);
        courseDao.save(course1);
        Course course2 = new Course("Spring Data JPA", "10 Hours", "Master Spring Data JPA", instructor);
        courseDao.save(course2);
    }

}
