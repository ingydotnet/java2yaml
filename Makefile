SHELL := bash

export CLASSPATH=$(PWD)/target/classes

TEST_FILE_1 := Cons.java
TEST_URL_1 := https://raw.githubusercontent.com/clojure/clojure/clojure-1.11.1/src/jvm/clojure/lang/$(TEST_FILE_1)

APP_SRC := src/main/java/org/clojars/ingy/App.java
APP_CLASS := target/classes/org/clojars/ingy/App.class
APP_NAME := org.clojars.ingy.App


default:

build: $(APP_CLASS)

$(APP_CLASS): $(APP_SRC)
	mvn compile

run: build $(TEST_FILE_1)
	java $(APP_NAME) $(TEST_FILE_1)

force:
	touch $(APP_SRC)

clean:
	$(RM) -r target
	$(RM) $(TEST_FILE_1)

$(TEST_FILE_1):
	curl -s $(TEST_URL_1) > $@
