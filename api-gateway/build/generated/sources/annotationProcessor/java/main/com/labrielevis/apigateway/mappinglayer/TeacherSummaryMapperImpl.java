package com.labrielevis.apigateway.mappinglayer;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-15T01:54:01-0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TeacherSummaryMapperImpl implements TeacherSummaryMapper {

    @Override
    public TeacherSummaryModel responseModelToSummaryModel(TeacherDetails responseModel) {
        if ( responseModel == null ) {
            return null;
        }

        TeacherSummaryModel teacherSummaryModel = new TeacherSummaryModel();

        teacherSummaryModel.setFirstName( responseModel.getFirstName() );
        teacherSummaryModel.setLastName( responseModel.getLastName() );
        teacherSummaryModel.setEmail( responseModel.getEmail() );
        teacherSummaryModel.setSectionId( responseModel.getSectionId() );

        return teacherSummaryModel;
    }

    @Override
    public TeacherDetails summaryModelToResponseModel(TeacherSummaryModel summaryModel) {
        if ( summaryModel == null ) {
            return null;
        }

        TeacherDetails.TeacherDetailsBuilder teacherDetails = TeacherDetails.builder();

        teacherDetails.firstName( summaryModel.getFirstName() );
        teacherDetails.lastName( summaryModel.getLastName() );
        teacherDetails.email( summaryModel.getEmail() );
        teacherDetails.sectionId( summaryModel.getSectionId() );

        return teacherDetails.build();
    }
}
