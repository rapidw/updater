import groovy.json.JsonBuilder
import io.rapidw.updater.Updater
import spock.lang.Specification

class ConfigurationSpec extends Specification {

    def "load config"() {
        given:
        def builder = new JsonBuilder()
        builder {
            base {
                baseUri 'baseUri'
                basePath 'basePath'
            }
            properties {
                name 'value'
            }
        }

        def json = builder.toPrettyString()
        def config = Updater.loadFromJson(json)
        expect:
        config.configInfo.base.baseUri == 'baseUri'
    }

    def "test"() {
        given:
        given:
        def builder = new JsonBuilder()
        builder {
            base {
                baseUri 'baseUri'
                basePath 'basePath'
            }
            properties {
                name 'value'
            }
        }

        def json = builder.toPrettyString()
        def config = Updater.loadFromJson(json)
        if (config.requireUpdate()) {
            config.doUpdate()
            config.launch()
        }
    }
}