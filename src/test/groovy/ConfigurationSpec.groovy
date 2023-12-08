import groovy.json.JsonBuilder
import io.rapidw.updater.Configuration
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
        def oldBuilder = new JsonBuilder()
        oldBuilder {
            base {
                baseUri 'baseUri'
                basePath 'basePath'
            }
            properties {
                name 'value'
            }
        }

        def oldJson = oldBuilder.toPrettyString()

        def newBuilder = new JsonBuilder()
        newBuilder {
            base {
                baseUri 'baseUri'
                basePath 'basePath'
            }
            properties {
                name 'value'
            }
        }

        def newJson = newBuilder.toPrettyString()
        def oldConfig = Configuration.loadFromJson(oldJson)
        def newConfig = Configuration.loadFromJson(newJson)

        def updater = Updater.builder().build()
        if (updater.isUpdateRequired()) {
            updater.backup()
            if (!updater.doUpdate()) {
                updater.rollBack()
            }
        }

        updater.launchApplication()
    }
}
