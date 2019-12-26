package org.apiman.client.domain.plugin;

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
public class PluginSummary implements Serializable {

    private Long id;
    private String groupId;
    private String artifactId;
    private String version;
    private String classifier;
    private String type;
    private String name;
    private String description;
    private String createdBy;
    private Date createdOn;
}
