package com.vladimirpandurov.javacorneradmin.web;

import com.vladimirpandurov.javacorneradmin.dto.CourseDTO;
import com.vladimirpandurov.javacorneradmin.dto.InstructorDTO;
import com.vladimirpandurov.javacorneradmin.entity.User;
import com.vladimirpandurov.javacorneradmin.service.CourseService;
import com.vladimirpandurov.javacorneradmin.service.InstructorService;
import com.vladimirpandurov.javacorneradmin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@CrossOrigin
public class InstructorRestController {

    private InstructorService instructorService;

    private UserService userService;

    private CourseService courseService;

    public InstructorRestController(InstructorService instructorService, UserService userService, CourseService courseService) {
        this.instructorService = instructorService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public Page<InstructorDTO> searchInstructors(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name= "size", defaultValue = "5") int size){
        return instructorService.findInstructorByName(keyword, page, size);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public List<InstructorDTO> findAllInstructors(){
        return instructorService.fetchInstructors();
    }

    @DeleteMapping("/{instructorId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteInstructor(@PathVariable Long instructorId){
        instructorService.removeInstructor(instructorId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Adimn')")
    public InstructorDTO saveInstructor(@RequestBody InstructorDTO instructorDTO){
        User user = userService.loadUserByEmail(instructorDTO.getUser().getEmail());
        if(user != null) throw new RuntimeException("Email Already Exist");
        return instructorService.createInstructor(instructorDTO);
    }

    @PutMapping("/{instructorId}")
    @PreAuthorize("hasAuthority('Instructor')")
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long instructorId){
        instructorDTO.setInstructorId(instructorId);
        return instructorService.updateInstructor(instructorDTO);
    }

    @GetMapping("/{instructorId}/courses")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public Page<CourseDTO> coursesByInstructorId(@PathVariable Long instructorId,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size){
        return courseService.fetchCoursesForInstructor(instructorId, page, size);
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('Instructor')")
    public InstructorDTO loadInstructorByEmail(@RequestParam(name = "email", defaultValue = "") String email){
        return instructorService.loadInstructorByEmail(email);
    }
}
