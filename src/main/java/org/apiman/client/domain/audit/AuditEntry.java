package org.apiman.client.domain.audit;

import java.io.Serializable;
import java.util.Date;

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
public class AuditEntry implements Serializable {

    private Long id;
    private String who;
    private String organizationId;
    private AuditEntityType entityType;
    private String entityId;
    private String entityVersion;
    private Date createdOn;
    private AuditEntryType what;
    private String data;
}
