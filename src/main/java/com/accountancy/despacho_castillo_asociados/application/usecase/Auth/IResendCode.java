package com.accountancy.despacho_castillo_asociados.application.usecase.Auth;

import jakarta.mail.MessagingException;

import java.util.Optional;

public interface IResendCode {

    void resendCode(String email) throws MessagingException;

}
