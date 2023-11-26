package io.dongvelop.actuatordemo.custom;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Endpoint(id = "myLibraryInfo")
public class MyLibraryInfoEndpoint {

    /**
     * "/actuator/myLibraryInfo" 로 GET 요청이 들어오면 이 메서드가 실행
     */
    @ReadOperation
    public List<LibraryInfo> getLibraryInofs(@Nullable final String name, final Boolean includeVersion) {

        final LibraryInfo libraryInfo = new LibraryInfo("logback", "1.0.0");
        final LibraryInfo libraryInfo2 = new LibraryInfo("jackson", "2.0.0");

        List<LibraryInfo> libraryInfos = Arrays.asList(libraryInfo, libraryInfo2);

        if (name != null) {
            libraryInfos = libraryInfos.stream()
                    .filter(libInfo -> libInfo.name().equals(name))
                    .toList();
        }

        if (Boolean.FALSE.equals(includeVersion)) {
            libraryInfos = libraryInfos.stream()
                    .map(lib -> new LibraryInfo(lib.name(), null))
                    .toList();
        }

        return libraryInfos;
    }
}
