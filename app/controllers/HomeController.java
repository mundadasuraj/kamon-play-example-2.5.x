package controllers;

import kamon.Kamon;
import kamon.annotation.EnableKamon;
import kamon.annotation.Trace;
import kamon.trace.TraceContext;
import play.mvc.*;
import services.Service1;
import services.Service2;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@EnableKamon
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	@Trace("TrackKamon")
	public Result index() {
		
		final TraceContext testTrace = Kamon.tracer().newContext("test-trace");


		Service1 service1 = new Service1();
        service1.method1();
        Service2 service2 = new Service2();
        service2.method2();
		
        testTrace.finish();

		return ok(index.render("Your new application is ready."));
    }
}
