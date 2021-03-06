package services;

import kamon.Kamon;
import kamon.annotation.EnableKamon;
import kamon.annotation.Trace;
import kamon.metric.instrument.Counter;
import kamon.trace.TraceContext;
import kamon.trace.Tracer;
import kamon.trace.Segment;

@EnableKamon
public class Service2 {
	@Trace("TrackKamon")
	public void method2() {
		System.out.println("In Service2 : method2()");
		
		final Counter serviceCounter2 = Kamon.metrics().counter("service-counter-2");
		serviceCounter2.increment();

		final TraceContext context = Tracer.currentContext();

		Tracer.withContext(context, () -> {
			System.out.println("In Context test-trace - Service2 : method2()");
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
