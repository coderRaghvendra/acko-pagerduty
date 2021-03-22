package com.larztalk.backend;

import com.larztalk.androidsdk.service.AbstractService;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 16/10/20.
 */
@NoArgsConstructor
@SuperBuilder
public abstract class EntityDto implements Serializable {

    @Override
    public String toString() {
        return AbstractService.GSON.toJson(this);
    }

    public String prettyPrint() {
        return AbstractService.GSON_P.toJson(this);
    }
}
