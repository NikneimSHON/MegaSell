package model.entity.dto;

import lombok.Getter;

@Getter
public enum TypePermissionDTO {
    ADMIN("Admin"), USER("User");

    private final String permissionDescription;

    TypePermissionDTO(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

}
