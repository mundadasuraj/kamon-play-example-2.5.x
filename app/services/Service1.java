package services;

import kamon.Kamon;
import kamon.annotation.EnableKamon;
import kamon.annotation.Trace;
import kamon.trace.TraceContext;
import kamon.trace.Tracer;
import kamon.metric.instrument.Counter;

@EnableKamon
public class Service1 {
	@Trace("TrackKamon")
	public void method1() {
		System.out.println("In Service1 : method1()");
		
		final Counter serviceCounter1 = Kamon.metrics().counter("service-counter-1");
		serviceCounter1.increment();
		
		final TraceContext context = Tracer.currentContext();
		
		Tracer.withContext(context, () -> {
			System.out.println("In Context test-trace - Serive1 : method1()");
			Segment segment = Tracer.currentContext().startSegment("some-cool-section", "business-logic", "kamon");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			segment.finish();
			return "done";
		});
	}
}
