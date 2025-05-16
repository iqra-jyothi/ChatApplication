package com.jyo.practiceprojectformList.DTO;
import lombok.Data;

@Data
public class ProfileUpdateRequest {
    private String email;
    private String name;
    private String profilePhotoUrl;
}
