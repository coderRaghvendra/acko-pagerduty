package com.larztalk.backend.team;

import com.larztalk.backend.BaseEntityAudit;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Entity
@Table(name = "team")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Team extends BaseEntityAudit {

    @Builder.Default
    private String name = "";
}
