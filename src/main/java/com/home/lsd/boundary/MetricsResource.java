package com.home.lsd.boundary;

import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

@Path("metrics")
@Produces(MediaType.TEXT_PLAIN)
public class MetricsResource {

	@GET
	public StreamingOutput metrics() {
		return output -> {
			try (Writer writer = new OutputStreamWriter(output)) {
				TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
			}
		};

	}

}
