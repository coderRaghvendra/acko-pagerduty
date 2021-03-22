package com.larztalk.backend;

import com.larztalk.androidsdk.service.AbstractService;
import java.io.Serializable;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@SuperBuilder
public abstract class Dto implements Serializable {

    @Override
    public String toString() {
        return AbstractService.GSON.toJson(this);
    }

    public String prettyPrint() {
        return AbstractService.GSON_P.toJson(this);
    }
}
