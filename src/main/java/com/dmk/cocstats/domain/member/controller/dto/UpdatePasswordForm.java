package com.dmk.cocstats.domain.member.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordForm {

    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
