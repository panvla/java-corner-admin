package com.vladimirpandurov.javacorneradmin.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.vladimirpandurov.javacorneradmin.dto.InstructorDTO;
import com.vladimirpandurov.javacorneradmin.entity.Instructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InstructorMapper {

    public InstructorDTO fromInstructor(Instructor instructor){
        InstructorDTO instructorDTO = new InstructorDTO();
        System.out.println("****" + instructor.getUser());
        BeanUtils.copyProperties(instructor, instructorDTO);
        return instructorDTO;
    }

    public Instructor fromInstructorDTO(InstructorDTO instructorDTO){
        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(instructorDTO, instructor);
        return instructor;
    }
}
