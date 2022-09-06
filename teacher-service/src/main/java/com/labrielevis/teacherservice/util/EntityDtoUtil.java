package com.labrielevis.teacherservice.util;

import com.labrielevis.teacherservice.dataaccesslayer.Teacher;
import com.labrielevis.teacherservice.servicelayer.TeacherDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDtoUtil {

    public static TeacherDTO toDTO(Teacher Teacher) {
        TeacherDTO dto = new TeacherDTO();
        BeanUtils.copyProperties(Teacher, dto);
        return dto;
    }

    public static Teacher toEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(dto, teacher);
        return teacher;
    }

    public static String generateSecIdString() {
        return UUID.randomUUID().toString();
    }

}
