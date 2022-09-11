package com.labrielevis.apigateway.util;

import com.labrielevis.apigateway.mappinglayer.*;
import org.springframework.beans.BeanUtils;

import java.util.Random;

    public class EntityDTOUtil {

        public static SectionSummaryModel toSectionSummaryModel(SectionDetails dto) {
            SectionSummaryModel sectionSummaryModel = new SectionSummaryModel();
            BeanUtils.copyProperties(dto, sectionSummaryModel);
            return sectionSummaryModel;
        }

        public static SectionDetails toSectionDetails(SectionSummaryModel sectionSummaryModel) {
            SectionDetails dto = new SectionDetails();
            BeanUtils.copyProperties(sectionSummaryModel, dto);
            return dto;
        }
        public static TeacherSummaryModel toTeacherSummaryModel(TeacherDetails dto) {
            TeacherSummaryModel teacherSummaryModel = new TeacherSummaryModel();
            BeanUtils.copyProperties(dto, teacherSummaryModel);
            return teacherSummaryModel;
        }

        public static TeacherDetails toTeacherDetails(TeacherSummaryModel teacherSummaryModel) {
            TeacherDetails dto = new TeacherDetails();
            BeanUtils.copyProperties(teacherSummaryModel, dto);
            return dto;
        }

        public static void setCollegeAggregate (SectionTeacherRequestContext sectionTeacherRequestContext) {
            CollegeAggregate collegeAggregate = new CollegeAggregate();
            collegeAggregate.setSectionDetails(sectionTeacherRequestContext.getSectionDetailsList());
            sectionTeacherRequestContext.setCollegeAggregate(collegeAggregate);
        }

        public static CollegeAggregate getCollegeAggregate (SectionTeacherRequestContext sectionTeacherRequestContext) {
            if (sectionTeacherRequestContext.getCollegeAggregate().getTeacherDetails() == null) {
                sectionTeacherRequestContext.getCollegeAggregate().setTeacherDetails(sectionTeacherRequestContext.getTeacherDetailsList());
            }
            return sectionTeacherRequestContext.getCollegeAggregate();
        }





    }


