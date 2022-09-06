package com.labrielevis.teacherservice.util;

import com.labrielevis.teacherservice.dataaccesslayer.Teacher;
import com.labrielevis.teacherservice.servicelayer.TeacherDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        BeanUtils.copyProperties(Teacher, dto);
        return dto;
    }

    public static Teacher toEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();

    }

}
