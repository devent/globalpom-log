!%globalpom.custom.jenkins.build.badge.main%(Build Status)!:%globalpom.custom.jenkins.url%/job/main !%globalpom.custom.sonarqube.qualitygate.badge%(Gate)!:%globalpom.custom.sonarqube.qualitygate.link% !https://project.anrisoftware.com/attachments/download/217/apache2.0-small.gif(Apache License, Version 2.0)!:http://www.apache.org/licenses/LICENSE-2.0 © %project.inceptionYear%&#45;%globalpom.custom.current.year% Erwin Müller

h1. Description

Logger factory to inject a logger for a class.

h1. Links

* "Generated Site":https://javadoc.anrisoftware.com/com.anrisoftware.globalpom/globalpom-log/%project.version%/index.html
* "Download Central":https://search.maven.org/artifact/com.anrisoftware.globalpom/globalpom-log/%project.version%/pom
* "Source code":https://gitea.anrisoftware.com/com.anrisoftware.globalpom/globalpom-log
* "Source code Github":https://github.com/devent/globalpom-log
* "Project Home":https://project.anrisoftware.com/projects/globalpom-log
* "Project Roadmap":https://project.anrisoftware.com/projects/globalpom-log/roadmap
* "Project Issues":https://project.anrisoftware.com/projects/globalpom-log/issues
* "Jenkins":%globalpom.custom.jenkins.url%

h1. Packages Overview

!https://project.anrisoftware.com/attachments/download/447/packages.svg(Packages Overview)!

h1. Examples

The logging framework makes it easier to separate logging messages from the production code. Instead of having logging messages all around the code, the messages are all put together in one logging class and the class is injected into the client class.

<pre class="source">
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
</pre>

For static logging messages a simple enumeration can be defined and the constants used as logging messages strings. The enumeration must be package public so that Java's static imports can be used to simplify the code.

<pre class="source">
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
</pre>

Further, to dynamically load logging messages with internationalization support, the resources-texts project can be used.

<pre class="source">
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
</pre>

h1. License

Copyright ©%project.inceptionYear% - %globalpom.custom.current.year% "Advanced Natural Research Institute":https://anrisoftware.com/. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

h5. Markdown

<pre>
pandoc -t markdown -f textile -o README.md README.textile
</pre>

<pre>
[![Build Status](%globalpom.custom.jenkins.build.badge.main%)](%globalpom.custom.jenkins.url%/job/main)
[![Gate](%globalpom.custom.sonarqube.qualitygate.badge%)](%globalpom.custom.sonarqube.qualitygate.link%)
[![Apache License, Version 2.0](https://project.anrisoftware.com/attachments/download/217/apache2.0-small.gif)](http://www.apache.org/licenses/LICENSE-2.0)
© %project.inceptionYear% - %globalpom.custom.current.year% Erwin Müller
</pre>
