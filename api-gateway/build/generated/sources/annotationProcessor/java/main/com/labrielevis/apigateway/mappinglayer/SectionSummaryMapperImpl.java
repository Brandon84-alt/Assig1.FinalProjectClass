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
public class SectionSummaryMapperImpl implements SectionSummaryMapper {

    @Override
    public SectionSummaryModel responseModelToSummaryModel(SectionDetails responseModel) {
        if ( responseModel == null ) {
            return null;
        }

        SectionSummaryModel sectionSummaryModel = new SectionSummaryModel();

        sectionSummaryModel.setCourseNumber( responseModel.getCourseNumber() );
        sectionSummaryModel.setRoomNumber( responseModel.getRoomNumber() );

        return sectionSummaryModel;
    }

    @Override
    public SectionDetails summaryModelToResponseModel(SectionSummaryModel summaryModel) {
        if ( summaryModel == null ) {
            return null;
        }

        SectionDetails sectionDetails = new SectionDetails();

        sectionDetails.setCourseNumber( summaryModel.getCourseNumber() );
        sectionDetails.setRoomNumber( summaryModel.getRoomNumber() );

        return sectionDetails;
    }

    @Override
    public List<SectionSummaryModel> responseModelListToSummaryModelList(List<SectionDetails> responseModels) {
        if ( responseModels == null ) {
            return null;
        }

        List<SectionSummaryModel> list = new ArrayList<SectionSummaryModel>( responseModels.size() );
        for ( SectionDetails sectionDetails : responseModels ) {
            list.add( responseModelToSummaryModel( sectionDetails ) );
        }

        return list;
    }

    @Override
    public List<SectionDetails> summaryModelListToResponseModelList(List<SectionSummaryModel> summaryModels) {
        if ( summaryModels == null ) {
            return null;
        }

        List<SectionDetails> list = new ArrayList<SectionDetails>( summaryModels.size() );
        for ( SectionSummaryModel sectionSummaryModel : summaryModels ) {
            list.add( summaryModelToResponseModel( sectionSummaryModel ) );
        }

        return list;
    }
}
