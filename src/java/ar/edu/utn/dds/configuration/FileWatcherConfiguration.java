package ar.edu.utn.dds.configuration;

import ar.edu.utn.dds.filter.RedisAcceptOnceFileListFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;

import java.io.File;
import java.util.Arrays;

@EnableIntegration
@Configuration
@Import(RedisConfiguration.class)
public class FileWatcherConfiguration {

    @Value("${imprt.counts.file.dir}")
    private String directory;

    @Autowired
    private RedisTemplate redisTemplate;

    private Log log = LogFactory.getLog(this.getClass());

    @Bean
    public RedisAcceptOnceFileListFilter<File> redisAcceptOnceFileListFilter() {
        return new RedisAcceptOnceFileListFilter<>(redisTemplate);
    }

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(s -> s.file(new File(directory))
                                .autoCreateDirectory(true)
                                .ignoreHidden(true)
                                .filter(
                                        new CompositeFileListFilter<>(Arrays.asList(redisAcceptOnceFileListFilter(),
                                                new SimplePatternFileListFilter("*.csv")))),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .handle((p, h) -> {
                    log.info("Procesando archivo: " + ((File) p).getPath()); return p;} )
                .transform(Transformers.fileToString())
                .handle("importadorCuentas", "importar")
                .get();
    }
}
