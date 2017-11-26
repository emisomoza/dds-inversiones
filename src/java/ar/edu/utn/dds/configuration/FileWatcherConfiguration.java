package ar.edu.utn.dds.configuration;

import groovy.util.logging.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;

import java.io.File;

@EnableIntegration
@Configuration
public class FileWatcherConfiguration {

    @Value("${imprt.counts.file.dir}")
    private String directory;
    private Log log = LogFactory.getLog(this.getClass());

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(s -> s.file(new File(directory))
                                .autoCreateDirectory(true)
                                .ignoreHidden(true)
                                .preventDuplicates(true)
                                .patternFilter("*.csv"),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .handle((p, h) -> {
                    log.info("Procesando archivo: " + ((File) p).getPath()); return p;} )
                .transform(Transformers.fileToString())
                .handle("importadorCuentas", "importar")
                .get();
    }
}
