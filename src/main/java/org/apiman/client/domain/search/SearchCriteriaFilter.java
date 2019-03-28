package org.apiman.client.domain.search;

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
public class SearchCriteriaFilter {

    private String name;
    private String value;
    private SearchCriteriaFilterOperator operator;
}
