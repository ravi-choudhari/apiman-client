package org.apiman.client.domain.apis;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
public class StatusItem implements Serializable {

    private String id;
    private String name;
    private boolean done;
    private boolean optional;
    private String remediation;
}
