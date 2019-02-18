package mng.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author oac
 */
public class Util {
    private static final String AES_ENCODE_RULES = "ehoac@sina.com";
    private static Properties properties;
    private static boolean reload = true;

    private static void init(){
        try{
            properties = PropertiesLoaderUtils.loadAllProperties("config/config.properties");
        }catch (IOException e){
            e.printStackTrace();
        }
        reload = false;
    }

    public static String getProperties(String key){
        if (reload) {
            init();
        }
        return properties.getProperty(key);
    }

    public static String aesEncode(String content){
        return SymmetricEncoder.aesEncode(AES_ENCODE_RULES, content);
    }

    public static String aesDecode(String content){
        return SymmetricEncoder.aesDecode(AES_ENCODE_RULES, content);
    }

    public static boolean sendEmail(String from, String subject, String body, String authUser, String authCode){
        // 收件人电子邮箱
        String to = getProperties("email.to");

        // 发件人电子邮箱
//        String from = "caopeihe@topnet.net.cn";

        // 指定发送邮件的主机
        String host = getProperties("email.host");
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
                //发件人邮件用户名、授权码
//                return new PasswordAuthentication("caopeihe@topnet.net.cn", "ix5St2vPqhFnJocw");
                return new PasswordAuthentication(authUser, authCode);
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: 头部头字段
            message.setSubject(subject);
            // 设置消息体
            message.setContent(body, "text/html;charset=UTF-8");
            // 发送消息
            Transport.send(message);
            return true;
        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }
}
