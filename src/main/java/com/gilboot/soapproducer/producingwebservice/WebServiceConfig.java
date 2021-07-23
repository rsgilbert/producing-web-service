package com.gilboot.soapproducer.producingwebservice;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static com.gilboot.soapproducer.producingwebservice.Constants.NAMESPACE_URL;


/**
 * Web service configuration.
 * Configures Spring to work with web services.
 * We need to specify bean names for {@link MessageDispatcherServlet} and {@link DefaultWsdl11Definition}.
 * Bean names determine the URL under which the web service and generated WSDL file are available.
 * In this case the WSDL will be available under: http://<host>:<port>/ws/countries.wsdl
 * @see <a href="https://spring.io/guides/gs/producing-web-service/">producing web services</a>
 * @since 1.0
 * @author  Gilbert Ssenyonjo
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    /**
     * Spring WS uses a different servlet type for handling SOAP messages: {@link MessageDispatcherServlet}.
     * It is important to inject and set {@link ApplicationContext } to {@link MessageDispatcherServlet}. Without
     * that Spring WS will not automatically detect Spring beans.
     * @param applicationContext - Application context
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext
    ) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Exposes a standard WSDL 1.1 by using XsdSchema.
     * @param countriesSchema
     * @return
     */
    @Bean(name="countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URL);
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd.old"));
    }
}
