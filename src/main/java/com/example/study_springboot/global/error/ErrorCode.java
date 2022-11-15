package com.example.study_springboot.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),

    PASSWORD_MISMATCH(401, "Password MisMatch"),
    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),
    INVALID_REFRESH_TOKEN(401, "Invalid Refresh Token"),
    FEED_CANNOT_DELETE(401, "Feed Cannot Delete"),
    FEED_CANNOT_UPDATE(401, "Feed Cannot Update"),
    COMMENT_CANNOT_DELETE(401, "Comment Not Delete"),
    COMMENT_CANNOT_UPDATE(401, "Comment Cannot Update"),
    FEIGN_UN_AUTHORIZED(401, "Feign Un Authorized"),

    FEIGN_FORBIDDEN(403, "Feign Forbidden"),

    USER_NOT_FOUND(404, "User Not Found"),
    FEED_NOT_FOUND(404, "Feed Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    USER_EXIST(409, "User Exist"),

    FEIGN_EXPIRED_TOKEN(419, "Feign Expired Token"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;

}
