package org.apiman.client.domain.action;

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
public class Action implements Serializable {

    private ActionType type;
    private String organizationId;
    private String entityId;
    private String entityVersion;
}
