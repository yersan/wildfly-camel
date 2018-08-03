/*
 * #%L
 * Wildfly Camel :: Example :: Camel CXF JAX-WS CDI Secure
 * %%
 * Copyright (C) 2013 - 2017 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wildfly.camel.test.cxf.ws.secure.subB;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.cxf.CxfEndpoint;

@ApplicationScoped
@ContextName("cxfws-secure-cdi-camel-context-b")
public class CxfWsRouteBuilder extends RouteBuilder {

    @Inject
    @Named("greetingsProcessorB")
    Processor greetingsProcessor;

    @Inject
    @Named("cxfConsumerEndpointB")
    CxfEndpoint cxfConsumerEndpoint;

    @Inject
    @Named("cxfProducerEndpointB")
    CxfEndpoint cxfProducerEndpoint;

    @Override
    public void configure() throws Exception {
        from("direct:start")
        .to(this.cxfProducerEndpoint);

        from(this.cxfConsumerEndpoint)
        .process(this.greetingsProcessor);
    }
}
