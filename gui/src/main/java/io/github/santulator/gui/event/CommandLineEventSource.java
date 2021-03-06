/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.santulator.gui.event;

import java.nio.file.Paths;
import java.util.List;
import javax.inject.Singleton;

import static java.util.stream.Collectors.toList;

@Singleton
public class CommandLineEventSource implements SingleExternalEventSource {
    private final List<ExternalOpenFileEvent> events;

    public CommandLineEventSource(final List<String> args) {
        events = args.stream()
                .map(Paths::get)
                .map(ExternalOpenFileEvent::new)
                .limit(1)
                .collect(toList());
    }

    @Override
    public void setListener(final ExternalEventListener listener) {
        events.forEach(listener::fireOpenFileEvent);
    }
}
