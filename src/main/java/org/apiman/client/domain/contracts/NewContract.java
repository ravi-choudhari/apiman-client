package org.apiman.client.domain.contracts;

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
public class NewContract implements Serializable {

    private String apiOrgId;
    private String apiId;
    private String apiVersion;
    private String planId;
}
