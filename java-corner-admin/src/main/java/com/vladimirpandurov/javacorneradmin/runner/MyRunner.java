package com.vladimirpandurov.javacorneradmin.runner;

import com.vladimirpandurov.javacorneradmin.dto.CourseDTO;
import com.vladimirpandurov.javacorneradmin.dto.InstructorDTO;
import com.vladimirpandurov.javacorneradmin.dto.StudentDTO;
import com.vladimirpandurov.javacorneradmin.dto.UserDTO;
import com.vladimirpandurov.javacorneradmin.entity.Student;
import com.vladimirpandurov.javacorneradmin.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {

    private RoleService roleService;

    private UserService userService;

    private InstructorService instructorService;

    private CourseService courseService;

    private StudentService studentService;


    public MyRunner(RoleService roleService, UserService userService, InstructorService instructorService, CourseService courseService, StudentService studentService) {
        this.roleService = roleService;
        this.userService = userService;
        this.instructorService = instructorService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
        createInstructors();
        createCourses();
        StudentDTO studentDTO = createStudent();
        assingCourseToStudent(studentDTO);
    }

    private void createAdmin() {
        userService.createUser("admin@gmail.com", "1234");
        userService.assignRoleToUser("admin@gmail.com", "Admin");
    }

    private void createRoles(){
        Arrays.asList("Admin", "Instructor", "Student").forEach(role-> roleService.createRole(role));
    }

    private void createInstructors(){
        for(int i = 0; i<10; i++){
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setFirstName("instructor" + i + "FN");
            instructorDTO.setLastName("instructor" + i + "LN");
            instructorDTO.setSummary("master" + i);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail("instructor" + i + "@gmail.com");
            userDTO.setPassword("1234");
            instructorDTO.setUser(userDTO);
            instructorService.createInstructor(instructorDTO);
        }
    }

    private void createCourses() {
        for(int i = 0; i<20; i++){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourseDescription("Java"+ i);
            courseDTO.setCourseDuration(i + "Hours");
            courseDTO.setCourseName("Java Course" + i);
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setInstructorId(1L);
            courseDTO.setInstructor(instructorDTO);
            courseService.createCourse(courseDTO);
        }
    }

    private StudentDTO createStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("studentFN");
        studentDTO.setLastName("studentLN");
        studentDTO.setLevel("intermediate");
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("student@gmail.com");
        userDTO.setPassword("1234");
        studentDTO.setUser(userDTO);
        return studentService.createStudent(studentDTO);
    }

    private void assingCourseToStudent(StudentDTO studentDTO){
        courseService.assignStudentToCourse(1L, studentDTO.getStudentId());
    }

}
