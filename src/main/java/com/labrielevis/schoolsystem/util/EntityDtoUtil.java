package com.labrielevis.schoolsystem.util;

import com.labrielevis.schoolsystem.dataacceslayer.Section;
import com.labrielevis.schoolsystem.servicelayer.SectionDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDtoUtil {

    public static SectionDTO toDTO(Section Section){
        SectionDTO dto = new SectionDTO();
        BeanUtils.copyProperties(Section, dto);
        return dto;
    }
    public static Section toEntity(SectionDTO dto){
        Section section = new Section();
        BeanUtils.copyProperties(dto, section); //Source=dto  -  Target = product
        return section;
    }

    public static String generateSecIdString(){
        return UUID.randomUUID().toString();
    }

}
