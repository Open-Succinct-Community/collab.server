package in.succinct.collab.server.extensions;

import com.venky.extension.Extension;
import com.venky.extension.Registry;
import com.venky.swf.plugins.collab.util.CompanyUtil;

import java.util.Map;

public class SendOtp implements Extension {
    static {
        Registry.instance().registerExtension("before.send.email.otp",new SendOtp());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void invoke(Object... context) {
        Map<String,String> map = (Map<String,String>) context[0];
        String emailId = map.get("EMAIL");
        String otp = map.get("OTP");

        String subject = "Your One-Time Code for GEX Terminal." ;
        String text = """
                Hello %s,

                We received your request for a one-time code to use with your GEX Terminal registration.\s

                Your one-time code is: %s

                If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.

                Thanks,
                The GreenEarthX Registration Team
                https://%s""".formatted(emailId,otp, CompanyUtil.getCompany().getDomainName());

        map.put("SUBJECT",subject);
        map.put("TEXT",text);

    }
}
