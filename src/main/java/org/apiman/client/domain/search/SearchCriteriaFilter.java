package org.apiman.client.domain.search;

import java.io.Serializable;

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
public class SearchCriteriaFilter implements Serializable {

    private String name;
    private String value;
    private SearchCriteriaFilterOperator operator;
}
