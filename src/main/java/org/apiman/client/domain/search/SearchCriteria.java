package org.apiman.client.domain.search;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SearchCriteria {

    private List<SearchCriteriaFilter> filters;
    private OrderBy orderBy;
    private Paging paging;
}
