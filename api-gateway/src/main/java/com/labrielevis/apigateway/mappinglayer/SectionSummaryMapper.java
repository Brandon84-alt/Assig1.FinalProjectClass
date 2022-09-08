package com.labrielevis.apigateway.mappinglayer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionSummaryMapper {
    public SectionSummaryModel responseModelToSummaryModel(SectionDetails responseModel);

    @Mappings({
            @Mapping(target = "sectionId", ignore = true)
    })
    public SectionDetails summaryModelToResponseModel(SectionSummaryModel summaryModel);

    public List<SectionSummaryModel> responseModelListToSummaryModelList(List<SectionDetails> responseModels);

    public List<SectionDetails> summaryModelListToResponseModelList(List<SectionSummaryModel> summaryModels);
}

