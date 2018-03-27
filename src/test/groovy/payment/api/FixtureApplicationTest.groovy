package payment.api

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import spock.lang.Specification

class FixtureApplicationTest extends Specification{

    void setup(){
        FixtureFactoryLoader.loadTemplates("payment.api")
    }

}
