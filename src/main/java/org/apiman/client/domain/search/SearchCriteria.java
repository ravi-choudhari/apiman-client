package org.apiman.client.domain.search;

import java.io.Serializable;
import java.util.ArrayList;
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
public class SearchCriteria implements Serializable {

    private List<SearchCriteriaFilter> filters = new ArrayList<SearchCriteriaFilter>();
    private OrderBy orderBy;
    private Paging paging;
}
