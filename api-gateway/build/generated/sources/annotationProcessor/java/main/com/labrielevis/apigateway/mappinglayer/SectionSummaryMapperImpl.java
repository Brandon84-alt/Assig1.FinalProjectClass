package com.labrielevis.apigateway.mappinglayer;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-14T17:13:55-0400",
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

        SectionDetails.SectionDetailsBuilder sectionDetails = SectionDetails.builder();

        sectionDetails.courseNumber( summaryModel.getCourseNumber() );
        sectionDetails.roomNumber( summaryModel.getRoomNumber() );

        return sectionDetails.build();
    }
}
