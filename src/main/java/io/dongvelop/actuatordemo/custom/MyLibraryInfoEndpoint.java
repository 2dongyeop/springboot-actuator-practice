package io.dongvelop.actuatordemo.custom;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Arrays;
import java.util.List;

@Endpoint(id = "myLibraryInfo")
public class MyLibraryInfoEndpoint {

    /**
     * "/actuator/myLibraryInfo" 로 GET 요청이 들어오면 이 메서드가 실행
     */
    @ReadOperation
    public List<LibraryInfo> getLibraryInofs() {

        final LibraryInfo libraryInfo = new LibraryInfo("logback", "1.0.0");
        final LibraryInfo libraryInfo2 = new LibraryInfo("jackson", "2.0.0");

        final List<LibraryInfo> libraryInfos = Arrays.asList(libraryInfo, libraryInfo2);
        return libraryInfos;
    }
}
