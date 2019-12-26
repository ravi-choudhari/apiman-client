package org.apiman.client.domain.summary;

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
public class ApiPlanSummary implements Serializable {

    private String planId;
    private String planName;
    private String planDescription;
    private String version;
}
