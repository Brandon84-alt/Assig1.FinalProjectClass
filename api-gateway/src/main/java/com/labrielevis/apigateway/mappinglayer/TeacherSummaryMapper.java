package com.labrielevis.apigateway.mappinglayer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherSummaryMapper {

    public TeacherSummaryModel responseModelToSummaryModel(TeacherDetails responseModel);

    @Mappings({
            @Mapping(target = "teacherId", ignore = true)

    })
    public TeacherDetails summaryModelToResponseModel(TeacherSummaryModel summaryModel);
}
