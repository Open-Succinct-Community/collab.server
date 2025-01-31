package in.succinct.collab.server.extensions;


import com.venky.extension.Extension;
import com.venky.extension.Registry;
import com.venky.swf.db.model.Model;
import com.venky.swf.plugins.collab.db.model.user.User;
import com.venky.swf.plugins.collab.util.CompanyUtil;
import com.venky.swf.views.controls.page.Body;
import com.venky.swf.views.controls.page.Html;
import com.venky.swf.views.controls.page.Link;
import com.venky.swf.views.controls.page.layout.Paragraph;

import java.util.Map;

public class PasswordReset implements Extension {
    static {
        Registry.instance().registerExtension("before.send.password.reset",new PasswordReset());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Object... context) {
        Map<String,Object> map = (Map<String,Object>)context[0];
        User user = ((Model)map.get("USER")).getRawRecord().getAsProxy(User.class);
        Link link = (Link) map.get("RESET_LINK");
        //link.setUrl("real_reset_url");
        //https://greenearthx.atlassian.net/browse/GEX-214

        Html html = new Html();
        Body body = new Body();
        html.addControl(body);
        Paragraph p = new Paragraph();
        body.addControl(p);

        String text = """
                <br>Hello %s,
                <br>
                <br>          We received your request to reset your password for your GEX Terminal account.
                <br>
                <br>          Please click on this link to reset your password: %s
                <br>
                <br>          If you didn't request this, you can safely ignore this email. Someone else might have typed your email address by mistake.
                <br>
                <br>          Thanks,
                <br>          The GreenEarthX Registration Team
                <br>          https://%s""".formatted(user.getEmail(), link.toString(), CompanyUtil.getCompany().getDomainName());
        p.setText(text,false);
        map.put("TEXT",html);
    }
}
