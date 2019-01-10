package pl.storeware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.storeware.appControl.ControlPanel;
import pl.storeware.utils.DataWriter;

@Configuration
@ComponentScan("pl.storeware")
public class AppConfig {



    @Bean
    public ControlPanel controlPanel() {
        return new ControlPanel();
    }

}
