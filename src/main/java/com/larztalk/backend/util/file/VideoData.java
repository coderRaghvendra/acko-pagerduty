package com.larztalk.backend.util.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by raghvendra.mishra on 10/08/20.
 */
@Getter
@Setter
@Builder
public class VideoData {

    private String videoUrl;
    private String thumbnailUrl;
}
