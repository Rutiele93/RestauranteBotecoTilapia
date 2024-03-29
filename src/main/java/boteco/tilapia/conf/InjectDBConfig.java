package boteco.tilapia.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import boteco.tilapia.services.InjectDB;

@Configuration
//@Profile("dev")
public class InjectDBConfig {

    @Autowired
    private InjectDB injectDB;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean injectDbinfos(){
        if(value.equals("create")){
            this.injectDB.injectDB();
        }
        return false;
    }
}