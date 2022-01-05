package grupo7.egg.nutrividas.mail;


import grupo7.egg.nutrividas.entidades.Menu;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import org.springframework.stereotype.Component;

@Component
public class Template {

    public String getTemplateConfirmMail(String name,Long token){

        return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "<meta name=\"x-apple-disable-message-reformatting\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "<title>Nuevo mensaje 5</title>\n" +
                "<!--[if (mso 16)]>\n" +
                "<style type=\"text/css\">\n" +
                "a {text-decoration: none;}\n" +
                "</style>\n" +
                "<![endif]-->\n" +
                "<!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "<!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "<o:OfficeDocumentSettings>\n" +
                "<o:AllowPNG></o:AllowPNG>\n" +
                "<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "</o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "<!--[if !mso]><!-- -->\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "<!--<![endif]-->\n" +
                "<style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "padding:0;\n" +
                "}\n" +
                ".es-button {\n" +
                "mso-style-priority:100!important;\n" +
                "text-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "color:inherit!important;\n" +
                "text-decoration:none!important;\n" +
                "font-size:inherit!important;\n" +
                "font-family:inherit!important;\n" +
                "font-weight:inherit!important;\n" +
                "line-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "display:none;\n" +
                "float:left;\n" +
                "overflow:hidden;\n" +
                "width:0;\n" +
                "max-height:0;\n" +
                "line-height:0;\n" +
                "mso-hide:all;\n" +
                "}\n" +
                "[data-ogsb] .es-button {\n" +
                "border-width:0!important;\n" +
                "padding:10px 30px 10px 30px!important;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:36px!important; text-align:left } h2 { font-size:26px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:12px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:20px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<div class=\"es-wrapper-color\" style=\"background-color:#EFEFEF\">\n" +
                "<!--[if gte mso 9]>\n" +
                "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "<v:fill type=\"tile\" color=\"#efefef\"></v:fill>\n" +
                "</v:background>\n" +
                "<![endif]-->\n" +
                "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#EFEFEF\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#ffffff\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/logonutri.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"300\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/heroimg.jpg\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"560\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h1 style=\"Margin:0;line-height:26px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:26px;font-style:normal;font-weight:bold;color:#1bac91\">Bienvenid@ "+name+"</h1></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" class=\"es-m-p0r es-m-p0l\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:40px;padding-right:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#a9a9a9;font-size:14px\"><strong>¡ Gracias por formar parte de esta experiencia !</strong></p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">Para continuar por favor confirma tu email</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\">\n" +
                "<form method=\"POST\" action=\"http://localhost:8080/confirmar\">\n" +
                "<input type=\"hidden\" name=\"tokenMail\" value="+token+">\n" +
                "<span class=\"es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#1BAC91;border-width:0px;display:inline-block;border-radius:6px;width:auto\"><button type=\"submit\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;border-style:solid;border-color:#1BAC91;border-width:10px 30px 10px 30px;display:inline-block;background:#1BAC91;border-radius:6px;font-size:20px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;color:#FFFFFF;width:auto;text-align:center;border-left-width:30px;border-right-width:30px\">Confirmar e-mail</button></span>\n" +
                "</form></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#F2F9F8\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:25px;background-color:#f2f9f8\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:30px;color:#999999;font-size:20px\">¿Cómo continuar colaborando?</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#F2F9F8\" style=\"Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;padding-top:25px;background-color:#f2f9f8\">\n" +
                "<!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:197px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:167px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;background-color:#ffffff;border-radius:14px\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/fileregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Chequea&nbsp;la lista de comedores</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#999999;font-size:14px\">Tenemos muchos comedores que necesitan de nuestra ayuda. ¡Conócelos!.<br><br><br></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "<td class=\"es-hidden\" style=\"padding:0;Margin:0;width:30px\"></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:167px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:167px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;border-radius:15px;background-color:#ffffff\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/storeregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Seleccioná las canastas</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#999999;font-size:14px\">Cada comedor tiene su lista de canastas especialmente diseñadas según las necesidades de sus miembros.</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:30px\"></td><td style=\"width:166px\" valign=\"top\"><![endif]-->\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;width:166px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;background-color:#ffffff;border-radius:13px\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/creditcardregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\"><strong>Completá la compra</strong></h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#999999;font-size:14px\">Una vez elegida la cantidad de canastas, realiza el pago y nosotros nos encargamos de hacerles llegar tu donación.</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#000000;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#000000\" align=\"center\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#ffffff\" bgcolor=\"#ffffff\" align=\"left\">\n" +
                "<!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\"><strong>Monteagudo 275, C1437 CABA<br>Email: nutrividas.info@gmail.com</strong></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-l\" align=\"right\" style=\"padding:0;Margin:0;padding-top:5px;font-size:0px\">\n" +
                "<table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Facebook\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/facebook-circle-colored.png\" alt=\"Fb\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Twitter\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/twitter-circle-colored.png\" alt=\"Tw\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><img title=\"Instagram\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/instagram-circle-colored.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr class=\"es-mobile-hidden\">\n" +
                "<td height=\"30\" align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;padding-bottom:10px;padding-left:20px;padding-right:20px;background-color:#ffffff\" bgcolor=\"#ffffff\" align=\"left\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:20px;Margin:0;font-size:0\">\n" +
                "<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:none;height:1px;width:100%;margin:0px\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-l\" align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">Copyright© 2010-2021 |&nbsp;<strong>&nbsp;Nutrividas&nbsp;</strong>&nbsp;| Todos los derechos reservados</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

    public String getTemplateConfirmMailNutri(String name, Long token){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "<meta name=\"x-apple-disable-message-reformatting\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "<title>Nuevo mensaje 5</title>\n" +
                "<!--[if (mso 16)]>\n" +
                "<style type=\"text/css\">\n" +
                "a {text-decoration: none;}\n" +
                "</style>\n" +
                "<![endif]-->\n" +
                "<!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "<!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "<o:OfficeDocumentSettings>\n" +
                "<o:AllowPNG></o:AllowPNG>\n" +
                "<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "</o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "<!--[if !mso]><!-- -->\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "<!--<![endif]-->\n" +
                "<style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "padding:0;\n" +
                "}\n" +
                ".es-button {\n" +
                "mso-style-priority:100!important;\n" +
                "text-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "color:inherit!important;\n" +
                "text-decoration:none!important;\n" +
                "font-size:inherit!important;\n" +
                "font-family:inherit!important;\n" +
                "font-weight:inherit!important;\n" +
                "line-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "display:none;\n" +
                "float:left;\n" +
                "overflow:hidden;\n" +
                "width:0;\n" +
                "max-height:0;\n" +
                "line-height:0;\n" +
                "mso-hide:all;\n" +
                "}\n" +
                "[data-ogsb] .es-button {\n" +
                "border-width:0!important;\n" +
                "padding:10px 30px 10px 30px!important;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:36px!important; text-align:left } h2 { font-size:26px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:12px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:20px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<div class=\"es-wrapper-color\" style=\"background-color:#EFEFEF\">\n" +
                "<!--[if gte mso 9]>\n" +
                "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "<v:fill type=\"tile\" color=\"#efefef\"></v:fill>\n" +
                "</v:background>\n" +
                "<![endif]-->\n" +
                "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#EFEFEF\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#ffffff\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/logonutri.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"300\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/nutricionistacardmin_1_opt_1.jpg\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"560\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h1 style=\"Margin:0;line-height:26px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:26px;font-style:normal;font-weight:bold;color:#1bac91\">Bienvenid@ "+name+"</h1></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" class=\"es-m-p0r es-m-p0l\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:40px;padding-right:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#a9a9a9;font-size:14px\"><strong>¡ Gracias por formar parte de esta experiencia !</strong></p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">Para continuar por favor confirma tu email</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\">\n" +
                "<form method=\"POST\" action=\"http://localhost:8080/confirmar\">\n" +
                "<input type=\"hidden\" name=\"tokenMail\" value="+token+">\n" +
                "<span class=\"es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#1BAC91;border-width:0px;display:inline-block;border-radius:6px;width:auto\"><button type=\"submit\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;border-style:solid;border-color:#1BAC91;border-width:10px 30px 10px 30px;display:inline-block;background:#1BAC91;border-radius:6px;font-size:20px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;color:#FFFFFF;width:auto;text-align:center;border-left-width:30px;border-right-width:30px\">Confirmar e-mail</button></span>\n" +
                "</form></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#F2F9F8\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:25px;background-color:#f2f9f8\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:30px;color:#999999;font-size:20px\">¿Cómo continuar colaborando?</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#F2F9F8\" style=\"Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;padding-top:25px;background-color:#f2f9f8\">\n" +
                "<!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:197px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:167px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;background-color:#ffffff;border-radius:14px\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/fileregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Chequea&nbsp;la lista de comedores</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\">Tenemos muchos comedores que necesitan de nuestra ayuda.<br>&nbsp;¡Conócelos!.<br><br><br><br></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "<td class=\"es-hidden\" style=\"padding:0;Margin:0;width:30px\"></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:167px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:167px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;border-radius:15px;background-color:#ffffff\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/facesolid240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Elaborá un plan</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\">Accedé a los datos médicos de cada uno de los niños y elaborá un plan especialmente diseado&nbsp;para sus requerimientos nutricionales.<br></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:30px\"></td><td style=\"width:166px\" valign=\"top\"><![endif]-->\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;width:166px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;background-color:#ffffff;border-radius:13px\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/basketregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\"><strong>Creá las canasta</strong></h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\">Accedé al listado de los productos y el base al pana nutricional elaborado creá las canastas con los elementos necesarios para que el comedor elaboré el menú.</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#000000;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#000000\" align=\"center\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#ffffff\" bgcolor=\"#ffffff\" align=\"left\">\n" +
                "<!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\"><strong>Monteagudo 275, C1437 CABA<br>Email: info@nutrividas.com</strong></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-l\" align=\"right\" style=\"padding:0;Margin:0;padding-top:5px;font-size:0px\">\n" +
                "<table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Facebook\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/facebook-circle-colored.png\" alt=\"Fb\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Twitter\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/twitter-circle-colored.png\" alt=\"Tw\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><img title=\"Instagram\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/instagram-circle-colored.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr class=\"es-mobile-hidden\">\n" +
                "<td height=\"30\" align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;padding-bottom:10px;padding-left:20px;padding-right:20px;background-color:#ffffff\" bgcolor=\"#ffffff\" align=\"left\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:20px;Margin:0;font-size:0\">\n" +
                "<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:none;height:1px;width:100%;margin:0px\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-l\" align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">Copyright© 2010-2021 |&nbsp;<strong>&nbsp;Nutrividas&nbsp;</strong>&nbsp;| Todos los derechos reservados</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

    public String getTemplateConfirmComedor(String name,Long token){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "<meta name=\"x-apple-disable-message-reformatting\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "<title>Nuevo mensaje 5</title>\n" +
                "<!--[if (mso 16)]>\n" +
                "<style type=\"text/css\">\n" +
                "a {text-decoration: none;}\n" +
                "</style>\n" +
                "<![endif]-->\n" +
                "<!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "<!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "<o:OfficeDocumentSettings>\n" +
                "<o:AllowPNG></o:AllowPNG>\n" +
                "<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "</o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "<!--[if !mso]><!-- -->\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "<!--<![endif]-->\n" +
                "<style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "padding:0;\n" +
                "}\n" +
                ".es-button {\n" +
                "mso-style-priority:100!important;\n" +
                "text-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "color:inherit!important;\n" +
                "text-decoration:none!important;\n" +
                "font-size:inherit!important;\n" +
                "font-family:inherit!important;\n" +
                "font-weight:inherit!important;\n" +
                "line-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "display:none;\n" +
                "float:left;\n" +
                "overflow:hidden;\n" +
                "width:0;\n" +
                "max-height:0;\n" +
                "line-height:0;\n" +
                "mso-hide:all;\n" +
                "}\n" +
                "[data-ogsb] .es-button {\n" +
                "border-width:0!important;\n" +
                "padding:10px 30px 10px 30px!important;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:36px!important; text-align:left } h2 { font-size:26px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:12px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:20px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<div class=\"es-wrapper-color\" style=\"background-color:#EFEFEF\">\n" +
                "<!--[if gte mso 9]>\n" +
                "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "<v:fill type=\"tile\" color=\"#efefef\"></v:fill>\n" +
                "</v:background>\n" +
                "<![endif]-->\n" +
                "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#EFEFEF\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#ffffff\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/logonutri.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"265\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/children21.jpg\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"560\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h1 style=\"Margin:0;line-height:26px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:26px;font-style:normal;font-weight:bold;color:#1bac91\">Bienvenid@ "+name+"</h1></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" class=\"es-m-p0r es-m-p0l\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:40px;padding-right:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#a9a9a9;font-size:14px\"><strong>¡ Gracias por formar parte de esta experiencia !</strong></p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">Para continuar por favor confirma tu email</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\">\n" +
                "<form method=\"POST\" action=\"http://localhost:8080/confirmar\">\n" +
                "<input type=\"hidden\" name=\"tokenMail\" value="+token+">\n" +
                "<span class=\"es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#1BAC91;border-width:0px;display:inline-block;border-radius:6px;width:auto\"><button type=\"submit\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;border-style:solid;border-color:#1BAC91;border-width:10px 30px 10px 30px;display:inline-block;background:#1BAC91;border-radius:6px;font-size:20px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;color:#FFFFFF;width:auto;text-align:center;border-left-width:30px;border-right-width:30px\">Confirmar e-mail</button></span>\n" +
                "</form></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#F2F9F8\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:25px;background-color:#f2f9f8\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:30px;color:#999999;font-size:20px\">¿Cómo continuar recibiendo ayuda?</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td align=\"left\" bgcolor=\"#F2F9F8\" style=\"Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;padding-top:25px;background-color:#f2f9f8\">\n" +
                "<!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:197px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:167px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;background-color:#ffffff;border-radius:14px\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/faceregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Cargá el listado de niños</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\">Cargá el listado de los niños que forman parte del comedor. Estos datos solo serán visibles para el nutricionista.<br><br><br></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "<td class=\"es-hidden\" style=\"padding:0;Margin:0;width:30px\"></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:167px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:167px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;border-radius:15px;background-color:#ffffff\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/mailsendregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Esperá el contacto del nutricionista</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\">Un nutricionista analizará los datos cargados y elaborá un plan específico para cumplir con los requerimientos nutricionales de todos los niños.</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:30px\"></td><td style=\"width:166px\" valign=\"top\"><![endif]-->\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0;width:166px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:4px;border-style:solid;border-color:transparent;background-color:#ffffff;border-radius:13px\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_155b0273449779561fded24486681927/images/basketregular240.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"49\"></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:17px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;font-style:normal;font-weight:bold;color:#333333;text-align:center\">Recibí las donaciones</h3></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-left:5px;padding-right:5px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\">Todos los meses recibirás en tu comedor las donaciones que se recaudaron.<br><br><br><br><br></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#000000;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#000000\" align=\"center\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#ffffff\" bgcolor=\"#ffffff\" align=\"left\">\n" +
                "<!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\"><strong>Monteagudo 275, C1437 CABA<br>Email: info@nutrividas.com</strong></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr>\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-l\" align=\"right\" style=\"padding:0;Margin:0;padding-top:5px;font-size:0px\">\n" +
                "<table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Facebook\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/facebook-circle-colored.png\" alt=\"Fb\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Twitter\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/twitter-circle-colored.png\" alt=\"Tw\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><img title=\"Instagram\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/instagram-circle-colored.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr class=\"es-mobile-hidden\">\n" +
                "<td height=\"30\" align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;padding-bottom:10px;padding-left:20px;padding-right:20px;background-color:#ffffff\" bgcolor=\"#ffffff\" align=\"left\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td align=\"center\" style=\"padding:20px;Margin:0;font-size:0\">\n" +
                "<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:none;height:1px;width:100%;margin:0px\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"es-m-txt-l\" align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">Copyright© 2010-2021 |&nbsp;<strong>&nbsp;Nutrividas&nbsp;</strong>&nbsp;| Todos los derechos reservados</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

    public static String menuTemplate(Menu menu, Nutricionista nutricionista){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title>Nuevo mensaje 2</title>\n" +
                "    <style type=\"text/css\">\n" +
                "a {text-decoration: none;}\n" +
                "</style>\n" +
                "    <style>sup { font-size: 100% !important; }</style>\n" +
                "\n" +
                "    <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "            <o:AllowPNG></o:AllowPNG>\n" +
                "            <o:PixelsPerInch></o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "    </xml>\n" +
                "\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\">\n" +
                "\n" +
                "    <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "        <v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\n" +
                "    </v:background>\n" +
                "\n" +
                "    <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\">\n" +
                "        <tr style=\"border-collapse:collapse\">\n" +
                "            <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "                    <tr style=\"border-collapse:collapse\">\n" +
                "                        <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0;background-color:#ffffff\">\n" +
                "                            <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px\">\n" +
                "                                <tr style=\"border-collapse:collapse\">\n" +
                "                                    <td style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px;background-position:center center\" align=\"left\">\n" +
                "                                       <table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\">\n" +
                "                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:5px;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#659C35;font-size:16px\"><img src=\"https://tpavby.stripocdn.email/content/guids/CABINET_666a544a8b5e42bc37f055b5282b24b8/images/logonutri.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" class=\"adapt-img\" width=\"145\"></a></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\">\n" +
                "                                        <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#333333;font-size:16px\"><br></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td></tr></table></td>\n" +
                "                                </tr>\n" +
                "                            </table></td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "                    <tr style=\"border-collapse:collapse\">\n" +
                "                        <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding:0;Margin:0;background-color:#ffffff;background-image:url(https://tpavby.stripocdn.email/content/guids/CABINET_666a544a8b5e42bc37f055b5282b24b8/images/backgroundmenu_aa0.png);background-repeat:no-repeat;background-position:center top\" background=\"https://tpavby.stripocdn.email/content/guids/CABINET_666a544a8b5e42bc37f055b5282b24b8/images/backgroundmenu_aa0.png\">\n" +
                "                            <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "                                <tr style=\"border-collapse:collapse\">\n" +
                "                                    <td align=\"left\" style=\"padding:20px;Margin:0\">\n" +
                "                                        <table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:100px\" valign=\"top\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:100px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://tpavby.stripocdn.email/content/guids/CABINET_666a544a8b5e42bc37f055b5282b24b8/images/usernutricionista.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"50\"></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td><td style=\"width:20px\"></td><td style=\"width:440px\" valign=\"top\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td align=\"left\" style=\"padding:0;Margin:0;width:440px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\"><strong>Nutricionista:</strong>"+nutricionista.getNombre()+", "+nutricionista.getApellido()+"<br><strong>Matrícula:</strong> "+nutricionista.getMatricula()+"<br><strong>Mail: </strong>"+nutricionista.getCredencial().getMail()+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td></tr></table></td>\n" +
                "                                </tr>\n" +
                "                                <tr style=\"border-collapse:collapse\">\n" +
                "                                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'comic sans ms', 'marker felt-thin', arial, sans-serif;line-height:33px;color:#daa520;font-size:22px\"><em><strong style='content: \"\";\n'><em> Menú: "+menu.getTitulo()+"</em></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table></td>\n" +
                "                                </tr>\n" +
                "                                <tr style=\"border-collapse:collapse\">\n" +
                "                                    <td align=\"left\" style=\"padding:20px;Margin:0;border-radius:10px\">\n" +
                "                                        <table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:145px\" valign=\"top\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td class=\"es-m-p0r es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:125px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\"><br></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#808080;font-size:14px\"><em><strong>Lunes</strong></em></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#808080;font-size:14px\"><em><strong>Martes</strong></em></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#808080;font-size:14px\"><em><strong>Miercoles</strong></em></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#808080;font-size:14px\"><em><strong>Jueves</strong></em></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#808080;font-size:14px\"><em><strong>Viernes</strong></em></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#808080;font-size:14px\"><br><br></p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                                <td class=\"es-hidden\" style=\"padding:0;Margin:0;width:20px\"></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td><td style=\"width:145px\" valign=\"top\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:125px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'comic sans ms', 'marker felt-thin', arial, sans-serif;line-height:27px;color:#1bac91;font-size:18px\">Almuerzo</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px;background-color: #0cc09f33;\">"+menu.getMenuSemanal()[0][0]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px;background-color: #0cc09f33;\">"+menu.getMenuSemanal()[1][0]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px;background-color: #0cc09f33;\">"+menu.getMenuSemanal()[2][0]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px;background-color: #0cc09f33;\">"+menu.getMenuSemanal()[3][0]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px;background-color: #0cc09f33;\">"+menu.getMenuSemanal()[4][0]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                                <td class=\"es-hidden\" style=\"padding:0;Margin:0;width:20px\"></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td><td style=\"width:125px\" valign=\"top\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td class=\"es-m-p20b\" align=\"center\" style=\"padding:0;Margin:0;width:125px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'comic sans ms', 'marker felt-thin', arial, sans-serif;line-height:27px;color:#1bac91;font-size:18px\">Merienda</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[0][1]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[1][1]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[2][1]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[3][1]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[4][1]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td><td style=\"width:20px\"></td><td style=\"width:125px\" valign=\"top\">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                                            <tr style=\"border-collapse:collapse\">\n" +
                "                                                <td align=\"center\" style=\"padding:0;Margin:0;width:125px\">\n" +
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'comic sans ms', 'marker felt-thin', arial, sans-serif;line-height:27px;color:#1bac91;font-size:18px\">Cena</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[0][2]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[1][2]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[2][2]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[3][2]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr style=\"border-collapse:collapse\">\n" +
                "                                                            <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">"+menu.getMenuSemanal()[4][2]+"</p></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table></td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        </td></tr></table></td>\n" +
                "                                </tr>\n" +
                "                            </table></td>\n" +
                "                    </tr>\n" +
                "                </table></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

}
