[![Build Status](https://jenkins.anrisoftware.com/view/com.anrisoftware.globalpom/job/com.anrisoftware.globalpom-globalpom-log/job/main/badge/icon)](https://jenkins.anrisoftware.com/view/com.anrisoftware.globalpom/job/com.anrisoftware.globalpom-globalpom-log)
[![Gate](https://sonarcloud.io/api/project_badges/measure?project=devent_globalpom-log&metric=alert_status)](https://sonarcloud.io/project/overview?id=devent_globalpom-log)
[![Apache License, Version 2.0](https://project.anrisoftware.com/attachments/download/217/apache2.0-small.gif)](http://www.apache.org/licenses/LICENSE-2.0)
© 2011-2022 Erwin Müller

Description
===========

Logger factory to inject a logger for a class.

Links
=====

-   [Generated
    Site](https://javadoc.anrisoftware.com/com.anrisoftware.globalpom/globalpom-groovy-parent/4.6.3-SNAPSHOT/index.html)
-   [Download
    (Central)](https://search.maven.org/artifact/com.anrisoftware.globalpom/globalpom-groovy-base/4.6.3-SNAPSHOT/pom)
-   [Source
    code](https://gitea.anrisoftware.com/com.anrisoftware.globalpom/globalpom-groovy)
-   [Source code (Github)](https://github.com/devent/globalpom-groovy)
-   [Project
    Home](https://project.anrisoftware.com/projects/globalpom-groovy)
-   [Project
    Roadmap](https://project.anrisoftware.com/projects/globalpom-groovy/roadmap)
-   [Project
    Issues](https://project.anrisoftware.com/projects/globalpom-groovy/issues)
-   [Jenkins](https://jenkins.anrisoftware.com/view/com.anrisoftware.globalpom/job/com.anrisoftware.globalpom-globalpom-log)

Packages Overview
=================

![Packages Overview](https://project.anrisoftware.com/attachments/download/447/packages.svg "Packages Overview")

Examples
========

The logging framework makes it easier to separate logging messages from
the production code. Instead of having logging messages all around the
code, the messages are all put together in one logging class and the
class is injected into the client class.

``` {.source}
/**
 * Logging messages for {@link Foo}.
 */
@Singleton
class FooLogger extends AbstractLogger {

    /**
     * Create logger for {@link Foo}.
     */
    FooLogger() {
        super(Foo.class);
    }

    void loggingMessage(Foo foo) {
        if (isDebugEnabled()) {
            debug("Debug logging message for {}.", foo);
        } else {
            info("Info logging message for {}.", foo);
        }
    }
}

/**
 * Production class.
 */
class Foo {

    @Inject
    private FooLogger log;

    public void method() {
        log.loggingMessage(this);
    }
}
```

For static logging messages a simple enumeration can be defined and the
constants used as logging messages strings. The enumeration must be
package public so that Java's static imports can be used to simplify the
code.

``` {.source}
/**
 * Logging messages for {@link Foo}.
 */
@Singleton
class FooLogger extends AbstractLogger {

    enum m {

        logging_message_debug("Debug logging message for {}."),

        logging_message_info("Info logging message for {}.");

        private String name;

        private m(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Create logger for {@link Foo}.
     */
    FooLogger() {
        super(Foo.class);
    }

    void loggingMessage(Foo foo) {
        if (isDebugEnabled()) {
            debug(logging_message_debug, foo);
        } else {
            info(logging_message_info, foo);
        }
    }
}
```

Further, to dynamically load logging messages with internationalization
support, the resources-texts project can be used.

``` {.source}
/**
 * Logging messages for {@link Foo}.
 */
@Singleton
class FooLogger extends AbstractLogger {

    enum m {

        logging_message_debug,

        logging_message_info;

        /**
         * Retrieves the text resources for the logging messages.
         *
         * @param texts
         *            the texts {@link Texts} resources.
         */
        public static void retrieveResources(Texts texts) {
            for (m value : values()) {
                value.setText(texts);
            }
        }

        private String name;

        private String text;

        private m(String name) {
            this.name = name;
        }

        /**
         * Retrieve the text resource for the logging message.
         *
         * @param texts
         *            the texts {@link Texts} resources.
         */
        public void setText(Texts texts) {
            this.text = texts.getResource(name).getText();
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * Create logger for {@link Foo}.
     */
    FooLogger() {
        super(Foo.class);
    }

    /**
     * Retrieves the text resources for the logging messages.
     *
     * @param texts
     *            the texts {@link Texts} resources.
     */
    public void retrieveResources(Texts texts) {
        m.retrieveResources(texts);
    }

    void loggingMessage(Foo foo) {
        if (isDebugEnabled()) {
            debug(logging_message_debug, foo);
        } else {
            info(logging_message_info, foo);
        }
    }
}
```

License
=======

Copyright ©2011 - 2022 [Advanced Natural Research
Institute](https://anrisoftware.com/). All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");\
you may not use this file except in compliance with the License.\
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software\
distributed under the License is distributed on an "AS IS" BASIS,\
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied.\
See the License for the specific language governing permissions and\
limitations under the License.

##### Markdown

    pandoc -t markdown -f textile -o README.md README.textile

    [![Build Status](https://jenkins.anrisoftware.com/view/com.anrisoftware.globalpom/job/com.anrisoftware.globalpom-globalpom-log/job/main/badge/icon)](https://jenkins.anrisoftware.com/view/com.anrisoftware.globalpom/job/com.anrisoftware.globalpom-globalpom-log/job/main)
    [![Gate](https://sonarcloud.io/api/project_badges/measure?project=devent_globalpom-log&metric=alert_status)](https://sonarcloud.io/project/overview?id=devent_globalpom-log)
    [![Apache License, Version 2.0](https://project.anrisoftware.com/attachments/download/217/apache2.0-small.gif)](http://www.apache.org/licenses/LICENSE-2.0)
    © 2011 - 2022 Erwin Müller
