package org.apiman.client.domain.summary;

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
public class ResponseStatsDataPoint extends HistogramDataPoint implements Comparable<ResponseStatsDataPoint> {

    private long total;
    private long failures;
    private long errors;
    
	@Override
	public int compareTo(ResponseStatsDataPoint o) {
		return 0;
	}
}
