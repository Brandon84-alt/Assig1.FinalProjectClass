package com.labrielevis.apigateway.mappinglayer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-08T00:02:54-0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4.1 (Amazon.com Inc.)"
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

        return teacherSummaryModel;
    }

    @Override
    public TeacherDetails summaryModelToResponseModel(TeacherSummaryModel summaryModel) {
        if ( summaryModel == null ) {
            return null;
        }

        TeacherDetails teacherDetails = new TeacherDetails();

        teacherDetails.setFirstName( summaryModel.getFirstName() );
        teacherDetails.setLastName( summaryModel.getLastName() );

        return teacherDetails;
    }

    @Override
    public List<TeacherSummaryModel> responseModelListToSummaryModelList(List<TeacherDetails> responseModels) {
        if ( responseModels == null ) {
            return null;
        }

        List<TeacherSummaryModel> list = new ArrayList<TeacherSummaryModel>( responseModels.size() );
        for ( TeacherDetails teacherDetails : responseModels ) {
            list.add( responseModelToSummaryModel( teacherDetails ) );
        }

        return list;
    }

    @Override
    public List<TeacherDetails> summaryModelListToResponseModelList(List<TeacherSummaryModel> summaryModels) {
        if ( summaryModels == null ) {
            return null;
        }

        List<TeacherDetails> list = new ArrayList<TeacherDetails>( summaryModels.size() );
        for ( TeacherSummaryModel teacherSummaryModel : summaryModels ) {
            list.add( summaryModelToResponseModel( teacherSummaryModel ) );
        }

        return list;
    }
}
