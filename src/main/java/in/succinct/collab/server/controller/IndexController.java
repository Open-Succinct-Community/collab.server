package in.succinct.collab.server.controller;

import com.venky.swf.controller.Controller;
import com.venky.swf.controller.annotations.RequireLogin;
import com.venky.swf.path.Path;
import com.venky.swf.views.RedirectorView;
import com.venky.swf.views.View;

public class IndexController extends Controller {
    public IndexController(Path path) {
        super(path);
    }
    
    @Override
    @RequireLogin(false)
    public View index() {
        return new RedirectorView(getPath(),"app/index");
    }
}
