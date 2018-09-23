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
public class SearchQuery {

	private List<Filter> filters;
	private OrderBy orderBy;
	private int pageSize;
	private int page;
	private Paging paging;
}
