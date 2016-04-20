# globalpom-log

## Description

The logging framework makes it easier to separate logging messages from the
production code. Instead of having logging messages all around the code, the
messages are all put together in one logging class and the class is injected
into the client class.

```
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
constants used as logging messages strings. The enumeration must be package
public so that Java's static imports can be used to simplify the code.

```
/**
 * Logging messages for {@link Foo}.
 */
@Singleton
class FooLogger extends AbstractLogger {

    enum _ {

        logging_message_debug("Debug logging message for {}."),

        logging_message_info("Info logging message for {}.");

        private String name;

        private _(String name) {
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

```
/**
 * Logging messages for {@link Foo}.
 */
@Singleton
class FooLogger extends AbstractLogger {

    enum _ {

        logging_message_debug,

        logging_message_info;

        /**
         * Retrieves the text resources for the logging messages.
         *
         * @param texts
         *            the texts {@link Texts} resources.
         */
        public static void retrieveResources(Texts texts) {
            for (_ value : values()) {
                value.setText(texts);
            }
        }

        private String name;

        private String text;

        private _(String name) {
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
        _.retrieveResources(texts);
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

## SCM

* [Main Repository](https://anrisoftware.com/projects/projects/globalpom-log/repository)
* `git@anrisoftware.com:globalpom-log.git`
* [Github Mirror Repository](https://github.com/devent/globalpom-log)
* `git@anrisoftware.com:globalpom-log.git`

## Maven

```
<dependency>
    <groupId>com.anrisoftware.globalpom</groupId>
    <artifactId>globalpom-log</artifactId>
    <version>2.1</version>
</dependency>
```

# License

Copyright 2011-2016 Erwin Müller <erwin.mueller@deventm.org>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
