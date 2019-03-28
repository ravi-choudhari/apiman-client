package org.apiman.client.domain.plan;

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
public class NewPlanVersion {

    private String version;
    private boolean clone;
    private String cloneVersion;
}
