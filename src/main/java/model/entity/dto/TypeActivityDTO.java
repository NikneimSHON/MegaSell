package model.entity.dto;

import lombok.Getter;

@Getter
public enum TypeActivityDTO {
    ALLOWED("Allowed"), BLOCKED("Blocked");

    private final String ActivityDescription;

    TypeActivityDTO(String ActivityDescription) {
        this.ActivityDescription = ActivityDescription;
    }
    }
