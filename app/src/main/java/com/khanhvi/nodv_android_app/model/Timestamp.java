package com.khanhvi.nodv_android_app.model;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Timestamp {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
