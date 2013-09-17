package sonique.bango.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sonique.bango.controller.ServiceProblemApiController;
import sonique.bango.store.ServiceProblemStore;
import sonique.bango.util.SpringSecurityAuthorisedActorProvider;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableWebMvc
public class ServiceProblemApiConfig extends ApiConfig {

    @Resource
    private SpringSecurityAuthorisedActorProvider authorisedActorProvider;

    @Resource
    private ServiceProblemStore serviceProblemStore;

    @Bean
    public ServiceProblemApiController searchApiController() {
        return new ServiceProblemApiController(serviceProblemStore, authorisedActorProvider);
    }
}
