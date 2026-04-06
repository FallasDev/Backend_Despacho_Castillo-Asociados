package com.accountancy.despacho_castillo_asociados.shared.utils;

public class HtmlContent {

    private static final String TEMPLATE_CODE = """
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Código de Verificación</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f5f6f8;
                        margin: 0;
                        padding: 0;
                        color: #2d3748;
                        line-height: 1.6;
                    }
                    .container {
                        max-width: 580px;
                        margin: 40px auto;
                        background-color: white;
                        border-radius: 12px;
                        overflow: hidden;
                        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
                    }
                    .header {
                        background: linear-gradient(135deg, #1e3a8a 0%%, #1e40af 100%%);
                        color: white;
                        padding: 32px 40px;
                        text-align: center;
                    }
                    .header h1 {
                        margin: 0;
                        font-size: 28px;
                        font-weight: 600;
                    }
                    .content {
                        padding: 40px;
                    }
                    .code-box {
                        background-color: #f1f5f9;
                        border: 2px dashed #64748b;
                        border-radius: 10px;
                        padding: 24px;
                        text-align: center;
                        margin: 28px 0;
                        font-size: 32px;
                        font-weight: bold;
                        letter-spacing: 8px;
                        color: #1e40af;
                        font-family: 'Courier New', Courier, monospace;
                    }
                    .footer {
                        background-color: #f8fafc;
                        padding: 24px 40px;
                        text-align: center;
                        font-size: 14px;
                        color: #64748b;
                        border-top: 1px solid #e2e8f0;
                    }
                    .highlight {
                        color: #1e40af;
                        font-weight: 600;
                    }
                    p {
                        margin: 16px 0;
                    }
                    .small {
                        font-size: 14px;
                        color: #64748b;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Hola %s,</h1>
                    </div>
                    <div class="content">
                        <p>Tu código de verificación es:</p>
                        <div class="code-box">%s</div>
                        <p>Este código es válido por <span class="highlight">15 minutos</span>.</p>
                        <p class="small">Si no solicitaste este código, por favor ignora este correo de forma segura.</p>
                    </div>
                    <div class="footer">
                        <p>Saludos cordiales,</p>
                        <p><strong>Despacho Castillo & Asociados</strong></p>
                    </div>
                </div>
            </body>
            </html>
            """;

    private static final String TEMPLATE_LOGIN = """
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Alerta de Inicio de Sesión</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f5f6f8;
                        margin: 0;
                        padding: 0;
                        color: #2d3748;
                        line-height: 1.6;
                    }
                    .container {
                        max-width: 580px;
                        margin: 40px auto;
                        background-color: white;
                        border-radius: 12px;
                        overflow: hidden;
                        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
                    }
                    .header {
                        background: linear-gradient(135deg, #1e3a8a 0%%, #1e40af 100%%);
                        color: white;
                        padding: 32px 40px;
                        text-align: center;
                    }
                    .header h1 {
                        margin: 0;
                        font-size: 28px;
                        font-weight: 600;
                    }
                    .content {
                        padding: 40px;
                    }
                    .alert-box {
                        background-color: #fef2f2;
                        border-left: 5px solid #dc2626;
                        border-radius: 6px;
                        padding: 20px;
                        margin: 24px 0;
                        color: #7f1d1d;
                    }
                    .safe {
                        background-color: #f0fdf4;
                        border-left: 5px solid #16a34a;
                        color: #14532d;
                        padding: 20px;
                    }
                    .footer {
                        background-color: #f8fafc;
                        padding: 24px 40px;
                        text-align: center;
                        font-size: 14px;
                        color: #64748b;
                        border-top: 1px solid #e2e8f0;
                    }
                    .highlight {
                        color: #1e40af;
                        font-weight: 600;
                    }
                    p {
                        margin: 16px 0;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Hola %s</h1>
                    </div>
                    <div class="content">
                        <p>Se ha detectado un <strong>nuevo inicio de sesión</strong> en tu cuenta.</p>
                        
                        <div class="alert-box">
                            <strong>Si no fuiste tú:</strong> por favor contacta inmediatamente con nuestro soporte.
                        </div>
                        
                        <div class="safe">
                            <strong>Si fuiste tú:</strong> puedes ignorar este mensaje con tranquilidad.
                        </div>
                    </div>
                    <div class="footer">
                        <p>Saludos cordiales,</p>
                        <p><strong>Despacho Castillo & Asociados</strong></p>
                    </div>
                </div>
            </body>
            </html>
            """;

    private final String TEMPLATE_IN_PROGRESS = """
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Estado de Formalitie</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f5f6f8;
                        margin: 0;
                        padding: 0;
                        color: #2d3748;
                        line-height: 1.6;
                    }
                    .container {
                        max-width: 580px;
                        margin: 40px auto;
                        background-color: white;
                        border-radius: 12px;
                        overflow: hidden;
                        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
                    }
                    .header {
                        background: linear-gradient(135deg, #1e3a8a 0%%, #1e40af 100%%);
                        color: white;
                        padding: 32px 40px;
                        text-align: center;
                    }
                    .header h1 {
                        margin: 0;
                        font-size: 28px;
                        font-weight: 600;
                    }
                    .content {
                        padding: 40px;
                    }
                    .footer {
                        background-color: #f8fafc;
                        padding: 24px 40px;
                        text-align: center;
                        font-size: 14px;
                        color: #64748b;
                        border-top: 1px solid #e2e8f0;
                    }
                    p {
                        margin: 16px 0;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Hola %s</h1>
                    </div>
                    <div class="content">
                        <p>Queremos informarte que tu tramite sobre el servicio %s ha cambiado su estado a "En Progreso".</p>
                        <p>Estamos trabajando para completar tu tramite lo antes posible. Te notificaremos cuando haya un nuevo cambio de estado.</p>
                    </div>
                    <div class="footer">
                        <p>Saludos cordiales,</p>
                        <p><strong>Despacho Castillo & Asociados</strong></p>
                    </div>
                </div>
        """;

    public String generateVerificationEmail(String name, String code) {
        return String.format(TEMPLATE_CODE, name, code);
    }


    public String generateInProgressEmail(String name, String serviceName) {
        return String.format(TEMPLATE_IN_PROGRESS, name, serviceName);
    }

    public String generateLoginAlertEmail(String name) {
        return String.format(TEMPLATE_LOGIN, name);
    }
}