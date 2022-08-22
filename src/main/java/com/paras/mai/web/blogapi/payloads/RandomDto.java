package com.paras.mai.web.blogapi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RandomDto {
    Integer id;
    String uid;
    String appName;
    String appVersion;
    String appAuthor;
    String appSemanticVersion;
    String appMajorVersion;
    String appMinorVersion;
    String appPatchVersion;

    @Override
    public String toString() {
        return "RandomDto{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appAuthor='" + appAuthor + '\'' +
                ", appSemanticVersion='" + appSemanticVersion + '\'' +
                ", appMajorVersion='" + appMajorVersion + '\'' +
                ", appMinorVersion='" + appMinorVersion + '\'' +
                ", appPatchVersion='" + appPatchVersion + '\'' +
                '}';
    }
}
